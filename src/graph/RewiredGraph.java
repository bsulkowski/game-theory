package gametheory.graph;

/**
 *	Losowe przekręcenie krawędzi w danym grafie.
 *
 *	@author Bartosz Sułkowski
 */
public class RewiredGraph extends RandomGraphGenerator {
	GraphGenerator generator;
	double prob;

    public RewiredGraph(GraphGenerator generator, double probability) {
        this.generator = generator;
        this.prob = probability;
    }
    
    public Graph createGraph() {
		setGraph(generator.createGraph());

		rewire(prob);
		
		return graph;
    }
}
