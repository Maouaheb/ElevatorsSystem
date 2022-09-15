package ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class Floor {
	private final int thisFloorID;
	private boolean elevatorCall = false;
	private List<Person> waiting = new ArrayList<Person>();
	private Environment env;

	public Floor(Environment env, int id) {
		this.env = env;
		thisFloorID = id;
	}

	public int getFloorID() {
		return this.thisFloorID;
	}

	/*
	 * @ ensures env.calledAt_Spec1[floor.getFloorID()]; assignable elevatorCall; @
	 */
	public void callElevator() {
		// @set env.calledAt_Spec1[floor.getFloorID()] = true;
		elevatorCall = true;
	}

	public void reset() {
		elevatorCall = false;
	}

	public /* @pure@ */ boolean hasCall() {
		return elevatorCall;
	}

	public void processWaitingPersons(ArrayList<Person> persons, ElevatorType1 e, int weight) {
		for (Person p : waiting) {
			e.enterElevator(persons, p, e, weight);
		}
		waiting.clear();
		reset();
	}
	// add person who would like to be shifted in the waiting list of the elevator

	public void addWaitingPerson(Person person) {
		waiting.add(person);
		callElevator();
	}
}
