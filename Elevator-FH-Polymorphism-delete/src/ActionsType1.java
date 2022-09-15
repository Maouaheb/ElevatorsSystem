import ElevatorSystem.Elevator;
import ElevatorSystem.ElevatorType1;
import ElevatorSystem.Environment;
import ElevatorSystem.Floor;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;

public class ActionsType1 extends Actions {
	public Person person=new Person();
	public ActionsType1(Environment env, Elevator e) {
		super(env, e);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Person personCall(String name, int weight, int o, int dest, Environment env, ElevatorType1 e) {
		/*Person p=new Person(name, weight, o, dest, env);
		e.persons.add(p);*/
		
			
		
		person = new Person(name,weight,o,dest,env);

		System.out.println("call elevator for "+name);
		if(name == "disabled") {
			person=new Handicap(name, weight, o, dest, env);
			//e.getHandicap().add((Handicap) person);
		}

		return person;
	}
	
	public Handicap handicapCall(String name, int weight, int o, int dest, Environment env, ElevatorType1 e) {
		/*Person p=new Person(name, weight, o, dest, env);
		e.persons.add(p);*/
		
			
		
			Handicap p= new Handicap(name,weight,o,dest,env);

		System.out.println("call elevator for "+name);

		return p;
	}
	@Override
	public void pressInLift(int button, ElevatorType1 e) {
		if (!e.persons.isEmpty())
			e.pressInLiftFloorButton(button);
	}

}
