package se.t2i.fittslawex.task;

import java.text.NumberFormat;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class TimerController implements Runnable {

	private final Color DEFAULT_COLOR;
	
	private boolean runState;
	private Label label;

	private long curVal;
	private NumberFormat formatter;

	private Display display;
	Runnable dispCallback = new Runnable(){public void run(){label.setText(formatter.format((double)curVal/1000) + " s");}};
	
	public TimerController(Label label) {
		formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumFractionDigits(3);
		formatter.setMaximumFractionDigits(3);
		
		this.label = label;
		display = label.getDisplay();
	
		DEFAULT_COLOR = label.getBackground();
		
		runState = false;
		reset();
	}

	public void run() {
		long startTime = System.currentTimeMillis();

		while(isRunning()) {
			long curTime = System.currentTimeMillis();
			curVal += curTime - startTime;
			startTime = curTime;
			display.syncExec(dispCallback);
		}
	}
	
	public void start() {
		setRunning(true);
		new Thread(this).start();
	}

	public void stop() {
		setRunning(false);
	}

	public void reset() {
		curVal = 0L;
		label.setText(formatter.format((double)curVal/1000) + " s");
	}

	public void highlightTimer(Color col) {
		label.setBackground(col);
	}

	public void unhighlightTimer() {
		label.setBackground(DEFAULT_COLOR);
	}
	
	synchronized private boolean isRunning() {
		return runState;
	}

	synchronized private void setRunning(boolean runState) {
		this.runState  = runState;
	}
}
