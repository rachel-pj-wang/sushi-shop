import javafx.scene.image.Image;
public class Thinking extends Entity {
    public Thinking(double x, double y) {
        super(x, y);
        this.sprite = new Image("thinking.png");
        System.out.println(this.toString());
    }
    @Override
    public void update(double deltaTime) {
        this.x += 2 * deltaTime;
    }
}
