package startrails;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

import kinematics.Kinematics;
import processing.core.PApplet;
import util.PObject;

public class StarTrailGenerator extends PObject {
	private IntSupplier quantitySupplier;
	private Supplier<Kinematics> kinematicsSupplier;
	private Supplier<StarTrailOptions> starOptionsSupplier;
	
	public StarTrailGenerator(PApplet applet) {
		super(applet);
	}
	
	public void generate() {
		int quantity = quantitySupplier.getAsInt();
		
		for(int i = 0; i < quantity; i++) {
			Kinematics kin = kinematicsSupplier.get();
			StarTrailOptions opt = starOptionsSupplier.get();
			
			new StarTrail(applet, kin, opt);
		}
	}
	
	public StarTrailGenerator setQuantity(int quantity) {
		this.quantitySupplier = () -> quantity;
		return this;
	}
	
	public StarTrailGenerator setQuantitySupplier(IntSupplier quantitySupplier) {
		this.quantitySupplier = quantitySupplier;
		return this;
	}
	
	public StarTrailGenerator setKinematicsSupplier(Supplier<Kinematics> kinematicsSupplier) {
		this.kinematicsSupplier = kinematicsSupplier;
		return this;
	}

	public StarTrailGenerator setStarOptionsSupplier(Supplier<StarTrailOptions> starOptionsSupplier) {
		this.starOptionsSupplier = starOptionsSupplier;
		return this;
	}
}
