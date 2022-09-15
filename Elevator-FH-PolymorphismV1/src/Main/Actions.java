package Main;
import ElevatorSystem.Elevator;import ElevatorSystem.ElevatorType1;import ElevatorSystem.Environment;import ElevatorSystem.Person;
//Super-type action that defines the set of actions requested to the elevator
public class Actions {
	
	Environment env;
	Elevator e;
	public Actions(Environment env, Elevator e) {
		super();
		if (env.getFloors().length < 5)
			throw new IllegalArgumentException(				"These Actions assume at least 5 Floors!");
		this.env = env;
		this.e = e;}
	// person calls elevator the lift it
	public Person personCall(String name, int weight, int o, int dest, Environment env,ElevatorType1 e) {
		return null;}
	// press button indicating the floor to demand a lift request
	public void pressInLift(int button,ElevatorType1 e) {}}
