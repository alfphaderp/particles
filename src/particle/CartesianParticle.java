package particle;

import kinematics.Kinematics;
import processing.core.PApplet;

public class CartesianParticle extends AbstractParticle {
	public CartesianParticle(PApplet applet, Kinematics kin) {
		super(applet, kin);
	}
	
	@Override
	public void draw() {
		applet.ellipse(kin.getPos().x, kin.getPos().y , 5, 5);
	}

	@Override
	public boolean isDead() {
		return false;
	}
}
