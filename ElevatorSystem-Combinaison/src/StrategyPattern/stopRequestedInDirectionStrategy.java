package StrategyPattern;

import ElevatorSystem.Elevator;
import ElevatorSystem.Elevator.Direction;
// strategy to stop can be the basic function or the overloaded one
public interface stopRequestedInDirectionStrategy {
	public boolean stopRequestedInDirection (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls, Elevator e);

}
