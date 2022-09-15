package ElevatorSystem;

import java.util.ArrayList;

import ElevatorSystem.Elevator.Direction;

public class LiftSystemHandicap extends LiftSystem {
	public void LiftHandicap(Elevator e, Environment env) {
		System.out.println("handicap is calling");
		
		 if (e.getHandicap().size()>0) {

				disabledLift(e,e.getCurrentFloorID());
			
				//timeShift__wrappee__Base();
				}
		super.lift(e, env);
	}
	private void disabledLift(Elevator e, int currentFloorID) {
		 for (Handicap p: new ArrayList<Handicap>(e.getHandicap())) {
			 if(p.getClass().getCanonicalName() == "ElevatorSystem.Handicap") {
				 System.out.println(p.getName()+" from "+p.getOrigin()+" to "+p.getDestination()+" current floor is "+currentFloorID);
					while(p.getOrigin()<currentFloorID) {
						e.continueInDirection(Direction.down);
						currentFloorID=e.getCurrentFloorID();

					}
					while(p.getOrigin()> currentFloorID) {

						e.continueInDirection(Direction.up);
						currentFloorID=e.getCurrentFloorID();

					}
					if(p.getOrigin()==currentFloorID) {
						e.enterElevator(p);
						
						while(p.getDestination()<currentFloorID) {
							e.continueInDirection(Direction.down);
							currentFloorID=e.getCurrentFloorID();

						}
						while(p.getDestination()> currentFloorID) {

							e.continueInDirection(Direction.up);
							currentFloorID=e.getCurrentFloorID();

						}
						if(p.getDestination() == currentFloorID) {
							e.leaveElevator(p);
							e.getHandicap().remove(p);
						}
					}

			 }
		 }
	}

}
