import ElevatorSystem.Elevator;

public class PressInLift2 implements PressInLiftStrategy {

	@Override
	public void pressInLift(Elevator e) {
		if (!e.isEmpty())
			e.pressInLiftFloorButton(2);		
	}

}
