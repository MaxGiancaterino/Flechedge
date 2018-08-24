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
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Flechedge extends Application {
	
	public static int windowWidth = 1000;
	public static int windowHeight = 600;
	public boolean p1AttachBlade = true;
	public boolean p2AttachBlade = true;
	
	public boolean p1Touch, p2Touch, p1ParryChance, p2ParryChance, pointsUpdated, p1ParrySuccess, p2ParrySuccess, p1ParryGo, p2ParryGo;
	public int p1Score, p2Score;
	public long scoreTime, parryTime, creditTime;
	public enum gameStates {
		PLAYING, POINTSCORED, MENU, MATCHCOMPLETE, CREDITS;
	}
	public gameStates gameState;
	public Text p1ScoreText;
	public Text p2ScoreText;
	public Text statusText;
	
	public Text menuTitle;
	public Text playButtonText;
	public Text creditsButtonText;
	
	
	public void start(Stage stage) throws Exception {
		//initialize variables
		gameState = gameStates.MENU;
		p1Touch = false;
		p2Touch = false;
		p1ParryChance = false;
		p2ParryChance = false;
		p1ParrySuccess = false;
		p2ParrySuccess = false;
		p1ParryGo = false;
		p2ParryGo = false;
		p1Score = 0;
		p2Score = 0;
		scoreTime = 0;
		parryTime = 0;
		creditTime = 0;
		
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
		p1.playIdle();
		
		//create p2
		Duelist p2 = new Duelist(b2, 1, 700, 350);
		p2.changeSubState(SubStates.MIDRET, true);
		p2.playIdle();
		
		//create scoreboard
		//TODO: include better font (probably need to use OpenFont or something like it)
		//TODO: draw a scoreboard gui
		ImageView scoreboard = new ImageView();
		p1ScoreText = new Text();
		p2ScoreText = new Text();
		p1ScoreText.setFont(new Font("Helvetica", 40));
		p2ScoreText.setFont(new Font("Helvetica", 40));
		p1ScoreText.setFill(Color.IVORY);
		p2ScoreText.setFill(Color.IVORY);
		p1ScoreText.setVisible(true);
		p2ScoreText.setVisible(true);
		p1ScoreText.relocate(395, 0);
		p2ScoreText.relocate(595, 0);
		
		//create announcement text
		statusText = new Text();
		statusText.setFont(Font.font("Helvetica", FontWeight.LIGHT, FontPosture.ITALIC, 40));
		statusText.setFill(Color.RED);
		statusText.setVisible(false);
		statusText.relocate(350, 200);
		
		//create menu text
		menuTitle = new Text();
		menuTitle.setFont(Font.font("Helvetica", FontWeight.LIGHT, FontPosture.ITALIC, 40));
		menuTitle.setText("Flechedge");
		menuTitle.setFill(Color.RED);
		menuTitle.relocate(425, 50);
		
		playButtonText = new Text();
		playButtonText.setFont(Font.font("Helvetica", 20));
		playButtonText.setText("Play");
		playButtonText.setFill(Color.FLORALWHITE);
		playButtonText.relocate(475, 200);
		playButtonText.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				menuTitle.setVisible(false);
				playButtonText.setVisible(false);
				creditsButtonText.setVisible(false);
				background.setImage(new Image("Backgrounds/Flechedge_SpaceHanger_V1-1.png"));
				p1.setVisible(true);
				p2.setVisible(true);
				b1.setVisible(true);
				b2.setVisible(true);
				p1ScoreText.setVisible(true);
				p2ScoreText.setVisible(true);
				gameState = gameStates.PLAYING;
			}
		});
		playButtonText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				playButtonText.setFill(Color.GRAY);
			}
		});
		playButtonText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				playButtonText.setFill(Color.FLORALWHITE);
			}
		});
		
		creditsButtonText = new Text();
		creditsButtonText.setFont(Font.font("Helvetica", 20));
		creditsButtonText.setText("Credits");
		creditsButtonText.setFill(Color.FLORALWHITE);
		creditsButtonText.relocate(475, 300);
		creditsButtonText.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				creditTime = System.nanoTime();
				menuTitle.setVisible(false);
				playButtonText.setVisible(false);
				creditsButtonText.setVisible(false);
				gameState = gameStates.CREDITS;
			}
		});
		creditsButtonText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				creditsButtonText.setFill(Color.GRAY);
			}
		});
		creditsButtonText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				creditsButtonText.setFill(Color.FLORALWHITE);
			}
		});
		
		//create credit roll
		ImageView credits = new ImageView();
		credits.setImage(new Image("Backgrounds/FlechedgeCredits1.png"));
		credits.setVisible(false);
		
		//set up scene
		Group root = new Group(background, p1, p2, b1, b2);
		Scene scene = new Scene(root);
		scene.setFill(Color.BISQUE);
		stage.setScene(scene);
		stage.setTitle("Flechedge");
		
		Canvas canvas = new Canvas(windowWidth, windowHeight);
		root.getChildren().add(canvas);
		root.getChildren().add(p1ScoreText);
		root.getChildren().add(p2ScoreText);
		root.getChildren().add(statusText);
		root.getChildren().add(menuTitle);
		root.getChildren().add(playButtonText);
		root.getChildren().add(creditsButtonText);
		root.getChildren().add(credits);
		
		
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
				
				p1ScoreText.setText(Integer.toString(p1Score));
				p2ScoreText.setText(Integer.toString(p2Score));
				if(gameState == gameStates.PLAYING) {
					//check collisions
					checkCollisions(p1, p2, b1, b2);
					b1.setParryChance(p1ParryChance);
					b2.setParryChance(p2ParryChance);
					
					//movement logic
					if(p1ParryGo && p2ParryGo) {
						parser.parse(-1); //no parsing
					}
					else if(p1ParryGo) {
						parser.parse(1); //only p1 active
					}
					else if(p2ParryGo) {
						parser.parse(2); //only p2 active
					}
					else {
						parser.parse(0);
					}
					
					b1.updatePosition(p1);
					b2.updatePosition(p2);
					
					//react to collisions
					
					p1ParrySuccess = b1.getParrySuccess();
					p2ParrySuccess = b2.getParrySuccess();

					//Check touch conditions
					if(p1Touch && !p2ParryGo) {
						scoreTime = lastNanoTime;
						if(p1Score==1) {
							gameState = gameStates.MATCHCOMPLETE;
						}
						else {
						gameState = gameStates.POINTSCORED;
						}
					}
					if(p2Touch && !p1ParryGo) {
						scoreTime = lastNanoTime;
						if(p2Score==1) {
							gameState = gameStates.MATCHCOMPLETE;
						}
						else {
							gameState = gameStates.POINTSCORED;
						}
					}
					
					//check conditions for parry
					if(p1ParrySuccess && !b1.getParrying() && !p1ParryGo) {
						parryTime = lastNanoTime;
						p1ParryGo = true;
						System.out.println("parryGo set!");
					}
					if(p2ParrySuccess && !b2.getParrying() && !p2ParryGo) {
						parryTime = lastNanoTime;
						p2ParryGo = true;
					}
					
					//continually set blade color if parry
					if(p1ParryGo) {
						b1.parryColor();
					}
					if(p2ParryGo) {
						b2.parryColor();
					}
					
					//end parry time
					if(p1ParryGo && currentNanoTime-parryTime>=1000000000.0) {
						System.out.println((currentNanoTime-parryTime)/1000000000.0);
						b1.resetParrySuccess();
						b1.resetColor();
						p1ParryGo = false;
						System.out.println("reset!");
					}
					if(p2ParryGo && currentNanoTime-parryTime>=1000000000.0) {
						b2.resetParrySuccess();
						b2.resetColor();
						p2ParryGo = false;
					}
				}
				else if(gameState == gameStates.POINTSCORED) {
					if(!pointsUpdated) {
						//update scores
						if(p1Touch) {
							p1Score+=1;
							statusText.setText("Touché, player 1!");
						}
						if(p2Touch) {
							p2Score+=1;
							statusText.setText("Touché, player 2!");
						}
						statusText.setVisible(true);
						pointsUpdated = true;
					}
					
					//change blade color
					if(p1Touch) {
						b1.touch();
					}
					if(p2Touch) {
						b2.touch();
					}
					
					b1.updatePosition(p1);
					b2.updatePosition(p2);
					

					//play score noise
					//wait
					if(currentNanoTime-scoreTime>=3*1000000000.0) {
						//reset
						reset(p1,p2,b1,b2);
						statusText.setVisible(false);
						gameState = gameStates.PLAYING;
					}
				}
				else if(gameState == gameStates.MATCHCOMPLETE) {
					if(!pointsUpdated) {
						//update scores
						if(p1Touch) {
							p1Score+=1;
							statusText.setText("Touché, player 1!");
						}
						if(p2Touch) {
							p2Score+=1;
							statusText.setText("Touché, player 2!");
						}
						statusText.setVisible(true);
						pointsUpdated = true;
					}
					
					//change blade color
					if(p1Touch) {
						b1.touch();
					}
					if(p2Touch) {
						b2.touch();
					}
					//print victory message
					if(p1Score==2 && p2Score==2) {
						statusText.relocate(470, 200);
						statusText.setText("Draw!");
					}
					else if(p1Score==2) {
						statusText.relocate(370, 200);
						statusText.setText("Player 1 victory!");
					}
					else {
						statusText.relocate(370, 200);
						statusText.setText("Player 2 victory!");
					}
					if(currentNanoTime-scoreTime>=3*1000000000.0) {
						//reset
						reset(p1,p2,b1,b2);
						statusText.setVisible(false);
						p1ScoreText.setVisible(false);
						p2ScoreText.setVisible(false);
						p1.setVisible(false);
						p2.setVisible(false);
						b1.setVisible(false);
						b2.setVisible(false);
						p1Score = 0;
						p2Score = 0;
						gameState = gameState.MENU;
					}
				}
				else if(gameState == gameStates.MENU) {
					//TODO: fix up menu appearance (add buttons etc)
					background.setImage(new Image("Backgrounds/Flechedge_Menu_V1.png"));
					menuTitle.setVisible(true);
					playButtonText.setVisible(true);
					creditsButtonText.setVisible(true);
					p1.setVisible(false);
					p2.setVisible(false);
					b1.setVisible(false);
					b2.setVisible(false);
					statusText.setVisible(false);
					p1ScoreText.setVisible(false);
					p2ScoreText.setVisible(false);
				}
				else if(gameState == gameStates.CREDITS) {
					//TODO: make credits scroll
					background.setImage(new Image("Backgrounds/Flechedge_Menu_V1.png"));
					credits.setVisible(true);
					long time = System.nanoTime();
					if(time-creditTime >= 2*1000000000.0 && time-creditTime <= 53*1000000000.0) {
						credits.setY(credits.getY()-.5);
					}
					if(time-creditTime > 57*1000000000.0) {
						gameState = gameStates.MENU;
						credits.setVisible(false);
					}
				}
			}
		}.start();
		stage.show();
	}
	
	public void checkCollisions(Duelist p1, Duelist p2, Blade b1, Blade b2) {
		//blade-duelist collisions
		if(b1.getX()+126>=p2.getX()+110) {
			System.out.println("p1 touche!");
			p1Touch = true;
		}
		if(b2.getX()+14<=p1.getX()+110) {
			System.out.println("p2 touche!");
			p2Touch = true;
		}
		//blade-blade collisions
		if(b1.getX()+126>=b2.getX()+14 && (b1.getY()>=b2.getY() && b1.getY()<=b2.getY()+14)) {
			p1ParryChance = true;
		}
		else {
			p1ParryChance = false;
		}
		if(b1.getX()+126>=b2.getX()+14 && (b2.getY()>=b1.getY() && b2.getY()<=b1.getY()+14)) {
			p2ParryChance = true;
		}
		else {
			p2ParryChance = false;
		}
	}
	
	public void reset(Duelist p1, Duelist p2, Blade b1, Blade b2) {
		b1.resetColor();
		b2.resetColor();
		p1.setX(100);
		p2.setX(700);
		b1.setX(260);
		b1.setY(357);
		b2.setX(760);
		b2.setY(357);
		p1.changeSubState(SubStates.MIDRET, true);
		p2.changeSubState(SubStates.MIDRET, true);
		p1.resetState();
		p2.resetState();
		p1.playIdle();
		p2.playIdle();
		p1ParryGo = false;
		p2ParryGo = false;
		p1Touch = false;
		p2Touch = false;
		p1ParryChance = false;
		p2ParryChance = false;
		p1ParrySuccess = false;
		p2ParrySuccess = false;
		pointsUpdated = false;
		statusText.setVisible(false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
