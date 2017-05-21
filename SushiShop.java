import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.lang.Math;
import javafx.scene.input.KeyCode;

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
    private int bestScore;

    private double roachSpawnChance;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        this.stage = stage;
        if(gameTimer != null) gameTimer.stop();
        if(animationTimer != null) animationTimer.stop();

        Entity.setGame(this);
        this.entities = new ArrayList<Entity>();
        this.display = new Display(WINDOW_WIDTH, WINDOW_HEIGHT);
        InputHandler.detectKeyStrokes(this.display.getScene());

        //spawning
        order =  new Platter(10, 100, 3);
        player = new PlayerPlatter(10, WINDOW_HEIGHT - order.sprite.getHeight() - 10, 700, new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT));
        topHitBox = player;

        isCorrectOrder = true;
        roachSpawnChance = 0.2;


        startGameLoops();

        stage.setTitle("this game is about sushi");
        stage.setScene(display.getScene());

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
                checkCollisions();

                handleSpawning(deltaTime, 2);
                previousTime = currentNanoTime;

                //placeholder loss handling
                if(!isCorrectOrder) { // losing
                  loseGame();
                }

                if(player.x > display.getLineOffSet()) {
                  startGame();
                }

                /*if(InputHandler.keyPressed(KeyCode.ESCAPE)){
                  reset();
                  gameTimer.stop();
                }*/ //this is bugged, fix later if someon efeels like it
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
                display.setScoreDisplay("SCORE: " + score);
                display.setBestScoreDisplay("TOP SCORE: " + bestScore);
                display.handleStart(deltaTime);
                previousTime = currentNanoTime;
            }
        };
        gameTimer.start();
        animationTimer.start();
    }

    public void startGame() {
      player.setMoveBounds(new Rectangle(display.getLineOffSet(), 0, WINDOW_WIDTH, WINDOW_HEIGHT));
      display.setPlayMode();
    }

    public void loseGame() {
      display.setDemoMode();
      score = 0;
      isCorrectOrder = true;
      roachSpawnChance = 0.2;

      player.clearPlatter();
      player.setPosition(10, WINDOW_HEIGHT - order.sprite.getHeight() - 10);

      topHitBox = player;

    }

    private void checkCollisions() {
      for(Entity e1 : entities) {
        for(Entity e2 : entities) {
            if (e1 != e2 && e1.isCollidingWith(e2)) {
                e1.onCollision(e2);
            }
          }
        }
      }

    private void topCollisionCheck(Entity entity) {
        if(entity != player) {
          if(entity.isCollidingWith(topHitBox)) {
            if (entity instanceof Ingredient && !entity.isPinned()
                && !player.slots.contains(entity)) {
              Ingredient touchedIngredient = (Ingredient)entity;
              player.place(touchedIngredient);
              if(!(touchedIngredient.isSame(order.slots.get(player.slots.size() - 1)))) {
                isCorrectOrder = false;
                return;
              }else if (player.slots.size() == order.slots.size()){
                finishPlatter();
                return;
              }
                topHitBox = player.slots.get(player.slots.size() - 1);
              }
            }
          }
    }

    private void finishPlatter() {
      player.clearPlatter();

      destroy(order);
      order =  new Platter(10, 100, 3);

      topHitBox = player;
      score++;
      display.makeScoreBlack();
      if(score > bestScore) bestScore = score;
    }

    public void setPlayerAsTopHitBox() {
      topHitBox = player;
    }

    public void killRoachScore() {
      display.makeScoreBrown();
      score += 10;
    }

     private void handleSpawning(double deltaTime, double spawnRate) {
      timeUntilNextSpawn -= deltaTime;
      if(timeUntilNextSpawn < 0) {
          spawnRandomWave((int)nextWaveNum);
          if(nextWaveNum < 4)
            nextWaveNum += 0.5;
          timeUntilNextSpawn = spawnRate;
      }

      if(score > 100)  {
        roachSpawnChance = 0.9;
      }

     }

     private void spawnRandomWave(int count)  {
      double verticalVariance = 400;
      for(int i = 0; i < count; i++) {
          double linePos = display.getLineOffSet();
          new Ingredient(linePos + Math.random()*(WINDOW_WIDTH - linePos - Sprites.cucumber.getWidth()), -300 - Math.random() * verticalVariance, 0, 300);
        }
        if(Math.random() < roachSpawnChance) {
            new Roach(WINDOW_WIDTH, Math.random()*WINDOW_HEIGHT - Sprites.roach0.getHeight());
        }
      }

    public void addEntity(Entity entity) {
      this.entities.add(entity);
    }
    public void destroy(Entity entity) {
      entity.onDestroy();
      this.entities.remove(entity);
    }

    public void reset() {
      stage.close();
      start(stage);
    }

    public double getWinWidth() {
      return WINDOW_WIDTH;
    }
    public double getWinHeight() {
      return WINDOW_HEIGHT;
    }

}
