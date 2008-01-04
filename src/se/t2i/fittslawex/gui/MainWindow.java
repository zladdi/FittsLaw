package se.t2i.fittslawex.gui;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class MainWindow {

	private static final RGB CANVAS_BACKGROUND = new RGB(125,125,125);  //  @jve:decl-index=0:
	
	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="12,33"
	private Canvas canvas = null;
	private Composite composite = null;
	private Group group = null;
	private Spinner spinner = null;
	private Button button = null;

	private Group group1 = null;

	private Label label = null;

	public MainWindow() {
		createSShell();
	}
	
	public void show() {
		Display display = Display.getDefault();

		sShell.open();
		
		while (!sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
				
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.BEGINNING;
		gridData1.grabExcessHorizontalSpace = false;
		gridData1.grabExcessVerticalSpace = false;
		gridData1.horizontalIndent = 6;
		gridData1.widthHint = 60;
		gridData1.verticalAlignment = GridData.CENTER;
		sShell = new Shell();
		sShell.setText("Fitt's Law Exercise");
		createCanvas();
		createComposite();
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(800, 700));
		button = new Button(sShell, SWT.NONE);
		button.setText("Start");
		button.setLayoutData(gridData1);
	}

	/**
	 * This method initializes canvas	
	 *
	 */
	private void createCanvas() {
		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 3;
		rowLayout.type = org.eclipse.swt.SWT.HORIZONTAL;
		rowLayout.fill = false;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		canvas = new Canvas(sShell, SWT.NONE);
		canvas.setLayout(rowLayout);
		canvas.setLayoutData(gridData);
		canvas.setBackground(new Color(canvas.getDisplay(), CANVAS_BACKGROUND));
	}

	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 2;
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.BEGINNING;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.verticalAlignment = GridData.CENTER;
		composite = new Composite(sShell, SWT.NONE);
		composite.setLayout(gridLayout1);
		composite.setLayoutData(gridData3);
		createGroup();
		createGroup1();
	}

	/**
	 * This method initializes group	
	 *
	 */
	private void createGroup() {
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.CENTER;
		gridData2.grabExcessHorizontalSpace = false;
		gridData2.grabExcessVerticalSpace = false;
		gridData2.verticalAlignment = GridData.CENTER;
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = org.eclipse.swt.SWT.HORIZONTAL;
		fillLayout.marginHeight = 8;
		fillLayout.marginWidth = 6;
		fillLayout.spacing = 8;
		group = new Group(composite, SWT.NONE);
		group.setText("Size of Movable Object");
		group.setLayoutData(gridData2);
		group.setLayout(fillLayout);
		spinner = new Spinner(group, SWT.NONE);
		spinner.setMinimum(1);		
	}

	/**
	 * This method initializes group1	
	 *
	 */
	private void createGroup1() {
		FillLayout fillLayout1 = new FillLayout();
		fillLayout1.spacing = 8;
		fillLayout1.marginWidth = 6;
		fillLayout1.marginHeight = 8;
		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = GridData.CENTER;
		gridData4.verticalAlignment = GridData.CENTER;
		group1 = new Group(composite, SWT.NONE);
		group1.setLayoutData(gridData4);
		group1.setLayout(fillLayout1);
		group1.setText("Elapsed Time (seconds)");
		label = new Label(group1, SWT.NONE);
		label.setText("0.000");
		label.setAlignment(SWT.RIGHT);
	}

}
