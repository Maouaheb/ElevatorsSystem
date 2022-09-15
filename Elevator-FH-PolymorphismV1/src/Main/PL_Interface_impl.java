package Main;

import ElevatorSystem.AgentMaintenance;
import ElevatorSystem.ElevatorType1;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Throwable;
// Sub-type of the interface-impl-supertype
public class PL_Interface_impl extends PL_Interface_SuperType_impl implements PL_Interface {
	public static boolean executedUnimplementedAction = false;
	// history of performed actions by the elevator
	public static List<String> actionHistory = new ArrayList<String>();
	private static int cleanupTimeShifts = 24;
	// We suppose the environment contains 5 floors
	private static final int NUM_FLOORS = 5;
	// verbose is boolean attribute that checks always a specific detail
	private static boolean verbose = false;
	
	public static void main(String[] args) {
		try {
			PL_Interface_impl impl = new PL_Interface_impl();
			args = new String[1];
			verbose = true;
			// start the program by specification 13 with variation = -1
			impl.start(2, -1);
			System.out.println("no Exception");
		} catch (Throwable e) {
			System.out.println("Caught Exception: " + e.getClass() + " " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void start(int specification, int variation) throws Throwable {
		try {
			if (verbose)
				System.out
						.print("Started Elevator PL with Specification " + specification + ", Variation: " + variation);
			test(specification, variation);
		} catch (Throwable e) {
			throw e;
		} finally {
		}
	}

	/*
	 * @ ensures (\forall int i; 0 <= i && i < env.calledAt_Spec1.length;
	 * !env.calledAt_Spec1[i]); ensures (\forall int i; 0 <= i && i <
	 * env.calledAt_Spec2.length; !env.calledAt_Spec2[i]); assignable env,
	 * isAbortedRun; @
	 */
	/*
	 * @ ensures (\forall int i; 0 <= i && i < env.calledAt_Spec9.length;
	 * !env.calledAt_Spec9[i]); assignable \nothing; @
	 */
	@Override
	public void test(int specification, int variation) {
		// select the correct specification
		super.test(specification, variation);
		if (variation == -1) {
			Specification();
		} else {
			randomSequenceOfActions(variation);
		}
	}

	Environment env;

	public void randomSequenceOfActions(int maxLength) {
		// initiate the environment with 5 floors
		cleanupTimeShifts = 6 * maxLength;
		env = new Environment(NUM_FLOORS);
		if (getBoolean()) {
			// if we did not define the starting floor so we suppose that elevator is in bottom floor
			e = new ElevatorType1(env, verbose);
			// store the action
			actionHistory.add("StartFromBottom");
		} else {
			// elevator is in top floor
			e = new ElevatorType1(env, verbose, 4, false);
			actionHistory.add("StartFromTop");
		}
		// we select the sub-type Action1
		Actions a = new ActionsType1(env, e);
		// the number of performed actions
		int counter = 0;
		ArrayList<String> name = new ArrayList<String>();
		while (counter < maxLength) {
			counter++;
			// set a random value to choose a random action
			int action = getIntegerMinMax(0, 7);
			// the list of persons names
			List<String> names = Arrays.asList("Bob", "Angelina", "Monica", "Madona", "Hiba");
			Random r = new Random();
			int randomitem = r.nextInt(names.size());
			String randomElement = names.get(randomitem);
			// the list of persons weight
			List<Integer> weights = Arrays.asList(40, 30, 50, 60, 70);
			Random w = new Random();
			int randomweight = w.nextInt(weights.size());
			int randomWeight = weights.get(randomweight);
			String actionName = randomElement + " call";
			// call the operation to lift the person by the elevator
			a.personCall(randomElement, randomWeight, getIntegerMinMax(1, 5), getIntegerMinMax(1, 5), env, e);
			actionHistory.add(actionName);
			if (e.isBlocked()) {
				return;
			}
		}
		// continue lifting operations till attending cleanup number
		for (counter = 0; counter < cleanupTimeShifts && !e.isBlocked(); counter++) {
			if (e.isIdle())
				return;
			e.timeShift();
		}
	}
	// we override the specification to make the elevator lifting the user
	@Override
	public void Specification() {
		// instantiate the env , e , and a
		super.Specification();
		// create the person who will call
		Handicap h = (Handicap) super.a.personCall("disabled", 70, 3, 1, super.env, super.e);
		e.getHandicap().add(h);
		// launch the lift system
		e.timeShift();
		AgentMaintenance agent = null;
		e.brokenDown(true, agent);
	}
}
