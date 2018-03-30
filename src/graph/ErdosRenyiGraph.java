package gametheory.graph;

/**
 * 	Całkowicie losowe grafy - model Erdos-Renyi.
 * 
 *	@author Bartosz Sułkowski
 */
public class ErdosRenyiGraph extends RandomGraphGenerator {
	int size;
	int edges;
	
	public ErdosRenyiGraph(int size, double averageDegree) {
		this.size = size;
		edges = (int) (averageDegree * size / 2);
	}

	public Graph createGraph() {
		initGraph(size);
		
        for (int e = 0; e < edges; ++e)
			addRandomEdge();

		return graph;
	}
}
