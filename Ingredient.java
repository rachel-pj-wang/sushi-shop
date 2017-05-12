public class Ingredient extends Projectile {
    protected enum IngredientTypes {
        RICE, SALMON, OCTOPUS, TUNA, CUCUMBER, YELLOWTAIL;
        public static IngredientTypes getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
    protected Entity pinParent;
    protected double pinParentX, pinParentY;
    protected IngredientTypes ingredient;
    public Ingredient(double x, double y) {
        super(x, y);
        this.ingredient = IngredientTypes.getRandom();
        switch (this.ingredient) {
            case RICE:
                this.sprite = Sprites.rice;
                break;
            case SALMON:
                this.sprite = Sprites.salmon;
                break;
            case OCTOPUS:
                this.sprite = Sprites.octopus;
                break;
            case TUNA:
                this.sprite = Sprites.tuna;
                break;
            case CUCUMBER:
                this.sprite = Sprites.cucumber;
                break;
            case YELLOWTAIL:
                this.sprite = Sprites.yellowtail;
                break;
            default:
                this.sprite = Sprites.thinking;
        }
    }

    @Override
    public void update(double deltaTime) {
        if(pinParent != null) {
            followParent(); 
            return;
        }
        
        setPosition(x + hspeed, y + vspeed); 
    }

    public void pinTo(Entity entity) {
        setVelocity(0,0);
        pinParent = entity;
    }

    private void followParent() {
        //adds the CHANGE IN POSITIONS so that the ingredient follows the parent while retaining its original pinned position
        setPosition(this.x + (pinParent.x - pinParentX), this.y + (pinParent.y - pinParentY));
        
        //updates pinParent positions for the next iteration (think deltaTime) 
        pinParentX = pinParent.x;
        pinParentY = pinParent.y;
    }

}
