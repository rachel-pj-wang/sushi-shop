public class Platter extends Entity {
    public Ingredient[] slots;
    protected int capacity;
    protected int nextFreeIndex; 
    public Platter(double x, double y, Ingredient[] slots){
        super(x, y);
        this.slots = slots;
        if(this.slots[0] != null) {  //if the platter was constructed with preset ingredients (ie. the order display)
            lineIngredientsFromLeft(0,0);
            nextFreeIndex = null; 
        } else {
          nextFreeIndex = 0; 
        }
        
        
        capacity = slots.length; 
        
    }

    public void lineIngredientsFromLeft( double xOffset, double yOffset) {
      if (slots != null) {
        double startX = x + xOffset;
        double startY = y + yOffset;
        for (Ingredient ingredient : ingredients) {
            ingredient.setPosition(startX, startY);
            ingredient.pinTo(this);
            startX += ingredient.
        }
      }
    }
  
}
