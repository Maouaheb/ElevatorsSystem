package ElevatorSystem;
// the second state is when the elevator is stop when doing the lift

 class StopRequestedInDirection implements LiftElevatorState {
	 // this function returns true if there is a request to stop the elevator when doing the lift meaning if there another floor call
	 public boolean  stopRequestedInDirection__wrappee__Base (Environment env, Elevator e,Elevator.Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls) {
		// get the list of floors	
		 Floor[] floors = env.getFloors();
		 
			if (dir == Elevator.Direction.up) {
				// if the direction is up and the elevator is actually on top floor so the function returns false
				if (env.isTopFloor(e.getCurrentFloorID())) return false;
				// check this for all the floors after the current floor
				for (int i = e.getCurrentFloorID()+1; i < floors.length; i++) {
					// next floors call the elevator so our elevator will stop there 
					if (respectFloorCalls && floors[i].hasCall()) return true;
					if (respectInLiftCalls && e.floorButtons[i]) return true; }
				return false;
			} else {
				// if the direction is down and the elevator is actually on bottom floor so the function returns false
				if (e.getCurrentFloorID() == 0) return false;
				for (int i = e.getCurrentFloorID()-1; i >= 0; i--) {
					// next floors call the elevator so our elevator will stop there 
					if (respectFloorCalls && floors[i].hasCall()) return true;
					if (respectInLiftCalls && e.floorButtons[i]) return true;		}
				return false;
			}
		}


}
