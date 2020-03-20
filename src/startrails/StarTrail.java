package startrails;

import java.util.ArrayDeque;

import particle.PolarParticle;
import processing.core.PApplet;
import processing.core.PVector;
import util.Kinematics;

public class StarTrail extends PolarParticle {
	private final StarTrailOptions options;
	
	private final ArrayDeque<PVector> cartesianHistory;
	private final PVector cartesianPos;
	
	public StarTrail(PApplet applet, Kinematics kin, StarTrailOptions options) {
		super(applet, kin);
		
		this.options = options;
		
		float x = kin.getPos().x;
		float y = kin.getPos().y;
		this.cartesianPos = new PVector(x * PApplet.cos(y), x * PApplet.sin(y));
		this.cartesianHistory = new ArrayDeque<>(options.historySize);
	}
	
	@Override
	public void update() {
		super.update();
		
		updateCartesianPos();
		
		if(cartesianHistory.size() >= options.historySize)
			cartesianHistory.removeLast();
		cartesianHistory.addFirst(cartesianPos.copy());
	}
	
	private PVector updateCartesianPos() {
		float x = kin.getPos().x;
		float y = kin.getPos().y;
		return cartesianPos.set(x * PApplet.cos(y), x * PApplet.sin(y));
	}
	
	@Override
	public void draw() {
		if(cartesianHistory.size() != 0) {
			applet.stroke(options.color);
	
			float strokeWidth = options.initStrokeWidth;
			int counter = 1;
			PVector prevPos = cartesianHistory.getFirst();
			
			for(PVector currPos : cartesianHistory) {
				if(counter % (Math.ceil((double) cartesianHistory.size() / options.linesPerStar)) == 0 || currPos.equals(cartesianHistory.getLast())) {
					applet.strokeWeight(strokeWidth);
					applet.line(prevPos.x, prevPos.y, currPos.x, currPos.y);
					prevPos = currPos;
				}
				
				strokeWidth *= options.strokeWidthDecay;
				counter++;
			}
		}
	}

	@Override
	public boolean isDead() {
		return false;
	}
}
