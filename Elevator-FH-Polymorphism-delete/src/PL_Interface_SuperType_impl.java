import java.util.ArrayList;
import java.util.List;

import ElevatorSystem.Elevator;
import ElevatorSystem.ElevatorType1;
import ElevatorSystem.Environment;
import ElevatorSystem.Handicap;
import ElevatorSystem.Person;

public class PL_Interface_SuperType_impl implements PL_Interface {
	public static List<String> actionHistory = new ArrayList<String>();
	private static boolean isAbortedRun = false;
	private static int cleanupTimeShifts = 24;
	public static ElevatorType1 e;
	Environment env;
	ActionsType1 a;
	Handicap h=null;
	@Override
	public List<String> getExecutedActions() {
		return actionHistory;
	}


	@Override
	public void start(int specification, int variation) throws Throwable {
		// TODO Auto-generated method stub
		
	}
	public void test(int specification, int variation) {
	}

	@Override
	public boolean isAbortedRun() {
		// TODO Auto-generated method stub
		return isAbortedRun;
	}
	public void Specification() {
		env = new Environment(5);
		e = new ElevatorType1(env, true);
		 a = new ActionsType1(env, e);
		
		
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
	int getIntegerMinMax(int min, int max) {
		//System.out.println(random);
		int r=min + (int) (Math.random() * (max - min + 1));
	
		return r;
}

}
