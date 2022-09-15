import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class Specification3 implements Specification {

	@Override
	public void specification(Environment env) {
		// TODO Auto-generated method stub
		env = new Environment(5);
		Elevator e = new Elevator(env, false, 4, false);
		Actions a = new Actions(env, e);

		Person bob = a.Call(new BobCallState());
		while (env.getFloor(bob.getOrigin()).hasCall())
			e.timeShift();
		
		
		e.timeShift();
		
		
		
		
		a.Call(new BobCallState());
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();

	}

}
