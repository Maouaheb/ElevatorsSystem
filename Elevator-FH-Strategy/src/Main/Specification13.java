package Main;
import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
public class Specification13 implements SpecificationsStrategy {
	@Override
	public void specification() {
		Environment env = new Environment(5);
		Elevator e = new Elevator(env, false);
		ActionsContext a = new ActionsContext(env, e);
		Handicap h=(Handicap) a.callStrategy("disabled", env);
		e.getHandicap().add(h);
		e.timeShift();}}
