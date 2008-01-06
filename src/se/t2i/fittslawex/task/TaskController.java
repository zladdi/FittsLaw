package se.t2i.fittslawex.task;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import se.t2i.fittslawex.Model;
import se.t2i.fittslawex.gui.MainWindow;

public class TaskController implements Listener, MovementEventSubscriber {
	private static final String ELAPSED_TIME_STR = "Elapsed Time";
	private static final String BUTTON_START = "Start";
	private static final String BUTTON_CANCEL = "Cancel";
	
	private final Color RUNNING_COL;
	private final Color HIT_COL;
	
	private TimerController t;
	private PositionListener p;
	private MainWindow view;
	
	public TaskController(Model model, MainWindow view) {
		this.view = view;
		RUNNING_COL = view.getLabel1().getDisplay().getSystemColor(SWT.COLOR_RED);
		HIT_COL = view.getLabel1().getDisplay().getSystemColor(SWT.COLOR_GREEN);
		
		view.getLabel1().setAlignment(SWT.RIGHT);
		view.setLabel1Text(ELAPSED_TIME_STR);
		
		t = new TimerController(view.getLabel1());
		p = new PositionListener(model.getMovable(), model.getTarget());
		p.addSubscriber(this);
		view.getMainCanvas().addMouseMoveListener(p);
		view.getButton().addListener(SWT.Selection, this);
		reset();
	}
	
	private void reset() {
		view.getButton().setText(BUTTON_START);
		view.getButton().setData(BUTTON_START);
		view.getSpinner().setEnabled(true);
	}
	
	public void handleEvent(Event event) {
		Object data = event.widget.getData();
		if (BUTTON_START.equals(data)) {
			// Disable the spinner
			view.getSpinner().setEnabled(false);
			// Rename the button
			view.getButton().setText(BUTTON_CANCEL);
			view.getButton().setData(BUTTON_CANCEL);
			t.reset();
			t.highlightTimer(RUNNING_COL);
			// Add additional mouse movement listener
			p.setEnabledOnce(true);
		} else if (BUTTON_CANCEL.equals(data)) {
			p.setEnabledOnce(false);
			t.stop();
			t.reset();
			t.unhighlightTimer();
			reset();
		} else {
			throw new IllegalArgumentException("Unknown event");
		}
	}

	
	
	public void targetHit() {
		t.stop();
		t.highlightTimer(HIT_COL);
		reset();
	}

	public void distanceChanged() {
		t.reset();
		t.start();
	}
}
