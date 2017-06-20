package flechedge;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Flechedge extends Application {
	
	public double speed = 100;
	
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		scene.setFill(Color.BISQUE);
		stage.setScene(scene);
		
		Canvas canvas = new Canvas(512, 512);
		root.getChildren().add(canvas);
		
		
		ArrayList<String> input = new ArrayList<String>();
		
		EventHandler<KeyEvent> keyHandler = new KeyHandler();
		scene.setOnKeyPressed(keyHandler);
		scene.setOnKeyReleased(keyHandler);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Sprite block = new Sprite();
		block.setImage("Sprites/Rectangle.png");
		block.render(gc);
		
		
		LongValue lastNanoTime = new LongValue(System.nanoTime());
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double elapsedTime = (currentNanoTime-lastNanoTime.value)/1000000000;
				lastNanoTime.value = currentNanoTime;
				
				//movement
				block.setVelocity(0, 0);
				if(input.contains("UP")) {
					block.addVelocity(0, -speed);
				}
				if(input.contains("DOWN")) {
					block.addVelocity(0, speed);
				}
				if(input.contains("LEFT")) {
					block.addVelocity(-speed, 0);
				}
				if(input.contains("RIGHT")) {
					block.addVelocity(speed, 0);
				}
				
			}
		}.start();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
