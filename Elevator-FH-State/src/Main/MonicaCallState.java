package Main;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class MonicaCallState implements Call {

	@Override
	public Person call(Environment env) {
		// TODO Auto-generated method stub
		return new Person("monica", 30, 0, 1, env);
	}

}
