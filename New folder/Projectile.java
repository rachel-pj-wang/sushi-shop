import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.event.*;
import java.util.ArrayList;
import javafx.scene.paint.*;
import javafx.animation.*;



public class Projectile extends Collider{ 
	
	public double speed; 
	public String direction; 
	
	public Projectile (double s, String d) {
		speed = s;  
		direction = d; 
	}
	
}