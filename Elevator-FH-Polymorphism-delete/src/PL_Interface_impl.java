import ElevatorSystem.AgentMaintenance;
import ElevatorSystem.Elevator;
import ElevatorSystem.ElevatorType1;
import ElevatorSystem.Environment;
import ElevatorSystem.EvilPerson;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Throwable;

public class PL_Interface_impl extends PL_Interface_SuperType_impl implements PL_Interface {
	public static boolean executedUnimplementedAction = false;

	public static List<String> actionHistory = new ArrayList<String>();

	private static int cleanupTimeShifts = 24;

	private static final int NUM_FLOORS = 5;

	private static boolean verbose = true;

	public static void main(String[] args) {
		try {
			PL_Interface_impl impl = new PL_Interface_impl();
			args = new String[1];
			verbose = true;
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
		super.test(specification, variation);
		if (variation == -1) {
			Specification();

		} else {
			randomSequenceOfActions(variation);
		}
	}

	Environment env;

	public void randomSequenceOfActions(int maxLength) {
		cleanupTimeShifts = 6 * maxLength;
		env = new Environment(NUM_FLOORS);
		if (getBoolean()) {

			e = new ElevatorType1(env, verbose);
			actionHistory.add("StartFromBottom");
		} else {

			e = new ElevatorType1(env, verbose, 4, false);
			actionHistory.add("StartFromTop");
		}
		Actions a = new ActionsType1(env, e);

		int counter = 0;
		ArrayList<String> name = new ArrayList<String>();
		while (counter < maxLength) {
			counter++;
			int action = getIntegerMinMax(0, 7);
			List<String> names = Arrays.asList("Bob", "Angelina", "Monica", "Madona", "Hiba");
			Random r = new Random();
			int randomitem = r.nextInt(names.size());
			String randomElement = names.get(randomitem);
			List<Integer> weights = Arrays.asList(40, 30, 50, 60, 70);
			Random w = new Random();
			int randomweight = w.nextInt(weights.size());
			int randomWeight = weights.get(randomweight);
			String actionName = randomElement + " call";
			a.personCall(randomElement, randomWeight, getIntegerMinMax(1, 5), getIntegerMinMax(1, 5), env, e);
			actionHistory.add(actionName);
			if (e.isBlocked()) {

				return;
			}
		}
		for (counter = 0; counter < cleanupTimeShifts && !e.isBlocked(); counter++) {
			if (e.isIdle())
				return;
			e.timeShift();
		}
	}

	@Override
	public void Specification() {
		super.Specification();
		System.out.println("Specification sub type");
		Handicap h=(Handicap)super.a.personCall("disabled", 70, 3, 1, super.env, super.e);
		//h = (Handicap) a.personCall("Disabled", 60, 3, 1, env, e);
		e.getHandicap().add(h);
		e.timeShift();
		AgentMaintenance agent=null;
		e.brokenDown(true, agent);
		/*
		 * for (int i = 0; i < cleanupTimeShifts && !e.isBlocked(); i++) {
		 * e.timeShift();
		 * 
		 * }
		 * 
		 * e.timeShift();
		 */

	}

}
