import java.util.ArrayList;

public class Platter extends Entity {
    public ArrayList<Ingredient> slots;
    protected int capacity;
    protected int nextFreeIndex;
    public Platter(double x, double y, ArrayList<Ingredient> slots){
        super(x, y);
        this.slots = slots;
        this.sprite = Sprites.platter;
        if(!this.slots.isEmpty()) {  //if the platter was constructed with preset ingredients (ie. the order display)
            lineIngredientsFromLeft(0,0);
        }
    }

    public void place (Ingredient ingredient) {
      slots.add(ingredient);
      nextFreeIndex++;

      lineIngredientsFromLeft(0,-20);

      }

    public void lineIngredientsFromLeft(double xOffset, double yOffset) {
       if (slots != null) {
         double startX = x + xOffset;
         double startY = y + yOffset;
         for (Ingredient ingredient : slots) {
             ingredient.setPosition(startX, startY);
             ingredient.pinTo(this);
             startX += ingredient.sprite.getWidth();
         }
       }
     }

}
