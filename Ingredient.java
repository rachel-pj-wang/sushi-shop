public class Ingredient extends Projectile {
    protected enum IngredientTypes {
        RICE, SALMON, ROACH, OCTOPUS, TUNA, CUCUMBER, YELLOWTAIL;
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
            case ROACH:
                this.sprite = Sprites.roach;
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
        
    }
}
