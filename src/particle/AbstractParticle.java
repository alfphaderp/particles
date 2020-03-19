package particle;

import processing.core.PApplet;
import util.DrawableBase;
import util.Kinematics;

public abstract class AbstractParticle extends DrawableBase {	
	protected Kinematics kin;
	
	public AbstractParticle(PApplet applet, Kinematics kin) {
		super(applet);
		
		this.kin = kin;
	}
}
