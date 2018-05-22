package flechedge;


import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BetterDuelist extends ImageView {
	private int direction;
	private SpriteAnimation midRetAdvance; 
	private SpriteAnimation midRetRetreat;
	private SpriteAnimation midLunge;
	private AtomicBoolean locked = new AtomicBoolean();
	
	public BetterDuelist(int direction, int x, int y) {
		this.direction = direction;
		this.setX(x);
		this.setY(y);
		this.setScaleX(direction);
		midRetAdvance = new SpriteAnimation("Sprites/MidRetAdvance.png", this, new Duration(500), new int[] {0, 0, 13, 13, 0}, 1, 0, 0, 219, 200, direction, locked);
		midRetRetreat = new SpriteAnimation("Sprites/MidRetRetreat.png", this, new Duration(500), new int[] {0, 0, -13, -13}, 1, 0, 0, 219, 200, direction, locked);
		midLunge = new SpriteAnimation("Sprites/MidLunge.png", this, new Duration(650), new int[] {0, 0, 78, 30, 0}, 1, 0, 0, 219, 200, direction, locked);
	}
	
	public void lunge() {
		if(!locked.get()) {
			locked.set(true);
			midLunge.play();
		}
	}
	
	public void advance() {
		if(!locked.get()) {
			locked.set(true);
			System.out.println("once");
			midRetAdvance.play();
		}
	}
	
	public void retreat() {
		if(!locked.get()) {
			locked.set(true);
			System.out.println("once");
			midRetRetreat.play();
		}
	}
	
	public void extend() {
	}
	
	public void high() {
	}
	
	public void mid() {
	}
	
	public void low() {
	}
	
	
	//etc.
}
