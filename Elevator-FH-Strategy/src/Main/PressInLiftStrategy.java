package Main;
import ElevatorSystem.Elevator;

public interface PressInLiftStrategy {
	// The strategy can be press button 1, button 2 ... button 4
	public void pressInLift(Elevator e);
}
