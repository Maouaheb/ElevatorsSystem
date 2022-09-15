import ElevatorSystem.Elevator;

public class PressInLift4 implements PressInLiftStrategy {

	@Override
	public void pressInLift(Elevator e) {
		if (!e.isEmpty())
			e.pressInLiftFloorButton(4);		
	}

}
