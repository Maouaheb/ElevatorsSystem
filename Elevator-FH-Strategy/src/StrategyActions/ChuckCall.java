package StrategyActions;

import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class ChuckCall implements CallStrategy{

	@Override
	public Person call(String name,  Environment env) {
		// TODO Auto-generated method stub
		return new Person("chuck", 40, 1, 3, env);
	}

}
