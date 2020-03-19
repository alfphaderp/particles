
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
		// frame rate cap
		frameRate(30);
		
		new StarTrailGenerator(this)
			// number of stars to create
			.setQuantity(500)
			.setKinematicsSupplier(() -> {
				// generate stars randomly in a square with the width of the screen, centered at the origin,
				// then translate cartesian into polar
				float halfWidth = width / 2;
				
				float x = random(-halfWidth, halfWidth);
				float y = random(-halfWidth, halfWidth);
				
				float r = sqrt(x * x + y * y);
				float t = atan2(y, x);
				
				// initial polar position vector
				PVector pos = new PVector(r, t);
				// initial polar velocity vector
				PVector vel = new PVector(0, 0.005f);
				// initial polar acceleration vector
				PVector acc = new PVector(0, 0);
				
				return new Kinematics(pos, vel, acc);
			})
			.setStarOptionsSupplier(() -> {
				// number of past positions to remember
				int historySize = 80;
				// number of lines to draw for each star; higher = better quality but lower performance
				int linesPerStar = 10;
				// color of the star
				int color = color(
					random(180, 255),
					random(150, 255),
					255
				);
				// initial stroke width of the first line
				float initStrokeWidth = 7.5f;
				// stroke width decays geometrically per frame, this variable is the multiplier by which it decays
				// lower = faster decay, higher = slower decay, 1 = no decay, >1 = geometric growth instead of decay
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
