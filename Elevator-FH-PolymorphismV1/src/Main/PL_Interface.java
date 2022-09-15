package Main;

import java.util.List;

public interface PL_Interface {
// the list of executed actions
	public List<String> getExecutedActions();

// starting the process
	public void start(int specification, int variation) throws Throwable;

// cancel elevator lifting process

	public boolean isAbortedRun();

}
