package ElevatorSystem;

import ElevatorSystem.Elevator.Direction;

public class stopRequestedIndirectionBasic implements stopRequestedInDirectionStrategy{

	@Override
	public boolean stopRequestedInDirection(Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls,
			Elevator e) {
		// TODO Auto-generated method stub
		 return e.stopRequestedInDirection__wrappee__ExecutiveFloor(dir, respectFloorCalls, respectInLiftCalls);
	}

}
