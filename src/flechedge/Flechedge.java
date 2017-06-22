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
	
	public double speed = 100;
	
	public void start(Stage stage) throws Exception {
		//set up scene
		Group root = new Group();
		Scene scene = new Scene(root);
		scene.setFill(Color.BISQUE);
		stage.setScene(scene);
		stage.setTitle("Flechedge");
		
		Canvas canvas = new Canvas(512, 512);
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
		
		
		
		//key handler
		Set<String> keyInputs = new HashSet<String>();
		EventHandler<KeyEvent> keyHandler = new KeyHandler(keyInputs);
		scene.setOnKeyPressed(keyHandler);
		scene.setOnKeyReleased(keyHandler);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//create sprites
		Sprite block = new Sprite();
		block.setImage("Sprites/Rectangle.png");
		block.render(gc);
		
		//create the command parser
		CommandParser parser = new CommandParser(block, speed, keyInputs, keyMap);
		
		new AnimationTimer() {
			
			private long lastNanoTime = System.nanoTime();
			
			public void handle(long currentNanoTime) {
				double elapsedTime = (currentNanoTime - lastNanoTime)
						/1000000000.0;
				lastNanoTime = currentNanoTime;
				
				//movement logic
				block.setVelocity(0, 0);
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
