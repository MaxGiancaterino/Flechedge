package flechedge;

import java.util.ArrayList;

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
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
