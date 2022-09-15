package ElevatorSystem;

import java.util.ArrayList;
import java.util.Arrays;

public class Elevator {
	// check if the elevator is not blocked
	boolean blocked = false;
	// check if the elevator is broken down
	private boolean brokenDown = false;
	Environment env;
	boolean verbose;

	public boolean isBrokenDown() {
		return brokenDown;
	}

	public void setBrokenDown(boolean brokenDown) {
		this.brokenDown = brokenDown;
	}
	// the current position of the elevator
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
	// the direction of the elevator if it goes up or down
	Direction currentHeading;

	enum DoorState {
		open, close
	};

	DoorState doors;
	boolean[] floorButtons;

	public Elevator(Environment env, boolean verbose) {
	}

	public Elevator(Environment env, boolean verbose, int floor, boolean headingUp) {
	}
	// check if elevator is blocked
	public boolean isBlocked() {
		return blocked;
	}
	// if the elevator is broken call the agent with number 81424242

	public boolean brokenDown(boolean brokenDown, AgentMaintenance agent) {
		if (brokenDown == true) {
			agent = new Schindler(81424242, "Schindler");
			return agent.resolve(this);
		}
		return true;
	}

	// person enter into the elevator
	private void enterElevator__wrappee__Base(ArrayList<Person> persons, Person p, ElevatorType1 e) {
		persons.add(p);
		p.enterElevator(e);
		if (verbose)
			System.out.println(p.getName() + " entered the Elevator at Landing " + getCurrentFloorID() + ", going to "
					+ p.getDestination());
	}

	public void enterElevator(ArrayList<Person> persons, Person p, ElevatorType1 e, int weight) {
		enterElevator__wrappee__Base(persons, p, e);
		weight += p.getWeight();
	}

	// person leave the elevator
	private boolean leaveElevator__wrappee__Base(ArrayList<Person> persons, Person p) {
		// check if person is already in the elevator
		if (persons.contains(p)) {
			// remove the person from the list of persons in elevator
			persons.remove(p);
			// leave the elevator and return true
			p.leaveElevator();
			verbose = true;
			if (verbose)
				System.out.println(p.getName() + " left the Elevator at Landing " + currentFloorID);
			return true;
		} else
			// if the person is not in the elevator so it returns false 
			return false;
	}

	private boolean leaveElevator__wrappee__Weight(ArrayList<Person> persons, Person p, int weight) {
		if (leaveElevator__wrappee__Base(persons, p)) {
			// person leaves the elevator and we decrease the weight
			weight -= p.getWeight();
			return true;
		} else
			return false;
	}

	public boolean leaveElevator(ArrayList<Person> persons, Person p, int weight) {
		if (leaveElevator__wrappee__Weight(persons, p, weight)) {
			if (persons.isEmpty())
				Arrays.fill(floorButtons, false);
			return true;
		} else
			return false;
	}

	/*
	 * @ ensures env.calledAt_Spec2[floorID]; assignable floorButtons[*]; @
	 */
	private void pressInLiftFloorButton__wrappee__Base(int floorID) {
		// @ set env.calledAt_Spec2[floorID] = true;
		if (floorID == 0) {
			// the index of the floorID in the floor table is set to true when it is pressed

			floorButtons[floorID] = true;
		} else
			floorButtons[floorID - 1] = true;
	}

	/*
	 * @ ensures env.calledAt_Spec9[floorID]; assignable \nothing; @
	 */
	public void pressInLiftFloorButton(int floorID) {
		// @ set env.calledAt_Spec9[floorID] = true;
		pressInLiftFloorButton__wrappee__Base(floorID);
	}

	public void resetFloorButton(int floorID) {
		// reset the button of the floor when reaching it
		floorButtons[floorID] = false;
	}

	public /* @pure@ */ int getCurrentFloorID() {
		return currentFloorID;
	}

	public /* @pure@ */ boolean areDoorsOpen() {
		//check if doors are open or not
		return doors == DoorState.open;
	}

	public boolean stopRequestedAtCurrentFloor__wrappee__Base() {
		return env.getFloor(currentFloorID).hasCall() || floorButtons[currentFloorID] == true;
	}

	public boolean stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor() {
		if (isExecutiveFloorCalling() && !isExecutiveFloor(currentFloorID)) {
			return false;
		} else
			return stopRequestedAtCurrentFloor__wrappee__Base();
	}
	// check if the elevator will stop at the current floor
	public boolean stopRequestedAtCurrentFloor(int maximumWeight, int weight) {
			// when exceeding ,maximum weight the elevator must stop
			if (weight > maximumWeight * 2 / 3) {
					return floorButtons[currentFloorID] == true;
		} else
			return stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor();
	}
	// determines the direction that elevator must follow 

