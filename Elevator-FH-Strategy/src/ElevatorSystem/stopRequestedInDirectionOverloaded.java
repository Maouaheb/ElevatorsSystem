package ElevatorSystem;
import ElevatorSystem.Elevator.Direction;
public class stopRequestedInDirectionOverloaded implements stopRequestedInDirectionStrategy {
	@Override
	public boolean stopRequestedInDirection(Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls, Elevator e, Environment env) {
		// TODO Auto-generated method stub
		if (e.weight > e.maximumWeight*2/3 && e.isAnyLiftButtonPressed()) {
			if (e.verbose) System.out.println("over 2/3 threshold, ignoring calls from FloorButtons until weight is below 2/3*threshold");
			return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, false, respectInLiftCalls,e,env);
		} else return stopRequestedInDirection__wrappee__ExecutiveFloor(dir, respectFloorCalls, respectInLiftCalls,e,env);	}
	private boolean stopRequestedInDirection__wrappee__ExecutiveFloor(Direction dir, boolean respectFloorCalls,
			boolean respectInLiftCalls, Elevator e, Environment env) {
		// TODO Auto-generated method stub
		StopRequestedInDirection strategy=new StopRequestedInDirection();
		 if (e.isExecutiveFloorCalling()) {
			if (e.verbose) System.out.println("Giving Priority to Executive Floor");
			return ((e.getCurrentFloorID() < e.executiveFloor)  == (dir == Direction.up));		
		} else   return strategy.stopRequestedInDirection__wrappee__Base(env, e, dir, respectFloorCalls, respectInLiftCalls);}}
