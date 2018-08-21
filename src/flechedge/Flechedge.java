package flechedge;

import java.io.File;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import flechedge.CommandParser.BaseCommands;
import flechedge.Duelist.SubStates;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Flechedge extends Application {
	
	public static double speed = 100;
	public static int windowWidth = 1000;
	public static int windowHeight = 600;
	public boolean p1AttachBlade = true;
	public boolean p2AttachBlade = true;
	
	
	public void start(Stage stage) throws Exception {
		//make background
		ImageView background = new ImageView();
		background.setImage(new Image("Backgrounds/Flechedge_SpaceHanger_V1-1.png"));
		
		//create b1
		Blade b1 = new Blade(-1, 260, 357);
				
		//create b2
		Blade b2 = new Blade(1, 760, 357);
		
		//create p1
		Duelist p1 = new Duelist(b1, -1, 100, 350);
		p1.changeSubState(SubStates.MIDRET, true);
		
		//create p2
		Duelist p2 = new Duelist(b2, 1, 700, 350);
		p2.changeSubState(SubStates.MIDRET, true);
		
		
		
		//set up scene
		Group root = new Group(background, p1, p2, b1, b2);
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

		//media player
		File file = new File("src\\Audio\\Flechedge_Space.mp3");
		String fileString = file.toURI().toString();
		Media media = new Media(fileString); 
		final MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setAutoPlay(true);
		MediaView mediaView = new MediaView(mediaPlayer);
		root.getChildren().add(mediaView);
		
		
		//create the command parser
		CommandParser parser = new CommandParser(p1, p2, keyInputs, keyMap);
		
		new AnimationTimer() {
			
			private long lastNanoTime = System.nanoTime();
			
			public void handle(long currentNanoTime) {
				double elapsedTime = (currentNanoTime - lastNanoTime)
						/1000000000.0;
				lastNanoTime = currentNanoTime;
				
				//movement logic
				parser.parse();
				b1.updatePosition(p1);
				b2.updatePosition(p2);
				//System.out.println(p1.getState());
				//System.out.println(p1.getSubState());
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
			}
		}.start();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
