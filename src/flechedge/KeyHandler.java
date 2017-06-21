package flechedge;

import java.util.HashSet;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent>{
	
	private Set<String> keyInputs = new HashSet<String>();  
	
	public KeyHandler(Set<String> keyInputs) {
		this.keyInputs = keyInputs;
	}
	
	public void handle(KeyEvent e) {
		if (e.getEventType() == KeyEvent.KEY_PRESSED) {
			keyInputs.add(e.getCode().toString());
		}
		if (e.getEventType() == KeyEvent.KEY_RELEASED) {
			keyInputs.remove(e.getCode().toString());
		}
	}
	
	
}
