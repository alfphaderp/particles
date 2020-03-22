package kinematics;

import processing.core.PVector;

public class Kinematics {
	protected PVector pos, vel, acc;
	
	public Kinematics(PVector pos, PVector vel, PVector acc) {
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
	}
	
	public void update() {
		pos.add(vel);
		vel.add(acc);
	}
	
	public PVector getPos() {
		return pos;
	}
	
	public PVector getVel() {
		return vel;
	}
	
	public PVector getAcc() {
		return acc;
	}
}
