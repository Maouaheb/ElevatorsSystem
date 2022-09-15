package Main;
import ElevatorSystem.Environment;import ElevatorSystem.Person;
public class AliceCallState implements Call {
	@Override
	public Person call(Environment env) {
		// TODO Auto-generated method stub
		return new Person("alice", 40, 3, 0, env);}

}
