package ElevatorSystem;

public class Handicap extends Person{
	// the elevator can shift normal and disabled persons
	public Handicap(String name, int weight, int origin, int destination, Environment env) {
		super(name, weight, origin, destination, env);
		// TODO Auto-generated constructor stub
		env.getFloor(origin).setHandicapCall(true);
	}

}
