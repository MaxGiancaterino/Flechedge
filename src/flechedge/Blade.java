package flechedge;

public class Blade extends Sprite {
	private int direction;
	
	public Blade(String filename, int direction) {
		super(filename);
		this.direction= direction;
	}
	
	public void advance() {
		addVelocity(Flechedge.speed*direction, 0);
	}
	
	public void retreat() {
		addVelocity(-Flechedge.speed*direction, 0);
	}
	
	public void high() {
		setPosition(getX(), 400);
	}
	
	public void mid() {
		setPosition(getX(), 425);
	}
	
	public void low() {
		setPosition(getX(), 450);
	}
	
}
