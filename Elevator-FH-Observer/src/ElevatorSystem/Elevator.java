
package ElevatorSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ElevatorSystem.Elevator.Direction;
import ObserverPattern.ObsHandicap;
import ObserverPattern.ObsTwoThirdFull;
import ObserverPattern.ObsWeight;

public class Elevator extends Observable {
	Environment env;
	boolean verbose;
	private int currentFloorID;

	public enum Direction {
		up {
			@Override
			public Direction reverse() {
				return down;
			}
		},
		down {
			@Override
			public Direction reverse() {
				return up;
			}
		};

		public abstract Direction reverse();
	}

	Direction currentHeading;
	private List<Person> persons = new ArrayList<Person>();
	ArrayList<Handicap> handicap = new ArrayList<Handicap>();
	// the list of disabled people 
	public ArrayList<Handicap> getHandicap() {
		return handicap;
	}
	// a disabled person entered to the elevator so the oberver will be notified
	public void addHandicap(Handicap h) {
		handicap.add(h);
		obs.add(new ObsHandicap());
		this.notifyObservers();
	}

	public void setHandicap(ArrayList<Handicap> handicap) {
		this.handicap = handicap;
	}

	// declare the observers
	ArrayList<Observer> obs = new ArrayList<Observer>();

	enum DoorState {
		open, close
	};

	DoorState doors;
	boolean[] floorButtons;

	public void setCurrentFloorID(int currentFloorID) {
		this.currentFloorID = currentFloorID;
	}

