package flechedge;

import flechedge.Duelist.States;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BladeAnimation extends Transition {
	
	private final ImageView imageView;
	private final String filename;
	private final int[] movement;
	private final int columns;
	private final int offsetX;
	private final int offsetY;
	private final int width;
	private final int height;
	private final int direction;
	private Blade blade;
	private final Boolean dynamic;
	private int lastIndex = 0;
	private final Boolean parry;

	
	//BladeAnimation "duration" variable is list of x,y coordinates. Values alternate. Eg. (3,5),(4,5) = 3,5,4,5
	public BladeAnimation(String filename, Blade blade, ImageView imageView, Duration duration, int[] movement, int columns, 
		int offsetX, int offsetY, int width, int height, int direction, Boolean dynamic) {
		this.imageView = imageView;
		this.filename = filename;
		this.movement = movement;
		this.columns = columns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		this.blade = blade;
		this.direction = direction;
		this.dynamic = dynamic;
		this.parry = false;
		setCycleDuration(duration);
		setInterpolator(Interpolator.LINEAR);
	}
	public BladeAnimation(String filename, Blade blade, ImageView imageView, Duration duration, int[] movement, int columns, 
			int offsetX, int offsetY, int width, int height, int direction, Boolean dynamic, Boolean parry) {
			this.imageView = imageView;
			this.filename = filename;
			this.movement = movement;
			this.columns = columns;
			this.offsetX = offsetX;
			this.offsetY = offsetY;
			this.width = width;
			this.height = height;
			this.blade = blade;
			this.direction = direction;
			this.dynamic = dynamic;
			this.parry = parry;
			setCycleDuration(duration);
			setInterpolator(Interpolator.LINEAR);
		}

	@Override
	public void play() {
		imageView.setImage(new Image(filename));
		//System.out.println(imageView.getX() + ","+ imageView.getY());
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		super.play();
	}
	
	@Override
	protected void interpolate(double k) {
		final int index = 2*Math.min((int)(k * Math.floor(movement.length/2)), (int)Math.floor(movement.length/2) - 1);
		if (index != lastIndex) {
			if(dynamic) {
				int anIndex = index/2;
				//x and y of new animation view
				System.out.println("index: "+anIndex);
				int x = 0;
				if (anIndex != 0) {
					x = (anIndex / columns) * (width + 1) + offsetX; 
				}
				final int y = (anIndex % columns) * height + offsetY;
				imageView.setViewport(new Rectangle2D(x, y, width, height));
			}
			
			//x of imageView
			blade.setX((int)(imageView.getX() + (-direction * movement[index])));
			blade.setY((int)(imageView.getY() + (movement[index+1])));
			lastIndex = index;
		}
		if(k==1 && parry) {
			blade.parryingFalse();
		}
	}
}
