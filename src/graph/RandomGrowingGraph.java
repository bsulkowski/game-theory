package gametheory.graph;

/**
 * 	Graf rozrastający się stopniowo (jak B-A),
 *  ale bez preferencyjnego wybierania wierzchołków.
 * 
 *	@author Bartosz Sułkowski
 */
public class RandomGrowingGraph extends RandomGraphGenerator {
	int size;
	double averageDegree;
	
	public RandomGrowingGraph(int size, double averageDegree) {
		this.size = size;
		this.averageDegree = averageDegree;
	}

	public Graph createGraph() {
		initGraph(size);
		
//        for (int x = 1; x < size; ++x) {
//        	double newEdges = averageDegree * (x + 1) / 2 - graph.edges();
//        	if (newEdges > x - 1) {
//				for (int y = 0; y < x; ++y)
//					graph.addEdge(x, y);
//        	}
//	    	else while (newEdges > 0) {
//				while (!addNormalEdge(x, random.nextInt(x))) {
//				}
//				newEdges -= 1;
//      		}
//		}
		int m = (int) (averageDegree / 2);
        for (int x = m; x < size; ++x) {
        	for (int i = 0; i < m; ++i) {
				while (!addNormalEdge(x, random.nextInt(x))) {
				}
      		}
		}
		
		return graph;
	}
}
