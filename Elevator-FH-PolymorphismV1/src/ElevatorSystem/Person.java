package ElevatorSystem; public class Person {
	// each person has weight
	private int weight;
	// origin is the number of floor from which the person request a shift

	private int origin;
	// destination is the number of floor to which the person wants to go

	private int destination;
	// name of person

	private String name;
	// check if destination of the person is reached by the elevator or not

	private boolean destinationReached = false;
	public int getWeight() {
		return weight;}
	public Person(String name, int weight, int origin, int destination, Environment env) {
		super();
		this.name = name;
		this.weight = weight;
		this.origin = origin;
		this.destination = destination;
		env.getFloor(origin).addWaitingPerson(this);}
	public Person() {	}
	public String getName() {
		return name;	}
	public int getOrigin() {
		return origin;}
	public int getDestination() {
		return destination;	}
	public void leaveElevator() {
		this.destinationReached = true;	}
	public boolean isDestinationReached() {
		return destinationReached;	}
	public void enterElevator(Elevator e) {		
		e.pressInLiftFloorButton(destination);	}}
