import ElevatorSystem.Elevator; import ElevatorSystem.Environment;
import ElevatorSystem.Floor;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person; public class Actions {
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

	public Handicap handicapCall() {
		for (int i=0; i<env.getFloors().length;i++) {
			Floor floor=env.getFloors()[i];
			if(floor.getFloorID()==3) {
				floor.setHandicapCall(true);
			}
		}
		return new Handicap("disabled",60, 3,2,env);
	}
	public Person bobCall() {
		return new Person("bob", 90, 4, 0, env);
	}


	public Person aliceCall() {
		return new Person("alice", 40, 3, 0, env);
	}


	public Person angelinaCall() {
		return new Person("angelina", 40, 2, 1, env);
	}


	public Person chuckCall() {
		return new Person("chuck", 40, 1, 3, env);
	}


	public Person monicaCall() {
		return new Person("monica", 30, 0, 1, env);
	}


	public Person bigMacCall() {
		return new Person("BigMac", 150, 1, 3, env);
	}


	public void pressInLift0() {
		if (!e.isEmpty())
			e.pressInLiftFloorButton(0);
	}


	public void pressInLift1() {
		if (!e.isEmpty())
			e.pressInLiftFloorButton(1);
	}


	public void pressInLift2() {
		if (!e.isEmpty())
			e.pressInLiftFloorButton(2);
	}


	public void pressInLift3() {
		if (!e.isEmpty())
			e.pressInLiftFloorButton(3);
	}


	public void pressInLift4() {
		if (!e.isEmpty())
			e.pressInLiftFloorButton(4);
	}


}
