package util;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public abstract class DrawableBase extends PObject implements Drawable {
	private static int MAX_ACTIVE = 1000;
	
	private static List<Drawable> active = new ArrayList<>();
	private static List<Drawable> queue = new ArrayList<>();
	
	public DrawableBase(PApplet applet) {
		super(applet);
		
		queue.add(this);
	}
	
	public static int count() {
		return active.size();
	}
	
	public static void updateAll() {
		long l = System.currentTimeMillis();
		
		active.removeIf(d -> d.isDead());
		
		for(int i = queue.size() - 1; i >= 0 && active.size() < MAX_ACTIVE; i--) {
			active.add(queue.get(i));
		}
		
		active.forEach(d -> d.update());
		
		System.out.print("Update time: " + (System.currentTimeMillis() - l) + "\t");
	}
	
	public static void drawAll() {
		long l = System.currentTimeMillis();
		
		active.stream()
			.forEach(d -> d.draw());
		
		System.out.print("Draw time: " + (System.currentTimeMillis() - l) + "\t");
	}
}
