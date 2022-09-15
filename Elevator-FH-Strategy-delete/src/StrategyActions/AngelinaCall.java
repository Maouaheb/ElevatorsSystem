package StrategyActions;

import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class AngelinaCall implements CallStrategy {

	@Override
	public Person call(String name, Environment env) {
		// TODO Auto-generated method stub
		return new Person("angelina", 40, 2, 1, env);
	}

}
