import ElevatorSystem.Elevator; import ElevatorSystem.Environment; import ElevatorSystem.Person;
import StrategyActions.AliceCall;
import StrategyActions.AngelinaCall;
import StrategyActions.BigMacCall;
import StrategyActions.BobCall;
import StrategyActions.CallStrategy;
import StrategyActions.ChuckCall;
import StrategyActions.HandicapCall;
import StrategyActions.MonicaCall; 
public class ActionsContext {
	Environment env;
	CallStrategy callStrategy;
	Elevator e;


	public ActionsContext(Environment env, Elevator e) {
		super();
		if (env.getFloors().length < 5)
			throw new IllegalArgumentException(
					"These Actions assume at least 5 Floors!");
		this.env = env;
		this.e = e;
	}
	public Person callStrategy(String name, Environment environment) {
		if(name.equals("Bob")) {
			System.out.println("Bob");
			callStrategy=new BobCall();
			
		}
		if(name.equals("Alice")) {
			callStrategy=new AliceCall();
		}
		if(name.equals("Angelina")) {
			callStrategy=new AngelinaCall();
		}
		if(name.equals("BigMac")) {
			callStrategy=new BigMacCall();	
		}
		if(name.equals("Chuck")) {
			callStrategy=new ChuckCall();	
		}
		if(name.equals("Monica")) {
			callStrategy=new MonicaCall();	
		}
		if(name.equals("disabled")) {
			callStrategy=new HandicapCall();	
			
		}
		return  callStrategy.call(name,environment);
	}

	public void pressInLift(int num, Elevator e) {
		PressInLiftStrategy press=null;
		if(num == 0) {
			press=new PressInLift0();
		}
		if(num == 1) {
			press=new PressInLift1();
		}
		if(num == 2) {
			press=new PressInLift2();
		}
		if(num == 3) {
			press=new PressInLift3();
		}
		if(num == 4) {
			press=new PressInLift4();
		}
		press.pressInLift(e);
	}



}
