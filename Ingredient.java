import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Point2D;

public class Ingredient extends Projectile {
    protected Entity pinParent;
    protected double pinParentX, pinParentY;
    protected String ingredient;
    // wip i dont know what im doing
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
        pinParent = entity;
    }

    @Override
    public void update(double deltaTime) {
        if(pinParent != null) {
            //adds the CHANGE IN POSITIONS so that the ingredient follows the parent while retaining its original pinned position
            setPosition(this.x + (pinParent.x - pinParentX), this.y + (pinParent.y - pinParentY));
            refreshPinParentPos();
            return;
        }
    }

    private void refreshPinParentPos() {
        pinParentX = pinParent.x;
        pinParentY = pinParent.y;
    }
}
