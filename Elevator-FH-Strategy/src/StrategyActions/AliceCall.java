package StrategyActions;import ElevatorSystem.Environment;import ElevatorSystem.Person;
public class AliceCall implements CallStrategy{
	@Override
	public Person call(String name, Environment env) {
		// TODO Auto-generated method stub
		return new Person("alice", 40, 3, 0, env);	}}
