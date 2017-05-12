import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;

public class InputHandler {
    private final static ArrayList<KeyCode> PRESSED_KEYS = new ArrayList<KeyCode>();
    public static ArrayList<KeyCode> detectKeyStrokes(Scene scene) {
        scene.setOnKeyPressed( new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                //System.out.printf("Key pressed: %s\n", e.getCode().toString());
                // only add once... prevent duplicates
                if (!InputHandler.PRESSED_KEYS.contains(e.getCode()))
                    InputHandler.PRESSED_KEYS.add(e.getCode());
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                //System.out.printf("Key released: %s\n", e.getCode().toString());
                InputHandler.PRESSED_KEYS.remove(e.getCode());
            }
        });
        return InputHandler.PRESSED_KEYS;
    }
    public static boolean keyPressed(String key) { return InputHandler.PRESSED_KEYS.contains(KeyCode.getKeyCode(key)); }
    public static boolean keyPressed(KeyCode key) { return InputHandler.PRESSED_KEYS.contains(key); }
}
