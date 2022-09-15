package ElevatorSystem;

import java.util.List;

import ElevatorSystem.Elevator.Direction;
import ElevatorSystem.Elevator.DoorState;

public interface timeShiftStrategy {
	// time shift system can be the basic or the system for handicap
	public void timeShift1(Elevator e, DoorState doors, int currentFloorID, List<Person> persons,Environment env, Direction currentHeading);

}
