package flechedge;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
	
	private final ImageView imageView;
	private Image[] frames;
	
	public SpriteAnimation (ImageView imageView, Image[] frames, Duration duration) {
		this.imageView = imageView;
		this.frames = frames;
		setCycleDuration(duration);
		setInterpolator(Interpolator.LINEAR);
	}
	
	@Override
	protected void interpolate(double frac) {
		final int index = (int) frac * (frames.length - 1);
		imageView.setImage(frames[index]);
	}
}
