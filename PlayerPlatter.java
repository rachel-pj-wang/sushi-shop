import javafx.scene.input.KeyCode;

public class PlayerPlatter extends Platter{
    private double moveSpeed;

    public PlayerPlatter(double x, double y, double moveSpeed){
        super(x,y, null);
        this.moveSpeed = moveSpeed; 
        this.sprite = Sprites.salmon;
    }

    public void update(double deltaTime){
        if (InputHandler.keyPressed(KeyCode.LEFT))
            setPosition(x - moveSpeed * deltaTime, y);
        if (InputHandler.keyPressed(KeyCode.RIGHT))
            setPosition(x + moveSpeed * deltaTime, y);
        if (InputHandler.keyPressed(KeyCode.UP))
            setPosition(x, y - moveSpeed * deltaTime);
        if (InputHandler.keyPressed(KeyCode.DOWN))
            setPosition(x, y + moveSpeed * deltaTime);
    }
}
