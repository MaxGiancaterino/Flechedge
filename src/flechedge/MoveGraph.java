package flechedge;

import java.util.HashMap;
import java.util.Map;

import flechedge.Duelist.States;

public class MoveGraph {
	private HashMap<States, HashMap<States, Boolean>> map;
	
	public MoveGraph() {
		map = new HashMap<States, HashMap<States, Boolean>>();
		//true implies finishing that action -> the adjacent
		//LEGIDLE
		HashMap<States, Boolean> legIdleAdj = new HashMap<States, Boolean>();
		legIdleAdj.put(States.ARMMOVE, false);
		legIdleAdj.put(States.STEP, false);
		legIdleAdj.put(States.LUNGE, false); 
		legIdleAdj.put(States.FLECHE, false);
		map.put(States.LEGIDLE, legIdleAdj);
		//ARMIDLE
		HashMap<States, Boolean> armIdleAdj = new HashMap<States, Boolean>();
		armIdleAdj.put(States.STEP, false);
		armIdleAdj.put(States.ARMMOVE, false);
		armIdleAdj.put(States.LUNGE, false);
		armIdleAdj.put(States.FLECHE, false);
		map.put(States.ARMIDLE, armIdleAdj);
		//ARMMOVE
		HashMap<States, Boolean> armMoveAdj = new HashMap<States, Boolean>();
		armMoveAdj.put(States.STEP, false);
		armMoveAdj.put(States.ARMIDLE, true);
		map.put(States.ARMMOVE, armMoveAdj);
		//STEP
		HashMap<States, Boolean> stepAdj = new HashMap<States, Boolean>();
		stepAdj.put(States.ARMMOVE, false);
		stepAdj.put(States.LEGIDLE, true);
		map.put(States.STEP, stepAdj);
		//LUNGE
		HashMap<States, Boolean> lungeAdj = new HashMap<States, Boolean>();
		lungeAdj.put(States.LUNGED, true);
		map.put(States.LUNGE, lungeAdj);
		//FLECHE
		HashMap<States, Boolean> flecheAdj = new HashMap<States, Boolean>();
		map.put(States.FLECHE, flecheAdj);
		//LUNGED
		HashMap<States, Boolean> lungedAdj = new HashMap<States, Boolean>();
		lungedAdj.put(States.ARMMOVE, false);
		lungedAdj.put(States.RECOVER, false);
		map.put(States.LUNGED, lungedAdj);
		//RECOVER
		HashMap<States, Boolean> recAdj = new HashMap<States, Boolean>();
		recAdj.put(States.ARMIDLE, true);
		recAdj.put(States.LEGIDLE, true);
		map.put(States.RECOVER, recAdj);
	}
	
	public Boolean containsEdge(States currState, States checkState) {
		if(map.get(currState).get(checkState) == null) {
			return false;
		}
		return true;
	}
	
	public Boolean check(HashMap<States, Boolean> state, States action) {
		for(HashMap.Entry<States, Boolean> entry : state.entrySet()) {
			if(entry.getValue()==true && !containsEdge(entry.getKey(), action)) {
				return false;
			}
		}
		return true;
	}
	
	public States findNext(States state) {
		for(Map.Entry<States, Boolean> connectedNodes: map.get(state).entrySet()) {
			if(connectedNodes.getValue()) {
				return connectedNodes.getKey();
			}
		}
		System.out.println("no Next");
		return null;
	}

}
