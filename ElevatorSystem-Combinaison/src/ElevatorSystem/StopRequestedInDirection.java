package ElevatorSystem;

import ElevatorSystem.Elevator.Direction;

public class StopRequestedInDirection implements LiftElevatorState {
	 public boolean  stopRequestedInDirection__wrappee__Base (Environment env, Elevator e,Elevator.Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls) {
			Floor[] floors = env.getFloors();
			if (dir == Elevator.Direction.up) {
				if (env.isTopFloor(e.getCurrentFloorID())) return false;
				for (int i = e.getCurrentFloorID()+1; i < floors.length; i++) {
					if (respectFloorCalls && floors[i].hasCall()) return true;
					if (respectInLiftCalls && e.floorButtons[i]) return true; 
				}
				return false;
			} else {
				if (e.getCurrentFloorID() == 0) return false;
				for (int i = e.getCurrentFloorID()-1; i >= 0; i--) {
					if (respectFloorCalls && floors[i].hasCall()) return true;
					if (respectInLiftCalls && e.floorButtons[i]) return true;
				}
				return false;
			}
		}


}
