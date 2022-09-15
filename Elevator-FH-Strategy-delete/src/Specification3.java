import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class Specification3 implements SpecificationsStrategy {

	@Override
	public void specification() {
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false, 4, false);
		ActionsContext a = new ActionsContext(env, e);

		Person bob = a.callStrategy("Bob", env);
		while (env.getFloor(bob.getOrigin()).hasCall())
			e.timeShift();
		
		
		e.timeShift();
		
		
		
		
		a.callStrategy("Bob", env);
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();		
	}

}
