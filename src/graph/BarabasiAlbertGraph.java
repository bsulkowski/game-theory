package gametheory.graph;

/**
 * 	Grafy Barabasi-Alberta.
 * 
 *	@author Bartosz Sułkowski
 */
public class BarabasiAlbertGraph extends RandomGraphGenerator {
	int size;
	double averageDegree;
	
	int[] connections;
	
	public BarabasiAlbertGraph(int size, double averageDegree) {
		this.size = size;
		this.averageDegree = averageDegree;
		
		connections = new int[(int) (size * averageDegree) + 1];
	}

	// TODO skonfrontować z opisem w pracy Barabasi i Alberta
	public Graph createGraph() {
		initGraph(size);
		
		// CHECK zastanowić się, czy na pewno jest wszystko dobrze
        for (int x = 1; x < size; ++x) {
        	double newEdges = averageDegree * (x + 1) / 2 - graph.edges();
        	// DEBUG!!!
        	// System.out.println("" + newEdges);
        	if (newEdges > x - 1 + 1e-9) {
				for (int y = 0; y < x; ++y)
					addEdge(x, y);
        	}
	    	else while (newEdges > 0) {
				int m = graph.edges() * 2;
				while (!addNormalEdge(x, connections[random.nextInt(m)])) {
					// próbuj znowu
				}
				newEdges -= 1;
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
