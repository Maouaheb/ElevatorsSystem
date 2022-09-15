package ElevatorSystem; import java.util.ArrayList; import java.util.Arrays; import java.util.List; public  class Elevator {
	Environment env;

	boolean verbose;
	private boolean brokenDown=false;

	public boolean isBrokenDown() {
		return brokenDown;
	}


	public void setBrokenDown(boolean brokenDown) {
		this.brokenDown = brokenDown;
	}


	private int currentFloorID;

	public void setCurrentFloorID(int currentFloorID) {
		this.currentFloorID = currentFloorID;
	}

	public enum Direction {up { 
			@Override public Direction reverse() {return down;}	
		} , down { 
			@Override public Direction reverse() {return up;}
		};
	public abstract Direction reverse();}

	Direction currentHeading;


	 enum DoorState {open , close}

	;

	DoorState doors;

	boolean[] floorButtons;


	public Elevator(Environment env, boolean verbose) {
		
	}


	public Elevator(Environment env, boolean verbose, int floor, boolean headingUp) {
		
	}


	public boolean isBlocked  () {
		return blocked;
	}

	public boolean brokenDown(boolean brokenDown, AgentMaintenance agent) {
		if(brokenDown==true) {
			agent=new Schindler(81424242, "Schindler");
			
			return agent.resolve(this);
		}
		return true;
	}
	 private void  enterElevator__wrappee__Base  (ArrayList<Person>persons,Person p,ElevatorType1 e) {
		persons.add(p);
		p.enterElevator(e);
		if (verbose) System.out.println(p.getName() + " entered the Elevator at Landing " + getCurrentFloorID() + ", going to " + p.getDestination());
	}


	public void enterElevator(ArrayList<Person>persons,Person p, ElevatorType1 e,int weight) {
		enterElevator__wrappee__Base(persons,p,e);
		weight+=p.getWeight();
	}


	 private boolean  leaveElevator__wrappee__Base  (ArrayList<Person>persons,Person p) {
		if (persons.contains(p)) {
			persons.remove(p);
			p.leaveElevator();
			verbose=true;
			if (verbose) System.out.println(p.getName() + " left the Elevator at Landing " + currentFloorID);
			return true;
		} else return false;
	}


	 private boolean  leaveElevator__wrappee__Weight  (ArrayList<Person>persons,Person p,int weight) {
		if (leaveElevator__wrappee__Base(persons,p)) {
			weight-=p.getWeight();
			return true;
		} else return false;
	}


	public boolean leaveElevator(ArrayList<Person>persons,Person p,int weight) { 
		if (leaveElevator__wrappee__Weight(persons,p,weight)) {
			if (persons.isEmpty())
				Arrays.fill(floorButtons, false);
			return true;
		} else return false;
	}

	/*@ 
	 ensures env.calledAt_Spec2[floorID];
	 assignable floorButtons[*]; @*/
	 private void  pressInLiftFloorButton__wrappee__Base(int floorID) {
		//@ set env.calledAt_Spec2[floorID] = true;
		 if(floorID == 0) {
			 floorButtons[floorID] = true;
			}
			else floorButtons[floorID-1] = true;
		
	}

	/*@ 
	 ensures env.calledAt_Spec9[floorID];
	 assignable \nothing; @*/
	public void pressInLiftFloorButton(int floorID) {
		//@ set env.calledAt_Spec9[floorID] = true; 
		pressInLiftFloorButton__wrappee__Base(floorID);
	}


	public  void resetFloorButton(int floorID) {
		floorButtons[floorID] = false;
	}


	public /*@pure@*/  int getCurrentFloorID() {
		return currentFloorID;
	}


	public /*@pure@*/ boolean areDoorsOpen() {
		return doors == DoorState.open;
	}


	 public boolean  stopRequestedAtCurrentFloor__wrappee__Base  () {
		return env.getFloor(currentFloorID).hasCall() 
				|| floorButtons[currentFloorID] == true;
	}


	 public boolean  stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor  () { 
		if (isExecutiveFloorCalling() && !isExecutiveFloor(currentFloorID)) {
			return false;
		} else return stopRequestedAtCurrentFloor__wrappee__Base();
	}


	public boolean stopRequestedAtCurrentFloor(int maximumWeight,int weight) {
	/*	if (weight > maximumWeight*2/3) {
			return floorButtons[currentFloorID] == true;
		} else*/ return stopRequestedAtCurrentFloor__wrappee__ExecutiveFloor();
	}


	 public void  continueInDirection__wrappee__Base  (Direction dir, int currentFloorID) {
		currentHeading = dir;
		if (currentHeading == Direction.up) {
			if (env.isTopFloor(currentFloorID)) {
				
				currentHeading = currentHeading.reverse();
			} else {
				currentFloorID = currentFloorID + 1;
				this.setCurrentFloorID(currentFloorID);

			}
		} else { 
			if (currentFloorID == 0) {
				
				currentHeading = currentHeading.reverse();
			}
			else {
				currentFloorID = currentFloorID - 1;
				this.setCurrentFloorID(currentFloorID);

			}
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
	public void continueInDirection(Direction dir,int floorCurrentID) {
		continueInDirection__wrappee__Base(dir,floorCurrentID);
	}


	public boolean isAnyLiftButtonPressed() {
		for (int i = 0; i < floorButtons.length; i++) {
			if (floorButtons[i]) return true;
		}
		return false;
	}


	 public boolean  stopRequestedInDirection__wrappee__Base  (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls) {
		Floor[] floors = env.getFloors();
		if (dir == Direction.up) {
			if (env.isTopFloor(currentFloorID)) return false;
			for (int i = currentFloorID+1; i < floors.length; i++) {
				if (respectFloorCalls && floors[i].hasCall()) return true;
				if (respectInLiftCalls && floorButtons[i]) return true; 
			}
			return false;
		} else {
			if (currentFloorID == 0) return false;
			for (int i = currentFloorID-1; i >= 0; i--) {
				if (respectFloorCalls && floors[i].hasCall()) return true;
				if (respectInLiftCalls && floorButtons[i]) return true;
			}
			return false;
		}
	}


	 public boolean  stopRequestedInDirection__wrappee__ExecutiveFloor  (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls) {
		if (isExecutiveFloorCalling()) {
			if (verbose) ;
			return ((currentFloorID < executiveFloor)  == (dir == Direction.up));
			
		} else return stopRequestedInDirection__wrappee__Base(dir, respectFloorCalls, respectInLiftCalls);
	}


	public boolean stopRequestedInDirection (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls, int maximumWeight,int weight) {
	/*	if (weight > maximumWeight*2/3 && isAnyLiftButtonPressed()) {
			if (verbose) System.out.println("over 2/3 threshold, ignoring calls from FloorButtons until weight is below 2/3*threshold");
			return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, false, respectInLiftCalls);
		} else*/ return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, respectFloorCalls, respectInLiftCalls);
	}


	public boolean anyStopRequested () {
		Floor[] floors = env.getFloors();
		for (int i = 0; i < floors.length; i++) {
			if (floors[i].hasCall()) return true;
			else if (floorButtons[i]) return true; 
		}
		return false;		
	}


	public /*@pure@*/  boolean buttonForFloorIsPressed(int floorID) {
		return floorButtons[floorID];
	}


	public /*@pure@*/  Direction getCurrentDirection() {
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
		return "Elevator " + (areDoorsOpen() ? "[_]" :  "[] ") + " at " + currentFloorID + " heading " + currentHeading;
	}


	int executiveFloor = 4;


	public /*@pure@*/  boolean isExecutiveFloor(int floorID) {return floorID == executiveFloor; }


	public /*@pure@*/  boolean isExecutiveFloorCalling() {
		for (Floor f : env.floors) 
			if (f.getFloorID() == executiveFloor && f.hasCall()) return true;
		return false;
	}


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
