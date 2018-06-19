package flechedge;

import java.util.HashMap;

import flechedge.Duelist.SubStates;

public class SubMoveGraph {
	private HashMap<SubStates, HashMap<SubStates, Boolean>> map;
	
	public SubMoveGraph() {
		map = new HashMap<SubStates, HashMap<SubStates, Boolean>>();
		//true implies finishing that action -> the adjacent
		//HIGHRET
		HashMap<SubStates, Boolean> highRetAdj = new HashMap<SubStates, Boolean>();
		highRetAdj.put(SubStates.HIGHEXT, false);
		highRetAdj.put(SubStates.MIDRET, false);
		highRetAdj.put(SubStates.LOWRET, false); 
		map.put(SubStates.HIGHRET, highRetAdj);
		//MIDRET
		HashMap<SubStates, Boolean> midRetAdj = new HashMap<SubStates, Boolean>();
		midRetAdj.put(SubStates.MIDEXT, false);
		midRetAdj.put(SubStates.HIGHRET, false);
		midRetAdj.put(SubStates.LOWRET, false);
		map.put(SubStates.MIDRET, midRetAdj);
		//LOWRET
		HashMap<SubStates, Boolean> lowRetAdj = new HashMap<SubStates, Boolean>();
		lowRetAdj.put(SubStates.LOWEXT, false);
		lowRetAdj.put(SubStates.HIGHRET, false);
		lowRetAdj.put(SubStates.MIDRET, false);
		map.put(SubStates.LOWRET, lowRetAdj);
		//HIGHEXT
		HashMap<SubStates, Boolean> highExtAdj = new HashMap<SubStates, Boolean>();
		highExtAdj.put(SubStates.HIGHRET, false);
		highExtAdj.put(SubStates.MIDEXT, false);
		highExtAdj.put(SubStates.LOWEXT, false);
		map.put(SubStates.HIGHEXT, highExtAdj);
		//MIDEXT
		HashMap<SubStates, Boolean> midExtAdj = new HashMap<SubStates, Boolean>();
		midExtAdj.put(SubStates.MIDRET, false);
		midExtAdj.put(SubStates.HIGHEXT, false);
		midExtAdj.put(SubStates.LOWEXT, false);
		map.put(SubStates.MIDEXT, midExtAdj);
		//LOWEXT
		HashMap<SubStates, Boolean> lowExtAdj = new HashMap<SubStates, Boolean>();
		lowExtAdj.put(SubStates.LOWRET, false);
		lowExtAdj.put(SubStates.HIGHEXT, false);
		lowExtAdj.put(SubStates.MIDEXT, false);
		map.put(SubStates.LOWEXT, lowExtAdj);
	}
	
	public Boolean containsEdge(SubStates currState, SubStates checkState) {
		if(map.get(currState).get(checkState) == null) {
			return false;
		}
		return true;
	}
	
	public Boolean check(HashMap<SubStates, Boolean> state, SubStates action) {
		for(HashMap.Entry<SubStates, Boolean> entry : state.entrySet()) {
			if(entry.getValue()==true && !containsEdge(entry.getKey(), action)) {
				return false;
			}
		}
		return true;
	}

}
