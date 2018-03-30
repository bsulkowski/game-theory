package gametheory.graph;

/**
 * 	Kwadratowa sieć, zawinięta na brzegach (czyli torus).
 * 
 *	@author Bartosz Sułkowski
 */
public class SquareLattice implements GraphGenerator {
	int width;
	int height;
	boolean diagonalEdges;

	public SquareLattice(int width, int height, boolean diagonalEdges) {
		this.width = width;
		this.height = height;
		this.diagonalEdges = diagonalEdges;
	}
	
	public Graph createGraph() {
		SparseGraph graph = new SparseGraph(width * height);
		
        for (int x = 0; x < width; ++x) {
	        for (int y = 0; y < width; ++y) {
		        graph.addEdge(cell(x, y), cell(x + 1, y));
		        graph.addEdge(cell(x, y), cell(x, y + 1));
				if (diagonalEdges) {
			        graph.addEdge(cell(x, y), cell(x + 1, y + 1));
			        graph.addEdge(cell(x, y), cell(x - 1, y + 1));
		        }
	        }
        }
		
		return graph;
	}
	
	protected int cell(int x, int y) {
		return ((y + height) % height) * width + (x + width) % width;
	}
}
