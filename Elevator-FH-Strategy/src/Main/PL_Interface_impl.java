package Main;
import ElevatorSystem.Elevator; import ElevatorSystem.Environment; import java.util.List;import java.util.Random;import java.util.ArrayList;import java.util.Arrays;import java.lang.Throwable; public  class PL_Interface_impl implements PL_Interface {
	public static boolean executedUnimplementedAction = false;
	public static List<String> actionHistory = new ArrayList<String>();
	public static int cleanupTimeShifts = 24;
	public static final int NUM_FLOORS = 5;
	private static boolean verbose = false;
	private static boolean isAbortedRun = false;
	public SpecificationsStrategy spec=null;
	public static int variation=-1;
	public static int specification=13;
	public static void main(String[] args) {
		try {
			PL_Interface_impl impl = new PL_Interface_impl();
			args = new String[1];
			verbose = true;
			impl.start(specification,variation);
		System.out.println("no Exception");
		} catch (Throwable e) {
			System.out.println("Caught Exception: " + e.getClass() + " "			+ e.getMessage());	e.printStackTrace();}	}
	public void start(int specification, int variation) throws Throwable {
	try {
		if (verbose) 
			System.out.print("Started Elevator PL with Specification " + specification +  ", Variation: " +variation);
		test(specification, variation);
	} catch (Throwable e) {
		throw e;
	}finally {		}	}
public List<String> getExecutedActions() {
		return actionHistory;}
	public boolean isAbortedRun() {
		return isAbortedRun;}
	/*@ 
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec1.length; !env.calledAt_Spec1[i]);
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec2.length; !env.calledAt_Spec2[i]);
	 assignable env, isAbortedRun; @*/
	 private void  test__wrappee__Base  (int specification, int variation) {
		 // instantiate the strategy specification
		if (variation==-1) {
			switch (specification) {
			case 1: spec=new Specification1();break;
			case 2: spec=new Specification1();break;
			case 3: spec=new Specification3();break;
			case 8: isAbortedRun=true;break;
			case 9: spec=new Specification9();break;
			case 10: isAbortedRun=true;break;
			case 11: isAbortedRun=true;break;
			case 13: spec=new Specification13();break;
			case 14: spec=new Specification14();break;
			}
			spec.specification();
		} else {
			randomSequenceOfActions(variation);	}}
	/*@ 
	 ensures (\forall int i; 0 <= i && i < env.calledAt_Spec9.length; !env.calledAt_Spec9[i]);
	 assignable \nothing; @*/
	public void test(int specification, int variation) {
		test__wrappee__Base(specification, variation);}
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
			actionHistory.add("StartFromTop");	}
		ActionsContext a = new ActionsContext(env, e);
		int counter = 0;
		while (counter < maxLength) {
			counter++;
			int action = getIntegerMinMax(0, 7); 
			String actionName = "";
			List<String> names = Arrays.asList("Bob", "Angelina", "Monica", "Alice","BigMac","Chuck");
			Random r = new Random();
		    int randomitem = r.nextInt(names.size());
		    String randomElement = names.get(randomitem);
			actionHistory.add(actionName);
			a.callStrategy(randomElement, env);
			if (e.isBlocked()) {				
				return;			}		}
		for (counter = 0; counter < cleanupTimeShifts && ! e.isBlocked(); counter++) {
			if (e.isIdle())
				return;
			e.timeShift();	}}
	public static int getIntegerMinMax(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));}
	public static boolean getBoolean() {
		return Math.random() >= 0.5;}
	static String listToString(List<String> list) {
		String ret = "";
		for (String s : list) {
			ret = ret + " " + s;}
		return ret;}}
