package flechedge;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
