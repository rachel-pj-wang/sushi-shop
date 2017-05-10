import javafx.scene.image.*;
import javafx.event.*;
import java.util.ArrayList;
import javafx.scene.paint.*;
import javafx.animation.*;

public class Player extends Collider { 
	public float speed;

	//PLACEHOLDER mechanics for 1st playable level 
	public int charge;
	public int lives;
	
	public double nextShot; //a cooldown 'timer' 
	public double shootRate; 
	
	public Player (String startingCullor, float s) {
		super(startingCullor); 
		lives = 3; 
		nextShot = 0; 
		shootRate = 0.1;

		switch (startingCullor) {
			case "RED":
				setImage("Assets/playerShip3_red.png");
				break; 
			case "BLUE": 
				setImage("Assets/playerShip3_blue.png");
				break;
		}
		speed = s; 
		charge = 0; 
	}
	
	@Override
	public void onCollision(Collider collided) {
		if(collided instanceof Orb) {
			if(collided.tag == tag) {
				destroy (collided); 
				charge++; 
				if(charge == 30) {
					charge = 69;
				}
			}
			else if (collided.tag == "BLUE" || collided.tag == "RED") { 			//the else if guarantees that this is only reached if it's the OPPOSITE CULLOR
				charge = 0; 
				lives--;
				swapColors();
			}
		}
	}
	
	
	public void shoot(double currentTimeSeconds) {
		if(charge > 0 && nextShot < currentTimeSeconds) {
			spawn(new Bolt(1000, "UP", tag));
			charge--; 
			nextShot = currentTimeSeconds + shootRate; 
		}

	}
	
	private void swapColors() {
		charge = 0; 
		if(tag == "RED") {
			tag = "BLUE"; 
			setImage("Assets/playerShip3_blue.png");
		}
		else {
			tag = "RED";
			setImage("Assets/playerShip3_red.png");
		}
	}
}