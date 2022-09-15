package Main;
import ElevatorSystem.Elevator;

public class PressInLift1 implements PressInLiftStrategy{

	@Override
	public void pressInLift(Elevator e) {
		if (!e.isEmpty())
			e.pressInLiftFloorButton(1);		
	}

}
