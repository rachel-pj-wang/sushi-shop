import javafx.scene.image.Image;
public class Thinking extends Entity {
    public Thinking(double x, double y) {
        super(x, y);
        this.sprite = SpriteContainer.sprite_salmon;
        System.out.println(this.toString());
    }
    @Override
    public void update(double deltaTime) {
        this.x += 2 * deltaTime;
    }
}
