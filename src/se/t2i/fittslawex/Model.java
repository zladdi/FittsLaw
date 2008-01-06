package se.t2i.fittslawex;

public class Model {

	public static class MObject {
		private int xPos = 0, yPos = 0, size = 0, color = 0;
		private String name;
		
		MObject(String name) {
			this.name = name;
		}
		
		public int getXPos() {
			return xPos;
		}
		
		public int getYPos() {
			return yPos;
		}

		public int setXPos(int value) {
			return xPos = value;
		}
		
		public int setYPos(int value) {
			return yPos = value;
		}
		
		public int getSize() {
			return size;
		}
		
		public void setSize(int value) {
			size = value;
		}
				
		public int getColor() {
			return color;
		}
		
		public void setColor(int value) {
			color = value;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
	private MObject movable;
	private MObject target;
	
	Model() {
		movable = new MObject("M");
		target = new MObject("T");
	}
	
	public MObject getMovable() {
		return movable;
	}
	
	public MObject getTarget() {
		return target;
	}
}
