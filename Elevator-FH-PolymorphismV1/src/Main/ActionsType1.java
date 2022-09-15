package Main;
import ElevatorSystem.Elevator;import ElevatorSystem.ElevatorType1;import ElevatorSystem.Environment;import ElevatorSystem.Handicap;import ElevatorSystem.Person;
// sub-type of supertype actions
// we overwrite the functions defined in the class Actions
public class ActionsType1 extends Actions {
	// create person object and define its characteristics name, weight, origin, destination
	public Person person=new Person();
	private String name;
	private int weight;
	private int o;
	private int dest;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getO() {
		return o;
	}
	public void setO(int o) {
		this.o = o;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
	}
	
	public ActionsType1(Environment env, Elevator e) {
		super(env, e);	
		//aded for test the actions for disabled perosn
		setName("disabled");// TODO Auto-generated constructor stub
		}
	@Override
	public Person personCall(String name, int weight, int o, int dest, Environment env, ElevatorType1 e) {
		setName(name);
		person = new Person(name,weight,o,dest,env);
		System.out.println("call elevator for "+name);
		// if person is disabled
		if(name == "disabled") {
			person=new Handicap(name, weight, o, dest, env);	}
		return person;}
	
	public Handicap handicapCall(String name, int weight, int o, int dest, Environment env, ElevatorType1 e) {
			Handicap p= new Handicap(name,weight,o,dest,env);return p;	}
	// the button indicates the floor to where elevator must lift the person to it 
	@Override
	public void pressInLift(int button, ElevatorType1 e) {
		// if not the elevator is empty
		if (!e.persons.isEmpty())
			e.pressInLiftFloorButton(button);	}}
