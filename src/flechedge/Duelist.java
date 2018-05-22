package flechedge;

public class Duelist extends Sprite{
	private int direction;

	
	public Duelist(String filename, double reqWidth, double reqHeight, int direction){
		super(filename, reqWidth, reqHeight);
		this.direction = direction;
	}
	
	public Duelist(String filename, int direction){
		super(filename);
		this.direction = direction;
	}
	
	public void advance() {
		addVelocity(Flechedge.speed*direction, 0);
	}
	
	public void retreat() {
		addVelocity(-Flechedge.speed*direction, 0);
	}
	
	public void lunge() {
		this.addPosition(10*direction, 0);
	}
}
