import javafx.scene.image.*;
import javafx.event.*;
import java.util.ArrayList;
import javafx.scene.paint.*;
import javafx.animation.*;
import javafx.scene.image.Image; 

public class Collider { 
	public ImageView sprite; 
	public String tag; 
	
	public Collider () {  //empty constructor just to satisfy requirement to have one
		sprite = new ImageView(); 
		tag = ""; 
	}
	
	public Collider (String t) {
		tag = t; 
		sprite = new ImageView(); 
	}

	public Collider (String img, String t) {
		sprite = new ImageView(img); 
		Image image = new Image(img);
		sprite.setFitWidth(image.getWidth()); 
		sprite.setFitHeight(image.getHeight()); 
		
		tag = t; 
	}
	
	public void spawn(Projectile o, double x, double y) {
		CULLOR.root.getChildren().add(o.sprite);	
		CULLOR.renderedProjectiles.add(o); 
		
		o.sprite.setLayoutX(x);
		o.sprite.setLayoutY(y); 
	};
	
	public void setImage(String img) {
		Image image = new Image(img);
		
		sprite.setImage(image); 
		sprite.setFitHeight(image.getHeight());
		sprite.setFitWidth(image.getWidth());
	}

	public void spawn(Projectile o) {
		CULLOR.root.getChildren().add(o.sprite);	
		CULLOR.renderedProjectiles.add(o); 
		
		o.sprite.setLayoutX(sprite.getLayoutX() + sprite.getFitWidth() / 2.3);
		o.sprite.setLayoutY(sprite.getLayoutY() - sprite.getFitHeight() / 2); 
		

	};

	public void setTag(String t) {
		tag = t; 
	}
	
	public void onCollision(Collider other) { 
	}
	
	public void destroy(Collider destroying) {	//removes their sprite from root group node for moveProjectiles to delete from renderedProjectile 
		CULLOR.root.getChildren().remove(destroying.sprite); 
	}
}
	