package Main;
import ElevatorSystem.Elevator;import ElevatorSystem.Environment;
public class Specification2 implements SpecificationsStrategy{
	@Override
	public void specification() {
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		ActionsContext a = new ActionsContext(env, e);
		a.callStrategy("BigMac",env);
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();			}}
