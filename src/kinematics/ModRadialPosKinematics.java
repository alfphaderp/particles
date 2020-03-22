package kinematics;

import processing.core.PVector;

public class ModRadialPosKinematics extends Kinematics {
	private final float maxRadialPos;
	
	public ModRadialPosKinematics(PVector pos, PVector vel, PVector acc, float maxRadius) {
		super(pos, vel, acc);
		
		this.maxRadialPos = maxRadius;
	}

	@Override
	public void update() {
		super.update();
		pos.x %= maxRadialPos;
	}
}
