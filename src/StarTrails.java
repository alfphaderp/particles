
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;
import startrails.StarTrailGenerator;
import startrails.StarTrailOptions;
import util.DrawableBase;
import util.Kinematics;

public class StarTrails extends PApplet {
	private float cameraX = 0, cameraY = 0, zoom = 1;
	
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
//				float halfWidth = width / 2;
				
//				float x = random(-halfWidth, halfWidth);
//				float y = random(-halfWidth, halfWidth);
//				
//				float r = sqrt(x * x + y * y);
//				float t = atan2(y, x);
				
				
				// generate stars randomly with r and theta directly in a circle, centered at the origin
				float screenRadius = sqrt(width * width / 4 + height * height / 4);
				
				float r = random(0, screenRadius);
				float t = random(-PI, PI);
				
				// initial polar position vector
				PVector pos = new PVector(r, t);
				// initial polar velocity vector
				PVector vel = new PVector(1, 0);
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
					255,
					random(150, 255),
					random(180, 255)
				);
				// initial stroke width of the first line
				float initStrokeWidth = 8f;
				// stroke width decays geometrically per frame, this variable is the multiplier by which it decays
				// lower = faster decay, higher = slower decay, 1 = no decay, >1 = geometric growth instead of decay
				float strokeWidthDecay = 0.975f;
				// whether or not to teleport the stars back to the origin if they travel too far away
				boolean hasModularRPosition = true;
				// the maximum allowed polar radius before being teleported back
				float maxR = sqrt(width * width / 4 + height * height / 4);
				
				return new StarTrailOptions(historySize, linesPerStar, color, initStrokeWidth, strokeWidthDecay, hasModularRPosition, maxR);
			})
			.generate();
	}
	
	public void draw() {
		System.out.print("[INFO]\t");
		
		DrawableBase.updateAll();
		
		background(15, 0, 30);
		
		translate(width / 2 + cameraX, height / 2 + cameraY);
		scale(zoom);
		DrawableBase.drawAll();
		
		
		System.out.println("FPS: " + frameRate);
	}
	
	public void mouseWheel(MouseEvent event) {
		zoom += event.getCount() * -0.01;
	}
	
	public void keyPressed() {
		switch(key) {
		case 'w':
			cameraY -= 5;
			break;
		case 's':
			cameraY += 5;
			break;
		case 'a':
			cameraX -= 5;
			break;
		case 'd':
			cameraX += 5;
			break;
		}
	}
	
	public static void main(String[] args) {
		PApplet.main(StarTrails.class.getSimpleName());
	}	
}
