package flechedge;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Sprite	 {
	private Image image;
	private double positionX;
	private double positionY;
	private double velocityX;
	private double velocityY;
	private double width;
	private double height;
	
	public Sprite(){
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
	}
	
	public void setImage(String filename) {
		Image i = new Image(filename);
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}
	
	public void setPosition(int x, int y) {
		positionX = x;
		positionY = y;
	}
	
	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}
	
	public void addVelocity(double x, double y) {
		velocityX += x;
		velocityY += y;
	}
	
	public double getX(){
		return positionX;
	}
	
	public double getY() {
		return positionY;
	}
	
	public double getVX() {
		return this.velocityX;
	}
	
	public double getVY() {
		return this.velocityY;
	}
	
	public void update(double time) {
		positionX += velocityX*time;
		System.out.println(positionX+"||"+velocityX*time);
		positionY += velocityY*time;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX,positionY,width,height);
	}
	
	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	
	public String toString() {
		return "Position: [" +positionX+"c" +positionY+"]" + "Velocity: [" +
	velocityX +","+velocityY+"]";
	}

}