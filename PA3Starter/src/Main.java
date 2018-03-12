/* The driver for the program. */

public class Main {
	public static void main(String[] args) {

		// Main method should create a Dijkstras object and call
		// the runShortestPath method.
		// TODO

		
		// Example of building a graph. Delete before turning in.
		AdjListGraph graph = new AdjListGraph(true);
		
		Vertex a = graph.addVertex(new DijkstraVertex("A"));
		Vertex b = graph.addVertex(new DijkstraVertex("B"));
		Vertex c = graph.addVertex(new DijkstraVertex("C"));

		graph.addEdge(a, b);
		graph.addEdge(a, c);

		graph.print();

	}
}
