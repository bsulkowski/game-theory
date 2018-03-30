package gametheory.graph;

/**
 * @author Bartosz Sułkowski
 */
public class Graphs {
    public static int maxDegree(Graph graph) {
		int max = 0;
	
		for (int x = 0; x < graph.size(); ++x)
			if (graph.degree(x) > max)
				max = graph.degree(x);

		return max;
	}
    public static int minDegree(Graph graph) {
		int min = Integer.MAX_VALUE;
	
		for (int x = 0; x < graph.size(); ++x)
			if (graph.degree(x) < min)
				min = graph.degree(x);

		return min;
    }
	public static double averageDegree(Graph graph) {
		return (double) graph.edges() / graph.size();
	}
}
