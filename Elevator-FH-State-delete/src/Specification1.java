import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;

public class Specification1 implements Specification{

	@Override
	public void specification(Environment env) {
		// TODO Auto-generated method stub
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.Call(new BigMacCallState());
		a.Call(new AngelinaCallState());
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
	}

}
