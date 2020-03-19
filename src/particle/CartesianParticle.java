package particle;

import processing.core.PApplet;
import util.Kinematics;

public class CartesianParticle extends AbstractParticle {
	public CartesianParticle(PApplet applet, Kinematics kin) {
		super(applet, kin);
	}
	
	@Override
	public void update() {
		kin.update();
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
