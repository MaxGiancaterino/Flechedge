package flechedge;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandParser {
	
	public enum BaseCommands {
		P1_MOD, P1_RET, P1_PAR, P1_ADV, P1_HIGH, P1_MID, P1_LOW, P1_EXT, P1_THROW
	}
	
	private Set<String> keyInputs = new HashSet<String>();
	private Map<BaseCommands, String> keyMap = new EnumMap<BaseCommands, String>(BaseCommands.class);
	private Sprite p1 = new Sprite();
	private double speed;
	
	public CommandParser(Sprite p1, double speed, Set<String> keyInputs, Map<BaseCommands, String> keyMap) {
		this.keyInputs = keyInputs;
		this.keyMap = keyMap;
		this.p1 = p1;
		this.speed = speed;
	}
	
	public void parse() {

		if(keyInputs.contains(keyMap.get(BaseCommands.P1_MOD))) {
			p1.addVelocity(0, -speed);
		}
		if(keyInputs.contains(keyMap.get(BaseCommands.P1_RET))) {
			p1.addVelocity(-speed, 0);
		}
		if(keyInputs.contains(keyMap.get(BaseCommands.P1_PAR))) {
			p1.addVelocity(0, speed);
		}
		if(keyInputs.contains(keyMap.get(BaseCommands.P1_ADV))) {
			p1.addVelocity(speed, 0);
		}
		if(keyInputs.contains(keyMap.get(BaseCommands.P1_HIGH))) {
			
		}
		if(keyInputs.contains(keyMap.get(BaseCommands.P1_MID))) {
			
		}
		if(keyInputs.contains(keyMap.get(BaseCommands.P1_LOW))) {
			
		}
		if(keyInputs.contains(keyMap.get(BaseCommands.P1_EXT))) {
			
		}
		if(keyInputs.contains(keyMap.get(BaseCommands.P1_THROW))) {
			
		}
		if(keyInputs.contains((keyMap.get(BaseCommands.P1_ADV))
				&&keyInputs.contains((keyMap.get(BaseCommands.P1_EXT))
				&&keyInputs.contains((keyMap.get(BaseCommands.P1_MOD)))))) {
			p1.lunge();
		}
	}
}
