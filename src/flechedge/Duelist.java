package flechedge;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Duelist extends Group {
	
	public enum States {
		LEGIDLE, ARMIDLE, STEP, ARMMOVE, LUNGE, FLECHE, LUNGED, RECOVER;
	}
	public enum SubStates {
		HIGHEXT, MIDEXT, LOWEXT, HIGHRET, MIDRET, LOWRET;
	}
	//moveflow:
	//state is hashmap indicating which state(s) the duelist is in
	private HashMap<States, Boolean> state;
	private HashMap<SubStates, Boolean> subState;
	//get edges between States from moveGraph to find possible moves from that State
	public MoveGraph moveGraph;
	private SubMoveGraph subMoveGraph;
	
	private ImageView top = new ImageView();
	private ImageView bot = new ImageView();
	private int direction;
	private SpriteAnimation retreat, advance, midRet, highRet, lowRet, midExt, highExt, lowExt, lunge, recover;
	
	public Duelist(int direction, int x, int y) {
		state = new HashMap<States, Boolean>();
		//using a hashmap for subState is not ideal because you only can be in one substate at a time. just using an enum would be good
		//but we need pass by reference, soo...
		subState = new HashMap<SubStates, Boolean>();
		state.put(States.LEGIDLE, false);
		state.put(States.STEP, false);
		state.put(States.ARMIDLE, true);
		state.put(States.ARMMOVE, false);
		state.put(States.LUNGE, false);
		state.put(States.FLECHE, false);
		state.put(States.LUNGED, false);
		state.put(States.RECOVER, false);
		subState.put(SubStates.HIGHEXT, false);
		subState.put(SubStates.MIDEXT, false);
		subState.put(SubStates.LOWEXT, false);
		subState.put(SubStates.HIGHRET, false);
		subState.put(SubStates.MIDRET, false);
		subState.put(SubStates.LOWRET, false);
		moveGraph = new MoveGraph();
		subMoveGraph = new SubMoveGraph();
		
		this.direction = direction;
		this.setX(x);
		this.setY(y);
		top.setScaleX(direction);
		bot.setScaleX(direction);
		this.getChildren().addAll(top, bot);
		//arms
		highRet = new SpriteAnimation("Sprites/HighRetAdvance.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		midRet = new SpriteAnimation("Sprites/MidRetAdvance.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		lowRet = new SpriteAnimation("Sprites/LowRetAdvance.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		highExt = new SpriteAnimation("Sprites/HighExtAdvance.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		midExt = new SpriteAnimation("Sprites/MidExtAdvance.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		lowExt = new SpriteAnimation("Sprites/LowExtAdvance.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0,0}, 1, 0, 0, 219, 100, direction);
		//legs
		advance = new SpriteAnimation("Sprites/MidRetAdvance.png", this, States.STEP, bot, new Duration(500), new int[] {0, 0, 13, 13, 0}, 1, 0, 100, 219, 100, direction);
		retreat = new SpriteAnimation("Sprites/Retreat.png", this, States.STEP, bot, new Duration(500), new int[] {0, 0, -13, -13}, 1, 0, 100, 219, 100, direction);
		lunge = new SpriteAnimation("Sprites/MidLunge.png", this, States.LUNGE, top, new Duration(650), new int[] {0, 0, 78, 30, 0}, 1, 0, 0, 219, 200, direction);
		recover = new SpriteAnimation("Sprites/MidRecover.png", this, States.RECOVER, top, new Duration(650), new int[] {0, -22, -15, -12}, 1, 0, 0, 219, 200, direction);
	}
	
	public void setX(double x) {
		top.setX(x);
		bot.setX(x);
	}
	
	public void setY(double y) {
		top.setY(y);
		bot.setY(y+100);
	}
	
	public void changeState(States key, Boolean value) {
		state.replace(key, value);
	}
	
	public void resetSubState() {
		for(SubStates key : subState.keySet()) {
			subState.put(key, false);
		}
	}
	
	//this is a small problem. pretty sure get functions should be read-only, but this isn't
	public HashMap<States, Boolean> getState() {
		return state;
	}
	
	//TODO: these line changes need cases for lunged linechange
	//high retract
	public void highRet() {
		if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.HIGHRET)) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			subState.put(SubStates.HIGHRET, true);
			highRet.play();
		}
	}
	
	public void midRet() {
		if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.MIDRET)) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			subState.put(SubStates.MIDRET, true);
			midRet.play();
		}
	}
	
	public void lowRet() {
		if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.LOWRET)) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			subState.put(SubStates.LOWRET, true);
			lowRet.play();
		}
	}
	
	public void lunge() {
		//TODO: add all the animations
		if(moveGraph.check(state, States.LUNGE)) {
			state.put(States.LUNGE, true);
			if(subState.get(SubStates.HIGHEXT)) {
			}
			else if(subState.get(SubStates.MIDEXT)) {
			}
			else if(subState.get(SubStates.LOWEXT)) {
			}
			else if(subState.get(SubStates.HIGHRET)) {
				resetSubState();
				subState.put(SubStates.HIGHEXT, true);
			}
			else if(subState.get(SubStates.MIDRET)) {
				resetSubState();
				subState.put(SubStates.MIDEXT, true);
				lunge.play();
			}
			else if(subState.get(SubStates.LOWRET)) {
				resetSubState();
				subState.put(SubStates.LOWEXT, true);
			}
			else {
				System.out.println("no substates were true");
		}
		}
	}
	
	//TODO: extension over other lines. recover animation needs to end in extension + worry about setting bot visible
	public void recover() {
		if(moveGraph.check(state, States.RECOVER) && subMoveGraph.check(subState, SubStates.LOWRET)) {
			state.put(States.RECOVER, true);
			recover.play();
		}
	}
	
	public void advance() {
		if(moveGraph.check(state, States.STEP)) {
			state.put(States.STEP, true);
			bot.setVisible(true);
			advance.play();
		}
	}
	
	public void retreat() {
		if(moveGraph.check(state, States.STEP)) {
			state.put(States.STEP, true);
			bot.setVisible(true);
			retreat.play();
		}
	}
	
	public void extend() {
	}

}
