package flechedge;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import flechedge.CommandParser.BaseCommands;
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
	
	public static double speed = 100;
	public static int windowWidth = 1000;
	public static int windowHeight = 600;
	public boolean p1AttachBlade = true;
	public boolean p2AttachBlade = true;
	
	
	public void start(Stage stage) throws Exception {
		//set up scene
		Group root = new Group();
		Scene scene = new Scene(root);
		scene.setFill(Color.BISQUE);
		stage.setScene(scene);
		stage.setTitle("Flechedge");
		
		Canvas canvas = new Canvas(windowWidth, windowHeight);
		root.getChildren().add(canvas);
		
		//makes map between basic controls and custom controls
		Map<BaseCommands, String> keyMap = new EnumMap<>(BaseCommands.class);
		keyMap.put(BaseCommands.P1_MOD, "W");
		keyMap.put(BaseCommands.P1_RET, "A");
		keyMap.put(BaseCommands.P1_PAR, "S");
		keyMap.put(BaseCommands.P1_ADV, "D");
		keyMap.put(BaseCommands.P1_HIGH, "C");
		keyMap.put(BaseCommands.P1_MID, "V");
		keyMap.put(BaseCommands.P1_LOW, "B");
		keyMap.put(BaseCommands.P1_EXT, "SPACE");
		keyMap.put(BaseCommands.P1_THROW, "F");
		
		keyMap.put(BaseCommands.P2_MOD, "UP");
		keyMap.put(BaseCommands.P2_RET, "RIGHT");
		keyMap.put(BaseCommands.P2_PAR, "DOWN");
		keyMap.put(BaseCommands.P2_ADV, "LEFT");
		keyMap.put(BaseCommands.P2_HIGH, "QUOTE");
		keyMap.put(BaseCommands.P2_MID, "SEMICOLON");
		keyMap.put(BaseCommands.P2_LOW, "L");
		keyMap.put(BaseCommands.P2_EXT, "CONTROL");
		keyMap.put(BaseCommands.P2_THROW, "P");
		
		
		
		//key handler
		Set<String> keyInputs = new HashSet<String>();
		EventHandler<KeyEvent> keyHandler = new KeyHandler(keyInputs);
		scene.setOnKeyPressed(keyHandler);
		scene.setOnKeyReleased(keyHandler);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//create p1
		Duelist p1 = new Duelist("Sprites/Rectangle.png", 1);
		p1.setPosition(50, 350);
		p1.render(gc);
		
		//create p2
		Duelist p2 = new Duelist("Sprites/Rectangle.png", -1);
		p2.setPosition(890, 350);
		p2.render(gc);
		
		//create p1 blade
		Blade b1 = new Blade("Sprites/Blade.png", 1);
		b1.setPosition(110, 400);
		b1.render(gc);
		
		//create p2 blade
		Blade b2 = new Blade("Sprites/Blade.png", -1);
		b2.setPosition(730, 400);
		b2.render(gc);
		
		
		//create the command parser
		CommandParser parser = new CommandParser(p1, b1, p2, b2, keyInputs, keyMap);
		
		new AnimationTimer() {
			
			private long lastNanoTime = System.nanoTime();
			
			public void handle(long currentNanoTime) {
				double elapsedTime = (currentNanoTime - lastNanoTime)
						/1000000000.0;
				lastNanoTime = currentNanoTime;
				
				//movement logic
				p1.setVelocity(0, 0);
				b1.setVelocity(0, 0);
				p2.setVelocity(0, 0);
				b2.setVelocity(0, 0);
				parser.parse();
				/*if(keyInputs.contains("UP")) {
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
				*/
				if(p1AttachBlade) {
					b1.setPosition(p1.getX()+60, b1.getY());
				}
				if(p2AttachBlade) {
					b2.setPosition(p2.getX()-160, b2.getY());
				}

				p1.update(elapsedTime);
				b1.update(elapsedTime);
				p2.update(elapsedTime);
				b2.update(elapsedTime);

				//render
				gc.clearRect(0, 0, windowWidth, windowHeight);
				p1.render(gc);
				b1.render(gc);
				p2.render(gc);
				b2.render(gc);
				
			}
		}.start();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
