import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import java.util.ArrayList;

public class InputHandler {
    public static ArrayList<String> pressedKeys = new ArrayList<String>();
    public static ArrayList<String> detectKeyStrokes(Scene randomScene) {
        randomScene.setOnKeyPressed( new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                System.out.println("I DETECTED A KEY!");
                // only add once... prevent duplicates
                if (!pressedKeys.contains(code))
                    pressedKeys.add(code);
            }
        });
        randomScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                System.out.println("I DETECTED A KEY!");
                String code = e.getCode().toString();
                pressedKeys.remove(code);
            }
        });
        return pressedKeys;
    }
}
