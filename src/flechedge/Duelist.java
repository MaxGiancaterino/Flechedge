package flechedge;


import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Duelist extends Group {
	
	//Move: can extend, change lines, and parry
	//Ready: all the things
	//Lunge: literally nothing
	//Lunged: can recover, change lines
	public enum State {
		MOVE, READY, LUNGE, LUNGED, RECOVER; 
	}
	private State state;
	private ImageView top = new ImageView();
	private ImageView bot = new ImageView();
	private int direction;
	private SpriteAnimation retreat, advance, midRet, highRet, lowRet, midExt, highExt, lowExt, lunge, recover;
	private AtomicBoolean[] locks = new AtomicBoolean[] {};
	private AtomicBoolean locked = new AtomicBoolean();
	
	public Duelist(int direction, int x, int y) {
		state = State.READY;
		this.direction = direction;
		this.setX(x);
		this.setY(y);
		top.setScaleX(direction);
		bot.setScaleX(direction);
		this.getChildren().addAll(top, bot);
		//arms
		highRet = new SpriteAnimation("Sprites/HighRetAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		midRet = new SpriteAnimation("Sprites/MidRetAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		lowRet = new SpriteAnimation("Sprites/LowRetAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		highExt = new SpriteAnimation("Sprites/HighExtAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		midExt = new SpriteAnimation("Sprites/MidExtAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		lowExt = new SpriteAnimation("Sprites/LowExtAdvance.png", this, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		//legs
		advance = new SpriteAnimation("Sprites/MidRetAdvance.png", this, bot, new Duration(500), new int[] {0, 0, 13, 13, 0}, 1, 0, 100, 219, 100, direction);
		retreat = new SpriteAnimation("Sprites/Retreat.png", this, bot, new Duration(500), new int[] {0, 0, -13, -13}, 1, 0, 100, 219, 100, direction);
		lunge = new SpriteAnimation("Sprites/MidLunge.png", this, top, new Duration(650), new int[] {0, 0, 78, 30, 0}, 1, 0, 0, 219, 200, direction);
		recover = new SpriteAnimation("Sprites/MidRecover.png", this, top, new Duration(650), new int[] {0, -22, -15, -12}, 1, 0, 0, 219, 200, direction);
	}
	
	public void setX(double x) {
		top.setX(x);
		bot.setX(x);
	}
	
	public void setY(double y) {
		top.setY(y);
		bot.setY(y+100);
	}
	
	public void setState(State state) {
		this.state = state;
	}
	public State getState() {
		return state;
	}
	
	public void highRet() {
		if(state != State.LUNGE && state != State.RECOVER) {
			highRet.play();
		}
	}
	
	public void midRet() {
		if(state != State.LUNGE && state != State.RECOVER) {
			midRet.play();
		}
	}
	
	public void lowRet() {
		if(state != State.LUNGE && state != State.RECOVER) { 
			lowRet.play();
		}
	}
	
	public void lunge() {
		if(state == State.READY) {
			state = State.LUNGE;
			//locked.set(true);
			bot.setVisible(false);
			lunge.play();
			System.out.println("LUNGE");
		}
	}
	
	public void recover() {
		if(state==State.LUNGED) {
			state = State.RECOVER;
			recover.play();
			System.out.println("RECOVER");
		}
	}
	
	public void advance() {
		System.out.println("in advance()");
		if(state == State.READY) {
			System.out.println("in if(state.READY...");
			state = State.MOVE;
			bot.setVisible(true);
			advance.play();
			System.out.println("ADVANCE");
		}
	}
	
	public void retreat() {
		if(state == State.READY) {
			state = State.MOVE;
			bot.setVisible(true);
			retreat.play();
			System.out.println("RETREAT");
		}
	}
	
	public void extend() {
	}

}
