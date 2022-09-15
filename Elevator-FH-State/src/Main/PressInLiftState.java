package Main;
import ElevatorSystem.Elevator;

public class PressInLiftState implements PressInLift {
	private int number;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public void pressInLift(Elevator e,int num) {
		setNumber(num);
			if(! e.isEmpty()) {
				e.pressInLiftFloorButton(num);

			}
	}

}
