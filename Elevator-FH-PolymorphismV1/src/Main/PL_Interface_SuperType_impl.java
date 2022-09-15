package Main;

import java.util.ArrayList;
import java.util.List;
import ElevatorSystem.ElevatorType1;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
// Define the supertime 
public class PL_Interface_SuperType_impl implements PL_Interface {
	// the actions history of the elevator
	public static List<String> actionHistory = new ArrayList<String>();
	// cancel an execution of the program
	private static boolean isAbortedRun = false;
	// number of lifting elevator operations 
	private static int cleanupTimeShifts = 24;
	// we consider the sub type elevator
	public static ElevatorType1 e;
	Environment env;
	ActionsType1 a;
	Handicap h = null;

	@Override
	public List<String> getExecutedActions() {
		return actionHistory;
	}
	// the method that starts the elevator with specification
	@Override
	public void start(int specification, int variation) throws Throwable { // TODO Auto-generated method stub
	}

	public void test(int specification, int variation) {
	}

	@Override
	public boolean isAbortedRun() {
		// TODO Auto-generated method stub
		return isAbortedRun;
	}

	public void Specification() {
		// the current environment contains 5 floors
		env = new Environment(5);
		// We use Elevator of type 1 with actions of type 1
		e = new ElevatorType1(env, true);
		a = new ActionsType1(env, e);
	}
	// get random value to choose later a random action

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
	// get random value to choose later a random action
	int getIntegerMinMax(int min, int max) {
		int r = min + (int) (Math.random() * (max - min + 1));
		return r;
	}
}
