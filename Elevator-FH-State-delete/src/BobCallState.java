import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class BobCallState implements Call{

	@Override
	public Person call(Environment env) {
		// TODO Auto-generated method stub
		return new Person("bob", 40, 4, 0, env);
	}

}
