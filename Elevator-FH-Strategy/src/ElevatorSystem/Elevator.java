package ElevatorSystem; import java.util.ArrayList; import java.util.Arrays; import java.util.List; public  class Elevator {
	Environment env;
	boolean verbose;
	private int currentFloorID;
	public timeShiftStrategy shiftStrategy=null;
	public void setCurrentFloorID(int currentFloorID) {
		this.currentFloorID = currentFloorID;}
	public enum Direction {up { 
			@Override public Direction reverse() {return down;}	} , down { 	@Override public Direction reverse() {return up;}};
	public abstract Direction reverse();}
	Direction currentHeading;
	private ArrayList<Handicap> handicap=new ArrayList<Handicap>();
	public ArrayList<Handicap> getHandicap() {	return handicap;}
	public void setHandicap(ArrayList<Handicap> handicap) {
		this.handicap = handicap;}
	private List<Person> persons = new ArrayList<Person>();
	 public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	enum DoorState {open , close}	;
	DoorState doors;
	public boolean[] floorButtons;
	public Elevator(Environment env, boolean verbose) {
		this.verbose = verbose;
		this.currentHeading = Direction.up;
		this.currentFloorID = 0;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];	}
	public Elevator(Environment env, boolean verbose, int floor, boolean headingUp) {
		this.verbose = verbose;
		this.currentHeading = (headingUp ? Direction.up : Direction.down);
		this.currentFloorID = floor;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];	}
	public boolean isBlocked  () {
		return blocked;	}
	 public void  enterElevator__wrappee__Base  (Person p) {
		persons.add(p);
		p.enterElevator(this);
		verbose=true;
		if (verbose) System.out.println(p.getName() + " entered the Elevator at Landing " + this.getCurrentFloorID() + ", going to " + p.getDestination());}
	public void enterElevator(Person p) {
		enterElevator__wrappee__Base(p);
		weight+=p.getWeight();}
	 public boolean  leaveElevator__wrappee__Base  (Person p) {
		if (persons.contains(p)) {
			persons.remove(p);
			p.leaveElevator();
			if (verbose) System.out.println(p.getName() + " left the Elevator at Landing " + currentFloorID);
			return true;
		} else return false;	}
	 public boolean  leaveElevator__wrappee__Weight  (Person p) {
		if (leaveElevator__wrappee__Base(p)) {
			weight-=p.getWeight();
			return true;
		} else return false;}
	public boolean leaveElevator(Person p) { 
		if (leaveElevator__wrappee__Weight(p)) {
			if (this.persons.isEmpty())
				Arrays.fill(this.floorButtons, false);
			return true;
		} else return false;	}
	/*@ 
	 ensures env.calledAt_Spec2[floorID];
	 assignable floorButtons[*]; @*/
	 private void  pressInLiftFloorButton__wrappee__Base(int floorID) {
		//@ set env.calledAt_Spec2[floorID] = true; 
		// System.out.println("Button of floor  "+floorID+" is pressed");
		floorButtons[floorID] = true;}
	/*@ 
	 ensures env.calledAt_Spec9[floorID];
	 assignable \nothing; @*/
	public void pressInLiftFloorButton(int floorID) {
		//@ set env.calledAt_Spec9[floorID] = true; 
		pressInLiftFloorButton__wrappee__Base(floorID);	}
	public void resetFloorButton(int floorID) {
		floorButtons[floorID] = false;	}
	public /*@pure@*/  int getCurrentFloorID() {
		return currentFloorID;	}
	public /*@pure@*/ boolean areDoorsOpen() {
		return doors == DoorState.open;}
	public void timeShift() {
		
		if (areDoorsOpen() && weight > maximumWeight) {
			blocked = true;
			if (verbose) System.out.println("Elevator blocked due to overloading (weight:" + weight + " > maximumWeight:" + maximumWeight + ")");
		} else {
			blocked = false;
			if(handicap.size()>0) {
				shiftStrategy=new timeShiftStrategyHandicap();	}
			else {
				shiftStrategy=new timeShiftStrategy1();	}
			shiftStrategy.timeShift1(this, doors, getCurrentFloorID(), persons, env, currentHeading);}}
	 private boolean  stopRequestedAtCurrentFloor__wrappee__Base  () {
		return env.getFloor(currentFloorID).hasCall() 				|| floorButtons[currentFloorID] == true;}
	 private boolean  stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor  () { 
		if (isExecutiveFloorCalling() && !isExecutiveFloor(currentFloorID)) {
			return false;
		} else return stopRequestedAtCurrentFloor__wrappee__Base();	}
	public boolean stopRequestedAtCurrentFloor() {
		if (weight > maximumWeight*2/3) {
			return floorButtons[currentFloorID] == true;
		} else return stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor();	}
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
	public void continueInDirection(Direction dir, int currentFloorID) {
		
		ContinueInDirection state=new ContinueInDirection();
		state.continueInDirection__wrappee__Base(env,this,dir);	}
	public boolean isAnyLiftButtonPressed() {
		for (int i = 0; i < this.floorButtons.length; i++) {
			if (floorButtons[i]) return true;	}
		return false;}
private boolean anyStopRequested () {
		Floor[] floors = env.getFloors();
		for (int i = 0; i < floors.length; i++) {
			if (floors[i].hasCall()) return true;
			else if (this.floorButtons[i]) return true; 	}
		return false;		}
public /*@pure@*/  boolean buttonForFloorIsPressed(int floorID) {
		return this.floorButtons[floorID];}
	public /*@pure@*/  Direction getCurrentDirection() {
		return currentHeading;}
	public Environment getEnv() {
		return env;	}
	public boolean isEmpty() {
		return this.persons.isEmpty();}
	public boolean isIdle() {
		return !anyStopRequested();	}
	@Override
	public String toString() {
		return "Elevator " + (areDoorsOpen() ? "[_]" :  "[] ") + " at " + currentFloorID + " heading " + currentHeading;}
	int weight;
	public static final int maximumWeight = 100;
	int executiveFloor = 4;
	public /*@pure@*/  boolean isExecutiveFloor(int floorID) {return floorID == executiveFloor; }
	public /*@pure@*/  boolean isExecutiveFloorCalling() {
		for (Floor f : env.floors) 
			if (f.getFloorID() == executiveFloor && f.hasCall()) return true;
		return false;}
	private boolean blocked = false;}
