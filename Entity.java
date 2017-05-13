import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    protected double x;
    protected double y;
    protected Image sprite;
    protected int xoffet; // probably to offset the sprites, probably not needed
    protected int yoffet;
    protected boolean visible = true;
    protected static SushiShop game;

    protected Entity pinParent;
    protected double pinParentX, pinParentY; // separate variables allow for measuring deltaPos over frames

    public Entity(double x, double y) {
        this.game.addEntity(this);
        this.x = x;
        this.y = y;
        this.xoffet = 0;
        this.yoffet = 0;
    }

    // override this to run code every frame
    public void update(double deltaTime) { return; }
    // same as update but reserved for drawing and has access to the canvas
    // draws the sprite if visible by default
    public void render(double deltaTime, GraphicsContext context) { context.drawImage(this.sprite, (int)Math.round(this.x) + this.xoffet, (int)Math.round(this.y) + this.yoffet); }
    // generic aabb collision
    // kinda cheating but reads the image width to check for collisions :^)
    public boolean isCollidingWith(Entity other) {
        return (this.x < other.getX() + other.getSprite().getWidth() && this.x + this.sprite.getWidth() > other.getX() && this.y < other.getY() + other.getSprite().getHeight() && this.sprite.getHeight() + this.y > other.getY());
    }
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void pinTo(Entity entity) {
        pinParent = entity;
        pinParentX = pinParent.x;
        pinParentY = pinParent.y;
    }

    public boolean isPinned() {
      return !(pinParent == null);
    }
    protected void followParent() { //intended to be run in update only
        //adds the CHANGE IN POSITIONS so that the ingredient follows the parent while retaining its original pinned position
        setPosition(this.x + (pinParent.x - pinParentX), this.y + (pinParent.y - pinParentY));

        //updates pinParent positions for the next iteration (think deltaTime)
        pinParentX = pinParent.x;
        pinParentY = pinParent.y;
    }

    public String toString() { return String.format("x: %s\ny: %s\nwidth: %s\nheight: %s", this.x, this.y, this.sprite.getWidth(), this.sprite.getHeight()); }
    public double getX() { return this.x; }
    public double getY() { return this.y; }
    public int getXoffet() { return this.xoffet; }
    public int getYoffet() { return this.yoffet; }
    public boolean isVisible() { return this.visible; }
    public void toggleVisible() { this.visible = !this.visible; }
    public Image getSprite() { return this.sprite; }
    public static void setGame(SushiShop game) { Entity.game = game; }
}
