import java.lang.Math;

public class Roach extends Projectile {
    protected double sinInput;
    public Roach(double x, double y) {
        super(x, y, -200, 0);
        this.sprite = Sprites.roach0;

        sinInput = Math.random() * Math.PI;
    }
    @Override
    public void update(double deltaTime) {
        if(pinParent != null) {
            followParent();
            return;
        }
        sinInput += deltaTime;
        setPosition(x + hspeed * deltaTime, y + (Math.random()* 6)*Math.sin((Math.random()*9)*sinInput));
    }
    @Override
    public void pinTo(Entity entity) {
        super.pinTo(entity);
        setVelocity(0,0);
    }
    @Override
    public void onCollision(Entity e)  {
        if(e instanceof Ingredient) {
            Ingredient ingredient = (Ingredient)e;
            if (ingredient.vspeed < 0) {
                pinTo(ingredient);
                game.killRoachScore();
            }
        }
    }
}
