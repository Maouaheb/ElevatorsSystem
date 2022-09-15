package ElevatorSystem;import java.util.ArrayList;import java.util.List;import ElevatorSystem.Elevator.Direction;import ElevatorSystem.Elevator.DoorState;
public class timeShiftStrategy1 implements timeShiftStrategy {
	public void timeShift1(Elevator e, DoorState doors, int currentFloorID, List<Person> persons,Environment env, Direction currentHeading) {
		if (e.stopRequestedAtCurrentFloor()) {		
		doors = DoorState.open;
		for (Person p: new ArrayList<Person>(persons)) {
			if (p.getDestination() == currentFloorID) {
				e.leaveElevator(p);								}		}
		env.getFloor(currentFloorID).processWaitingPersons(e);
		e.resetFloorButton(currentFloorID);
	} else {
		if (doors == DoorState.open)  {
			doors = DoorState.close;					}
		// if overloaded feature exist we select the stoprequestedoverloaded strategy
		stopRequestedInDirectionStrategy stoprequested;
		stoprequested= new stopRequestedInDirectionOverloaded();
		if (stoprequested.stopRequestedInDirection(currentHeading, true, true,e,env)) {
			e.continueInDirection(currentHeading,currentFloorID);
			currentFloorID=e.getCurrentFloorID();
		} else if (stoprequested.stopRequestedInDirection(currentHeading.reverse(), true, true,e,env)) {
			e.continueInDirection(currentHeading.reverse(),currentFloorID);
			currentFloorID=e.getCurrentFloorID();
		} else {			
			e.continueInDirection(currentHeading,currentFloorID);
			currentFloorID=e.getCurrentFloorID();		}	}	}}
