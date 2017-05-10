public PlayerPlatter extends Platter{
  private double moveSpeed;

  public PlayerPlatter(){

  }

  public void update(deltatime){
      if(INPUT.pressedKeys.contains("LEFT")) {
        setPosition(x - moveSpeed * deltatime, y);
      }
      if(INPUT.pressedKeys.contains("RIGHT")){
        setPosition(x + moveSpeed * deltaTime, y);
      }
      if(INPUT.pressedKeys.contains("UP")) {
        setPosition(x, y - moveSpeed * deltaTime);
      }
      if(INPUT.pressedKeys.contains("DOWN")){
        setPosition(x, y + moveSpeed * deltaTime);
      }
  }
}
