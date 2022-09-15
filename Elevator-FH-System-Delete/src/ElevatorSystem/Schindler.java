package ElevatorSystem;

public class Schindler {
	private long number;
	private String name;
	
	public Schindler(long number, String name) {
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
		System.out.println("calling Schindler agent for resolving break down of "+elevator.getClass().getCanonicalName());
		return true;
	}

}
