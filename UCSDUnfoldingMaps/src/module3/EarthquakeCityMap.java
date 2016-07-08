package module3;

//Java utilities libraries
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();
	    
	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    for (PointFeature quake : earthquakes) {
	    	//System.out.println(quake.getProperties());
	    	Object magObj = quake.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	Object depthObj = quake.getProperty("depth");
	    	float depth = Float.parseFloat(depthObj.toString());
	    	Object ageObj = quake.getProperty("age");
	    	markers.add(createMarker(quake,mag,depth,ageObj));
	    }
	    
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	 //   SimplePointMarker marker = new createMarker(earthquakes.get(0)) 
	//    System.out.println();
	    map.addMarkers(markers);
	    //TODO: Add code here as appropriate
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature, float mag, float depth, Object ageObj)
	{
		SimplePointMarker marker1 = new SimplePointMarker(feature.getLocation());
		if (mag > 5)
		{
			marker1.setRadius(15);
			marker1.setColor(color(255,0,0));
		}
		else if (mag > 4) {
			marker1.setRadius(10);
			marker1.setColor(color(255,255,0));
		}
		else
		{
			marker1.setRadius(5);
			marker1.setColor(color(0,0,255));
		}	
		return marker1;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	private void addKey() 
	{	
		fill(255,255,255);
		rect(25, 25, 140, 200);
		String s1 = "> 5.0 Magnitude";
		String s2 = "> 4.0 Magnitude";
		String s3 = "< 4.0 Magnitude";
		
		fill(0,0,0);
		text("Earthquake Key", 50,30,100,80);		
		text(s1,50,75,100,125);
		text(s2,50,125,100,175);
		text(s3,50,175,100,225);
		
		fill(255,0,0);
		ellipse(35,83,15,15);
		
		fill(255,255,0);
		ellipse(35,133,10,10);
		
		fill(0,0,255);
		ellipse(35,183,5,5);
		
	
	}
}
