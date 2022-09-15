import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;

public class HandicapCall implements Call {

	@Override
	public Person call(Environment env) {
		// TODO Auto-generated method stub
		return new Handicap("Disabled", 50, 3, 1, env);
	}

}
