package ObserverPattern;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


import ElevatorSystem.Elevator;
import ElevatorSystem.Handicap;
import Main.PL_Interface_impl;
import ElevatorSystem.Elevator.Direction;

public class ObsHandicap implements Observer {
	// observer for handicap
public Elevator elevator;

	@Override
	public void update(Observable o, Object arg) {
		// If there is a disabled person want to be shifted
		// TODO Auto-generated method stub
		System.out.println("Handicap call ");
		elevator=PL_Interface_impl.ee;
		// If there is a disabled person want to be shifted	
		setHandicap(PL_Interface_impl.ee, PL_Interface_impl.ee.getCurrentFloorID());
	
	}
	public void setHandicap(Elevator e , int currentFloorID) {
		System.out.println("i am handicap "+PL_Interface_impl.ee.getHandicap().size());

		for (Handicap p : new ArrayList<Handicap>(PL_Interface_impl.ee.getHandicap())) {
			// for each handicap in the list
			if (p.getClass().getCanonicalName().equals( "ElevatorSystem.Handicap")) {
				// while the origin floor of the person is down to the floor wherein the elevator is so the elevator must go down
				while (p.getOrigin() < currentFloorID) {
					e.continueInDirection(Direction.down,currentFloorID);
					// after each down movement we update the current floor of the elevator
					currentFloorID=e.getCurrentFloorID();
				}
				// while the origin floor of the person is up to the floor wherein the elevator is so the elevator must go up

				while (p.getOrigin() > currentFloorID) {

					e.continueInDirection(Direction.up,currentFloorID);
					// after each up movement we update the current floor of the elevator
					currentFloorID=e.getCurrentFloorID();
				}
				// elevator arrived to the origin floor of the person so he entered in the elevator
				if (p.getOrigin() == currentFloorID) {
					e.enterElevator(p);
					// while the destination floor of the person is down to the floor wherein the elevator is so the elevator must go down
					while (p.getDestination() < currentFloorID) {
						e.continueInDirection(Direction.down,currentFloorID);
						currentFloorID=e.getCurrentFloorID();

					}
					// while the destination floor of the person is up to the floor wherein the elevator is so the elevator must go up

					while (p.getDestination() > currentFloorID) {

						e.continueInDirection(Direction.up,currentFloorID);
						currentFloorID=e.getCurrentFloorID();

					}
					// person arrived to destination so he leave the floor
					if (p.getDestination() == currentFloorID) {
						e.leaveElevator(p);
						e.getHandicap().remove(p);
						// update the current floor id of the elevator
						currentFloorID=e.getCurrentFloorID();

					}
				}

			}
		}
	}

}
