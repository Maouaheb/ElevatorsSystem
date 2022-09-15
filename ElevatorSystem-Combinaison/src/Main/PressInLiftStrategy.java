package Main;
import ElevatorSystem.Elevator;

public interface PressInLiftStrategy {
	// strategy can be to press button 1 or 2 or 3 or 4
	public void pressInLift(Elevator e);
}
