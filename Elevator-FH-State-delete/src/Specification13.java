import ElevatorSystem.Elevator;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;

public class Specification13 implements Specification{

	@Override
	public void specification(Environment env) {
		// TODO Auto-generated method stub

		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);
		Handicap h=(Handicap) a.Call(new HandicapCall());
		e.getHandicap().add(h);
		e.timeShift();
		/*a.Call(new AliceCallState());
		Person angelina = a.Call(new AngelinaCallState());
		while (env.getFloor(angelina.getOrigin()).hasCall()) {
			e.timeShift();
		}
		a.Call(new BobCallState());
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();*/

	}

}
