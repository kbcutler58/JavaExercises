package test;
import processing.core.PApplet;
import processing.core.PImage;

public class MyPApplet extends PApplet{
	private static final long serialVersionUID = 1L;
	PImage backGroundImage;
	int canvasX;
	int canvasY;
	
	public void setup() {
		size(800,800);
		backGroundImage = loadImage("http://www.lajollabluebook.com/blog/wp-content/uploads/2014/07/0361115306.jpg");
		backGroundImage.resize(0,height);
		
		
		
		
	}
	
	public void draw() {
		image(backGroundImage,0,0);
		int[] sunColor = sunColorSet(second());
		fill(sunColor[0],sunColor[1],sunColor[2]);
		ellipse(width/2, height/9, width/5, height/5);
		
	}
	
	public int[] sunColorSet(float seconds)
	{
		int[] rgb = new int[3];
		float diffFrom30 = Math.abs(30-seconds);
		
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
		
	}
}
