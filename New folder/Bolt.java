import javafx.scene.image.*;
import javafx.event.*;
import java.util.ArrayList;
import javafx.scene.paint.*;
import javafx.animation.*;



public class Bolt extends Projectile{ 
	
	public Bolt (float s, String d, String t) {
		super (s, d);
		switch (t) {
			case "BLUE": 
				setImage("/Assets/laserBlue02.png");
				break; 
			case "RED": 
				setImage("/Assets/laserRed02.png"); 
				break; 
		}
		setTag(t);
	}
	
	@Override
	public void onCollision(Collider other) { 
		if(other instanceof Orb) {
			if(other.tag != tag) {
				destroy(other);
			}
			destroy(this); 
		}
	
	}
}