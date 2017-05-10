import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputHandler {
  public static ArrayList<String> pressedKeys = new ArrayList<String>();

  public ArrayList<String> detectKeyStrokes(Scene randomScene) {
    		randomScene.setOnKeyPressed(
    			new EventHandler<KeyEvent>()
    			{
    				public void handle(KeyEvent e)
    				{
    					String code = e.getCode().toString();

    					// only add once... prevent duplicates
    					if ( !input.contains(code) )
    						input.add( code );
    				}
    			});

    		randomScene.setOnKeyReleased(
    			new EventHandler<KeyEvent>()
    			{
    				public void handle(KeyEvent e)
    				{
    					String code = e.getCode().toString();
    					input.remove( code );
    				}
    			});
    		return input;
    	}
}
