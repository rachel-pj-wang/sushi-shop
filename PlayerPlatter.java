import javafx.scene.input.KeyCode;
import java.util.ArrayList;

public class PlayerPlatter extends Platter{
    private double moveSpeed;

    public PlayerPlatter(double x, double y, ArrayList<Ingredient> slots, double moveSpeed){
        super(x,y, slots);
        this.moveSpeed = moveSpeed;
        this.sprite = Sprites.platter;
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
