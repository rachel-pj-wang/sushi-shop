import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Display {
    private Group root;
    private Canvas canvas;
    private GraphicsContext context;
    private Scene scene;

    public Display(int width, int height) {
        this.root = new Group();
        this.canvas = new Canvas(width, height);
        this.context = canvas.getGraphicsContext2D();
        this.root.getChildren().add(this.canvas);
        this.scene = new Scene(this.root, width, height);

        Label orderLabel = new Label("ORDER:");
        orderLabel.setFont(new Font(30));
        orderLabel.setLayoutX(10);
        orderLabel.setLayoutY(10);
        root.getChildren().add(orderLabel);

    }
    public void clear() {
        this.context.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }
    public Scene getScene() { return this.scene; } // this is to hook up to the game class
    public Canvas getCanvas() { return this.canvas; } // probably useless
    public GraphicsContext getContext() { return this.context; } // just draw directly onto the context
}
