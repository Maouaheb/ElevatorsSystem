package ElevatorSystem;

//the schindler is type of the maintenance agency
public class Schindler extends AgentMaintenance {

	public Schindler(long number, String name) {
		super(number, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean resolve(Elevator elevator) {
		// TODO Auto-generated method stub
		System.out.println("calling "+getName()+" society with phone number "+getNumber());
		return super.resolve(elevator);
	}
	

}
