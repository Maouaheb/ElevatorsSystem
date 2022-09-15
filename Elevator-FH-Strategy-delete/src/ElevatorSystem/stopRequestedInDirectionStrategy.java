package ElevatorSystem;

import ElevatorSystem.Elevator.Direction;

public interface stopRequestedInDirectionStrategy {
	public boolean stopRequestedInDirection (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls, Elevator e);

}
