package flechedge;

import java.util.HashMap;

import flechedge.Duelist.States;
import flechedge.Duelist.SubStates;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Blade extends Group {
	private ImageView iv = new ImageView();
	private int direction;
	private BladeAnimation parry, highRetLunge, midRetLunge, lowRetLunge, highExtLunge, midExtLunge, lowExtLunge,
	highRecover, midRecover, lowRecover;
	private Image idle;
	private Boolean parrying;
	
	public Blade(int direction, int x, int y) {
		this.direction = direction;
		this.parrying = false;
		this.setX(x);
		this.setY(y);
		this.getChildren().addAll(iv);
		
		highRetLunge = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(650), new int[] {0, 0, 25, -12, 78, -5, 35, 6, 0, 13}, 1, 0, 0, 140, 120, direction, false);
		midRetLunge = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(650), new int[] {0, 0, 25, -12, 78, -5, 30, 5, 0, 13}, 1, 0, 0, 140, 120, direction, false);
		lowRetLunge = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(650), new int[] {0, 0, 16, -33, 78, -2, 27, 5, 0, 13}, 1, 0, 0, 140, 120, direction, false);
		highExtLunge = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(575), new int[] {0, 0, 78, -5, 30, 6, 0, 14}, 1, 0, 0, 140, 120, direction, false);
		midExtLunge = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(575), new int[] {0, 0, 78, -5, 29, 5, 0, 13}, 1, 0, 0, 140, 120, direction, false);
		lowExtLunge = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(575), new int[] {0, 0, 78, -2, 31 , 5, 0, 11}, 1, 0, 0, 140, 120, direction, false);
		highRecover = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(650), new int[] {0, 0, -22, -10, -12, -5, -45, 13}, 1, 0, 0, 140, 120, direction, false);
		midRecover = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(650), new int[] {0, 0, -22, -10, -15, -5, -37, 14}, 1, 0, 0, 140, 120, direction, false);
		lowRecover = new BladeAnimation("Sprites/BladeStillV1.png", this, iv, new Duration(650), new int[] {0, 0, -22, -8, -20, -5, -20, 30}, 1, 0, 0, 140, 120, direction, false);
		parry = new BladeAnimation("Sprites/Parry.png", this, iv, new Duration(400), new int[] {0,0, 0,0, 0,0}, 1, 0, 0, 140, 120, direction, true, true);
		
		playIdle();
	}
	
	public void setX(int x) {
		iv.setX(x);
	}
	public void setY(int y) {
		iv.setY(y);
	}
	
	public void playIdle() {
		idle = new Image("Sprites/BladeStillV1.png");
		iv.setImage(idle);
		iv.setScaleX(direction);
		iv.setVisible(true);
	}
	
	public void parryingFalse() {
		parrying = false;
	}
	
	public void updatePosition(Duelist duelist) {
		HashMap<States, Boolean> state = duelist.getState();
		HashMap<SubStates, Boolean> subState = duelist.getSubState();
		
		int x = duelist.getX();
		int y = duelist.getY();
		
		
		if(state.get(States.LUNGE)) {
		}
		else if(state.get(States.FLECHE)) {
		}
		else if(state.get(States.RECOVER)) {
		}
		else if(state.get(States.LUNGED)) {
			if(direction==-1) {
				if(subState.get(SubStates.HIGHEXT)) {
					setX(x+185);
					setY(y);
				}
				else if(subState.get(SubStates.MIDEXT)) {
					setX(x+185);
					setY(y+9);
				}
				else if(subState.get(SubStates.LOWEXT)) {
					setX(x+181);
					setY(y+20);
				}
			}
			else {
				if(subState.get(SubStates.HIGHEXT)) {
					setX(x-106);
					setY(y);
				}
				else if(subState.get(SubStates.MIDEXT)) {
					setX(x-105);
					setY(y+9);
				}
				else if(subState.get(SubStates.LOWEXT)) {
					setX(x-102);
					setY(y+20);
				}
			}
		}
		else {
			if(direction==-1) {
				if(subState.get(SubStates.HIGHRET)) {
					setX(x+155);
					setY(y-2);
				}
				else if(subState.get(SubStates.MIDRET)) {
					setX(x+160);
					setY(y+8);
				}
				else if(subState.get(SubStates.LOWRET)) {
					setX(x+168);
					setY(y+37);
				}
				else if(subState.get(SubStates.HIGHEXT)) {
					setX(x+185);
					setY(y-15);
				}
				else if(subState.get(SubStates.MIDEXT)) {
					setX(x+186);
					setY(y-4);
				}
				else if(subState.get(SubStates.LOWEXT)){
					setX(x+180);
					setY(y+6);
				}
			}
			else {
				if(subState.get(SubStates.HIGHRET)) {
					setX(x-76);
					setY(y-2);
				}
				else if(subState.get(SubStates.MIDRET)) {
					setX(x-81);
					setY(y+8);
				}
				else if(subState.get(SubStates.LOWRET)) {
					setX(x-89);
					setY(y+37);
				}
				else if(subState.get(SubStates.HIGHEXT)) {
					setX(x-106);
					setY(y-15);
				}
				else if(subState.get(SubStates.MIDEXT)) {
					setX(x-107);
					setY(y-4);
				}
				else if(subState.get(SubStates.LOWEXT)){
					setX(x-101);
					setY(y+6);
				}
			}
		}
	}
	public void highRetLunge() {
		highRetLunge.play();
	}
	public void midRetLunge() {
		midRetLunge.play();
	}
	public void lowRetLunge() {
		lowRetLunge.play();
	}
	public void highExtLunge() {
		highExtLunge.play();
	}
	public void midExtLunge() {
		midExtLunge.play();
	}
	public void lowExtLunge() {
		lowExtLunge.play();
	}
	public void highRecover() {
		highRecover.play();
	}
	public void midRecover() {
		midRecover.play();
	}
	public void lowRecover() {
		lowRecover.play();
	}
	
	public void parry() {
		if(!parrying) {
			parrying = true;
			parry.play();
		}
	}
}
