package StrategyActions;

import ElevatorSystem.Environment;
import ElevatorSystem.Person;
// different call strategies can be AliceCall, AngelinaCall, BigMacCall, ..MonicaCall
public interface CallStrategy {
	public Person call(String name,Environment env );

}
