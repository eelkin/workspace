import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * @author Evan Elkin and William Breen
 * The GraphMaker class asks the user to enter the name of a file. 
 * The code should then read in the input file and produce the corresponding graph.
 * The first line of the file will be the number of vertices. 
 * The next lines will give the edges as a table where table(i,j) gives the 
 * edge weight between vertices i and j. 
 * The two provided files are in this format.
 */
public class GraphMaker {
	public AdjListGraph makeGraphFromFile(String fileName) {
		// Create a new directed AdjListGraph and read from the file to
		// add DijkstraVertex and Edge object to the graph.
		AdjListGraph graph = new AdjListGraph(true);

		//enter the name of a file
		//TODO: double check about the filename
		//		Scanner sc = new Scanner(System.in);
		//		String myFile = sc.next();
		File input = new File(fileName);

		// read the input file
		Scanner fileSC = null;
		try{
			fileSC = new Scanner(input);

		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		//go through and get next int the number of times that there are vertices, which is the first number in the file, and create an array of that length
		double numVertices = fileSC.nextDouble();
		Vertex []verticies = new Vertex[(int) numVertices];
		double counter = 0;
		while(counter < numVertices){
			//add the names from the first row to an array of vertexes so they can be referenced later
			Vertex a = graph.addVertex(new DijkstraVertex(fileSC.next()));
			verticies[(int) counter]=a;
			counter++;
		}

		//go through line by line
		for(int i = 0; i<numVertices; i++){
			//go through each line one column at a time (have to skip first thing in columns, so add 1)
			for(int j = 0; j<numVertices+1; j++){
				if(fileSC.hasNextDouble()){
					double edgeToAdd = fileSC.nextDouble();
					if(edgeToAdd >0){
						//since you've added one to the column, you have to subtract one 
						graph.addEdge(verticies[i], verticies[j-1], edgeToAdd);
					}
				}
				else{
					fileSC.next();
				}
			}
		}

		//TODO: remove the print statement
		graph.print();
		return graph;
	}
}
