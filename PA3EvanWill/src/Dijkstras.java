/*
 * @author Evan Elkin and William Breen
 * Uses the GraphMaker to make the graph, asks the user for the source vertex 
 * and destination vertex, and then runs Dijkstra's algorithm. The shortest 
 * path length as well as the actual path should be printed to the screen, 
 * then the program should terminate. See the examples in assignment for the 
 * appropriate formatting.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstras {
	HashMap<DijkstraVertex, DijkstraVertex> parent = new HashMap<DijkstraVertex, DijkstraVertex>();

	// Constructor: prompt user to enter file name, then
	// call runShortestPath with the file name.
	public Dijkstras() {
		System.out.println("Enter a file name:");
		Scanner sc = new Scanner(System.in);
		String myFile = sc.next();

		runShortestPath(myFile);
	}

	// Make a graph and run Dijkstra's algorithm.
	public void runShortestPath(String fileName){
		// Create a new GraphMaker object and use it to make a new AdjListGraph.
		// TODO
		GraphMaker am = new GraphMaker();
		AdjListGraph graph = new AdjListGraph(true);
		am.makeGraphFromFile(fileName);

		// Print the graph out and prompt the user to enter the starting 
		// and ending vertices.
		// TODO
		graph.print();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter starting vertex");
		String startVert = sc.next();

		Vertex startingVert =new DijkstraVertex(startVert);

		System.out.println("Enter ending vertex");
		String endVert = sc.next();

		Vertex endingVert = new DijkstraVertex(endVert);
	}

	// Given the graph and source vertex, run Dijkstra's algorithm.
	public void shortestPath(AdjListGraph graph, DijkstraVertex source){
		// Initialize the distance to all the vertices in the graph to infinity,
		// except the source vertex, which should be 0.
		// TODO
		ArrayList<Vertex> vertexArray = graph.getVertices();
		
		int length = vertexArray.size();
		// array of dijkstra vertices
		DijkstraVertex[] dijArray = new DijkstraVertex [length];
		// predecessor array
		DijkstraVertex[] pred = new DijkstraVertex [length];
		// initializes all vertices array to infinity except source vertex
		for(int i = 0; i < vertexArray.size(); i++){
			dijArray[i] = new DijkstraVertex(vertexArray.get(i).getName());
			if(dijArray[i].getName() != source.getName()){
				//max value should act as infinity infinity
				dijArray[i].setDistance(Double.MAX_VALUE);
			} else {
				dijArray[i].setDistance(0);
			}
			pred[i] = null;
		}
		
		PriorityQueue<DijkstraVertex> pQueue = new PriorityQueue<DijkstraVertex>();
		
		/*
		Pseudocode because we couldn't get Dijkstra's working

		while(Q != null){
			u = Extract_Min(Q);
			S = S.add(path);
			for(v:adj[u]){
				if(d[v] > d[u] && w(u, v)){
					d[v] = d[u] + w(u, v);
					p[v] = u;
					Decrease_Key(Q, v, d[v]);
				}
			}
		}
		*/

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
