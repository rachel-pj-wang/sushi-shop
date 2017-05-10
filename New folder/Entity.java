import javafx.application.Application; 
import javafx.application.Platform; 
import javafx.stage.*; 
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.scene.text.Font; 
import javafx.scene.Node; 
import javafx.event.*;
import java.util.ArrayList;
import javafx.scene.paint.*;
import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.geometry.*;
import javafx.scene.control.Label; 
import javafx.scene.layout.AnchorPane; 
import java.math.*; 

public class Entity extends Application {
	public AnimationTimer animationTimer; 
	
	public  ArrayList<String> input;
	public BoundingBox movableBounds; 
	public double windowWidth, windowHeight; 
	public double timeRemaining; 
	
	public static void main(String[] args) {			
		launch(args); 
	}
	
	public void start(Stage theStage) {
		startGame(theStage); 
	}	
	
	private void startGame(Stage theStage)  
	{
		//initializing
		root = new Group(); 
		timeRemaining = 60;
		windowWidth = 800; 
		windowHeight = Screen.getPrimary().getVisualBounds().getMaxY();
		movableBounds = new BoundingBox(0,0, windowWidth, windowHeight);

				
		theStage.setTitle("cullor. 0.1"); 
		Scene playScene = new Scene(root, windowWidth, windowHeight, Color.BLACK); 
		theStage.setScene(playScene); 
	
		
		input = detectKeyStrokes(playScene);
	

		//rendering graphics 
		
		//timer	
		Label timer = new Label(""); 
		timer.setFont(new Font(30)); 
		timer.setTextFill(Color.GHOSTWHITE);
		//System.out.println(javafx.scene.text.Font.getFamilies()); //incase we want to switch fonts 
		
		//anchorpane for timer and eventually, the cullor meter/bar 
		AnchorPane aPane = new AnchorPane();	
		aPane.setPrefSize(windowWidth, windowHeight);
		aPane.getChildren().add(timer);
		aPane.setTopAnchor(timer, 20.0);
		//aPane.setLeftAnchor(timer, windowWidth/2 - timer.getWidth());
		root.getChildren().add(aPane); 
		
		//spawning player 
		Player player = new Player("RED", 700); 
		root.getChildren().add(player.sprite);			
		player.sprite.setLayoutX(playScene.getWidth()/2 - player.sprite.getFitWidth()/2);
		player.sprite.setLayoutY(playScene.getHeight()*3/4); 

		//runs every frame 
		animationTimer = new AnimationTimer() {
			private double previousTime = 0; 
			
			//PLACEHOLDER spawn mechanics - probably do a funciton or something that'll take wave objects
			double timeUntilNextSpawn = 5;
			double spawnRate = 1;
			double nextWaveNum = 1; 
			
			public void handle(long currentNanoTime) {
				double deltaTime = (currentNanoTime - previousTime) / 1e9; 	// time since last frame as a fraction of a second
				previousTime = currentNanoTime; 
				
				//reading input
				movePlayer(player, deltaTime); 
				readPlayerActions(player, previousTime / 1e9); //converted from nano - to 1 second 
				
				moveProjectiles(deltaTime); 
				collisionsCheck(player,  renderedProjectiles);
				timer.setText(" " + updateTimer(deltaTime) + "\n LIVES: " + player.lives + "\n AMMO: " + player.charge); //PLACEHOLDER: also updates lives & ammo display
				
				if(timeRemaining < 0 || player.lives < 0) {
					restartGame(theStage); 
				}
				
				//PLACEHOLDER wave spawning 
				timeUntilNextSpawn -= deltaTime;
				if(timeUntilNextSpawn < 0) {
					spawnRandomWave((int)nextWaveNum); 
					nextWaveNum += 0.25; 
					timeUntilNextSpawn = spawnRate; 
				}
            }
		};
		animationTimer.start(); 
		theStage.show();
	}
	
