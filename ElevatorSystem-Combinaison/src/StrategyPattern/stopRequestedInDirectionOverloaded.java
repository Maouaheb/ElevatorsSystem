package StrategyPattern;

import ElevatorSystem.Elevator;
import ElevatorSystem.Elevator.Direction;

public class stopRequestedInDirectionOverloaded implements stopRequestedInDirectionStrategy {

	@Override
	public boolean stopRequestedInDirection(Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls, Elevator e) {
		// TODO Auto-generated method stub
		if (e.weight > e.maximumWeight*2/3 && e.isAnyLiftButtonPressed()) {
			if (e.verbose) System.out.println("over 2/3 threshold, ignoring calls from FloorButtons until weight is below 2/3*threshold");
			return e.stopRequestedInDirection__wrappee__ExecutiveFloor(dir, false, respectInLiftCalls);
		} else return e.stopRequestedInDirection__wrappee__ExecutiveFloor(dir, respectFloorCalls, respectInLiftCalls);	}

}
