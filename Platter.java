import java.util.ArrayList;

public class Platter extends Entity {
    public ArrayList<Ingredient> slots;
    protected int capacity;
    protected int nextFreeIndex;
    public Platter(double X, double Y, int size){
        super(X, Y);
        this.slots = new ArrayList<Ingredient>();
        this.sprite = Sprites.platter;

        for (int i = 0; i < size; i++) {
          slots.add(new Ingredient());
          y += slots.get(i).sprite.getHeight();
        }
        stackIngredients(0, -20);
      }

    public void place (Ingredient ingredient) {
      slots.add(ingredient);
      nextFreeIndex++;

      stackIngredients(0,-20);

      }

       public void stackIngredients(double xOffset, double yOffset) {
          if (slots != null) {
            double startX = x + xOffset;
            double startY = y + yOffset;
            for (Ingredient ingredient : slots) {
                ingredient.setPosition(startX, startY);
                ingredient.pinTo(this);
                startY -= ingredient.sprite.getHeight();
            }
          }
        }
}
