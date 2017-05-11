public class Platter extends Entity {
    public Ingredient[] slots;

    public Platter(double x, double y, Ingredient[] slots){
        //super("",new Sprite("Assets/Platter.png"));  // will update with jason's method
        super(x, y);
        if(slots != null) {
            this.slots = slots;
            lineUpFromLeft(this.slots, 0,0);
        }
    }

    public void lineUpFromLeft(Ingredient[] ingredients, double xOffset, double yOffset) {
        double startX = x + xOffset;
        double startY = y + yOffset;
        for (Ingredient ingredient : ingredients) {
            ingredient.setPosition(startX, startY);
            ingredient.pinTo(this);
            startX += ingredient.sprite.getWidth();
        }
    }
}
