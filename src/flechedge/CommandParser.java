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
	private BetterDuelist p1;
	private BetterDuelist p2;

	public CommandParser(BetterDuelist p1, BetterDuelist p2, Set<String> keyInputs, Map<BaseCommands, String> keyMap) {
		this.keyInputs = keyInputs;
		this.keyMap = keyMap;
		this.p1 = p1;
		this.p2 = p2;
	}

	public void parse() {

		if (keyInputs.contains(keyMap.get(BaseCommands.P1_MOD))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_RET))) {
			p1.retreat();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_PAR))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_ADV))) {
			p1.advance();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_HIGH))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_MID))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_LOW))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_EXT))) {

		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P1_THROW))) {

		}
		if (keyInputs.contains((keyMap.get(BaseCommands.P1_ADV)))
				&& keyInputs.contains((keyMap.get(BaseCommands.P1_EXT)))
				&& keyInputs.contains((keyMap.get(BaseCommands.P1_MOD)))) {
			p1.lunge();
		}

		// p2
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_MOD))) {

		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_RET))) {
			p2.retreat();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_PAR))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_ADV))) {
			p2.advance();
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_HIGH))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_MID))) {
		}
		if (keyInputs.contains(keyMap.get(BaseCommands.P2_LOW))) {
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
