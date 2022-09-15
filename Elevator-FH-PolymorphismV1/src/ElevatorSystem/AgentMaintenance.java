package ElevatorSystem;
// the maintenance agency that is called whenever the elevator is broken down

public class AgentMaintenance {
	private long number;
	private String name;
	// te agent has a number and a name
	public AgentMaintenance(long number, String name) {
		super();
		this.number = number;
		this.name = name;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// if the call is performed and the connection was linked with agent we suppose the problem is resolve and retun true otherwise false
	public boolean resolve(Elevator elevator ) {
		System.out.println("calling the agent for resolving break down of "+elevator.getClass().getCanonicalName());
		return true;
	}
}
