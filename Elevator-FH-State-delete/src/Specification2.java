import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;

public class Specification2 implements Specification{

	@Override
	public void specification(Environment env) {
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.Call(new BigMacCallState());
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();		
	}

}
