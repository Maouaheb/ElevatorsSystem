package ElevatorSystem;
import ElevatorSystem.Elevator.Direction;
public interface stopRequestedInDirectionStrategy {
	// the stop requested state can be overloded or basic
	public boolean stopRequestedInDirection (Direction dir, boolean respectFloorCalls, boolean respectInLiftCalls, Elevator e, Environment env);

}
