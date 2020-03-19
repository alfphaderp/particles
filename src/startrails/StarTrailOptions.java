package startrails;

public class StarTrailOptions {
	public final int historySize;
	public final int linesPerStar;
	public final int color;
	public final float initStrokeWidth;
	public final float strokeWidthDecay;
	
	public StarTrailOptions(int historySize, int linesPerStar, int color, float initStrokeWidth, float strokeWidthDecay) {
		this.historySize = historySize;
		this.linesPerStar = linesPerStar;
		this.color = color;
		this.initStrokeWidth = initStrokeWidth;
		this.strokeWidthDecay = strokeWidthDecay;
	}
}
