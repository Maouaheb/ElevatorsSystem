import ElevatorSystem.Elevator;

public class PressInLiftState implements PressInLift {

	@Override
	public void pressInLift(Elevator e,int num) {

			if(! e.isEmpty()) {
				e.pressInLiftFloorButton(num);

			}
	}

}
