package se.t2i.fittslawex.task;

import java.util.ArrayList;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;

import se.t2i.fittslawex.DefaultObjMeasures;
import se.t2i.fittslawex.Model;
import se.t2i.fittslawex.Model.MObject;

public class PositionListener implements MouseMoveListener {

	private MObject obj1, obj2;
	private ArrayList<MovementEventSubscriber> subscribers;
	private boolean enabled;
	private double initialDist;
	private boolean unmoved;
	
	PositionListener(Model.MObject obj1, Model.MObject obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
		subscribers = new ArrayList<MovementEventSubscriber>();
		setEnabledOnce(false);
	}
	
	public void setEnabledOnce(boolean val) {
		if (val && !enabled) {
			unmoved = true;
			initialDist = DefaultObjMeasures.distance(obj1, obj2);
			
		}
		enabled = val;
		notifySubscribers();
	}

	private void notifySubscribers() {
		if (enabled) {
			double curDist = DefaultObjMeasures.distance(obj1, obj2);

			if (unmoved && (curDist != initialDist)) {
				unmoved = false;
				for(MovementEventSubscriber mes : subscribers) {
					mes.distanceChanged();
				}
			}
			
			if (DefaultObjMeasures.distance(obj1, obj2) == 0.0) {
				for(MovementEventSubscriber mes : subscribers) {
					mes.targetHit();
				}
				enabled = false;
			}
		}		
	}

	public void mouseMove(MouseEvent e) {
		notifySubscribers();
	}
	
	public void addSubscriber(MovementEventSubscriber obj) {
		subscribers.add(obj);
	}
}
