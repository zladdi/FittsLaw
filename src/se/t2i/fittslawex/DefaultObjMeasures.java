package se.t2i.fittslawex;

import se.t2i.fittslawex.Model.MObject;

public class DefaultObjMeasures {

	public static boolean isInRange(MObject obj, int x, int y) {
		int distX = obj.getXPos() - x;
		int distY = obj.getYPos() - y;
		int size = obj.getSize();
		
		if (distX*distX + distY*distY <= (size*size)/4) {
			return true;
		} else {
			return false;
		}
	}
	
	public static double distance(MObject obj1, MObject obj2) {
		int distX = obj1.getXPos() - obj2.getXPos();
		int distY = obj1.getYPos() - obj2.getYPos();
		int cumSize = (obj1.getSize() + obj2.getSize())/2;
		double val = (Math.sqrt(distX*distX + distY*distY) - cumSize);
		return val >= 0 ? val : 0;
	}

}
