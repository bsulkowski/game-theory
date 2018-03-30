package gametheory.graph;

/**
 * 	Grafy Barabasi-Alberta.
 * 
 *	@author Bartosz Sułkowski
 */
public class BarabasiAlbertStandardGraph extends RandomGraphGenerator {
	int size;
	int m0, m;
	
	int[] connections;
	
	public BarabasiAlbertStandardGraph(int size, int m0, int m) {
		this.size = size;
		this.m0 = m0;
		this.m = m;
		
		connections = new int[((size - m0) * 2 * m) + 1];
	}

	// TODO skonfrontować z opisem w pracy Barabasi i Alberta
	public Graph createGraph() {
		initGraph(size);
		
		// CHECK zastanowić się, czy na pewno jest wszystko dobrze
		for (int y = 0; y < m; ++y) {
			addEdge(m0, y);
		}
		for (int x = m0 + 1; x < size; ++x) {
			for (int i = 0; i < m; ++i) {
				int e = graph.edges() * 2;
//				addEdge(x, connections[random.nextInt(e)]);
				while (!addNormalEdge(x, connections[random.nextInt(e)])) {
				}
      		}
		}
		
		return graph;
	}
	
	protected void addEdge(int x, int y) {
		connections[graph.edges() * 2] = x;
		connections[graph.edges() * 2 + 1] = y;
		graph.addEdge(x, y);
	}
}
