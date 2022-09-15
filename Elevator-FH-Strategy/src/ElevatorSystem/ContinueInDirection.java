package ElevatorSystem;
import ElevatorSystem.Elevator.Direction;
public class ContinueInDirection implements LiftElevatorStrategy {
	public void  continueInDirection__wrappee__Base  (Environment env,Elevator e,Direction dir) {
		e.currentHeading = dir;
		if (e.currentHeading == Direction.up) {
			if (env.isTopFloor(e.getCurrentFloorID())) {		
				e.currentHeading = e.currentHeading.reverse();	}
		} else { 
			if (e.getCurrentFloorID() == 0) {		
				e.currentHeading = e.currentHeading.reverse();		}	}
		if (e.currentHeading == Direction.up) {
			int floor;
			floor = e.getCurrentFloorID() + 1;
			e.setCurrentFloorID(floor);
		} else {
			int floor;
			floor = e.getCurrentFloorID() - 1;
			e.setCurrentFloorID(floor);		}}}
