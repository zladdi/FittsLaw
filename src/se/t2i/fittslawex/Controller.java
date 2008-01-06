package se.t2i.fittslawex;

import java.text.NumberFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;

import se.t2i.fittslawex.Model.MObject;
import se.t2i.fittslawex.gui.MainWindow;

class Controller implements MouseMoveListener, MouseListener, PaintListener {

	private static final int SIZE_SCALE = 30;
	private static final int DEFAULT_SIZE = 1;
	
	private static final int MOVABLE_BACKGROUND = SWT.COLOR_GREEN;
	private static final int MOVABLE_BACKGROUND_SEL = SWT.COLOR_DARK_GREEN;
	private static final int TARGET_BACKGROUND = SWT.COLOR_BLUE;
	private static final int TARGET_BACKGROUND_SEL = SWT.COLOR_DARK_BLUE;
	
	private static final String SIZE_OF_MOV_STR = "Size of ";
	private static final String ELAPSED_TIME_STR = "Elapsed Time";
	private static final String DISTANCE_STR = "Distance";
	
	private final NumberFormat formatter;
	
	Model.MObject movable, target, gripped;
	MainWindow view;
	Canvas canvas;
		
	Controller(Model model, MainWindow view) {
		formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);
		
		this.view = view;
		
		canvas = view.getMainCanvas();
		canvas.addMouseListener(this);
		canvas.addMouseMoveListener(this);
		canvas.addPaintListener(this);
		
		// Set initial positions of model
		int canvasWidth = canvas.getClientArea().width;
		int canvasHeight = canvas.getClientArea().height;

		movable = model.getMovable();
		target = model.getTarget();
				
		movable.setSize(DEFAULT_SIZE*SIZE_SCALE);
		movable.setXPos(canvasWidth/10);
		movable.setYPos(canvasHeight/10);
		movable.setColor(MOVABLE_BACKGROUND);
		
		target.setSize(DEFAULT_SIZE*SIZE_SCALE);
		target.setXPos((canvasWidth*8)/10);
		target.setYPos((canvasHeight*8)/10);
		target.setColor(TARGET_BACKGROUND);
		
		gripped = null;
		
		view.getLabel1().setAlignment(SWT.RIGHT);
		view.setSpinnerText(SIZE_OF_MOV_STR + target.getName());
		view.getSpinner().setDigits(2);
		view.getSpinner().setMaximum(DEFAULT_SIZE*1000);
		view.getSpinner().setMinimum(DEFAULT_SIZE*100);
		view.getSpinner().setIncrement(DEFAULT_SIZE*25);
		
		view.setLabelText(DISTANCE_STR);
		view.getLabel().setAlignment(SWT.RIGHT);
		view.setLabel1Text(ELAPSED_TIME_STR);
		// Paint
		canvas.redraw();
	}

	public void run() {
		view.show();
	}
	
	public void mouseMove(MouseEvent e) {
		// See whether one of the objects is selected
		if (gripped != null) {
			gripped.setXPos(e.x);
			gripped.setYPos(e.y);
		} else if (isInRange(movable, e.x, e.y)) {
			movable.setColor(MOVABLE_BACKGROUND_SEL);
			target.setColor(TARGET_BACKGROUND);
		} else if (isInRange(target, e.x, e.y)) {
			movable.setColor(MOVABLE_BACKGROUND);
			target.setColor(TARGET_BACKGROUND_SEL);			
		} else {
			movable.setColor(MOVABLE_BACKGROUND);
			target.setColor(TARGET_BACKGROUND);			
		}
		canvas.redraw();
	}

	public void mouseDoubleClick(MouseEvent e) {
		// Don't do anything
	}

	public void mouseDown(MouseEvent e) {
		if (isInRange(movable, e.x, e.y)) {
			gripped = movable;
		} else if (isInRange(target, e.x, e.y)) {
			gripped = target;
		} else {
			gripped = null;
		}
	}

	public void mouseUp(MouseEvent e) {
		gripped = null;
	}

	public void paintControl(PaintEvent e) {
		e.gc.fillRectangle(e.x, e.y, e.width, e.height);
				
		e.gc.setFont(new Font(e.display, "Courier", target.getSize()/2, SWT.BOLD));

		// Paint the target
		e.gc.setBackground(e.display.getSystemColor(target.getColor()));
		e.gc.fillOval(target.getXPos() - target.getSize()/2,
				target.getYPos() - target.getSize()/2,
				target.getSize(),
				target.getSize());

		e.gc.drawText(target.getName(), target.getXPos() - target.getSize()/5, target.getYPos() - target.getSize()/3);

		// Paint the movable object
		e.gc.setFont(new Font(e.display, "Courier", movable.getSize()/2, SWT.BOLD));
		e.gc.setBackground(e.display.getSystemColor(movable.getColor()));
		
		e.gc.fillOval(movable.getXPos() - movable.getSize()/2,
				movable.getYPos() - movable.getSize()/2,
				movable.getSize(),
				movable.getSize());
		
		e.gc.drawText(movable.getName(), movable.getXPos() - movable.getSize()/5, movable.getYPos() - movable.getSize()/3);
		
		view.getLabel().setText(formatter.format(distance(target, movable)));
	}

	public boolean isInRange(MObject obj, int x, int y) {
		int distX = obj.getXPos() - x;
		int distY = obj.getYPos() - y;
		int size = obj.getSize();
		
		if (distX*distX + distY*distY <= (size*size)/4) {
			return true;
		} else {
			return false;
		}
	}
	
	public double distance(MObject obj1, MObject obj2) {
		int distX = obj1.getXPos() - obj2.getXPos();
		int distY = obj1.getYPos() - obj2.getYPos();
		int cumSize = (obj1.getSize() + obj2.getSize())/2;
		double val = (Math.sqrt(distX*distX + distY*distY) - cumSize) / SIZE_SCALE;
		return val >= 0 ? val : 0;
	}

}
