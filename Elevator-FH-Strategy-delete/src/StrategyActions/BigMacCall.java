package StrategyActions;

import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public class BigMacCall implements CallStrategy {

	@Override
	public Person call(String name,  Environment env) {
		// TODO Auto-generated method stub
		return new Person("BigMac", 150, 1, 3, env);
	}
	

}
