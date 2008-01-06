package se.t2i.fittslawex;

import se.t2i.fittslawex.gui.*;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		// Create controller
		Controller controller = new Controller(new Model(), new MainWindow());
		controller.run();
	}

}
