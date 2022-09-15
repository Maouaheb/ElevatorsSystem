package ElevatorSystem;
import ElevatorSystem.Elevator.Direction;
//the elevator is in the state of continuing 
public class ContinueInDirection implements LiftElevatorState {
	public void  continueInDirection__wrappee__Base  (Environment env,Elevator e,Direction dir) {
		//current direction of the elevator if it is up or down
		e.currentHeading = dir;
		// ensure that if elevator is n top or bottom floor to reverse the direction
		if (e.currentHeading == Direction.up) {
			// if the elevator is up and we are actually on top elevator so the elevator must goes down
			if (env.isTopFloor(e.getCurrentFloorID())) {			
			e.currentHeading = e.currentHeading.reverse();		}
		} else { 
			// if the elevator is down and we are actually on bottom elevator so the elevator must goes down
			if (e.getCurrentFloorID() == 0) {		
				e.currentHeading = e.currentHeading.reverse();	}	}
	// the elevator is neither on bottom neither on top floors	
	if (e.currentHeading == Direction.up) {
			int floor;
		//increase floor after up direction and update the current position of the elevator
			floor = e.getCurrentFloorID() + 1;
			e.setCurrentFloorID(floor);
		} else {
			int floor;
			//decrease floor after down direction and update the current position of the elevator

			floor = e.getCurrentFloorID() - 1;
			e.setCurrentFloorID(floor);		
		}
	}

	

}
