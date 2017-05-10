public abstract class Projectile extends Entity {
    protected double hspeed;
    protected double vspeed;

    public Projectile(double x, double y, double hspeed, double vspeed) {
        super(x, y);
        this.hspeed = hspeed;
        this.vspeed = vspeed;
    }
    
    public Projectile(double x, double y) {
        super(x, y);
        this.hspeed = 0;
        this.vspeed = 0;
    }
    
    public Projectile() { //empty constructor to allow Ingredient's constructor to switch-case for sprite initialization
        this.sprite = null;
        this.hspeed = 0;
        this.vspeed = 0;
    }

    @Override
    public void update(double deltaTime) {
        this.x + this.hspeed * deltaTime;
        this.y + this.vspeed * deltaTime;
    }

    public void setVelocity(double x, double y) {
        this.hspeed = x;
        this.vspeed = y;
    }
}
