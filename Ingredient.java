public class Ingredient extends Projectile{
    protected static enum IngredientTypes {
        SALMON, TUNA, CUCUMBER, YELLOWTAIL,SEAWEED;//RICE
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
            case TUNA:
                this.sprite = Sprites.tuna;
                break;
            case CUCUMBER:
                this.sprite = Sprites.cucumber;
                break;
            case YELLOWTAIL:
                this.sprite = Sprites.yellowtail;
                break;
            case SEAWEED:
                this.sprite = Sprites.seaweed;
            // case RICE:
            //     this.rice = Sprites.rice;
        }
    }
    public Ingredient() {
        super(0, 0, 0, 0);
        this.ingredient = IngredientTypes.getRandom();
        switch (this.ingredient) {
            case SALMON:
                this.sprite = Sprites.salmon;
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
            case SEAWEED:
                this.sprite = Sprites.seaweed;
                break;
        }
    }

    public IngredientTypes getIngredient(){return ingredient;}

    public boolean isSame(Ingredient obj){
       return this.ingredient == obj.getIngredient();
    }
    @Override
    public void update(double deltaTime) {
        if(pinParent != null) {
            followParent();
            return;
        }
        setPosition(x + hspeed * deltaTime, y + vspeed * deltaTime);
    }

    @Override
    public void onCollision(Entity e)  {
        super.onCollision(e);
        if(e instanceof Roach && vspeed >= 0) {
            if(pinParent instanceof PlayerPlatter)
                game.loseGame();
            else if (pinParent == null)
                game.destroy(this);
        }
    }

    @Override
    public void pinTo(Entity entity) {
        super.pinTo(entity);
        setVelocity(0,0);
    }
}
