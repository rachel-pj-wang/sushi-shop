import java.util.ArrayList;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

public abstract class Entity {
    public double x; // but i still doubt any outside objects need to change this
    public double y;
    protected Image sprite;
    protected String tag;
    protected int xoffet;
    protected int yoffet;
    protected Rectangle hitbox;
    protected boolean visible = true;
    protected static Game game;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
        this.xoffet = 0;
        this.yoffet = 0;
    }
    public void update(double deltaTime) {
        // override this to run code every frame
    }
    // same as update but reserved for drawing
    // runs after all objects are updated
    public void render(double deltaTime, GraphicsContext context) {
        context.drawImage(this.sprite, (int)Math.round(this.x) + this.xoffet, (int)Math.round(this.y) + this.yoffet);
    }
    // updates the position of the hitbox, runs in the game loop dont worry about it
    public boolean isCollidingWith(Entity other) {
        return (this.x < other.getX() + other.getSprite().getWidth() && this.x + this.sprite.getWidth() > other.getX() && this.y < other.getY() + other.getSprite().getHeight() && this.sprite.getHeight() + this.y > other.getY());
    }
    public Rectangle getHitbox() { return this.hitbox; }
    public double getX() { return this.x; }
    public double getY() { return this.y; }
    public int getXoffet() { return this.xoffet; }
    public int getYoffet() { return this.yoffet; }
    public double getWidth() { return this.width; }
    public double getHeight() { return this.height; }
    public boolean getVisible() { return this.visible; }
    public Image getSprite() { return this.sprite; }
    public String toString() {
        return String.format("x: %s\ny: %s\nwidth: %s\nheight: %s", this.x, this.y, this.sprite.getWidth(), this.sprite.getHeight());
        //return "";
    }
}
