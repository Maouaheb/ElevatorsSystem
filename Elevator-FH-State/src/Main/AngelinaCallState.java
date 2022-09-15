package Main;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class AngelinaCallState implements Call{

	@Override
	public Person call(Environment env) {
		// TODO Auto-generated method stub
		 return new Person("angelina", 40, 2, 1, env);
	}

}
