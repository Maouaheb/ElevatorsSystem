package ElevatorSystem;

import java.util.ArrayList;

import ElevatorSystem.Elevator.DoorState;

public class LiftSystem implements LiftElevatorState{
	public void lift(Elevator e, Environment env) {
if (e.stopRequestedAtCurrentFloor()) {
			
			e.doors = DoorState.open;
			
			for (Person p: new ArrayList<Person>(e.getPersons())) {
				if (p.getDestination() == e.getCurrentFloorID()) {
					e.leaveElevator(p);					
				}
			}
			env.getFloor(e.getCurrentFloorID()).processWaitingPersons(e);
			e.resetFloorButton(e.getCurrentFloorID());
		} else {
			if (e.doors == DoorState.open)  {
				e.doors = DoorState.close;
				
			}
			stopRequestedInDirectionStrategy stoprequested;
			stoprequested= new stopRequestedIndirectionBasic();
			if (stoprequested.stopRequestedInDirection(e.currentHeading, true, true,e)) {
				
				
				e.continueInDirection(e.currentHeading);
			} else if (stoprequested.stopRequestedInDirection(e.currentHeading.reverse(), true, true,e)) {
				
				
				e.continueInDirection(e.currentHeading.reverse());
			} else {
				
				
				e.continueInDirection(e.currentHeading);
			}
	}

	}
}
