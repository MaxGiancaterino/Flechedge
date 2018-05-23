package flechedge;


import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Duelist extends Group {
	
	//Move: can extend, change lines, and parry
	//Ready: all the things
	//Lunge: literally nothing
	public enum State {
		MOVE, READY, LUNGE,      
	}
	private ImageView top = new ImageView();
	private ImageView bot = new ImageView();
	private int direction;
	private SpriteAnimation retreat, advance, midRet, highRet, lowRet, midRetLunge, midRecover; 
	private AtomicBoolean locked = new AtomicBoolean();
	
	public Duelist(int direction, int x, int y) {
		this.direction = direction;
		this.setX(x);
		this.setY(y);
		top.setScaleX(direction);
		bot.setScaleX(direction);
		this.getChildren().addAll(top, bot);
		highRet = new SpriteAnimation("Sprites/HighRetAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction, locked);
		midRet = new SpriteAnimation("Sprites/MidRetAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction, locked);
		lowRet = new SpriteAnimation("Sprites/LowRetAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction, locked);
		//legs
		advance = new SpriteAnimation("Sprites/MidRetAdvance.png", this, bot, new Duration(500), new int[] {0, 0, 13, 13, 0}, 1, 0, 100, 219, 100, direction, locked);
		retreat = new SpriteAnimation("Sprites/Retreat.png", this, bot, new Duration(500), new int[] {0, 0, -13, -13}, 1, 0, 100, 219, 100, direction, locked);
		midRetLunge = new SpriteAnimation("Sprites/MidLunge.png", this, top, new Duration(650), new int[] {0, 0, 78, 30, 0}, 1, 0, 0, 219, 200, direction, locked);
		midRecover = new SpriteAnimation("Sprites/MidRecover.png", this, top, new Duration(650), new int[] {0, 0, -30, -12}, 1, 0, 0, 219, 200, direction, locked);
	}
	
	public void setX(double x) {
		top.setX(x);
		bot.setX(x);
	}
	
	public void setY(double y) {
		top.setY(y);
		bot.setY(y+100);
	}
	
	public void highRet() {
		highRet.play();
	}
	
	public void midRet() {
		midRet.play();
	}
	
	public void lowRet() {
		lowRet.play();
	}
	
	public void lunge() {
		if(!locked.get()) {
			locked.set(true);
			bot.setVisible(false);
			midRetLunge.play();
			System.out.println("LUNGE");
		}
	}
	
	public void advance() {
		if(!locked.get()) {
			locked.set(true);
			//System.out.println("once");
			advance.play();
			System.out.println("ADVANCE");
		}
	}
	
	public void retreat() {
		if(!locked.get()) {
			locked.set(true);
			//System.out.println("once");
			retreat.play();
			System.out.println("RETREAT");
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
}
