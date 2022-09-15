package StrategyActions;

import ElevatorSystem.Environment;
import ElevatorSystem.Person;

public interface CallStrategy {
	public Person call(String name,Environment env );

}