	public void continueInDirection__wrappee__Base(Direction dir, int currentFloorID) {
		currentHeading = dir;

		if (currentHeading == Direction.up) {
			if (env.isTopFloor(currentFloorID)) {
				// go down
				currentHeading = currentHeading.reverse();
			} else {
				currentFloorID = currentFloorID + 1;
				this.setCurrentFloorID(currentFloorID);
			}
		} else {
			// we are in the 0 floor and direction is up
			if (currentFloorID == 0) {
				currentHeading = currentHeading.reverse();
			} else {
				// if the elevator goes down so we decrease the floor id
				currentFloorID = currentFloorID - 1;
				this.setCurrentFloorID(currentFloorID);
			}
		}
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
	public void continueInDirection(Direction dir, int floorCurrentID) {
		continueInDirection__wrappee__Base(dir, floorCurrentID);
	}
	// check if any call request for the elevator 
	public boolean isAnyLiftButtonPressed() {
		for (int i = 0; i < floorButtons.length; i++) {
			if (floorButtons[i])
				return true;
		}
		return false;
	}
	 // check if the elevator must stop
	public boolean stopRequestedInDirection__wrappee__Base(Direction dir, boolean respectFloorCalls,
			boolean respectInLiftCalls) {
		Floor[] floors = env.getFloors();
		// if current direction is up
		if (dir == Direction.up) {
			if (env.isTopFloor(currentFloorID))
				return false;
			for (int i = currentFloorID + 1; i < floors.length; i++) {
				if (respectFloorCalls && floors[i].hasCall())
					return true;
				if (respectInLiftCalls && floorButtons[i])
					return true;
			}
			return false;
		} else {
			// if direction is down
			if (currentFloorID == 0)
				return false;
			// 
			for (int i = currentFloorID - 1; i >= 0; i--) {
				// the elevator will stop at every floor calling it so return true for each floor
				if (respectFloorCalls && floors[i].hasCall())
					return true;
				if (respectInLiftCalls && floorButtons[i])
					return true;
			}
			return false;
		}
	}

	public boolean stopRequestedInDirection__wrappee__ExecutiveFloor(Direction dir, boolean respectFloorCalls,
			boolean respectInLiftCalls) {
		// check if executive floor is calling 
		if (isExecutiveFloorCalling()) {
			if (verbose)
				;
			// go direction to executive floor because it has priority
			return ((currentFloorID < executiveFloor) == (dir == Direction.up));
		} else
			return stopRequestedInDirection__wrappee__Base(dir, respectFloorCalls, respectInLiftCalls);
	}

	public boolean stopRequestedInDirection(Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls,
			int maximumWeight, int weight) {
		// if weight in elevator exceeds the 2/3maximum weight of the elevator or any lift is requested so we know in advance that elevator will stop in its direction
		if (weight > maximumWeight * 2 / 3 && isAnyLiftButtonPressed()) {
			if (verbose)
				System.out.println(
						"over 2/3 threshold, ignoring calls from FloorButtons until weight is below 2/3*threshold");
			return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, false, respectInLiftCalls);
		} else
			return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, respectFloorCalls, respectInLiftCalls);
	}

	public boolean anyStopRequested() {
		Floor[] floors = env.getFloors();
		for (int i = 0; i < floors.length; i++) {
			if (floors[i].hasCall())
				return true;
			else if (floorButtons[i])
				return true;
		}
		return false;
	}
	// determines which floor is pressed

	public /* @pure@ */ boolean buttonForFloorIsPressed(int floorID) {
		return floorButtons[floorID];
	}
	// determines which direction

	public /* @pure@ */ Direction getCurrentDirection() {
		return currentHeading;
	}

	public Environment getEnv() {
		return env;
	}

	public boolean isIdle() {
		return !anyStopRequested();
	}

	@Override
	public String toString() {
		return "Elevator " + (areDoorsOpen() ? "[_]" : "[] ") + " at " + currentFloorID + " heading " + currentHeading;
	}
	// set the floor number 4 as executive floor
	int executiveFloor = 4;

	public /* @pure@ */ boolean isExecutiveFloor(int floorID) {
		return floorID == executiveFloor;
	}

	public /* @pure@ */ boolean isExecutiveFloorCalling() {
		for (Floor f : env.floors)
			if (f.getFloorID() == executiveFloor && f.hasCall())
				return true;
		return false;
	}
	// this function is considered in the orginal version of the featureIDE system but n ever used so keep it as core function 
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

}
