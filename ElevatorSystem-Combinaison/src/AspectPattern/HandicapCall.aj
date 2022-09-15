package AspectPattern;
import java.util.ArrayList;

import ElevatorSystem.ContinueInDirection;
import ElevatorSystem.Elevator;
import ElevatorSystem.Handicap;
import ElevatorSystem.Elevator.Direction;
import ElevatorSystem.Environment;;

public aspect HandicapCall {
public 	int currentFloorID=0;
public Elevator elevator=null;
public ContinueInDirection c=null;
	// inject the aspect in time shift elevator
	 pointcut timeShift(Elevator e) : execution(* ElevatorSystem.Elevator.timeShift()) && target(e);
	 after(Elevator e) returning() : timeShift(e) {
		 Environment env=new Environment(5);
		c=new ContinueInDirection();
		 elevator=e;
		 for (Handicap p : new ArrayList<Handicap>(e.getHandicap())) {
				System.out.println("i am handicap from "+p.getOrigin()+" to "+p.getDestination());
				if (p.getClass().getCanonicalName() == "ElevatorSystem.Handicap") {
					while (p.getOrigin() < e.getCurrentFloorID()) {
						c.continueInDirection__wrappee__Base(env, e, Direction.down);
						currentFloorID=e.getCurrentFloorID();
					}
					while (p.getOrigin() > currentFloorID) {
						System.out.println(p.getOrigin()+" going to "+p.getDestination()+" current floor "+currentFloorID);
						c.continueInDirection__wrappee__Base(env, e, Direction.up);
						currentFloorID=e.getCurrentFloorID();
					}
					if (p.getOrigin() == currentFloorID) {
						e.enterElevator(p);

						while (p.getDestination() < currentFloorID) {
							c.continueInDirection__wrappee__Base(env, e, Direction.down);
							currentFloorID=e.getCurrentFloorID();

						}
						while (p.getDestination() > currentFloorID) {

							c.continueInDirection__wrappee__Base(env, e, Direction.up);
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
}
