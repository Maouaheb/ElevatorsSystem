package ElevatorSystem; 

public   class  Environment {
	
	Floor[] floors;

	public Environment  (int numFloors) {
		// initiate the floors
		floors = new Floor[numFloors];
		for (int i = 0; i < numFloors; i++) {
			floors[i] = new Floor(i);
		}
	
		floors = new Floor[numFloors];
		for (int i = 0; i < numFloors; i++) {
			floors[i] = new Floor(i);
		}
	}

	
	
	public Floor getFloor  (int id) {
		return floors[id];
	}

	
	public Floor[] getFloors  () {
		return floors;
	}

	// check if the current floor referenced by id is the top
	public boolean isTopFloor  (int id) {
		return id == floors.length-1;
	}


}
