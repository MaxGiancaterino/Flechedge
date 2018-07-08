package flechedge;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandParser {

	public enum BaseCommands {
		P1_MOD, P1_RET, P1_PAR, P1_ADV, P1_HIGH, P1_MID, P1_LOW, P1_EXT, P1_THROW, P2_MOD, P2_RET, P2_PAR, P2_ADV, P2_HIGH, P2_MID, P2_LOW, P2_EXT, P2_THROW
	}

	private Set<String> keyInputs = new HashSet<String>();
	private Map<BaseCommands, String> keyMap = new EnumMap<BaseCommands, String>(BaseCommands.class);
	private Duelist p1;
	private Duelist p2;

	public CommandParser(Duelist p1, Duelist p2, Set<String> keyInputs, Map<BaseCommands, String> keyMap) {
		this.keyInputs = keyInputs;
		this.keyMap = keyMap;
		this.p1 = p1;
		this.p2 = p2;
	}

	public void parse() {
		
		/*if (keyInputs.contains((keyMap.get(BaseCommands.P1_ADV)))
				&& keyInputs.contains((keyMap.get(BaseCommands.P1_EXT)))
				&& keyInputs.contains((keyMap.get(BaseCommands.P1_MOD)))) {
			p1.lunge();
		}*/
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_MOD))) {
			p1.lunge();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_RET))) {
			p1.retreat();
			p1.recover();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_PAR))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_ADV))) {
			p1.advance();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_HIGH))) {
			p1.lineChangeHigh();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_MID))) {
			p1.lineChangeMid();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_LOW))) {
			p1.lineChangeLow();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_EXT))) {
			p1.extend();
		}
		if (!keyInputs.contains(keyMap.get(BaseCommands.P1_EXT))) {
			p1.retract();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_THROW))) {

		}

		// p2
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_MOD))) {

		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_RET))) {
			p2.retreat();
			p2.recover();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_PAR))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_ADV))) {
			p2.advance();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_HIGH))) {
			p2.lineChangeHigh();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_MID))) {
			p2.lineChangeMid();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_LOW))) {
			p2.lineChangeLow();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_EXT))) {

		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_THROW))) {

		}
		if (keyInputs.contains((keyMap.get(BaseCommands.P2_ADV)))
				&& keyInputs.contains((keyMap.get(BaseCommands.P2_EXT)))
				&& keyInputs.contains((keyMap.get(BaseCommands.P2_MOD)))) {
			p2.lunge();
		}
	}
}
