package particle;

import processing.core.PApplet;
import util.Kinematics;

public class PolarParticle extends AbstractParticle {	
	public PolarParticle(PApplet applet, Kinematics kin) {
		super(applet, kin);
	}
	
	@Override
	public void update() {
		kin.update();
	}
	
	@Override
	public void draw() {
		float x = kin.getPos().x;
		float y = kin.getPos().y;
		applet.ellipse(x * PApplet.cos(y), x * PApplet.sin(y), 5, 5);
	}

	@Override
	public boolean isDead() {
		return false;
	}
}
