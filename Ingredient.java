import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Point2D;

public class Ingredient extends Projectile {
    protected Entity pinParent;
    protected double pinParentX, pinParentY;
    protected String ingredient;
    // wip i dont know what im doing
    protected HashMap<String, Image> ingredients =
    protected final static Image[] INGREDIENT_SPRITES = new Image[] {
        new Image("Assets/Rice.png")
        new Image("Assets/Rice.png")
        new Image("Assets/Rice.png")
        new Image("Assets/Rice.png")
        new Image("Assets/Rice.png")
    };
    protected final static String[] INGREDIENT_NAMES = new String[] {
        "RICE",
        "FISH1",
        "FISH2",
        "FISH3",
        "FISH4"
    };

    public Ingredient(double x, double y) {
        super(x, y);

    }

    public void pinTo(Entity entity) {
        setVelocity(0,0);
        pinnedEntity = entity;
    }

    @Override
    public void update(double deltaTime) {
        if(pinnedEntity != null) {
            //adds the CHANGE IN POSITIONS so that the ingredient follows the parent while retaining its original pinned position
            setPosition(sprite.getLayoutX() + (pinParent.sprite.getLayoutX() - pinParentX, sprite.getLayoutY() + (pinParent.sprite.getLayoutY() - pinParentY)));
            refreshPinParentPos();
            return;
        }
    }

    private void refreshPinParentPos() {
        pinParentX = pinParent.sprite.getLayoutX();
        pinParentY = pinParent.sprite.getLayoutY();
    }
}
