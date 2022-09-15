import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class ChuchCallState implements Call {

	@Override
	public Person call(Environment env) {
		// TODO Auto-generated method stub
		return new Person("chuck", 40, 1, 3, env);
	}

}
