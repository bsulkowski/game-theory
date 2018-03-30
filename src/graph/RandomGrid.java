package gametheory.graph;

/**
 * 	Wierzchołki losowo rozmieszczone na płaszczyźnie,
 * 	pomysł z pracy "More Spatial Games" Nowaka i May'a.
 * 
 * 	@author Bartosz Sułkowski
 */
public class RandomGrid extends RandomGraphGenerator {
	int size;
	double radiusSquare;
	
	double[] x, y; 

	public RandomGrid(int size, double radius) {
		this.size = size;
		this.radiusSquare = radius * radius;
		
		x = new double[size];
		y = new double[size];
	}
	
	public Graph createGraph() {
		initGraph(size);
 
		double l = Math.sqrt(size);
		for (int i = 0; i < size; ++i) {
			x[i] = random.nextDouble() * l;
			y[i] = random.nextDouble() * l;
		}
		
		// TODO poprawić efektywność
		for (int i = 0; i < size; ++i)
			for (int j = i + 1; j < size; ++j) {
				double dx = x[i] - x[j];
				double dy = y[i] - y[j];
				if (dx * dx + dy * dy < radiusSquare)
					addEdge(i, j);
			}
		
		return graph;
	}
}
