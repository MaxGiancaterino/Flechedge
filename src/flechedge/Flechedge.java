package flechedge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
		stage.setTitle("Flechedge");
		
		Canvas canvas = new Canvas(512, 512);
		root.getChildren().add(canvas);
		
		
		Set<String> keyInputs = new HashSet<String>();
		
		EventHandler<KeyEvent> keyHandler = new KeyHandler(keyInputs);
		scene.setOnKeyPressed(keyHandler);
		scene.setOnKeyReleased(keyHandler);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Sprite block = new Sprite();
		block.setImage("Sprites/Rectangle.png");
		block.render(gc);
		
		new AnimationTimer() {
			
			private long lastNanoTime = System.nanoTime();
			
			public void handle(long currentNanoTime) {
				double elapsedTime = (currentNanoTime - lastNanoTime)
						/1000000000.0;
				lastNanoTime = currentNanoTime;
				
				//movement
				block.setVelocity(0, 0);
				if(keyInputs.contains("UP")) {
					block.addVelocity(0, -speed);
				}
				if(keyInputs.contains("DOWN")) {
					block.addVelocity(0, speed);
				}
				if(keyInputs.contains("LEFT")) {
					block.addVelocity(-speed, 0);
				}
				if(keyInputs.contains("RIGHT")) {
					block.addVelocity(speed, 0);
				}
				
				block.update(elapsedTime);
				
				//render
				gc.clearRect(0, 0, 512, 512);
				block.render(gc);
				
			}
		}.start();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
