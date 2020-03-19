
import processing.core.PApplet;
import processing.core.PVector;
import startrails.StarTrailOptions;
import startrails.StarTrailGenerator;
import util.DrawableBase;
import util.Kinematics;

public class StarTrails extends PApplet {	
	public void settings() {
		fullScreen();
	}
	
	public void setup() {
		frameRate(30);
		
		new StarTrailGenerator(this)
			.setQuantity(500)
			.setKinematicsSupplier(() -> {
				float halfWidth = width / 2;
				
				float x = random(-halfWidth, halfWidth);
				float y = random(-halfWidth, halfWidth);
				
				float r = sqrt(x * x + y * y);
				float t = atan2(y, x);
				
				PVector pos = new PVector(r, t);
				PVector vel = new PVector(0, 0.005f);
				PVector acc = new PVector(0, 0);
				
				return new Kinematics(pos, vel, acc);
			})
			.setStarOptionsSupplier(() -> {
				int historySize = 80;
				int linesPerStar = 10;
				int color = color(
					random(180, 255),
					random(150, 255),
					255
				);
				float initStrokeWidth = 7.5f;
				float strokeWidthDecay = 0.975f;
				
				return new StarTrailOptions(historySize, linesPerStar, color, initStrokeWidth, strokeWidthDecay);
			})
			.generate();
	}
	
	public void draw() {
		System.out.print("[INFO]\t");
		
		DrawableBase.updateAll();
		
		background(15, 0, 30);
		
		translate(width / 2, height / 2);
		DrawableBase.drawAll();
		
		System.out.println("FPS: " + frameRate);
	}
	
	public static void main(String[] args) {
		PApplet.main(StarTrails.class.getSimpleName());
	}	
}
