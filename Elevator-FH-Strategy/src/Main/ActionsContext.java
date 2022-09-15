package Main;
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
	private int num;
	public PressInLiftStrategy press=null;

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Environment env;
	public CallStrategy CallStrategy;
	public Elevator e;
	public ActionsContext(Environment env, Elevator e) {
		super();
		if (env.getFloors().length < 5)
			throw new IllegalArgumentException(			"These Actions assume at least 5 Floors!");
		this.env = env;
		this.e = e;}
	public Person callStrategy(String name, Environment environment) {
		//choose the corresponding call  strategy
		if(name.equals("Bob")) {
			System.out.println("Bob");		CallStrategy=new BobCall();				}
		if(name.equals("Alice")) {
			CallStrategy=new AliceCall();}
		if(name.equals("Angelina")) {
			CallStrategy=new AngelinaCall();}
		if(name.equals("BigMac")) {
			CallStrategy=new BigMacCall();	}
		if(name.equals("Chuck")) {
			CallStrategy=new ChuckCall();	}
		if(name.equals("Monica")) {
			CallStrategy=new MonicaCall();		}
		if(name.equals("disabled")) {
			CallStrategy=new HandicapCall();		}
		return  CallStrategy.call(name,environment);}
	public void pressInLift(int num, Elevator e) {
		// call the press lift strategy
		setNum(num);
		if(num == 0) {
			press=new PressInLift0();}
		if(num == 1) {
			press=new PressInLift1();	}
		if(num == 2) {
			press=new PressInLift2();		}
		if(num == 3) {
			press=new PressInLift3();		}
		if(num == 4) {
			press=new PressInLift4();		}
		press.pressInLift(e);	}}
