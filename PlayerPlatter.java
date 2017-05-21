import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class PlayerPlatter extends Platter{
    private double moveSpeed;

    private Rectangle moveBounds;

    public PlayerPlatter(double x, double y, double moveSpeed, Rectangle moveBounds){
        super(x,y, 0);
        this.moveSpeed = moveSpeed;
        this.sprite = Sprites.platter;
        this.moveBounds = moveBounds;
    }

    public void update(double deltaTime){
        if (x > moveBounds.getX() && InputHandler.keyPressed(KeyCode.LEFT))
            setPosition(x - moveSpeed * deltaTime, y);
        if (x + sprite.getWidth() < moveBounds.getWidth() && InputHandler.keyPressed(KeyCode.RIGHT))
            setPosition(x + moveSpeed * deltaTime, y);
        if (y > moveBounds.getY() && InputHandler.keyPressed(KeyCode.UP))
            setPosition(x, y - moveSpeed * deltaTime);
        if (y + sprite.getHeight() < moveBounds.getHeight() && InputHandler.keyPressed(KeyCode.DOWN))
            setPosition(x, y + moveSpeed * deltaTime);

        if(slots.size() > 0 && InputHandler.keyPressed(KeyCode.SPACE)) {
          shoot();
        }
    }

    public void setMoveBounds(Rectangle moveBounds) {
      this.moveBounds = moveBounds;
    }

    public void shoot() {
      game.setPlayerAsTopHitBox();
      ArrayList<Ingredient> temp = slots;
      slots = new ArrayList<Ingredient>();
      for (int i = 0; i < temp.size(); i++) {
        temp.get(i).unPin();
        if(i == 0) {
          temp.get(i).setVelocity(0, -1600);
        }
      }
    }

    @Override
    public void onCollision(Entity e) {
      if(e instanceof Roach) {
        game.loseGame();
      }
    }
}
