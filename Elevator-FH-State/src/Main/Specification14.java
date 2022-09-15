package Main;
import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class Specification14 implements Specification{

	@Override
	public void specification(Environment env) {
		// TODO Auto-generated method stub
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		Person bm = a.Call(new BobCallState());

		while (env.getFloor(bm.getOrigin()).hasCall()) {
			e.timeShift();
		}
		a.Call(new BobCallState());
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();

	}

}
