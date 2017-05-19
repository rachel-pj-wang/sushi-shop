import java.util.ArrayList;

public class Platter extends Entity {
    public ArrayList<Ingredient> slots;
    public Platter(double X, double Y, int size){
        super(X, Y);
        this.slots = new ArrayList<Ingredient>();
        this.sprite = Sprites.platter;

        for (int i = 0; i < size; i++) {
          slots.add(new Ingredient());
          y += slots.get(i).sprite.getHeight();
        }
        stackIngredients(0, 0);
      }

    public void place (Ingredient ingredient) {
      slots.add(ingredient);

      stackIngredients(0,0);

      }

       public void stackIngredients(double xOffset, double yOffset) {
          if (slots != null) {
            double startY = y;
            double startX = x;
            for (Ingredient ingredient : slots) {
                startY -= ingredient.sprite.getHeight();
                startX = x + sprite.getWidth()/2 - ingredient.sprite.getWidth()/2;
                ingredient.setPosition(startX, startY);
                ingredient.pinTo(this);
            }
          }
        }
}
