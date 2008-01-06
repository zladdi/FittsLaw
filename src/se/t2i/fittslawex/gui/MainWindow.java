package se.t2i.fittslawex.gui;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class MainWindow {
	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="12,33"
	private Canvas canvas = null;
	private Composite composite = null;
	private Group group = null;
	private Spinner spinner = null;
	private Button button = null;

	private Group group1 = null;

	private Label label1 = null;

	private Group group2 = null;

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
		canvas = new Canvas(sShell, SWT.BORDER);
		canvas.setLayout(rowLayout);
		canvas.setLayoutData(gridData);
	}

	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 3;
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.BEGINNING;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = false;
		gridData3.verticalAlignment = GridData.CENTER;
		composite = new Composite(sShell, SWT.NONE);
		composite.setLayout(gridLayout1);
		composite.setLayoutData(gridData3);
		createGroup();
		createGroup2();
		createGroup1();
	}

	/**
	 * This method initializes group	
	 *
	 */
	private void createGroup() {
		FillLayout fillLayout = new FillLayout();
		fillLayout.spacing = 8;
		fillLayout.marginWidth = 8;
		fillLayout.marginHeight = 6;
		group = new Group(composite, SWT.NONE);
		group.setLayout(fillLayout);
		spinner = new Spinner(group, SWT.NONE);	
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
		group1 = new Group(composite, SWT.NONE);
		group1.setLayout(fillLayout1);
		label1 = new Label(group1, SWT.NONE);
	}

	/**
	 * This method initializes group2	
	 *
	 */
	private void createGroup2() {
		FillLayout fillLayout2 = new FillLayout();
		fillLayout2.spacing = 8;
		fillLayout2.marginWidth = 6;
		fillLayout2.marginHeight = 8;
		group2 = new Group(composite, SWT.NONE);
		group2.setLayout(fillLayout2);
		label = new Label(group2, SWT.NONE);
	}

	public Canvas getMainCanvas() {
		return canvas;
	}
		
	public Spinner getSpinner() {
		return spinner;
	}
	
	public void setSpinnerText(String value) {
		group.setText(value);
		sShell.changed(new Control[]{group});
	}
	
	public Label getLabel() {
		return label;
	}

	public void setLabelText(String value) {
		group2.setText(value);
		sShell.changed(new Control[]{group2});
	}
	
	public Label getLabel1() {
		return label1;
	}

	public void setLabel1Text(String value) {
		group1.setText(value);
		sShell.changed(new Control[]{group1});
	}
	
	public Button getButton() {
		return button;
	}
}
