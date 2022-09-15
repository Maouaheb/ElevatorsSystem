 import ElevatorSystem.Elevator; import ElevatorSystem.Environment; import ElevatorSystem.EvilPerson;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;
import ElevatorSystem.Elevator.Direction;

import java.util.List; import java.util.ArrayList; import java.lang.Throwable; public  class PL_Interface_impl implements PL_Interface {
	public static boolean executedUnimplementedAction = false;

	public static List<String> actionHistory = new ArrayList<String>();

	private static int cleanupTimeShifts = 24;

	private static final int NUM_FLOORS = 5;

	private static boolean verbose = false;

	private static boolean isAbortedRun = false;


	public static void main(String[] args) {
		try {
			PL_Interface_impl impl = new PL_Interface_impl();
			args = new String[1];
			verbose = true;
			impl.start(3,-1);
			System.out.println("no Exception");
		} catch (Throwable e) {
			System.out.println("Caught Exception: " + e.getClass() + " "
					+ e.getMessage());
			e.printStackTrace();
		}
	}


	public void start(int specification, int variation) throws Throwable {
	try {
		if (verbose) 
			System.out.println("Started Elevator PL with Specification " + specification +  ", Variation: " +variation);
		test(specification, variation);
	} catch (Throwable e) {
		throw e;
	}finally {
		
	}
	}



	public List<String> getExecutedActions() {
		return actionHistory;
	}


	public boolean isAbortedRun() {
		return isAbortedRun;
	}

	/*@ 
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec1.length; !env.calledAt_Spec1[i]);
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec2.length; !env.calledAt_Spec2[i]);
	 assignable env, isAbortedRun; @*/
	 private void  test__wrappee__Base  (int specification, int variation) {
		if (variation==-1) {
			switch (specification) {
			case 1: Specification1();break;
			case 2: Specification2();break;
			case 3: Specification3();break;
			case 8: isAbortedRun=true;break;
			case 9: Specification9();break;
			case 10: isAbortedRun=true;break;
			case 11: isAbortedRun=true;break;
			case 13: Specification13();break;
			case 14: Specification14();break;
			}
		} else {
			randomSequenceOfActions(variation);
		}
	}

	/*@ 
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec9.length; !env.calledAt_Spec9[i]);
	 assignable \nothing; @*/
	public void test(int specification, int variation) {
		test__wrappee__Base(specification, variation);
	}

	Environment env;


	public void randomSequenceOfActions(int maxLength) {
		cleanupTimeShifts=6 * maxLength; 
		env = new Environment(NUM_FLOORS);
		Elevator e;
		if (getBoolean()) {
			
			e = new Elevator(env, verbose);
			actionHistory.add("StartFromBottom");
		} else {
			
			e = new Elevator(env, verbose, 4, false);
			actionHistory.add("StartFromTop");
		}
		Actions a = new Actions(env, e);

		int counter = 0;
		while (counter < maxLength) {
			counter++;
			int action = getIntegerMinMax(0, 7); 
			String actionName = "";
			switch (action) {
			case 0:	a.bobCall(); actionName = "bobCall"; break;
			case 1: a.aliceCall(); actionName = "aliceCall"; break;
			case 2:	a.angelinaCall(); actionName = "angelinaCall"; break;
			case 3:	a.chuckCall(); actionName = "chuckCall"; break;
			case 4:	a.monicaCall();	actionName = "monicaCall"; break;
			case 5:	a.bigMacCall();	actionName = "bigMacCall"; break;
			case 6: e.timeShift(); actionName = "1TS"; break; 
			case 7:
				actionName = "3TS";
				for (int i = 0; i < 3; i++) {
					e.timeShift();
				}
				
				break;
			default:
				throw new InternalError(
						"getIntegerMinMax produced illegal value:" + action);
			}
			actionHistory.add(actionName);
			
			if (e.isBlocked()) {
				
				return;
			}
		}
		for (counter = 0; counter < cleanupTimeShifts && ! e.isBlocked(); counter++) {
			if (e.isIdle())
				return;
			e.timeShift();
		}
	}


	public void modifiedrandomSequenceOfActions(int maxLength) {
		env = new Environment(NUM_FLOORS);
		Elevator e;
		if (getBoolean()) {
			
			e = new Elevator(env, verbose);
			actionHistory.add("StartFromBottom");
		} else {
			
			e = new Elevator(env, verbose, 4, false);
			actionHistory.add("StartFromTop");
		}
		Actions a = new Actions(env, e);

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
			
			
			if (counter < maxLength && action0) {a.bobCall(); actionHistory.add("bobCall"); counter++; }
			if (counter < maxLength && action1) {a.aliceCall(); actionHistory.add("aliceCall"); counter++; }
			if (counter < maxLength && action2) {a.angelinaCall(); actionHistory.add("angelinaCall"); counter++; }
			if (counter < maxLength && action3) {a.chuckCall(); actionHistory.add("chuckCall"); counter++; }
			if (counter < maxLength && action4) {a.monicaCall();	actionHistory.add("monicaCall"); counter++; }
			if (counter < maxLength && action5) {a.bigMacCall();	actionHistory.add("bigMacCall"); counter++; }
			if (counter < maxLength && action6) {e.timeShift(); actionHistory.add("1TS"); counter++; } 
			if (counter < maxLength && action7) {
				actionHistory.add("3TS");
				for (int i = 0; i < 3; i++) {
					e.timeShift();
				}
			}
			if (e.isBlocked()) {
				
				return;
			}
		}
		for (counter = 0; counter < cleanupTimeShifts && ! e.isBlocked(); counter++) {
			if (e.isIdle())
				return;
			e.timeShift();
		}
	}


	public void Specification1() {
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.bigMacCall();
		a.angelinaCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
	}


	public void Specification2() {
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.bigMacCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
	}


	public void Specification3() {
		env = new Environment(5);
		Elevator e = new Elevator(env, false, 4, false);
		Actions a = new Actions(env, e);

		Person bob = a.bobCall();
		while (env.getFloor(bob.getOrigin()).hasCall())
			e.timeShift();
		
		
		e.timeShift();
		
		
		
		
		a.bobCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
	}


	public void Specification9() {
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		a.bigMacCall();
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
	}


	public void Specification13() {
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);
		a.bobCall();
		a.aliceCall();
		
		Person angelina = a.angelinaCall();
		while (env.getFloor(angelina.getOrigin()).hasCall()) {
			e.timeShift();
		}
		
		for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		
		Handicap h=a.handicapCall();
		e.getHandicaps().add(h);
		e.timeShift();
		
		
	}


	public void Specification14() {
		env = new Environment(5);
		Elevator e = new Elevator(env, false);
		Actions a = new Actions(env, e);

		Person bm = a.bigMacCall();
		//e.getPersons().add(bm);
		
		while (env.getFloor(bm.getOrigin()).hasCall()) {
			e.timeShift();
		}
		Person bob= a.bobCall();
		//e.getPersons().add(bob);
		 for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++)
			e.timeShift();
		Handicap h=a.handicapCall();
		e.getHandicaps().add(h);
		e.timeShift();
	}


	public static int getIntegerMinMax(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}


	public static boolean getBoolean() {
		return Math.random() >= 0.5;
	}


	static String listToString(List<String> list) {
		String ret = "";
		for (String s : list) {
			ret = ret + " " + s;
		}
		return ret;
	}


}
