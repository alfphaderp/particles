package particle;

import kinematics.Kinematics;
import processing.core.PApplet;
import util.DrawableBase;

public abstract class AbstractParticle extends DrawableBase {	
	protected Kinematics kin;
	
	public AbstractParticle(PApplet applet, Kinematics kin) {
		super(applet);
		
		this.kin = kin;
	}
	
	@Override
	public void update() {
		kin.update();
	}
}
