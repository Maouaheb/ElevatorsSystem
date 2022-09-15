package ElevatorSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Elevator {
	Environment env;
	public boolean verbose;
	public LiftSystem lift;
	// list of disabled persons
	private ArrayList<Handicap> handicap = new ArrayList<Handicap>();

	public ArrayList<Handicap> getHandicap() {
		return handicap;
	}

	public void setHandicap(ArrayList<Handicap> handicap) {
		this.handicap = handicap;
	}

	// current position of the elevator
	private int currentFloorID;

	public void setCurrentFloorID(int currentFloorID) {
		this.currentFloorID = currentFloorID;
	}

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

	// current direction
	Direction currentHeading;
	// list of normal persons
	private List<Person> persons = new ArrayList<Person>();

	enum DoorState {
		open, close
	};

	DoorState doors;
	boolean[] floorButtons;

	public Elevator(Environment env, boolean verbose) {
		this.verbose = verbose;
		this.currentHeading = Direction.up;
		this.currentFloorID = 0;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];
	}

	public Elevator(Environment env, boolean verbose, int floor, boolean headingUp) {
		this.verbose = verbose;
		this.currentHeading = (headingUp ? Direction.up : Direction.down);
		this.currentFloorID = floor;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];
	}

	// check if elevator is blocked or not
	public boolean isBlocked() {
		return blocked;
	}

	private void enterElevator__wrappee__Base(Person p) {
		// person added
		persons.add(p);
		// person entered the elevator
		p.enterElevator(this);
		verbose = true;
		if (verbose)
			System.out.println(p.getName() + " entered the Elevator at Landing " + this.getCurrentFloorID()
					+ ", going to " + p.getDestination());
	}

	public void enterElevator(Person p) {
		enterElevator__wrappee__Base(p);
		// when the person entered the elevator the weight is increased
		weight += p.getWeight();
		setWeight(weight);
		// if the current weight of elevator is higher than threshold so the elevator is
		// blocked
		if (weight >= maximumWeight) {
			setBlocked(true);
		}
	}

	// return true if the person p leave elevator otherwise false
	private boolean leaveElevator__wrappee__Base(Person p) {
		// check if person is already in the elevator
		if (persons.contains(p)) {
			// remove person from the list of people in the elevator
			persons.remove(p);
			p.leaveElevator();
			verbose = true;
			if (verbose)
				System.out.println(p.getName() + " left the Elevator at Landing " + currentFloorID);
			return true;
		} else
			return false;
	}

	// return true if person leaves the elevator
	private boolean leaveElevator__wrappee__Weight(Person p) {
		if (leaveElevator__wrappee__Base(p)) {
			// when person leaves the elevator decrease the weight
			weight -= p.getWeight();
			setWeight(weight);
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
		floorButtons[floorID] = true;
	}

	/*
	 * @ ensures env.calledAt_Spec9[floorID]; assignable \nothing; @
	 */
	public void pressInLiftFloorButton(int floorID) {
		// @ set env.calledAt_Spec9[floorID] = true;
		pressInLiftFloorButton__wrappee__Base(floorID);
	}

	public void resetFloorButton(int floorID) {
		floorButtons[floorID] = false;
	}

	public /* @pure@ */ int getCurrentFloorID() {
		return currentFloorID;
	}

	public /* @pure@ */ boolean areDoorsOpen() {
		return doors == DoorState.open;
	}
	/*
	 * @ ensures env.calledAt_Spec1[currentFloorID] ==
	 * env.calledAt_Spec1[currentFloorID] && areDoorsOpen() ? false :
	 * env.calledAt_Spec1[currentFloorID]; ensures \old(getCurrentDirection()) ==
	 * Direction.up && getCurrentDirection() == Direction.down ==> (\forall int i;
	 * getCurrentFloorID() < i && i < numFloors; !buttonForFloorIsPressed(i));
	 * ensures \old(getCurrentDirection()) == Direction.down &&
	 * getCurrentDirection() == Direction.up ==> (\forall int i; 0 <= i && i <
	 * getCurrentFloorID(); !buttonForFloorIsPressed(i)); assignable doors, persons,
	 * floorButtons[*],currentHeading,currentFloorID; @
	 */

	/*
	 * @ ensures \old(isEmpty()) && areDoorsOpen() ==>
	 * floors[getCurrentFloorID()].hasCall(); ensures isEmpty() ==> (\forall int i;
	 * 0 <= i && i < env.calledAt_Spec9.length; !env.calledAt_Spec9[i]); assignable
	 * \nothing; @
	 */
	private void timeShift__wrappee__Empty() {
		// if disabled person requested a lift
		if (getHandicap().size() > 0) {
			lift = new LiftSystemHandicap();

		} else {
			// otherwise we call lift for normal person
			lift = new LiftSystem();
		}
		lift.lift(this, env);
		// timeShift__wrappee__Base();
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
		// the state is at beginning null
		lift = null;
// the elevator is blocked if doors are open and weight is higher than the threshold
		if ((areDoorsOpen() && weight > maximumWeight) || (areDoorsOpen() == false && weight > maximumWeight)) {
			blocked = true;
			setBlocked(true);
			if (verbose)
				System.out.println("Elevator blocked due to overloading (weight:" + weight + " > maximumWeight:"
						+ maximumWeight + ")");
		}
		// if disabled person request a lift
		if (getHandicap().size() > 0) {
			System.out.println("call handicap " + getHandicap().size());
			// call the lift state of elevator dedicated for handicap
			lift = new LiftSystemHandicap();
			((LiftSystemHandicap) lift).LiftHandicap(this, env);
		} else {
			// call lift for normal persons
			timeShift__wrappee__ExecutiveFloor();
		}
	}

	// set the elevator state as blocked
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	// return true if the elevator will stop in the next floor
	private boolean stopRequestedAtCurrentFloor__wrappee__Base() {
		return env.getFloor(currentFloorID).hasCall() || floorButtons[currentFloorID] == true;
	}

	public boolean stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor() {
		if (isExecutiveFloorCalling() && !isExecutiveFloor(currentFloorID)) {
			// elevator does not stop in the current floor
			return false;
		} else
			// stop at current floor
			return stopRequestedAtCurrentFloor__wrappee__Base();
	}
	// if current floor is executive floor is  return true
	public /* @pure@ */ boolean isExecutiveFloor(int floorID) {
		return floorID == executiveFloor;
	}
	// if executive floor is calling return true
	public /* @pure@ */ boolean isExecutiveFloorCalling() {
		for (Floor f : env.floors)
			if (f.getFloorID() == executiveFloor && f.hasCall())
				return true;
		return false;
	}
	// return true if the elevator must stop at current floor
	public boolean stopRequestedAtCurrentFloor() {
		// if the weight is higher than weight *2/3 threshold 
		if (weight > maximumWeight * 2 / 3) {
			return floorButtons[currentFloorID] == true;
		} else
			return stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor();
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	/*
	 * @ ensures \original; ensures getCurrentFloorID() != \old(getCurrentFloorID())
	 * && weight > maximumWeight*2/3 ==> (\old(getCurrentDirection()) ==
	 * Direction.up && existInLiftCallsInDirection(Direction.down) &&
	 * !existInLiftCallsInDirection(Direction.up) ==> getCurrentDirection() !=
	 * Direction.up) && (\old(getCurrentDirection()) == Direction.down &&
	 * existInLiftCallsInDirection(Direction.up) &&
	 * !existInLiftCallsInDirection(Direction.down) ==> getCurrentDirection() !=
	 * Direction.down); assignable currentHeading; @
	 */
	public void continueInDirection(Direction dir) {
		ContinueInDirection state = new ContinueInDirection();
		state.continueInDirection__wrappee__Base(env, this, dir);
	}

	public boolean isAnyLiftButtonPressed() {
		for (int i = 0; i < this.floorButtons.length; i++) {
			if (floorButtons[i])
				return true;
		}
		return false;
	}

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

	private int weight = 0;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public static final int maximumWeight = 100;
	int executiveFloor = 4;

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

}
