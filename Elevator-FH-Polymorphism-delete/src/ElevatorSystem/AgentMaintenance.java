package ElevatorSystem;

public class AgentMaintenance {
	private long number;
	private String name;
	
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
	public boolean resolve(Elevator elevator ) {
		System.out.println("calling the agent for resolving break down of "+elevator.getClass().getCanonicalName());
		return true;
	}
}
