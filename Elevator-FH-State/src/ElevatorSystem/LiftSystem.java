package ElevatorSystem;import java.util.ArrayList;import ElevatorSystem.Elevator.DoorState;
// this class is responsible for the lift
public class LiftSystem implements LiftElevatorState{
	public void lift(Elevator e, Environment env) {
		// if the elevator stop at the current floor
if (e.stopRequestedAtCurrentFloor()) {		
	// the elevator open the door
			e.doors = DoorState.open;
			// make all persons who arrived to their destination in the current floor leave the elevator
			for (Person p: new ArrayList<Person>(e.getPersons())) {
				if (p.getDestination() == e.getCurrentFloorID()) {
					// if arrived to destination all the concerned people must leave 
					e.leaveElevator(p);								}		}
			// delete persons who left elevator from the list of waiting process
			env.getFloor(e.getCurrentFloorID()).processWaitingPersons(e);
			// reset the floor button
			e.resetFloorButton(e.getCurrentFloorID());
		} else {
			if (e.doors == DoorState.open)  {
				// the current floor is not a destination for the person so we close the doors of the elevator
				e.doors = DoorState.close;							}
			stopRequestedInDirectionOverloaded stoprequested;
			stoprequested= new stopRequestedInDirectionOverloaded();
			// Check if elevator needs to stop in next floors to response to a lift request
			if (stoprequested.stopRequestedInDirection(e.currentHeading, true, true,e,env)) {						
				// if true so the elevator continues forward
				e.continueInDirection(e.currentHeading);
			} else if (stoprequested.stopRequestedInDirection(e.currentHeading.reverse(), true, true,e,env)) {				
				// otherwise the elevator continues in the inverse direction
				e.continueInDirection(e.currentHeading.reverse());
			} else {								
				e.continueInDirection(e.currentHeading);
			}	}

	}
}