	private void spawnRandomWave(int count)  {		//PLACEHOLDER - waves implementation will be converted to objects with a central spawn point
		String[] colors = new String[]{"BLUE", "RED"}; 
		double horizontalVariance = 200;
		for(int i = 0; i < count; i++) { 
			spawn(new Orb(300, "DOWN", colors[(int)(Math.random() * 2)]), Math.random() * windowWidth, -100 - horizontalVariance * Math.random()); 		//maybe enum directions and colors later
		}		
	}
	
	private void restartGame(Stage stage) { 
		animationTimer.stop(); 
		stage.close();
		startGame(stage);
	}
	
	private	double updateTimer(double deltaTime) { 			
		if(deltaTime < 1)  //frame-drop insurance
			timeRemaining = round(timeRemaining - deltaTime, 2); 
		return timeRemaining;
	}
	private void movePlayer(Player player, double deltaTime){
		double playerX = player.sprite.getLayoutX(); 
		double playerY = player.sprite.getLayoutY(); 
		if(input.contains("LEFT") && playerX > 0) {
			player.sprite.setLayoutX(playerX - player.speed * deltaTime);
		}
		if(input.contains("RIGHT") && playerX < windowWidth -  player.sprite.getFitWidth()){
			player.sprite.setLayoutX(playerX + player.speed * deltaTime);
		}
		if(input.contains("UP") && playerY > 0) {
			player.sprite.setLayoutY(playerY - player.speed * deltaTime);
		}
		if(input.contains("DOWN") && playerY < windowHeight - player.sprite.getFitHeight()) {
			player.sprite.setLayoutY(playerY + player.speed * deltaTime);
		}
	}
	
	private void readPlayerActions(Player player, double currentTime) {		
		if(input.contains("SPACE")) {
			player.shoot(currentTime); 
		}
	}

	private void moveProjectiles(double deltaTime) {
		for(Projectile projectile : renderedProjectiles) {
			double yPos = projectile.sprite.getLayoutY();
			 if(!(root.getChildren().contains(projectile.sprite))) { // updates array from newly removed nodes
				 renderedProjectiles.remove(projectile); 
				 break;
			 } 
			else if(yPos < windowHeight + 100 || yPos > 0 - 100) { // animates orb
				if (deltaTime < 1) { // ensures no first-frame shenanigansf
					switch(projectile.direction) {
						case "DOWN": 
							projectile.sprite.setLayoutY(projectile.sprite.getLayoutY() + projectile.speed * deltaTime);
							break;
						case "UP":
							projectile.sprite.setLayoutY(projectile.sprite.getLayoutY() - projectile.speed * deltaTime);
							break;
					}
				}
			} else  { //removes off-screen hazard from scene
				root.getChildren().remove(projectile.sprite); 
			}
		} 
		
	}
	private void collisionsCheck (Collider player, ArrayList<Projectile> everythingElse) {
		for(Projectile projectile : everythingElse) {
			if (player.sprite.getBoundsInParent().intersects(projectile.sprite.getBoundsInParent())){
				player.onCollision(projectile);
			}
			for (Projectile otherProjectile : everythingElse) {
				if(otherProjectile != projectile && otherProjectile.sprite.getBoundsInParent().intersects(projectile.sprite.getBoundsInParent())){
					projectile.onCollision(otherProjectile);
				}					
			}
		}
	}
	private void spawn(Projectile o, double x, double y) {
		CULLOR.root.getChildren().add(o.sprite);	
		CULLOR.renderedProjectiles.add(o); 
		
		o.sprite.setLayoutX(x);
		o.sprite.setLayoutY(y); 
	};
	private ArrayList<String> detectKeyStrokes(Scene theScene) {
		ArrayList<String> input = new ArrayList<String>();
		theScene.setOnKeyPressed(
			new EventHandler<KeyEvent>()
			{
				public void handle(KeyEvent e)
				{
					String code = e.getCode().toString();

					// only add once... prevent duplicates
					if ( !input.contains(code) )
						input.add( code );
				}
			});

		theScene.setOnKeyReleased(
			new EventHandler<KeyEvent>()
			{
				public void handle(KeyEvent e)
				{
					String code = e.getCode().toString();
					input.remove( code );
				}
			});
		return input; 
	}
	private double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	
}