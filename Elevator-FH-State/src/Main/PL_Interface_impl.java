package Main;
import ElevatorSystem.Elevator; import ElevatorSystem.Environment; import ElevatorSystem.EvilPerson; import ElevatorSystem.Person; import java.util.List; import java.util.ArrayList; import java.lang.Throwable;
public  class PL_Interface_impl implements PL_Interface {
	public static boolean executedUnimplementedAction = false;
	public static List<String> actionHistory = new ArrayList<String>();
	public static int cleanupTimeShifts = 24;
	public static final int NUM_FLOORS = 5;
	private static boolean verbose = false;
	private static boolean isAbortedRun = false;
	public Specification spec=null;
	public static int variation=-1;
	public static int specification=13;

	public static void main(String[] args) {
		try {
			PL_Interface_impl impl = new PL_Interface_impl();
			args = new String[1];
			verbose = true;
			variation=-1;
			specification=13;
			impl.start(specification,variation);
			System.out.println("no Exception");
		} catch (Throwable e) {
			System.out.println("Caught Exception: " + e.getClass() + " "				+ e.getMessage());		e.printStackTrace();	}}
public void start(int specification, int variation) throws Throwable {
	try {
		if (verbose) 
			System.out.print("Started Elevator PL with Specification " + specification +  ", Variation: " +variation);
		test(specification, variation);
	} catch (Throwable e) {
		throw e;
	}finally {			}	}
	public List<String> getExecutedActions() {
		return actionHistory;	}
	public boolean isAbortedRun() {
		return isAbortedRun;	}
	/*@ 
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec1.length; !env.calledAt_Spec1[i]);
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec2.length; !env.calledAt_Spec2[i]);
	 assignable env, isAbortedRun; @*/
	 private void  test__wrappee__Base  (int specification, int variation) {
		if (variation==-1) {
			//spec is the state
			switch (specification) {
			case 1: spec=new Specification1();spec.specification(env);break;
			case 2: spec=new Specification2();spec.specification(env);break;
			case 3: spec=new Specification3();spec.specification(env);break;
			case 8: isAbortedRun=true;break;
			case 9: spec=new Specification9();spec.specification(env);break;
			case 10: isAbortedRun=true;break;
			case 11: isAbortedRun=true;break;
			case 13: spec=new Specification13();spec.specification(env);break;
			case 14: spec=new Specification14();spec.specification(env);break;
			}
		} else {
			randomSequenceOfActions(variation);		}	}
	/*@ 
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec9.length; !env.calledAt_Spec9[i]);
	 assignable \nothing; @*/
	public void test(int specification, int variation) {
		test__wrappee__Base(specification, variation);}
	Environment env;
	Elevator e;
	public void randomSequenceOfActions(int maxLength) {
		cleanupTimeShifts=6 * maxLength; 
		env = new Environment(NUM_FLOORS);		
		if (getBoolean()) {	
			e = new Elevator(env, verbose);
			actionHistory.add("StartFromBottom");
		} else {			
			e = new Elevator(env, verbose, 4, false);
			actionHistory.add("StartFromTop");		}
		Actions a = new Actions(env, e);
		int counter = 0;
		while (counter < maxLength) {
			counter++;
			int action = getIntegerMinMax(0, 7); 
			String actionName = "";
			switch (action) {
			case 0:	a.Call(new BobCallState()); actionName = "bobCall"; break;
			case 1: a.Call(new AliceCallState()); actionName = "aliceCall"; break;
			case 2:	a.Call(new AngelinaCallState()); actionName = "angelinaCall"; break;
			case 3:	a.Call(new ChuchCallState()); actionName = "chuckCall"; break;
			case 4:	a.Call(new MonicaCallState());	actionName = "monicaCall"; break;
			case 5:	a.Call(new BigMacCallState());	actionName = "bigMacCall"; break;
			case 6: e.timeShift(); actionName = "1TS"; break; 
			case 7:
				actionName = "3TS";
				for (int i = 0; i < 3; i++) {
					e.timeShift();		}				
				break;
			default:
				throw new InternalError(
						"getIntegerMinMax produced illegal value:" + action);			}			actionHistory.add(actionName);		
			if (e.isBlocked()) {			
				return;		}	}
		for (counter = 0; counter < cleanupTimeShifts && ! e.isBlocked(); counter++) {
			if (e.isIdle())
				return;
			e.timeShift();		}	}
	public void modifiedrandomSequenceOfActions(int maxLength) {
		env = new Environment(NUM_FLOORS);
		Elevator e;
		if (getBoolean()) {			
			e = new Elevator(env, verbose);
			actionHistory.add("StartFromBottom");
		} else {		
			e = new Elevator(env, verbose, 4, false);
			actionHistory.add("StartFromTop");
		}		Actions a = new Actions(env, e);
		int counter = 0;
		while (counter < maxLength) {
			counter++;
			int action = getIntegerMinMax(0, 7); 
			String actionName = "";			
			boolean action0 = getBoolean();
			boolean action1 = getBoolean();
			boolean action2 = getBoolean();
			boolean action3 = getBoolean();
			boolean action4 = getBoolean();
			boolean action5 = getBoolean();
			boolean action6 = getBoolean();
			boolean action7 = action6 ? false : getBoolean();		
			if (counter < maxLength && action0) {a.Call(new BobCallState()); actionHistory.add("bobCall"); counter++; }
			if (counter < maxLength && action1) {a.Call(new AliceCallState()); actionHistory.add("aliceCall"); counter++; }
			if (counter < maxLength && action2) {a.Call(new AngelinaCallState()); actionHistory.add("angelinaCall"); counter++; }
			if (counter < maxLength && action3) {a.Call(new ChuchCallState()); actionHistory.add("chuckCall"); counter++; }
			if (counter < maxLength && action4) {a.Call(new MonicaCallState());	actionHistory.add("monicaCall"); counter++; }
			if (counter < maxLength && action5) {a.Call(new BigMacCallState());	actionHistory.add("bigMacCall"); counter++; }
			if (counter < maxLength && action6) {e.timeShift(); actionHistory.add("1TS"); counter++; } 
			if (counter < maxLength && action7) {
				actionHistory.add("3TS");
				for (int i = 0; i < 3; i++) {
					e.timeShift();				}			}
			if (e.isBlocked()) {				
				return;			}		}
		for (counter = 0; counter < cleanupTimeShifts && ! e.isBlocked(); counter++) {
			if (e.isIdle())
				return;
			e.timeShift();		}	}
	public static int getIntegerMinMax(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));	}
	public static boolean getBoolean() {
		return Math.random() >= 0.5;	}
	static String listToString(List<String> list) {
		String ret = "";
		for (String s : list) {
			ret = ret + " " + s;	}
		return ret;	}

}
