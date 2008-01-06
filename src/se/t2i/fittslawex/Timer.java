package se.t2i.fittslawex;

import org.eclipse.swt.widgets.Label;

public class Timer implements Runnable {

	private boolean runState;
	private Label label; 
	
	public Timer(Label label) {
		this.label = label;
		runState = false;
		reset();
	}

	public void reset() {
		label.setText(Long.toString(0L) + " ms");
	}

	public void run() {
		long startTime = System.currentTimeMillis();
		while(isRunning()) {
			label.setText(Long.toString(System.currentTimeMillis() - startTime) + " ms");
		}
	}
	
	synchronized private boolean isRunning() {
		return runState;
	}

	public void start() {
		setRunning(true);
		new Thread(this).start();
	}

	synchronized private void setRunning(boolean runState) {
		this.runState  = runState;
	}

	public void stop() {
		setRunning(false);
	}
	
}
