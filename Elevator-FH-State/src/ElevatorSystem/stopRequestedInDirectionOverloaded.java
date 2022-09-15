package ElevatorSystem;
import ElevatorSystem.Elevator.Direction;
public class stopRequestedInDirectionOverloaded implements stopRequestedInDirectionStrategy {
	@Override
	public boolean stopRequestedInDirection(Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls, Elevator e, Environment env) {
		// TODO Auto-generated method stub
		if (e.getWeight() > e.maximumWeight*2/3 && e.isAnyLiftButtonPressed()) {
			
			if (e.verbose) System.out.println("over 2/3 threshold, ignoring calls from FloorButtons until weight is below 2/3*threshold");
			return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, false, respectInLiftCalls,e, env);
		} else return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, respectFloorCalls, respectInLiftCalls,e,env);	}
	private boolean stopRequestedInDirection__wrappee__ExecutiveFloor(Direction dir, boolean respectFloorCalls,
			boolean respectInLiftCalls,Elevator e, Environment env) {
		// state of stop requested is instantiated
		StopRequestedInDirection state=null;
		 if (e.isExecutiveFloorCalling()) {
			if (e.verbose) System.out.println("Giving Priority to Executive Floor");
			return ((e.getCurrentFloorID() < e.executiveFloor)  == (dir == Direction.up));		
		} else    state = new StopRequestedInDirection();
			return state.stopRequestedInDirection__wrappee__Base(env,e,dir, respectFloorCalls, respectInLiftCalls);
	}
}
