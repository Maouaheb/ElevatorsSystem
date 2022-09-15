package Main;
import ElevatorSystem.Elevator;

public class PressInLift0 implements PressInLiftStrategy {

	@Override
	public void pressInLift(Elevator e) {
		// TODO Auto-generated method stub
		if (!e.isEmpty())
			e.pressInLiftFloorButton(0);
		
	}

}
