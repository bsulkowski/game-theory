package gametheory.graph;

import java.util.List;
import java.util.Random;

/**
 * 	Szkielet losowego generatora grafów.
 * 
 *	@author Bartosz Sułkowski
 */
abstract public class RandomGraphGenerator implements GraphGenerator {
	Random random = new Random();
	Graph graph;
	
	public void initGraph(int size) {
		graph = new SparseGraph(size);
	}
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	/**
	 *  Dodaje losową krawędź, której jeszcze nie ma w grafie.
	 */
	protected void addRandomEdge() {
		int n = graph.size();
		while (!addNormalEdge(random.nextInt(n), random.nextInt(n))) {
			// próbuj znowu
		}
	}

	/**
	 *  Łączy x z losowym wierzchołkiem, z którym jeszcze nie jest połączony.
	 *  
	 *  @param x  wierzchołek grafu
	 */
	protected void addRandomEdge(int x) {
		int n = graph.size();
		while (!addNormalEdge(x, random.nextInt(n))) {
			// próbuj znowu
		}
	}
	
	/**
	 *  Dodaje krawędź łączącą x z y, o ile jeszcze takiej nie ma.
	 *  Nie dodaje pętli.
	 *  
	 *  @param x  wierzchołek grafu
	 *  @param y  wierzchołek grafu
	 *  @return czy udało się dodać krawędź
	 */
	protected boolean addNormalEdge(int x, int y) {
		if ((graph.hasEdge(x, y)) || (x == y)) {
			return false;
		}
		else {
			addEdge(x, y);
			return true;
		}
	}
	
	/**
	 *  Dodaje krawędź łączącą x z y.
	 *  Działa dla krawędzi wielokrotnych i pętli.
	 *  
	 *  @param x  wierzchołek grafu
	 *  @param y  wierzchołek grafu
	 */
	protected void addEdge(int x, int y) {
		graph.addEdge(x, y);
	}
	
	/**
	 *  Usuwa losowy wierzchołek z grafu.
	 */
	protected void removeRandomVertex() {
		removeVertex(random.nextInt(graph.size()));
	}

	/**
	 *  Usuwa podany wierzchołek z grafu.
	 *  
	 *  @param x  usuwany wierzchołek
	 */
	protected void removeVertex(int x) {
		int n = graph.size() - 1;
		graph.swapVertices(x, n);
		graph.setSize(n);
	}

	/**
	 *  Zamienia podaną frakcję krawędzi na losowe.
	 *  
	 *  @param rewireProbability  frakcja zmienianych krawędzi
	 */
	protected void rewire(double rewireProbability) {
		int rewiredEdges = 0;

		// usuwanie losowej frakcji krawędzi
		for (int x = 0; x < graph.size(); ++x) {
			int petla = 0;
			List<Integer> nx = graph.neighbours(x);
			for (int i = nx.size() - 1; i >= 0; --i) {
				int y = nx.get(i);
				if (y > x) {
					if (random.nextDouble() < rewireProbability) {
						int yx = graph.neighbours(y).indexOf(x);
						graph.neighbours(y).remove(yx);
						nx.remove(i);
						++rewiredEdges;
					}
				}
				// obsługa pętli
				else if (y == x) {
					if (petla == 0) {
						if (random.nextDouble() < rewireProbability) {
							nx.remove(i);
							++rewiredEdges;
							petla = 1;
						}
						else
							petla = -1;
					}
					else {
						if (petla == 1)
							nx.remove(i);
						petla = 0;
					}
				}
			}
		}
		
		// dodanie z powrotem losowych krawędzi
		for (int e = 0; e < rewiredEdges; ++e) {
			addRandomEdge();
		}
	}
}
