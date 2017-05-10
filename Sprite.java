import javafx.scene.image.*;
import javafx.event.*;
import java.util.ArrayList;
import javafx.scene.paint.*;
import javafx.animation.*;
import javafx.scene.image.Image;

public class Sprite {
    private int x;
    private int y;
    private int xoffet;
    private int yoffet;
    private String fileName;
    private Entity parent;
    private Image image;

    public Sprite(String fileName) {
        this.image = new Image(fileName);
    }
    public Sprite(Image image) {
        this.image = image;
    }
    public render(GraphicsContext context) {
        this.context.drawImage(this.image, this.parent.x + this.xoffet, this.parent.y + this.yoffet);
    }

    //include a getWidth() method pls -evan
}
