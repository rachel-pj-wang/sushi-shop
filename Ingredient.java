import java.util.ArrayList;
import java.util.HashMap;
import javafx.geometry.Point2D;

public class Ingredient extends Projectile {
    protected Entity pinParent;
    protected double pinParentX, pinParentY;
    protected int ingredientId;
    // wip i dont know what im doing
    protected final static String[] INGREDIENT_NAMES = new String[] {"RICE", "FISH1", "FISH2", "FISH3", "FISH4"};

    protected enum Ingredients {
        RICE, SALMON, ROACH
    }

    public Ingredient(double x, double y) {
        super(x, y);
        this.ingredientId = (int)(Math.random()*Ingredient.INGREDIENT_NAMES.length);
        switch (this.ingredientId) {
            case Ingredients.RICE:
                this.sprite = SpriteContainer.sprite_rice
        }
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
