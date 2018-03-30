package gametheory.graph;

/**
 * 	Dodanie dziur do danej sieci.
 * 
 *	@author Bartosz Sułkowski
 */
public class SiteDilutedGraph extends RandomGraphGenerator {
	GraphGenerator generator;
	double prob;

    public SiteDilutedGraph(GraphGenerator generator, double prob) {
        this.generator = generator;
        this.prob = prob;
    }
    
    public Graph createGraph() {
		setGraph(generator.createGraph());
		
		int m = (int) (prob * graph.size()); 

		for (int k = 0; k < m; ++k) {
			removeRandomVertex();
		}
		
		return graph;
    }
}
