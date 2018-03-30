package gametheory.graph;

/**
 *	Wielowymiarowa sieć, zawinięta na brzegach (czyli hipertorus).
 *  
 * 	@author Bartosz Sułkowski
 */
public class Lattice implements GraphGenerator {
	int width;
	int dimensions;
	int[] i;
	int[] p;
	
	public Lattice(int width, int dimensions) {
        this.width = width;
		this.dimensions = dimensions;
		i = new int[dimensions + 1];
		p = new int[dimensions + 1];
		for (int d = 0; d < dimensions + 1; ++d) {
			i[d] = 0;
		}
		p[0] = 1;
		for (int d = 0; d < dimensions; ++d) {
			p[d + 1] = p[d] * width;
		}
	}
	
	public Graph createGraph() {
		SparseGraph graph = new SparseGraph(p[dimensions]);

		// CHECK upewnić się, że jest poprawnie
		int x = 0;
		while (i[dimensions] == 0) {
			// TODO kto ma być sąsiadem?
			// FIXME poprawić dla (width == 2)
			for (int d = 0; d < dimensions; ++d)
				graph.addEdge(x, (x + p[d]) % p[dimensions]);

			// następny wierzchołek
			++x;
			int d = 0;
			while (++i[d] == width) {
				i[d] = 0;
				++d;
			}
		}
		
		return graph;
	}
}