	public Elevator(Environment env, boolean verbose) {
		this.verbose = verbose;
		this.currentHeading = Direction.up;
		this.currentFloorID = 0;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];
		// observe the elevator
		this.addObserver(new ObsWeight());
	}

	public Elevator(Environment env, boolean verbose, int floor, boolean headingUp) {
		this.verbose = verbose;
		this.currentHeading = (headingUp ? Direction.up : Direction.down);
		this.currentFloorID = floor;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];
		// add observer that will be launch when elevator reaches the maximum weight
		this.addObserver(new ObsWeight());
	}

	public boolean isBlocked() {
		return blocked;
	}
	// person entered to the elevator
	private void enterElevator__wrappee__Base(Person p) {
		// add a person to the list of persons in the elevator
		persons.add(p);
		p.enterElevator(this);
		verbose = true;
		if (verbose)
			System.out.println(p.getName() + " entered the Elevator at Landing " + this.getCurrentFloorID()
					+ ", going to " + p.getDestination());
	}

	public void enterElevator(Person p) {
		
		enterElevator__wrappee__Base(p);
		//once person entered in the elevator we increase the weight of the elevator
		weight += p.getWeight();
	}

	private boolean leaveElevator__wrappee__Base(Person p) {
		// check that person who wants to leave the elevator is already inside
		if (persons.contains(p)) {
			// delete person from the list of persons in the elevator
			persons.remove(p);
			// make the person leaving the elevator
			p.leaveElevator();
			verbose = true;
			// return true to ensure that person left elevator
			if (verbose)
				System.out.println(p.getName() + " left the Elevator at Landing " + currentFloorID);
			return true;
		} else
			// person is already not in the elevator
			return false;
	}

	private boolean leaveElevator__wrappee__Weight(Person p) {
		if (leaveElevator__wrappee__Base(p)) {
			// once the person p left the elevator we decrease the weight
			weight -= p.getWeight();
			return true;
		} else
			return false;
	}

	public boolean leaveElevator(Person p) {
		if (leaveElevator__wrappee__Weight(p)) {
			if (this.persons.isEmpty())
				Arrays.fill(this.floorButtons, false);
			return true;
		} else
			return false;
	}

	/*
	 * @ ensures env.calledAt_Spec2[floorID]; assignable floorButtons[*]; @
	 */
	private void pressInLiftFloorButton__wrappee__Base(int floorID) {
		// @ set env.calledAt_Spec2[floorID] = true;
		// the index of the floorID in the floor table is set to true when it is pressed
		floorButtons[floorID] = true;
	}

	/*
	 * @ ensures env.calledAt_Spec9[floorID]; assignable \nothing; @
	 */
	public void pressInLiftFloorButton(int floorID) {
		// @ set env.calledAt_Spec9[floorID] = true;
		pressInLiftFloorButton__wrappee__Base(floorID);
	}
	// reset the button of the floor when reaching it
	private void resetFloorButton(int floorID) {
		floorButtons[floorID] = false;
	}
	// get the current position of the elevator
	public /* @pure@ */ int getCurrentFloorID() {
		return currentFloorID;
	}
	// check the doors of the elevator are open 
	public /* @pure@ */ boolean areDoorsOpen() {
		return doors == DoorState.open;
	}

	private void timeShift__wrappee__Base() {
		if (stopRequestedAtCurrentFloor()) {
			doors = DoorState.open;
			for (Person p : new ArrayList<Person>(persons)) {
				// Once the person are to the destination he leave the elevator
				if (p.getDestination() == currentFloorID) {
					leaveElevator(p);
				}
			}
			//waiting for the elevator
			env.getFloor(currentFloorID).processWaitingPersons(this);
			resetFloorButton(currentFloorID);
		} else {
			if (doors == DoorState.open) {
				doors = DoorState.close;
			}
			// the program determines the direction of the elevator and if it will stop or not
			if (stopRequestedInDirection(currentHeading, true, true)) {
				continueInDirection(currentHeading, currentFloorID);
			} else if (stopRequestedInDirection(currentHeading.reverse(), true, true)) {
				continueInDirection(currentHeading.reverse(), currentFloorID);
			} else {
				continueInDirection(currentHeading, currentFloorID);
			}
		}
		// @ set env.calledAt_Spec1[currentFloorID] = env.calledAt_Spec1[currentFloorID]
		// && areDoorsOpen() ? false : env.calledAt_Spec1[currentFloorID]
	}

	/*
	 * @ ensures \old(isEmpty()) && areDoorsOpen() ==>
	 * floors[getCurrentFloorID()].hasCall(); ensures isEmpty() ==> (\forall int i;
	 * 0 <= i && i < env.calledAt_Spec9.length; !env.calledAt_Spec9[i]); assignable
	 * \nothing; @
	 */
	private void timeShift__wrappee__Empty() {
		timeShift__wrappee__Base();
	}

	/*
	 * @ ensures isExecutiveFloorCalling() && areDoorsOpen() ==>
	 * isExecutiveFloor(e.getCurrentFloorID()); assignable \nothing; @
	 */
	private void timeShift__wrappee__ExecutiveFloor() {
		timeShift__wrappee__Empty();
	}

	/*
	 * @ ensures weight > maximumWeight ==> areDoorsOpen(); ensures \old(weight) >
	 * \old(maximumWeight) ==> getCurrentFloorID() == \old(getCurrentFloorID());
	 * assignable blocked; @
	 */
	public void timeShift() {
		if (areDoorsOpen() && weight > maximumWeight) {
			// declare obs weight
			obs.add(new ObsWeight());
			// notify the observer that we reached the maximum weight of the elevator or doors are open meaning the elevator is blocked
			this.notifyObservers();
			blocked = true;
			if (verbose)
				System.out.println("Elevator blocked due to overloading (weight:" + weight + " > maximumWeight:"
						+ maximumWeight + ")");
		} else {
			// the elevator can shift person because doors are closed with good weight value so it is not blocked
			blocked = false;
			timeShift__wrappee__ExecutiveFloor();
		}
	}

	private boolean stopRequestedAtCurrentFloor__wrappee__Base() {
		return env.getFloor(currentFloorID).hasCall() || floorButtons[currentFloorID] == true;
	}

	private boolean stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor() {
		if (isExecutiveFloorCalling() && !isExecutiveFloor(currentFloorID)) {
			return false;
		} else
			return stopRequestedAtCurrentFloor__wrappee__Base();
	}
	// check if the elevator will stop at the current floor
	private boolean stopRequestedAtCurrentFloor() {
		// when exceeding ,maximum weight the elevator must stop
		if (weight > maximumWeight * 2 / 3) {
			// declare the two thirds observer
			obs.add(new ObsTwoThirdFull());
			this.notifyObservers();
			return floorButtons[currentFloorID] == true;
		} else
			return stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor();
	}
	// determines the direction that elevator must follow 
	private void continueInDirection__wrappee__Base(Direction dir, int currentFloorID) {
		currentHeading = dir;
		if (currentHeading == Direction.up) {
			if (env.isTopFloor(currentFloorID)) {
				// current direction is up and the elevator is actually at top so program reverse the direction and elevator goes down
				currentHeading = currentHeading.reverse();
			}
		} else {
			// current direction is down and the elevator reaches already the floor 0 so it goes up
			if (currentFloorID == 0) {
				currentHeading = currentHeading.reverse();
			}
		}
		// updating the current floor of elevator
		if (currentHeading == Direction.up) {
			currentFloorID = currentFloorID + 1;
			this.setCurrentFloorID(currentFloorID);
		} else {
			currentFloorID = currentFloorID - 1;
			this.setCurrentFloorID(currentFloorID);
		}
	}

	public void continueInDirection(Direction dir, int currentFloorID) {
		continueInDirection__wrappee__Base(dir, currentFloorID);
	}
	// check if any call request for the elevator 
	private boolean isAnyLiftButtonPressed() {
		for (int i = 0; i < this.floorButtons.length; i++) {
			if (floorButtons[i])
				return true;
		}
		return false;
	}
 // check if the elevator must stop
	private boolean stopRequestedInDirection__wrappee__Base(Direction dir, boolean respectFloorCalls,
			boolean respectInLiftCalls) {
		Floor[] floors = env.getFloors();
		if (dir == Direction.up) {
			if (env.isTopFloor(currentFloorID))
				return false;
			for (int i = currentFloorID + 1; i < floors.length; i++) {
				if (respectFloorCalls && floors[i].hasCall())
					return true;
				if (respectInLiftCalls && this.floorButtons[i])
					return true;
			}
			return false;
		} else {
			if (currentFloorID == 0)
				return false;
			for (int i = currentFloorID - 1; i >= 0; i--) {
				if (respectFloorCalls && floors[i].hasCall())
					return true;
				if (respectInLiftCalls && this.floorButtons[i])
					return true;
			}
			return false;
		}
	}

	private boolean stopRequestedInDirection__wrappee__ExecutiveFloor(Direction dir, boolean respectFloorCalls,
			boolean respectInLiftCalls) {
		if (isExecutiveFloorCalling()) {
			if (verbose)
				System.out.println("Giving Priority to Executive Floor");
			return ((this.currentFloorID < executiveFloor) == (dir == Direction.up));
		} else
			return stopRequestedInDirection__wrappee__Base(dir, respectFloorCalls, respectInLiftCalls);
	}
	// check if the elevator must stop
	private boolean stopRequestedInDirection(Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls) {
		// the elevator must step if the maximum weight is reached or if it receives a request from a floor
		if (weight > maximumWeight * 2 / 3 && isAnyLiftButtonPressed()) {
			if (verbose)
				System.out.println(
						"over 2/3 threshold, ignoring calls from FloorButtons until weight is below 2/3*threshold");
			return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, false, respectInLiftCalls);
		} else
			return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, respectFloorCalls, respectInLiftCalls);
	}
	// check if any floor of the environment send a shift request
	private boolean anyStopRequested() {
		Floor[] floors = env.getFloors();
		for (int i = 0; i < floors.length; i++) {
			if (floors[i].hasCall())
				return true;
			else if (this.floorButtons[i])
				return true;
		}
		return false;
	}

	public /* @pure@ */ boolean buttonForFloorIsPressed(int floorID) {
		return this.floorButtons[floorID];
	}

	public /* @pure@ */ Direction getCurrentDirection() {
		return currentHeading;
	}

	public Environment getEnv() {
		return env;
	}

	public boolean isEmpty() {
		return this.persons.isEmpty();
	}

	public boolean isIdle() {
		return !anyStopRequested();
	}

	@Override
	public String toString() {
		return "Elevator " + (areDoorsOpen() ? "[_]" : "[] ") + " at " + currentFloorID + " heading " + currentHeading;
	}

	int weight;
	private static final int maximumWeight = 100;
	int executiveFloor = 4;

	public /* @pure@ */ boolean isExecutiveFloor(int floorID) {
		return floorID == executiveFloor;
	}
	// the executive floor is a special floor that has priority to response to its requests
	public /* @pure@ */ boolean isExecutiveFloorCalling() {
		for (Floor f : env.floors)
			if (f.getFloorID() == executiveFloor && f.hasCall())
				return true;
		return false;
	}

	private /* @pure@ */ boolean existInLiftCallsInDirection(Direction d) {
		if (d == Direction.up) {
			for (int i = getCurrentFloorID(); i < floorButtons.length; i++)
				if (buttonForFloorIsPressed(i))
					return true;
		} else if (d == Direction.down) {
			for (int i = getCurrentFloorID(); i >= 0; i--)
				if (buttonForFloorIsPressed(i))
					return true;
		}
		return false;
	}

	private boolean blocked = false;
	// the observers will be notified
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (int i = 0; i < obs.size(); i++) {
			obs.get(i).update(this, null);
		}
	}

}
