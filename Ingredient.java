public class Ingredient extends Projectile{
    protected static enum IngredientTypes {
        SALMON, OCTOPUS, TUNA, CUCUMBER, YELLOWTAIL;
        public static IngredientTypes getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    protected IngredientTypes ingredient;
    public Ingredient(double x, double y, double xSpeed, double ySpeed) {
        super(x, y, xSpeed, ySpeed);
        this.ingredient = IngredientTypes.getRandom();
        switch (this.ingredient) {
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
    public Ingredient() {
        super(0, 0, 0, 0);
        this.ingredient = IngredientTypes.getRandom();
        switch (this.ingredient) {
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

    public IngredientTypes getIngredient(){return ingredient;}

    @Override
    public boolean equals(Object obj){
       return obj instanceof Ingredient && this.ingredient == ((Ingredient)obj).getIngredient();
    }
    @Override
    public void update(double deltaTime) {
        if(pinParent != null) {
            followParent();
            return;
        }
        setPosition(x + hspeed, y + vspeed);
    }

    @Override
    public void pinTo(Entity entity) {
      super.pinTo(entity);
      setVelocity(0,0);
    }

}
