import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class BigMacCallState implements Call{

	@Override
	public Person call(Environment env) {
		// TODO Auto-generated method stub
		return new Person("BigMac", 150, 1, 3, env);
	}

}
