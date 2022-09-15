package ElevatorSystem; public class EvilPerson extends Person {
	private int[] additionalButtons;
	public EvilPerson(String name, int weight, int origin, int destination, int[] additionalButtons,
			Environment env) {
		super(name, weight, origin, destination, env);
		this.additionalButtons = additionalButtons;}
	// evil person entered to the elevator and try to make wrong actions=> aspect is injected here

	@Override
	public void enterElevator(Elevator e) {
		super.enterElevator(e);
		for (Integer btnID : additionalButtons)
			e.pressInLiftFloorButton(btnID);	}}
