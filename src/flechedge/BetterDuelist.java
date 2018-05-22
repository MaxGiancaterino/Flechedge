package flechedge;


import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BetterDuelist extends ImageView {
	private int direction;
	private SpriteAnimation midRetAdvance; 
	private SpriteAnimation midLunge;

	public BetterDuelist(int direction, int x, int y) {
		this.direction = direction;
		this.setX(x);
		this.setY(y);
		this.setScaleX(direction);
		midRetAdvance = new SpriteAnimation("Sprites/MidRetAdvance.png", this, new Duration(600), new int[] {0, 0, 13, 13, 0}, 1, 0, 0, 219, 200, direction);
		midLunge = new SpriteAnimation("Sprites/MidLunge.png", this, new Duration(800), new int[] {0, 0, 78, 30, 0}, 1, 0, 0, 219, 200, direction);
	}
	
	public void advance() {
		//System.out.println("once");
		//midRetAdvance.play();
	}
	
	public void retreat() {
	}
	
	public void extend() {
	}
	
	public void high() {
	}
	
	public void mid() {
	}
	
	public void low() {
	}
	
	public void lunge() {
		midLunge.play();
	}
	
	//etc.
}
