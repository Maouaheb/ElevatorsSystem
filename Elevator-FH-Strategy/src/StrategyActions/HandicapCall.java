package StrategyActions;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;

public class HandicapCall implements CallStrategy {
String name=null;

	@Override
	public Person call(String name, Environment env) {
		// TODO Auto-generated method stub
		System.out.println("Disabled person pressed the button");
		 return new Handicap("Disabled", 50, 3, 1, env);
	}

}
