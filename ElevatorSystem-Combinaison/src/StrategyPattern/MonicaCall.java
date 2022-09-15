package StrategyPattern;

import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class MonicaCall implements CallStrategy {

	@Override
	public Person call(String name,  Environment env) {
		// TODO Auto-generated method stub
		return new Person("monica", 30, 0, 1, env);
	}

}
