import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class SushiShop extends Application {
    private ArrayList<Entity> entities;
    private AnimationTimer gameTimer;
    private AnimationTimer animationTimer;
    private Display display;
    private double timeUntilNextSpawn;
    private double nextWaveNum;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Entity.setGame(this);
        this.entities = new ArrayList<Entity>();
        this.display = new Display(WINDOW_WIDTH, WINDOW_HEIGHT);
        InputHandler.detectKeyStrokes(this.display.getScene()); 
        stage.setTitle("SushiShop");
        stage.setScene(display.getScene());
        startGameLoops();
        startGame(stage);
        stage.show();
    }

    public void startGameLoops() {
        gameTimer = new AnimationTimer() {
            double previousTime = 0;
            //PLACEHOLDER spawn mechanics - proc bably do a funciton or something that'll take wave objects
            double timeUntilNextSpawn = 5;
            double spawnRate = 1;
            double nextWaveNum = 1;

            public void handle(long currentNanoTime) {
                double deltaTime = (currentNanoTime - previousTime) / 1e9;  // time since last frame as a fraction of a second
                for (Entity e : entities) {
                    if (deltaTime < 1) {
                        e.update(deltaTime);
                    }
                }
                previousTime = currentNanoTime;
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
                previousTime = currentNanoTime;
            }
        };
        gameTimer.start();
        animationTimer.start();
    }

    private void startGame(Stage stage)
    {
        //timeRemaining = 60;
        timeUntilNextSpawn = 5;
        nextWaveNum = 1;
        new PlayerPlatter(0, 0, 800);
        //new Thinking(0, 100);
        //new Thinking(0, 200);
        //new Thinking(0, 300);
    }

    // private void handleSpawning(double deltaTime) {
    //  timeUntilNextSpawn -= deltaTime;
    //  if(timeUntilNextSpawn < 0) {
    //      spawnRandomWave((int)nextWaveNum);
    //      nextWaveNum += 0.25;
    //      timeUntilNextSpawn = spawnRate;
    //  }
    // }

    // private void spawnRandomWave(int count)  {
    //  double horizontalVariance = 200;
    //  for(int i = 0; i < count; i++) {
    //      //spawn()
    //  }
    // }
    // private void restartGame(Stage stage) {
    //  animationTimer.stop();
    //  stage.close();
    //  startGame(stage);
    // }
    // private void spawn(Entity entity, double x, double y) {
    //  root.getChildren().add(entity.sprite);
    //  entity.setPosition(x, y);
    //  if(entity instanceOf Platter) {
    //      for(Ingredient ingredient : entity.slots) {
    //          root.getChildren().add(ingredient.sprite);
    //      }
    //  }
    // }
    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }
}
