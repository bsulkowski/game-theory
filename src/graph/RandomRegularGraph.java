package gametheory.graph;

/**
 * 	Losowy regularny graf o ustalonym stopniu.
 * 
 *	@author Bartosz Sułkowski
 */
public class RandomRegularGraph extends RandomGraphGenerator {
	int size;
	int degree;
	
	public RandomRegularGraph(int size, int degree) {
		this.size = size;
		this.degree = degree;
	}

	// TODO na razie mogą zdarzać się w nim krawędzie wielokrotne!
	public Graph createGraph() {
		initGraph(size);

		// FIXME napisać całość

		return graph;
	}
}
