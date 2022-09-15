package Main;
import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;

public class Specification13 implements Specification{

	@Override
	public void specification(Environment env) {
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);
		Handicap h=(Handicap) a.Call(new HandicapCall());
		e.getHandicap().add(h);
		e.timeShift();}

}
