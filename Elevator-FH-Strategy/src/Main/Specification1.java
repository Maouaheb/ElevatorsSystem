package Main;
import ElevatorSystem.Elevator;import ElevatorSystem.Environment;
public class Specification1 implements SpecificationsStrategy{
	@Override
	public void specification( ) {
		// TODO Auto-generated method stub
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		ActionsContext a = new ActionsContext(env, e);
		a.callStrategy("Bob",env);
		a.callStrategy("Angelina",env);
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();	}}
