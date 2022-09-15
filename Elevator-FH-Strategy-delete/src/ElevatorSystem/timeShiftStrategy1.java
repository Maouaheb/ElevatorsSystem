package ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

import ElevatorSystem.Elevator.Direction;
import ElevatorSystem.Elevator.DoorState;

public class timeShiftStrategy1 implements timeShiftStrategy {
	public void timeShift1(Elevator e, DoorState doors, int currentFloorID, List<Person> persons,Environment env, Direction currentHeading) {
		//System.out.println(currentFloorID+" "+persons.size());
	

		if (e.stopRequestedAtCurrentFloor()) {
		
		doors = DoorState.open;

		for (Person p: new ArrayList<Person>(persons)) {
			if (p.getDestination() == currentFloorID) {
				e.leaveElevator(p);					
			}
		}
		env.getFloor(currentFloorID).processWaitingPersons(e);
		e.resetFloorButton(currentFloorID);
	} else {
		if (doors == DoorState.open)  {
			doors = DoorState.close;
			
		}
		stopRequestedInDirectionStrategy stoprequested;
		stoprequested= new stopRequestedIndirectionBasic();
		if (stoprequested.stopRequestedInDirection(currentHeading, true, true,e)) {
			e.continueInDirection(currentHeading,currentFloorID);
			currentFloorID=e.getCurrentFloorID();
		} else if (stoprequested.stopRequestedInDirection(currentHeading.reverse(), true, true,e)) {
			e.continueInDirection(currentHeading.reverse(),currentFloorID);
			currentFloorID=e.getCurrentFloorID();

		} else {			
			e.continueInDirection(currentHeading,currentFloorID);
			currentFloorID=e.getCurrentFloorID();


		}
	}
	}
}
