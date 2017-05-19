import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.lang.Math;

public class SushiShop extends Application {
    private ArrayList<Entity> entities;
    private AnimationTimer gameTimer;
    private AnimationTimer animationTimer;
    private Display display;
    private Stage stage;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 720;

    private double timeUntilNextSpawn;
    private double nextWaveNum;

    //collisions
    public PlayerPlatter player;
    public Entity topHitBox;

    private Platter order;

    private boolean isCorrectOrder;
    private int score;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        this.stage = stage;

        initializeVariables();
        stage.show();
    }

    public void startGameLoops() {
        timeUntilNextSpawn = 1;
        nextWaveNum = 1;

        gameTimer = new AnimationTimer() {
            double previousTime = 0;
            double timeUntilNextSpawn = 2;
            double nextWaveNum = 1;

            public void handle(long currentNanoTime) {
                double deltaTime = (currentNanoTime - previousTime) / 1e9;  // time since last frame as a fraction of a second
                for (Entity e : entities) {
                    if (deltaTime < 1) {
                        e.update(deltaTime);
                    }
                    topCollisionCheck(e);
                }
                handleSpawning(deltaTime, 2);
                previousTime = currentNanoTime;

                //placeholder loss handling
                if(!isCorrectOrder) {
                  initializeVariables();
                  score = 0;
                }
            }
        };

        animationTimer = new AnimationTimer() {
            double previousTime = 0;
            public void handle(long currentNanoTime) {
                display.clear();
                double deltaTime = (currentNanoTime - previousTime) / 1e9;

                for (Entity e : entities) {
                    if (e.isVisible() && deltaTime < 1)
                        e.render(deltaTime, display.getContext());

                }
                display.setScoreDisplay(score + ""); 
                previousTime = currentNanoTime;
            }
        };
        gameTimer.start();
        animationTimer.start();
    }

    private void initializeVariables() {
      if(gameTimer != null) gameTimer.stop();
      if(animationTimer != null) animationTimer.stop();

      Entity.setGame(this);
      this.entities = new ArrayList<Entity>();
      this.display = new Display(WINDOW_WIDTH, WINDOW_HEIGHT);
      InputHandler.detectKeyStrokes(this.display.getScene());

      isCorrectOrder = true;

      //spawning
      player = new PlayerPlatter(300, WINDOW_HEIGHT/4, 800);
      topHitBox = player;
      order =  new Platter(10, 100, 3);

      startGameLoops();

      stage.setTitle("this game is about sushi");
      stage.setScene(display.getScene());

      }

    private void topCollisionCheck(Entity entity) {
        if(entity != player) {
          if(entity.isCollidingWith(topHitBox)) {
            if (entity instanceof Ingredient && !entity.isPinned()
                && !player.slots.contains(entity)) {
              Ingredient touchedIngredient = (Ingredient)entity;
              //add ideal platter check later
              player.place(touchedIngredient);
              if(!(touchedIngredient.isSame(order.slots.get(player.slots.size() - 1)))) {
                isCorrectOrder = false;
              }else if (player.slots.size() == order.slots.size()){
                initializeVariables();
                score++;
              }
                topHitBox = player.slots.get(player.slots.size() - 1);
              }
            }
          }
        System.out.println(isCorrectOrder);
    }

     private void handleSpawning(double deltaTime, double spawnRate) {
      timeUntilNextSpawn -= deltaTime;
      if(timeUntilNextSpawn < 0) {
          spawnRandomWave((int)nextWaveNum);
          if(nextWaveNum < 5)
            nextWaveNum += 0.25;
          timeUntilNextSpawn = spawnRate;
      }
     }

     private void spawnRandomWave(int count)  {
      double horizontalVariance = 400;
      for(int i = 0; i < count; i++) {
          new Ingredient(Math.random()* WINDOW_WIDTH, 0 - 300 - Math.random() * horizontalVariance, 0, 3);
        }
     }

    public void addEntity(Entity entity) {
      this.entities.add(entity);
    }
    public void removeEntity(Entity entity) {
      this.entities.remove(entity);
    }

    public double getWinWidth() {
      return WINDOW_WIDTH;
    }
    public double getWinHeight() {
      return WINDOW_HEIGHT;
    }

}
