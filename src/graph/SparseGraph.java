package gametheory.graph;

import java.util.ArrayList;

/**
 *	Standardowa implementacja interfejsu Graph.
 *
 *	@author Bartosz Sułkowski
 */
public class SparseGraph implements Graph {
	/**
	 *  Listy sąsiedztwa dla poszczególnych wierzchołków.
	 *  Jeśli w wierzchołku x jest pętla, to znajduje się on na liście podwójnie.
	 */
	ArrayList<ArrayList<Integer>> vertices;
	int connections;
	
	public SparseGraph(int size) {
		vertices = new ArrayList<ArrayList<Integer>>(size);
		for (int x = 0; x < size; ++x)
			vertices.add(new ArrayList<Integer>());
		connections = 0;
	}

	public int size() {
		return vertices.size();	
	}
	public int edges() {
		return connections / 2;
	}
	public ArrayList<Integer> neighbours(int x) {
		return vertices.get(x);
	}
	public int degree(int x) {
		return neighbours(x).size();
	}
	public boolean hasEdge(int x, int y) {
		return neighbours(x).contains(y);
	}	

	public double averageDegree() {
		return (double) connections / size();
	}

	public void addEdge(int x, int y) {
		neighbours(x).add(y);
		neighbours(y).add(x);
		connections += 2;
	}
	public boolean removeEdge(int x, int y) {
		int xy = neighbours(x).indexOf(y);
		if (xy != -1) {
			neighbours(x).remove(xy);
			int yx = neighbours(y).indexOf(x);
			neighbours(y).remove(yx);
			connections -= 2;
			return true;
		}
		else
			return false;
	}

	public void setSize(int size) {
		int oldSize = size();
		if (size > oldSize) {
			vertices.ensureCapacity(size);
			for (int x = oldSize; x < size; ++x)
				vertices.add(new ArrayList<Integer>());
		}
		else if (size < oldSize) {
			for (int x = oldSize - 1; x >= size; --x) {
				disconnectVertex(x);
				vertices.remove(x);
			}
		}
	}
	
	public void disconnectVertex(int x) {
		for (int y : neighbours(x)) {
			if (y != x) {
				int yx = neighbours(y).indexOf(x);
				neighbours(y).remove(yx);
				--connections;
			}
		}
		connections -= neighbours(x).size();
		neighbours(x).clear();
	}

	public void swapVertices(int x, int y) {
		if (x == y)
			return;
		
		ArrayList<Integer> nx = neighbours(x);
		ArrayList<Integer> ny = neighbours(y);
		
		for (int i = 0; i < nx.size(); ++i) {
			int z = nx.get(i);
			if (z == x)
				nx.set(i, y);
			else if (z == y)
				nx.set(i, x);
			else {
				int zx = neighbours(z).indexOf(x);
				neighbours(z).set(zx, y);
			}
		}

		for (int i = 0; i < ny.size(); ++i) {
			int z = ny.get(i);
			if (z == x)
				ny.set(i, y);
			else if (z == y)
				ny.set(i, x);
			else {
				int zy = neighbours(z).indexOf(y);
				neighbours(z).set(zy, x);
			}
		}
		
		vertices.set(x, ny);
		vertices.set(y, nx);
	}
}
