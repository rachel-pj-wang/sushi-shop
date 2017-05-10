import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Game extends Application {
    //private ArrayList<Entity> entities;
    private AnimationTimer timer;
    private Display display;

    @Override
    public void start(Stage stage) {
        this.display = new Display(800, 600);
        this.display.getContext().drawImage(new Image("thinking.png"), 0, 0); // just testing image drawing
        stage.setTitle("sushi?");
        stage.setScene(this.display.getScene());
        stage.show();


        // this.gameTimer = new AnimationTimer() {
        //     @Override
        //     public void handle(long now) {
        //         for(Entity e : entities) {
        //             // TODO: delta time
        //             e.update(1);
        //         }
        //     }
        // }

        // this.animationTimer = new AnimationTimer() {
        //     @Override
        //     public void handle(long now) {

        //     }
        // }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
