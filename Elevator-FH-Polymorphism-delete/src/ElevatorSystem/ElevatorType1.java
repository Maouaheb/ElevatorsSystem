package ElevatorSystem;

import java.util.ArrayList;
import java.util.List;

import ElevatorSystem.Elevator.Direction;
import ElevatorSystem.Elevator.DoorState;


public class ElevatorType1 extends Elevator {
	boolean blocked = false;
	boolean brokenDown=false;
	public static int maximumWeight = 5000;
	public ArrayList<Person> persons = new ArrayList<Person>();
	private List<Handicap> handicap = new ArrayList<Handicap>();

	public List<Handicap> getHandicap() {
		return handicap;
	}
	public void setHandicap(List<Handicap> handicap) {
		this.handicap = handicap;
	}
public int weight=0;
public int currentFloorID = 0;

	public ElevatorType1(Environment env, boolean verbose) {
		super(env, verbose);
		this.verbose = verbose;
		this.currentHeading = Direction.up;
		this.currentFloorID = 0;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];
		// TODO Auto-generated constructor stub
	}
	public ElevatorType1(Environment env, boolean verbose, int floor, boolean headingUp) {
		super(env,verbose,floor,headingUp);
		this.verbose = verbose;
		this.currentHeading = (headingUp ? Direction.up : Direction.down);
		this.currentFloorID = floor;
		this.doors = DoorState.open;
		this.env = env;
		this.floorButtons = new boolean[env.floors.length];
	}
	public boolean brokenDown(boolean brokenDown, AgentMaintenance agent) {
		if(brokenDown==true) {
			agent=new Schindler(81424242, "Schindler");
			
			return agent.resolve(this);
		}
		return true;
	}

	public void timeShift() {
		if (areDoorsOpen() && weight > maximumWeight) {
			blocked = true;
			if (verbose) System.out.println("Elevator blocked due to overloading (weight:" + weight + " > maximumWeight:" + maximumWeight + ")");
		} else {
			blocked = false;
			timeShift__wrappee__ExecutiveFloor();
		}
	}
	public void timeShift__wrappee__ExecutiveFloor() {
		timeShift__wrappee__Base();
	}
	private void  timeShift__wrappee__Base() {
		 if (handicap.size()>0) {
				disabledLift();
			
				//timeShift__wrappee__Base();
				}
	
	if (stopRequestedAtCurrentFloor(maximumWeight,weight)) {
		
		doors = DoorState.open;
		for (Person p: new ArrayList<Person>(persons)) {
			if (p.getDestination() == currentFloorID) {
				leaveElevator(persons,p,weight);					
			}
		}
		//persons will enter the elevator
		env.getFloor(currentFloorID).processWaitingPersons(persons,this,weight);
		resetFloorButton(currentFloorID);
		//Pas d'arrêt dans l'étage courant
	} else {
		if (doors == DoorState.open)  {
			doors = DoorState.close;
			
		}
		if (stopRequestedInDirection(currentHeading, true, true,maximumWeight,weight)) {
			

			continueInDirection(currentHeading,currentFloorID);
		} else if (stopRequestedInDirection(currentHeading.reverse(), true, true,maximumWeight,weight)) {
			

			continueInDirection(currentHeading.reverse(),currentFloorID);
		} else {
			

			continueInDirection(currentHeading,currentFloorID);
		}
	}
	//@ set env.calledAt_Spec1[currentFloorID] = env.calledAt_Spec1[currentFloorID] && areDoorsOpen() ? false : env.calledAt_Spec1[currentFloorID]
}
	private void disabledLift() {
		 for (Handicap p: new ArrayList<Handicap>(handicap)) {
			 if(p.getClass().getCanonicalName() == "ElevatorSystem.Handicap") {

					while(p.getOrigin()<currentFloorID) {
						continueInDirection(Direction.down,currentFloorID);
						currentFloorID=super.getCurrentFloorID();

					}
					while(p.getOrigin()> currentFloorID) {
						//System.out.println(p.getOrigin()+" going to "+p.getDestination()+" current floor "+currentFloorID);

						continueInDirection(Direction.up,currentFloorID);
						currentFloorID=super.getCurrentFloorID();

					}
					if(p.getOrigin()==currentFloorID) {
						
						enterElevator(persons, p, this, weight);
						//System.out.println(p.getName()+" entered to the elevator in the floor nubmer "+p.getOrigin()+" going to  "+p.getDestination());
						
						while(p.getDestination()<currentFloorID) {
							continueInDirection(Direction.down,currentFloorID);
							currentFloorID=super.getCurrentFloorID();

						}
						while(p.getDestination()> currentFloorID) {

							continueInDirection(Direction.up,currentFloorID);
							currentFloorID=super.getCurrentFloorID();

						}
						if(p.getDestination() == currentFloorID) {
							leaveElevator(persons,p,weight);
							//System.out.println(p.getName()+" left to the elevator in the floor nubmer "+p.getDestination());

							handicap.remove(p);
						}
					}

			 }
		 }
	}
}
