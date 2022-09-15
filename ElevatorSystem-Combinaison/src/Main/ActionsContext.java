package Main;
import ElevatorSystem.Elevator; import ElevatorSystem.Environment; import ElevatorSystem.Person;
import StrategyPattern.HandicapCall;
import StrategyPattern.AliceCall;
import StrategyPattern.AngelinaCall;
import StrategyPattern.BigMacCall;
import StrategyPattern.BobCall;
import StrategyPattern.CallStrategy;
import StrategyPattern.ChuckCall;
import StrategyPattern.MonicaCall; 
public class ActionsContext {
	Environment env;
	public CallStrategy callStrategy;
	public PressInLiftStrategy press;
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
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
		// choose the call strategy
		callStrategy=null;
		if(name.equals("Bob")) {
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
		// choose the press lift strategy
		 press=null;
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
