package roadgraph;

import geography.GeographicPoint;

public class MapEdge {

	// Properties in class, string road name, double road length, string road type
	// start and end location points
	private String roadName;
	private String roadType;
	private double roadLength;
	private GeographicPoint startLocation;
	private GeographicPoint endLocation;
	
	public MapEdge(GeographicPoint startLoc, GeographicPoint endLoc,
			String roadName1, String roadType1, double roadLength1)
	{
		this.roadName = roadName1;
		this.roadType = roadType1;
		this.roadLength = roadLength1;
		this.startLocation = startLoc;
		this.endLocation = endLoc;
	}

	// get functions for all private variables on MapEdge
	public String getRoadName()
	{
		return roadName;
	}
	
	public String getRoadType()
	{
		return roadType;
	}
	
	public double getRoadLength()
	{
		return roadLength;
	}
	
	public GeographicPoint getStart()
	{
		return startLocation;
	}
	
	public GeographicPoint getEnd()
	{
		return endLocation;
	}
}
