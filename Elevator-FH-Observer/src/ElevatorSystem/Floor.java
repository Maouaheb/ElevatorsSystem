package ElevatorSystem; import java.util.ArrayList; import java.util.List;

import java.util.Observable;
import java.util.Observer;

import ObserverPattern.ObsHandicap; 
public class Floor extends Observable {
// floor is considered as observable
	private final int thisFloorID;
	// check if there is a call launched from disabled person
	private boolean handicapCall=false;
	public boolean isHandicapCall() {
		return handicapCall;}
	public void setHandicapCall(boolean handicapCall) {
		this.handicapCall = handicapCall;
		System.out.println(handicapCall);
		if(handicapCall==true) {
			// add the handicap observer
		obs.add(new ObsHandicap());	
		this.notifyObservers();		}	}
	ArrayList<Observer> obs=new ArrayList<Observer>();
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for( int i=0;i<obs.size();i++) {
			// call the observer
			obs.get(i).update(this, null);	}}
	private boolean elevatorCall = false;
	private List<Person> waiting = new ArrayList<Person>();
	private Environment env;
	public Floor(Environment env, int id) {
		this.env = env;
		thisFloorID = id;	}
	public int getFloorID() {
		return this.thisFloorID;	}	
	/*@ 
	 ensures env.calledAt_Spec1[floor.getFloorID()];
	 assignable elevatorCall; @*/
	public void callElevator() {
		//@set env.calledAt_Spec1[floor.getFloorID()] = true; 
		elevatorCall = true;}
	public void reset() {
		elevatorCall = false;	}
	public /*@pure@*/  boolean hasCall() {
		return elevatorCall;	}
	public void processWaitingPersons(Elevator e) {
		for (Person p : waiting) {
			e.enterElevator(p);		}
		waiting.clear();
		reset();	}
	// add person who would like to be shifted in the waiting list of the elevator
	public void addWaitingPerson(Person person) {
		waiting.add(person);
		callElevator();	}}
