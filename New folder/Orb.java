import javafx.scene.image.*;
import javafx.event.*;
import java.util.ArrayList;
import javafx.scene.paint.*;
import javafx.animation.*;



public class Orb extends Projectile{ 
	
	public Orb (float s, String d, String t) {
		super (s, d);
		switch (t) {
			case "BLUE": 
				setImage("/Assets/laserBlue08.png");
				break; 
			case "RED": 
				setImage("/Assets/laserRed08.png"); 
				break; 
		}
		setTag(t);
	}
	
}