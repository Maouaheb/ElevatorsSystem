package StrategyPattern;

import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class BobCall implements CallStrategy {

	@Override
	public Person call(String name, Environment env) {
		// TODO Auto-generated method stub
		return new Person("bob", 40, 4, 0, env);
	}

}
