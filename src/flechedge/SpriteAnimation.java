package flechedge;

import flechedge.Duelist.States;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
	
	private final ImageView imageView;
	private final States actionType;
	private final String filename;
	private final int[] movement;
	private final int columns;
	private final int offsetX;
	private final int offsetY;
	private final int width;
	private final int height;
	private final int direction;
	private Duelist duelist;
	private int lastIndex = 0;

	public SpriteAnimation(String filename, Duelist duelist, States actionType, ImageView imageView, Duration duration, int[] movement, int columns, 
		int offsetX, int offsetY, int width, int height, int direction) {
		this.imageView = imageView;
		this.filename = filename;
		this.actionType = actionType;
		this.movement = movement;
		this.columns = columns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		this.duelist = duelist;
		this.direction = direction;
		setCycleDuration(duration);
		setInterpolator(Interpolator.LINEAR);
	}

	@Override
	public void play() {
		imageView.setImage(new Image(filename));
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		super.play();
	}
	
	@Override
	protected void interpolate(double k) {
		final int index = Math.min((int) (k * movement.length), movement.length - 1);
		if (index != lastIndex) {
			
			//x and y of new animation view
			int x = 0;
			x = (index / columns) * (width + 1) + offsetX; 
			final int y = (index % columns) * height + offsetY;
			imageView.setViewport(new Rectangle2D(x, y, width, height));
			
			//x of imageView
			duelist.setX(imageView.getX() + (-direction * movement[index]));
			
			lastIndex = index;
		}
		if(k==1){
			//I had to make moveGraph public for this, might be better just to make a getter method in duelist class
			States nextState = duelist.moveGraph.findNext(actionType);
			if(actionType==States.RECOVER) {
				duelist.changeState(actionType, false);
				duelist.playIdle();
			}
			else if(nextState != null) {
				//System.out.println("setting past state to false: "+actionType);
				//System.out.println("setting state to next: "+nextState);
				duelist.changeState(actionType, false);
				duelist.changeState(nextState, true);
			}
		}
	}
}
