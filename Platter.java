public class Platter extends Entity {
    public Ingredient[] slots;

    public Platter(double x, double y){
        //super("",new Sprite("Assets/Platter.png"));  // will update with jason's method
        super(x, y);
        if(prePlacedIngredients != null) {
            slots = prePlacedIngredients;
            lineUpFromLeft(slots, 0,0);
        }
    }

    public void lineUpFromLeft(Ingredient[] ingredients, double xOffset, yOffset) {
        double startX = x + xOffset;
        double startY = y + yOffset;
        for (Ingredient ingreident : ingredients) {
            ingredient.setPosition(startX, startY);
            ingredient.pinTo(this);
            startX += ingredient.sprite.getWidth();
        }
    }
}
