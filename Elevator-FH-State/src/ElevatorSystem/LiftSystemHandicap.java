package ElevatorSystem;

import java.util.ArrayList;

import ElevatorSystem.Elevator.Direction;

public class LiftSystemHandicap extends LiftSystem {
	public void LiftHandicap(Elevator e, Environment env) {
		System.out.println("handicap is calling");
		// disabled persons are waiting for the elevator
		 if (e.getHandicap().size()>0) {
			 // call the lift function adapted to disabled people 
				disabledLift(e,e.getCurrentFloorID());				}
		super.lift(e, env);
	}
	private void disabledLift(Elevator e, int currentFloorID) {
		// for each person in the handicap list
		 for (Handicap p: new ArrayList<Handicap>(e.getHandicap())) {
			 // ensure he is disabled
			 if(p.getClass().getCanonicalName() == "ElevatorSystem.Handicap") {
				 System.out.println(p.getName()+" from "+p.getOrigin()+" to "+p.getDestination()+" current floor is "+currentFloorID);
					while(p.getOrigin()<currentFloorID) {
						// if current position of elevator is up the position of the person so the elevator goes down
						e.continueInDirection(Direction.down);
						// update the current floor
						currentFloorID=e.getCurrentFloorID();
					}
					// the origin position of the person is up to the current position of the elevator
					while(p.getOrigin()> currentFloorID) {
						// the elevator goes up to reach the origin floor
						e.continueInDirection(Direction.up);
						//update the current floor id
						currentFloorID=e.getCurrentFloorID();
					}
					// the elevator arrives to the disabled person
					if(p.getOrigin()==currentFloorID) {
						// person entered the elevator
						e.enterElevator(p);	
						// continue the lift till reaching the destination
						while(p.getDestination()<currentFloorID) {
							// elevator goes down to reach the destination floor
							e.continueInDirection(Direction.down);
							//update the current position of the elevator
							currentFloorID=e.getCurrentFloorID();
						}
						while(p.getDestination()> currentFloorID) {
							// elevator goes up to reach the destination floor

							e.continueInDirection(Direction.up);
							//update the current position of the elevator

							currentFloorID=e.getCurrentFloorID();
						}
						// arriving to the destination 
						if(p.getDestination() == currentFloorID) {
							// person leave the elevator
							e.leaveElevator(p);
							// remove person from handicap list
							e.getHandicap().remove(p);
						}
					}

			 }
		 }
	}

}
