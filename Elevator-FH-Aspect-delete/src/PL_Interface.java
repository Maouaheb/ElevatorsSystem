import java.util.List;  

public  interface  PL_Interface {
	
	public List<String> getExecutedActions();
	//launch elevator
	public void start(int specification, int variation) throws Throwable;

	//choose specification
	public void checkOnlySpecification(int specID);

	//cancel
	public boolean isAbortedRun();


}
