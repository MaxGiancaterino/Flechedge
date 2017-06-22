package flechedge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandParser {
	
	private Set<String> keyInputs = new HashSet<String>();
	private Map<String, String> keyMap = new HashMap<String, String>();
	private Sprite p1 = new Sprite();
	private double speed;
	
	public CommandParser(Sprite p1, double speed, Set<String> keyInputs, Map<String, String> keyMap) {
		this.keyInputs = keyInputs;
		this.keyMap = keyMap;
		this.p1 = p1;
		this.speed = speed;
	}
	
	public void parse() {

		if(keyInputs.contains(keyMap.get("p1Mod"))) {
			p1.addVelocity(0, -speed);
		}
		if(keyInputs.contains(keyMap.get("p1Ret"))) {
			p1.addVelocity(-speed, 0);
		}
		if(keyInputs.contains(keyMap.get("p1Par"))) {
			p1.addVelocity(0, speed);
		}
		if(keyInputs.contains(keyMap.get("p1Adv"))) {
			p1.addVelocity(speed, 0);
		}
		if(keyInputs.contains(keyMap.get("p1High"))) {
			
		}
		if(keyInputs.contains(keyMap.get("p1Mid"))) {
			
		}
		if(keyInputs.contains(keyMap.get("p1Low"))) {
			
		}
		if(keyInputs.contains(keyMap.get("p1Ext"))) {
			
		}
		if(keyInputs.contains(keyMap.get("p1Throw"))) {
			
		}
	}
}
