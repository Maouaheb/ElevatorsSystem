package Main;
import java.util.ArrayList;import java.util.List;import ElevatorSystem.Elevator;import ElevatorSystem.Environment;
public class PL_Interface_SuperType_impl implements PL_Interface {
	public static List<String> actionHistory = new ArrayList<String>();
	private static boolean isAbortedRun = false;
	private static int cleanupTimeShifts = 24;
	public  Elevator e;
	Environment env;
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
		return isAbortedRun;	}
	public void Specification() {
		env = new Environment(5);
		e = new Elevator(env, true);
	//	ActionsContext a = new ActionsContext(env, e);		
		System.out.println("Specification super type");
		//a.callStrategy("BigMac", env);
		}
	public static boolean getBoolean() {
		return Math.random() >= 0.5;}
	static String listToString(List<String> list) {
		String ret = "";
		for (String s : list) {
			ret = ret + " " + s;
		}	return ret;	}
	int getIntegerMinMax(int min, int max) {
		int r=min + (int) (Math.random() * (max - min + 1));	
		return r;
}

}
