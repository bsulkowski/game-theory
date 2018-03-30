package gametheory.graph;

/**
 * 	Grafy o charakterze małego świata, model Wattsa-Strogatza.
 * 
 *	@author Bartosz Sułkowski
 */
public class WattsStrogatzGraph extends RandomGraphGenerator {
	int size;
	int degree;
	double randomness;
	
	public WattsStrogatzGraph(int size, int degree, double randomness) {
		this.size = size;
		this.degree = degree;
		this.randomness = randomness;
	}

	public Graph createGraph() {
		initGraph(size);

		int k = degree / 2;
		for (int x = 0; x < size; ++x)
			for (int i = 1; i <= k; ++i)
				addEdge(x, (x + i) % size);
		
		rewire(randomness);

		return graph;
	}
}
