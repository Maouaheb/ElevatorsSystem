package ElevatorSystem;

import ElevatorSystem.Elevator.Direction;
 // can be stop basic or stop with overloaded feature 
public interface stopRequestedInDirectionStrategy {
	public boolean stopRequestedInDirection (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls, Elevator e,Environment env);

}
