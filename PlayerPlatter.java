
public class PlayerPlatter extends Platter{
  private double moveSpeed;

  public PlayerPlatter(double x, double y, double moveSpeed){
    super(x,y, null);
    this.moveSpeed = moveSpeed; 
    this.sprite = SpriteContainer.sprite_salmon;
  }

  public void update(double deltaTime){
      if(InputHandler.pressedKeys.contains("LEFT")) {
        setPosition(x - moveSpeed * deltaTime, y);
      }
      if(InputHandler.pressedKeys.contains("RIGHT")){
        setPosition(x + moveSpeed * deltaTime, y);
      }
      if(InputHandler.pressedKeys.contains("UP")) {
        setPosition(x, y - moveSpeed * deltaTime);
      }
      if(InputHandler.pressedKeys.contains("DOWN")){
        setPosition(x, y + moveSpeed * deltaTime);
      }
  }
}
