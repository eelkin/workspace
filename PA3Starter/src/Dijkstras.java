/*
 * Uses the GraphMaker to make the graph, asks the user for the source vertex 
 * and destination vertex, and then runs Dijkstra's algorithm. The shortest 
 * path length as well as the actual path should be printed to the screen, 
 * then the program should terminate. See the examples in assignment for the 
 * appropriate formatting.
 */

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstras {
	HashMap<DijkstraVertex, DijkstraVertex> parent = new HashMap<DijkstraVertex, DijkstraVertex>();

	// Constructor: prompt user to enter file name, then
	// call runShortestPath with the file name.
	public Dijkstras() {

	}

	// Make a graph and run Dijkstra's algorithm.
	public void runShortestPath(String fileName){
		// Create a new GraphMaker object and use it to make a new AdjListGraph.
		// TODO
		
		// Print the graph out and prompt the user to enter the starting 
		// and ending vertices.
		// TODO
		
		// Call the shortestPath method with the graph and the source Vertex.
		// TODO
		
		// Get the distance to the destination Vertex and print it out.
		// TODO
		
		// Find and print the path by following back from the destination Vertex to each
		// parent. Note that, in the shortestPath method, you'll have stored 
		// the parent for each Vertex in the HashMap declared at the top of this file).
		// TODO
	}

	// Given the graph and source vertex, run Dijkstra's algorithm.
	public void shortestPath(AdjListGraph graph, DijkstraVertex source){
		// Initialize the distance to all the vertices in the graph to infinity,
		// except the source vertex, which should be 0.
		// TODO
		
		// Make a PriorityQueue (imported above in Java.Util.PriorityQueue)
		// of DijkstraVertex objects.
		// TODO
		
		// Keep looping as long as the priorty queue is not empty, doing the following:
		// - get the next closest Vertex from the priority queue
		// - get its adjacent vertices
		// - for each adjacent vertex, check if the distance to get there from the 
		//   current vertex would be shorter than its current distance. If so, remove
		//   it from the queue, update its distance, and re-add it. Keep track of
		//   the which vertex led to this vertex using the parent HashMap declared
		//   at the top of the file.
		// TODO
		
	}
}
