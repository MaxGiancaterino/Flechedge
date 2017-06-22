package flechedge;

import java.util.HashSet;
import java.util.Set;

public class CommandParser {
	private Set<String> keyInputs = new HashSet<String>();
	
	public CommandParser(Set<String> keyInputs) {
		this.keyInputs = keyInputs;
	}
	
	public void parse() {
		if(keyInputs.contains("UP")) {
			
		}
		if(keyInputs.contains("DOWN")) {
			
		}
		if(keyInputs.contains("LEFT")) {
			
		}
		if(keyInputs.contains("RIGHT")) {
			
		}
	}
}
