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
	private SpriteAnimation retreat, advance, midRet, highRet, lowRet, midExt, highExt, lowExt, lungedHigh, lungedMid, lungedLow, 
	midRetLunge, highRetLunge, lowRetLunge, highRecover, midRecover, lowRecover, botIdle;
	
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
		lungedHigh = new SpriteAnimation("Sprites/HighRecover.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0}, 1, 0, 0, 219, 200, direction);
		lungedMid = new SpriteAnimation("Sprites/MidRecover.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0}, 1, 0, 0, 219, 200, direction);
		lungedLow = new SpriteAnimation("Sprites/LowRecover.png", this, States.ARMMOVE, top, new Duration(300), new int[] {0}, 1, 0, 0, 219, 200, direction);
		//legs
		advance = new SpriteAnimation("Sprites/MidRetAdvance.png", this, States.STEP, bot, new Duration(500), new int[] {0, 0, 13, 13, 0}, 1, 0, 100, 219, 100, direction);
		retreat = new SpriteAnimation("Sprites/Retreat.png", this, States.STEP, bot, new Duration(500), new int[] {0, 0, -13, -13}, 1, 0, 100, 219, 100, direction);
		botIdle = new SpriteAnimation("Sprites/Retreat.png", this, States.STEP, bot, new Duration(500), new int[] {0}, 1, 0, 100, 219, 100, direction);
		//special
		highRetLunge = new SpriteAnimation("Sprites/HighRetLunge.png", this, States.LUNGE, top, new Duration(650), new int[] {0, 0, 78, 30, 0}, 1, 0, 0, 219, 200, direction);
		midRetLunge = new SpriteAnimation("Sprites/MidRetLunge.png", this, States.LUNGE, top, new Duration(650), new int[] {0, 0, 78, 30, 0}, 1, 0, 0, 219, 200, direction);
		lowRetLunge = new SpriteAnimation("Sprites/LowRetLunge.png", this, States.LUNGE, top, new Duration(650), new int[] {0, 0, 78, 30, 0}, 1, 0, 0, 219, 200, direction);
		highRecover = new SpriteAnimation("Sprites/HighRecover.png", this, States.RECOVER, top, new Duration(650), new int[] {0, -22, -15, -12}, 1, 0, 0, 219, 200, direction);
		midRecover = new SpriteAnimation("Sprites/MidRecover.png", this, States.RECOVER, top, new Duration(650), new int[] {0, -22, -15, -12}, 1, 0, 0, 219, 200, direction);
		lowRecover = new SpriteAnimation("Sprites/LowRecover.png", this, States.RECOVER, top, new Duration(650), new int[] {0, -22, -15, -12}, 1, 0, 0, 219, 200, direction);
		
		botIdle.play();
		midRet.play();
	}
	
	public void setX(double x) {
		top.setX(x);
		bot.setX(x);
	}
	
	public void setY(double y) {
		top.setY(y);
		bot.setY(y+100);
	}
	
	public void playIdle() {
		bot.setVisible(true);
		botIdle.play();
		if(subState.get(SubStates.HIGHRET)) {
			highRet.play();
		}
		else if(subState.get(SubStates.MIDRET)) {
			midRet.play();
		}
		else if(subState.get(SubStates.LOWRET)) {
			lowRet.play();
		}
	}
	
	public void changeState(States key, Boolean value) {
		state.replace(key, value);
	}
	public void changeSubState(SubStates key, Boolean value) {
		resetSubState();
		subState.replace(key, value);
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
	public HashMap<SubStates, Boolean> getSubState() {
		return subState;
	}
	
	//TODO: these line changes need cases for lunged linechange
	//high retract
	public void lineChangeHigh() {
		if(!state.get(States.LUNGED) && moveGraph.check(state, States.ARMMOVE) && 
				(subState.get(SubStates.LOWRET) || subState.get(SubStates.MIDRET))) {
			System.out.println("retHigh");
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE, false);
			subState.put(SubStates.HIGHRET, true);
			highRet.play();
		}
		else if(!state.get(States.LUNGED) && moveGraph.check(state, States.ARMMOVE) && 
				(subState.get(SubStates.LOWEXT) || subState.get(SubStates.MIDEXT))) {
			System.out.println("extHigh");
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE, false);
			subState.put(SubStates.HIGHEXT, true);
			highExt.play();
		}
		else if(state.get(States.LUNGED) && subMoveGraph.check(subState, SubStates.HIGHEXT)) {
			System.out.println("lungedHigh");
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE,  false);
			subState.put(SubStates.HIGHEXT, true);
			lungedHigh.play();
		}
	}
	
	public void lineChangeMid() {
		System.out.println("in lineChangeMid");
		if(!state.get(States.LUNGED) && moveGraph.check(state, States.ARMMOVE) && (subState.get(SubStates.LOWRET) || subState.get(SubStates.HIGHRET))) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE, false);
			subState.put(SubStates.MIDRET, true);
			midRet.play();
		}
		else if(!state.get(States.LUNGED) && moveGraph.check(state, States.ARMMOVE) && (subState.get(SubStates.LOWEXT) || subState.get(SubStates.HIGHEXT))) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE, false);
			subState.put(SubStates.MIDEXT, true);
			midExt.play();
		}
		else if(state.get(States.LUNGED) && subMoveGraph.check(subState, SubStates.MIDEXT)) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE,  false);
			subState.put(SubStates.MIDEXT, true);
			lungedMid.play();
		}
	}
	
	public void lineChangeLow() {
		System.out.println("in lineChangeLow");
		if(!state.get(States.LUNGED) && moveGraph.check(state, States.ARMMOVE) && (subState.get(SubStates.MIDRET) || subState.get(SubStates.HIGHRET))) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE, false);
			subState.put(SubStates.LOWRET, true);
			lowRet.play();
		}
		else if(!state.get(States.LUNGED) && moveGraph.check(state, States.ARMMOVE) && (subState.get(SubStates.MIDEXT) || subState.get(SubStates.HIGHEXT))) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE, false);
			subState.put(SubStates.LOWEXT, true);
			lowExt.play();
		}
		else if(state.get(States.LUNGED) && subMoveGraph.check(subState, SubStates.LOWEXT)) {
			resetSubState();
			state.put(States.ARMMOVE, true);
			state.put(States.ARMIDLE,  false);
			subState.put(SubStates.LOWEXT, true);
			lungedLow.play();
		}
	}
	
	public void extend() {
		System.out.println("in extend");
		if(subState.get(SubStates.HIGHRET)) {
			if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.HIGHEXT)) {
				resetSubState();
				state.put(States.ARMMOVE, true);
				state.put(States.ARMIDLE, false);
				subState.put(SubStates.HIGHEXT, true);
				highExt.play();
			}
		}
		else if(subState.get(SubStates.MIDRET)) {
			if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.MIDEXT)) {
				resetSubState();
				state.put(States.ARMMOVE, true);
				state.put(States.ARMIDLE, false);
				subState.put(SubStates.MIDEXT, true);
				midExt.play();
			}
		}
		else if(subState.get(SubStates.LOWRET)) {
			if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.LOWEXT)) {
				resetSubState();
				state.put(States.ARMMOVE, true);
				state.put(States.ARMIDLE, false);
				subState.put(SubStates.LOWEXT, true);
				lowExt.play();
			}
		}
	}
	public void retract() {
		/*this first line breaks my heart. it's an ugly patch on poor design. because retract is called when the modifier key (w)
		 * *isn't* pressed, retract is called when lunged. you're not allowed to retract while lunged (didn't make any animations),
		 * but you can change line. ARMMOVE is either type of arm movement, so when retract is (automatically) called, it retracts
		 * because ARMMOVEs are ok. :(
		 */
		if(!state.get(States.LUNGED)) {
			System.out.println("in retract");
			if(subState.get(SubStates.HIGHEXT)) {
				if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.HIGHRET)) {
					resetSubState();
					state.put(States.ARMMOVE, true);
					state.put(States.ARMIDLE, false);
					subState.put(SubStates.HIGHRET, true);
					highRet.play();
				}
			}
			else if(subState.get(SubStates.MIDEXT)) {
				if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.MIDRET)) {
					resetSubState();
					state.put(States.ARMMOVE, true);
					state.put(States.ARMIDLE, false);
					subState.put(SubStates.MIDRET, true);
					midRet.play();
				}
			}
			else if(subState.get(SubStates.LOWEXT)) {
				if(moveGraph.check(state, States.ARMMOVE) && subMoveGraph.check(subState, SubStates.LOWRET)) {
					resetSubState();
					state.put(States.ARMMOVE, true);
					state.put(States.ARMIDLE, false);
					subState.put(SubStates.LOWRET, true);
					lowRet.play();
				}
			}
		}
	}

	
	public void lunge() {
		//TODO: add all the animations
		if(moveGraph.check(state, States.LUNGE)) {
			System.out.println("in lunge");
			state.put(States.LUNGE, true);
			state.put(States.LEGIDLE, false);
			bot.setVisible(false);
			if(subState.get(SubStates.HIGHEXT)) {
			}
			else if(subState.get(SubStates.MIDEXT)) {
			}
			else if(subState.get(SubStates.LOWEXT)) {
			}
			else if(subState.get(SubStates.HIGHRET)) {
				System.out.println("in highRet branch");
				resetSubState();
				subState.put(SubStates.HIGHEXT, true);
				highRetLunge.play();
			}
			else if(subState.get(SubStates.MIDRET)) {
				System.out.println("in midRet branch");
				resetSubState();
				subState.put(SubStates.MIDEXT, true);
				midRetLunge.play();
			}
			else if(subState.get(SubStates.LOWRET)) {
				System.out.println("in lowRet branch");
				resetSubState();
				subState.put(SubStates.LOWEXT, true);
				lowRetLunge.play();
			}
			else {
				System.out.println("no substates were true");
			}
		}
		System.out.println("out");
	}
	
	//TODO: extension over other lines. recover animation needs to end in extension + worry about setting bot visible
	public void recover() {
		if(moveGraph.check(state, States.RECOVER) && subState.get(SubStates.HIGHEXT)) {
			state.put(States.LUNGED, false);
			state.put(States.RECOVER, true);
			resetSubState();
			subState.put(SubStates.HIGHRET, true);
			highRecover.play();
		}
		else if(moveGraph.check(state, States.RECOVER) && subState.get(SubStates.MIDEXT)) {
			state.put(States.LUNGED, false);
			state.put(States.RECOVER, true);
			resetSubState();
			subState.put(SubStates.MIDRET, true);
			midRecover.play();
		}
		else if(moveGraph.check(state, States.RECOVER) && subState.get(SubStates.LOWEXT)) {
			state.put(States.LUNGED, false);
			state.put(States.RECOVER, true);
			resetSubState();
			subState.put(SubStates.LOWRET, true);
			lowRecover.play();
		}
	}
	
	public void advance() {
		System.out.println("advance okay:"+moveGraph.check(state, States.STEP));
		if(moveGraph.check(state, States.STEP)) {
			System.out.println("advance okay!");
			state.put(States.STEP, true);
			state.put(States.LEGIDLE, false);
			bot.setVisible(true);
			advance.play();
		}
	}
	
	public void retreat() {
		if(moveGraph.check(state, States.STEP)) {
			state.put(States.STEP, true);
			state.put(States.LEGIDLE, false);
			bot.setVisible(true);
			retreat.play();
		}
	}

}
