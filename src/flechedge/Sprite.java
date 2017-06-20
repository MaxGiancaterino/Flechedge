package flechedge;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sprite {
	private Circle circle;
	private double positionX;
	private double positionY;
	private double velocityX;
	private double velocityY;
	private double radius;
	
	public Sprite(){
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
	}
	
	public void setCircle(Circle c){
		circle = c;
		radius = c.getRadius();
	}
	
	public void setColor(Color color) {
		circle.setFill(color);
	}
	
	public void setPosition(double x, double y) {
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
	
	public double getRadius() {
		return circle.getRadius();
	}
	
	public double getVX() {
		return this.velocityX;
	}
	
	public double getVY() {
		return this.velocityY;
	}
	
	public void update(double time) {
		positionX += velocityX*time;
		positionY += velocityY*time;
	}
	
	public void render(GraphicsContext gc) {
		gc.setFill(circle.getFill());
		gc.fillOval(positionX, positionY, radius, radius);
	}
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX,positionY,radius,radius);
	}
	
	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	
	public String toString() {
		return "Position: [" +positionX+"c" +positionY+"]" + "Velocity: [" +
	velocityX +","+velocityY+"]";
	}

}
