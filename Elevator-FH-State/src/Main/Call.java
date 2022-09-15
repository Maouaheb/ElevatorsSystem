package Main;
import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public interface Call {
	// Call can be AliceCall, BigMacCall, chuckCall, etc
	Person call(Environment env);

}
