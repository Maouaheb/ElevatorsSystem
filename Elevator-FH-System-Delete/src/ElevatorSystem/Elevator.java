package ElevatorSystem; import java.util.ArrayList; import java.util.Arrays; import java.util.List;

import ElevatorSystem.Elevator.Direction; public  class Elevator {
	Environment env;

	boolean verbose;

	int currentFloorID;
	private boolean brokenDown=false;

	public boolean isBrokenDown() {
		return brokenDown;
	}


	public void setBrokenDown(boolean brokenDown) {
		this.brokenDown = brokenDown;
	}

	public enum Direction {up { 
			@Override public Direction reverse() {return down;}	
		} , down { 
			@Override public Direction reverse() {return up;}
		};
	public abstract Direction reverse();}

	Direction currentHeading;

	private List<Person> persons = new ArrayList<Person>();
	private List<Handicap> handicap = new ArrayList<Handicap>();

	 enum DoorState {open , close}

	;

	DoorState doors;

	boolean[] floorButtons;

	public List<Handicap> getHandicaps(){
		return handicap;
	}
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


	public boolean isBlocked  () {
		return blocked;
	}


	 private void  enterElevator__wrappee__Base  (Person p) {
		persons.add(p);
		p.enterElevator(this);
		verbose =true;
		if (verbose) System.out.println(p.getName() + " entered the Elevator at Landing " + this.getCurrentFloorID() + ", going to " + p.getDestination());
	}


	public void enterElevator(Person p) {
		enterElevator__wrappee__Base(p);
		weight+=p.getWeight();
	}


	 private boolean  leaveElevator__wrappee__Base  (Person p) {
		if (persons.contains(p)) {
			persons.remove(p);
			p.leaveElevator();
			verbose=true;
			if (verbose) System.out.println(p.getName() + " left the Elevator at Landing " + currentFloorID);
			return true;
		} else return false;
	}


	 private boolean  leaveElevator__wrappee__Weight  (Person p) {
		if (leaveElevator__wrappee__Base(p)) {
			weight-=p.getWeight();
			return true;
		} else return false;
	}


	public boolean leaveElevator(Person p) { 
		if (leaveElevator__wrappee__Weight(p)) {
			if (this.persons.isEmpty())
				Arrays.fill(this.floorButtons, false);
			return true;
		} else return false;
	}

	/*@ 
	 ensures env.calledAt_Spec2[floorID];
	 assignable floorButtons[*]; @*/
	 private void  pressInLiftFloorButton__wrappee__Base(int floorID) {
		//@ set env.calledAt_Spec2[floorID] = true; 
		floorButtons[floorID] = true;
	}

	/*@ 
	 ensures env.calledAt_Spec9[floorID];
	 assignable \nothing; @*/
	public void pressInLiftFloorButton(int floorID) {
		//@ set env.calledAt_Spec9[floorID] = true; 
		pressInLiftFloorButton__wrappee__Base(floorID);
	}


	private void resetFloorButton(int floorID) {
		floorButtons[floorID] = false;
	}


	public /*@pure@*/  int getCurrentFloorID() {
		return currentFloorID;
	}


	public /*@pure@*/ boolean areDoorsOpen() {
		return doors == DoorState.open;
	}

	/*@ 
	 ensures env.calledAt_Spec1[currentFloorID] == env.calledAt_Spec1[currentFloorID] && areDoorsOpen() ? false : env.calledAt_Spec1[currentFloorID];
	 ensures \old(getCurrentDirection()) == Direction.up && getCurrentDirection() == Direction.down ==> (\forall int i; getCurrentFloorID() < i && i < numFloors; !buttonForFloorIsPressed(i));
	 ensures \old(getCurrentDirection()) == Direction.down && getCurrentDirection() == Direction.up ==> (\forall int i; 0 <= i && i < getCurrentFloorID(); !buttonForFloorIsPressed(i));
	 assignable doors, persons, floorButtons[*],currentHeading,currentFloorID; @*/
	private void disabledLift() {
		 for (Handicap p: new ArrayList<Handicap>(handicap)) {
			 if(p.getClass().getCanonicalName() == "ElevatorSystem.Handicap") {
					while(p.getOrigin()<currentFloorID) {
						continueInDirection(Direction.down);
					}
					while(p.getOrigin()> currentFloorID) {

						continueInDirection(Direction.up);
					}
					if(p.getOrigin()==currentFloorID) {
						enterElevator(p);
						
						while(p.getDestination()<currentFloorID) {
							continueInDirection(Direction.down);
						}
						while(p.getDestination()> currentFloorID) {

							continueInDirection(Direction.up);
						}
						if(p.getDestination() == currentFloorID) {
							leaveElevator(p);
							handicap.remove(p);
						}
					}

			 }
		 }
	}
	 private void  timeShift__wrappee__Base() {
		 if (handicap.size()>0) {
			disabledLift();
		
			//timeShift__wrappee__Base();
			}
		if (stopRequestedAtCurrentFloor() ) {
			
			doors = DoorState.open;
			
			for (Person p: new ArrayList<Person>(persons)) {
				if (p.getDestination() == currentFloorID) {
					leaveElevator(p);					
				}
			}
			env.getFloor(currentFloorID).processWaitingPersons(this);
			resetFloorButton(currentFloorID);
		} else {
			
		
			if (doors == DoorState.open)  {
				doors = DoorState.close;
				
			}
			
			if (stopRequestedInDirection(currentHeading, true, true)) {
				
				continueInDirection(currentHeading);
			} else if (stopRequestedInDirection(currentHeading.reverse(), true, true)) {
				

				continueInDirection(currentHeading.reverse());
			} else {
				
				
				continueInDirection(currentHeading);
			}
		}
		//@ set env.calledAt_Spec1[currentFloorID] = env.calledAt_Spec1[currentFloorID] && areDoorsOpen() ? false : env.calledAt_Spec1[currentFloorID]
	}

	/*@ 
	 ensures \old(isEmpty()) && areDoorsOpen() ==> floors[getCurrentFloorID()].hasCall();
	 ensures isEmpty() ==> (\forall int i; 0 <= i && i < env.calledAt_Spec9.length; !env.calledAt_Spec9[i]);
	 assignable \nothing; @*/
	 private void  timeShift__wrappee__Empty  () {
		timeShift__wrappee__Base();
		
	}

	/*@ 
	 ensures isExecutiveFloorCalling() && areDoorsOpen() ==> isExecutiveFloor(e.getCurrentFloorID());
	 assignable \nothing; @*/
	 private void  timeShift__wrappee__ExecutiveFloor  () {
		timeShift__wrappee__Empty();
	}

	/*@ 
	 ensures weight > maximumWeight ==> areDoorsOpen();
	 ensures \old(weight) > \old(maximumWeight) ==> getCurrentFloorID() == \old(getCurrentFloorID());
	 assignable blocked; @*/
	public void timeShift() {
		if (areDoorsOpen() && weight > maximumWeight) {
			blocked = true;
			if (verbose) System.out.println("Elevator blocked due to overloading (weight:" + weight + " > maximumWeight:" + maximumWeight + ")");
		} else {
			blocked = false;
			timeShift__wrappee__ExecutiveFloor();
		}
	}


	 public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	private boolean  stopRequestedAtCurrentFloor__wrappee__Base  () {
		return env.getFloor(currentFloorID).hasCall() 
				|| floorButtons[currentFloorID] == true;
	}


	 private boolean  stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor  () { 
		/*if (isExecutiveFloorCalling() && !isExecutiveFloor(currentFloorID)) {
			return false;
		} else */ return stopRequestedAtCurrentFloor__wrappee__Base();
	}


	private boolean stopRequestedAtCurrentFloor() {
		/*if (weight > maximumWeight*2/3) {
			return floorButtons[currentFloorID] == true;
		} else */ return stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor();
	}


	 private void  continueInDirection__wrappee__Base  (Direction dir) {
		currentHeading = dir;
		if (currentHeading == Direction.up) {
			if (env.isTopFloor(currentFloorID)) {
				
				currentHeading = currentHeading.reverse();
			}
		} else { 
			if (currentFloorID == 0) {
				
				currentHeading = currentHeading.reverse();
			}
		}
		if (currentHeading == Direction.up) {
			currentFloorID = currentFloorID + 1;
		} else {
			currentFloorID = currentFloorID - 1;
		}
	}

	/*@ 
	 ensures \original;
	 ensures getCurrentFloorID() != \old(getCurrentFloorID()) &&
	   weight > maximumWeight*2/3 ==> 
	 	(\old(getCurrentDirection()) == Direction.up &&
	 	 existInLiftCallsInDirection(Direction.down) && 
	 	 !existInLiftCallsInDirection(Direction.up) ==>
	 	 getCurrentDirection() != Direction.up) &&
	 	(\old(getCurrentDirection()) == Direction.down &&
	 	 existInLiftCallsInDirection(Direction.up) && 
	 	 !existInLiftCallsInDirection(Direction.down) ==>
	 	 getCurrentDirection() != Direction.down);
	 assignable currentHeading; @*/
	public void continueInDirection(Direction dir) {
		continueInDirection__wrappee__Base(dir);
	}


	private boolean isAnyLiftButtonPressed() {
		for (int i = 0; i < this.floorButtons.length; i++) {
			if (floorButtons[i]) return true;
		}
		return false;
	}


	 private boolean  stopRequestedInDirection__wrappee__Base  (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls) {
		Floor[] floors = env.getFloors();
		
		if (dir == Direction.up) {

			if (env.isTopFloor(currentFloorID)) return false;
			for (int i = currentFloorID+1; i < floors.length; i++) {
			
				if (respectFloorCalls && floors[i].hasCall()) return true;
				if (respectInLiftCalls && this.floorButtons[i]) return true; 
			}
			return false;
		} else {
			if (currentFloorID == 0) return false;
			for (int i = currentFloorID-1; i >= 0; i--) {
				
				if (respectFloorCalls && floors[i].hasCall()) return true;
				if (respectInLiftCalls && this.floorButtons[i]) return true;
			}
			return false;
		}
	}


	 private boolean  stopRequestedInDirection__wrappee__ExecutiveFloor  (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls) {
	/*	if (isExecutiveFloorCalling()) {
			if (verbose) System.out.println("Giving Priority to Executive Floor");
			return ((this.currentFloorID < executiveFloor)  == (dir == Direction.up));
			
		} else*/ return stopRequestedInDirection__wrappee__Base(dir, respectFloorCalls, respectInLiftCalls);
	}


	private boolean stopRequestedInDirection (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls) {
		/*if (weight > maximumWeight*2/3 && isAnyLiftButtonPressed()) {
			if (verbose) System.out.println("over 2/3 threshold, ignoring calls from FloorButtons until weight is below 2/3*threshold");
			return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, false, respectInLiftCalls);
		} else */return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, respectFloorCalls, respectInLiftCalls);
	}


	private boolean anyStopRequested () {
		Floor[] floors = env.getFloors();
		for (int i = 0; i < floors.length; i++) {
			if (floors[i].hasCall()) return true;
			else if (this.floorButtons[i]) return true; 
		}
		return false;		
	}


	public /*@pure@*/  boolean buttonForFloorIsPressed(int floorID) {
		return this.floorButtons[floorID];
	}


	public /*@pure@*/  Direction getCurrentDirection() {
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
		return "Elevator " + (areDoorsOpen() ? "[_]" :  "[] ") + " at " + currentFloorID + " heading " + currentHeading;
	}

	int weight;

	private static final int maximumWeight = 100;

	//int executiveFloor = 4;

	/* 
	public  boolean isExecutiveFloor(int floorID) {return floorID == executiveFloor; }


	public  boolean isExecutiveFloorCalling() {
		for (Floor f : env.floors) 
			if (f.getFloorID() == executiveFloor && f.hasCall()) return true;
		return false;
	}
*/

	private /*@pure@*/  boolean existInLiftCallsInDirection(Direction d) {
		 if (d == Direction.up) {
			 for (int i = getCurrentFloorID(); i < floorButtons.length; i++)
				 if (buttonForFloorIsPressed(i)) return true;
		 } else if (d == Direction.down) {
			 for (int i = getCurrentFloorID(); i >= 0; i--)
				 if (buttonForFloorIsPressed(i)) return true;
		 }
		 return false;		 
	 }

	private boolean blocked = false;


}
