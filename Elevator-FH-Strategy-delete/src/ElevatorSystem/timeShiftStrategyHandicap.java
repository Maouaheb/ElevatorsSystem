package ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

import ElevatorSystem.Elevator.Direction;
import ElevatorSystem.Elevator.DoorState;

public class timeShiftStrategyHandicap implements timeShiftStrategy {

	private void disabledLift(Elevator e, int currentFloorID) {

		for (Handicap p : new ArrayList<Handicap>(e.getHandicap())) {
			if (p.getClass().getCanonicalName() == "ElevatorSystem.Handicap") {

				while (p.getOrigin() < currentFloorID) {
					e.continueInDirection(Direction.down,currentFloorID);
					currentFloorID=e.getCurrentFloorID();
				}
				while (p.getOrigin() > currentFloorID) {

					e.continueInDirection(Direction.up,currentFloorID);
					currentFloorID=e.getCurrentFloorID();
				}
				if (p.getOrigin() == currentFloorID) {
					e.enterElevator(p);

					while (p.getDestination() < currentFloorID) {
						e.continueInDirection(Direction.down,currentFloorID);
						currentFloorID=e.getCurrentFloorID();

					}
					while (p.getDestination() > currentFloorID) {

						e.continueInDirection(Direction.up,currentFloorID);
						currentFloorID=e.getCurrentFloorID();

					}
					if (p.getDestination() == currentFloorID) {
						e.leaveElevator(p);
						e.getHandicap().remove(p);
						currentFloorID=e.getCurrentFloorID();

					}
				}

			}
		}
	}

	@Override
	public void timeShift1(Elevator e, DoorState doors, int currentFloorID, List<Person> persons, Environment env,
			Direction currentHeading) {
		if (e.getHandicap().size() > 0) {
			disabledLift(e, e.getCurrentFloorID());
		}
		if (e.stopRequestedAtCurrentFloor()) {

			doors = DoorState.open;

			for (Person p : new ArrayList<Person>(persons)) {
				if (p.getDestination() == currentFloorID) {
					e.leaveElevator(p);
				}
			}
			env.getFloor(currentFloorID).processWaitingPersons(e);
			e.resetFloorButton(currentFloorID);
		} else {
			if (doors == DoorState.open) {
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
