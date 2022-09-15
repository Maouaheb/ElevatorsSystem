package ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorType1 extends Elevator {
	// check if the elevator is not blocked
	boolean blocked = false;
	//check if the elevator is broken down
	boolean brokenDown = false;
	// this kind of elevator can held 500 kg
	public static int maximumWeight = 5000;
	public ArrayList<Person> persons = new ArrayList<Person>();
	private List<Handicap> handicap = new ArrayList<Handicap>();

	public List<Handicap> getHandicap() {
		return handicap;
	}

	public void setHandicap(List<Handicap> handicap) {
		this.handicap = handicap;
	}

	public int weight = 0;
	public int currentFloorID = 0;

	public ElevatorType1(Environment env, boolean verbose) {
		super(env, verbose);
		this.verbose = verbose;
		this.currentHeading = Direction.up;
		this.currentFloorID = 0;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];
		// TODO Auto-generated constructor stub
	}

	public ElevatorType1(Environment env, boolean verbose, int floor, boolean headingUp) {
		super(env, verbose, floor, headingUp);
		this.verbose = verbose;
		this.currentHeading = (headingUp ? Direction.up : Direction.down);
		this.currentFloorID = floor;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];
	}
	// if the elevator is broken call the agent with number 81424242
	public boolean brokenDown(boolean brokenDown, AgentMaintenance agent) {
		if (brokenDown == true) {
			agent = new Schindler(81424242, "Schindler");
			return agent.resolve(this);
		}
		return true;
	}

	public void timeShift() {
		if (areDoorsOpen() && weight > maximumWeight) {
			blocked = true;
			if (verbose)
				System.out.println("Elevator blocked due to overloading (weight:" + weight + " > maximumWeight:"
						+ maximumWeight + ")");
		} else {
			blocked = false;
			timeShift__wrappee__ExecutiveFloor();
		}
	}

	public void timeShift__wrappee__ExecutiveFloor() {
		timeShift__wrappee__Base();
	}

	private void timeShift__wrappee__Base() {
		// call for disabled person
		if (handicap.size() > 0) {
			disabledLift();
		}
		if (stopRequestedAtCurrentFloor(maximumWeight, weight)) {
			doors = DoorState.open;
			for (Person p : new ArrayList<Person>(persons)) {
				// person arrived to destination so leave the elevator
				if (p.getDestination() == currentFloorID) {
					leaveElevator(persons, p, weight);
				}
			}
			// persons will enter the elevator
			env.getFloor(currentFloorID).processWaitingPersons(persons, this, weight);
			resetFloorButton(currentFloorID);
			// Pas d'arrêt dans l'étage courant
		} else {
			if (doors == DoorState.open) {
				doors = DoorState.close;
			}
			// Whenever the weight is less than maximum weight and the current floor <
			// destination then continue in direction
			if (stopRequestedInDirection(currentHeading, true, true, maximumWeight, weight)) {
				continueInDirection(currentHeading, currentFloorID);
			}
			// Whenever the weight is less than maximum weight and the current floor >
			// destination then continue in reverse direction
			else if (stopRequestedInDirection(currentHeading.reverse(), true, true, maximumWeight, weight)) {
				continueInDirection(currentHeading.reverse(), currentFloorID);
			} else {
				continueInDirection(currentHeading, currentFloorID);
			}
		}
		// @ set env.calledAt_Spec1[currentFloorID] = env.calledAt_Spec1[currentFloorID]
		// && areDoorsOpen() ? false : env.calledAt_Spec1[currentFloorID]
	}

	public void disabledLift() {
		for (Handicap p : new ArrayList<Handicap>(handicap)) {
			// check if the caller is of type disabled
			if (p.getClass().getCanonicalName() == "ElevatorSystem.Handicap") {
				// continue till the elevator arrives to the floor to response the request
				while (p.getOrigin() < currentFloorID) {
					continueInDirection(Direction.down, currentFloorID);
					currentFloorID = super.getCurrentFloorID();
				}
				// continue till the elevator arrives to the floor to response the request
				while (p.getOrigin() > currentFloorID) {
					continueInDirection(Direction.up, currentFloorID);
					currentFloorID = super.getCurrentFloorID();
				}
				// the elevator arrived to the person floor
				if (p.getOrigin() == currentFloorID) {
					enterElevator(persons, p, this, weight);
					// continue till the person reaches the destination
					while (p.getDestination() < currentFloorID) {
						continueInDirection(Direction.down, currentFloorID);
						currentFloorID = super.getCurrentFloorID();
					}
					// if the destination is up to the current floor so the elevator goes up
					while (p.getDestination() > currentFloorID) {
						continueInDirection(Direction.up, currentFloorID);
						// update the floor number
						currentFloorID = super.getCurrentFloorID();
					}
					// person arrived so leave elevator
					if (p.getDestination() == currentFloorID) {
						// person leaves the elevator
						leaveElevator(persons, p, weight);
						handicap.remove(p);
					}
				}
			}
		}
	}
}
