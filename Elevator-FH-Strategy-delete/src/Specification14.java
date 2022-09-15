import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class Specification14 implements SpecificationsStrategy{

	@Override
	public void specification() {
		// TODO Auto-generated method stub
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		ActionsContext a = new ActionsContext(env, e);

		Person bm = a.callStrategy("BigMac", env);


		while (env.getFloor(bm.getOrigin()).hasCall()) {
			e.timeShift();
		}
		a.callStrategy("Bob", env);
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		
	}

}
