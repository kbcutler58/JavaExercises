package roadgraph;

import java.util.LinkedList;

import geography.GeographicPoint;

public class MapNode {
	// needs Location and list of edges
	GeographicPoint nodeLocation;
	LinkedList<MapEdge> mapEdges; 

	public MapNode(GeographicPoint location)
	{
		// store inputs into Node
		this.nodeLocation = location;
		this.mapEdges = new LinkedList<MapEdge>();
	}
	
	public void AddEdge(GeographicPoint startLocation, GeographicPoint endLocation,
			String roadName, String roadType, double roadLength)
	{
		// add edges using inputs from parent structure
		mapEdges.add(new MapEdge(startLocation,endLocation,roadName,roadType,roadLength));
	}
	
	public void ToString()
	{
		// used for testing
		System.out.println(nodeLocation);
	}
}
