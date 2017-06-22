package flechedge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandParser {
	private Set<String> keyInputs = new HashSet<String>();
	private Map<String, String> keyMap = new HashMap<String, String>();
	
	public CommandParser(Set<String> keyInputs, Map<String, String> keyMap) {
		this.keyInputs = keyInputs;
		this.keyMap = keyMap;
	}
	
	public void parse() {
		if(keyInputs.contains(keyMap.get("p1Mod"))) {
			
		}
		if(keyInputs.contains(keyMap.get("p1Ret"))) {
			
		}
		if(keyInputs.contains(keyMap.get("p1Par"))) {
			
		}
		if(keyInputs.contains(keyMap.get("p1Adv"))) {
			
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
