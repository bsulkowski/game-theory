package gametheory.graph;

/**
 * 	Grafy losowe Kauffmana.
 * 
 *	@author Bartosz Sułkowski
 */
public class KauffmanGraph extends RandomGraphGenerator {
	int size;
	double averageDegree;
	
	public KauffmanGraph(int size, double averageDegree) {
		this.size = size;
		this.averageDegree = averageDegree;
	}

	public Graph createGraph() {
		initGraph(size);
		
        for (int x = 0; x < size; ++x) {
        	double newEdges = averageDegree * (x + 1) / 2 - graph.edges();

			while (newEdges > 0) {
				addRandomEdge(x);
				newEdges -= 1;
      		}
		}

		return graph;
	}
}
