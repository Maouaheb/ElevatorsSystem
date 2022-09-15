import ElevatorSystem.Elevator; import ElevatorSystem.Environment; import ElevatorSystem.Person; public class Actions {
	Environment env;

	Elevator e;


	public Actions(Environment env, Elevator e) {
		super();
		if (env.getFloors().length < 5)
			throw new IllegalArgumentException(
					"These Actions assume at least 5 Floors!");
		this.env = env;
		this.e = e;
	}


	public Person Call(Call call) {
		return call.call(env);
	}
	public void pressInLift(Elevator e,int num) {
		PressInLift press=new PressInLiftState();
		press.pressInLift(e, num);
	}
	
}
