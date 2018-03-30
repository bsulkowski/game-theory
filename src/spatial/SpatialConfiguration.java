package gametheory.spatial;

import gametheory.UnimplementedError;
import gametheory.graph.*;

/**
 *	Założenie:
 *		Obiekty tej klasy zmieniają się w czasie symulacji.
 *
 *	@author Bartosz Sułkowski
 */
public class SpatialConfiguration {
	Graph graph;
	SpatialPayoff spatialPayoff;
	int players;
	int strategies;
	
	int[] strategy;
	int[][] neighboursStrategy;
	double[] payoff;
	
	int[] strategyCount;

	public SpatialConfiguration(Graph graph, SpatialPayoff spatialPayoff) {
		this.graph = graph;
		this.spatialPayoff = spatialPayoff;
		players = graph.size();
		strategies = spatialPayoff.game().strategies();
		
		strategy = new int[players];
		neighboursStrategy = new int[players][strategies];
		payoff = new double[players];
		
		strategyCount = new int[strategies];

		for (int x = 0; x < players; ++x) {
			strategy[x] = 0;
			neighboursStrategy[x][0] = graph.degree(x);
			for (int s = 1; s < strategies; ++s)
				neighboursStrategy[x][s] = 0;
		}
		strategyCount[0] = players;
		for (int s = 1; s < strategies; ++s)
			strategyCount[s] = 0;
		
		for (int x = 0; x < players; ++x)
			updatePayoff(x);
	}

	public void setGraph(Graph graph) {
		// CHECK na razie zakładamy, że wielkość grafu się nie zmienia
		if (graph.size() != this.graph.size())
			throw new UnimplementedError("zmieniła się wielkość grafu");
		this.graph = graph;
		
		for (int x = 0; x < players; ++x) {
			for (int s = 0; s < strategies; ++s)
				neighboursStrategy[x][s] = 0;
		}
		for (int x = 0; x < players; ++x) {
			int s = strategy[x];
			for (int y : graph.neighbours(x))
				++neighboursStrategy[y][s];
		}
		
		for (int x = 0; x < players; ++x)
			updatePayoff(x);
	}
	public Graph graph() {
		return graph;
	}
	
	public void addConnection(int x, int y) {
		graph.addEdge(x, y);
		++neighboursStrategy[x][strategy[y]];
		++neighboursStrategy[y][strategy[x]];
		updatePayoff(x);
		updatePayoff(y);
	}
	public boolean removeConnection(int x, int y) {
		if (!graph.removeEdge(x, y))
			return false;
		--neighboursStrategy[x][strategy[y]];
		--neighboursStrategy[y][strategy[x]];
		updatePayoff(x);
		updatePayoff(y);
		return true;
	}
	public void disconnectPlayer(int x) {
		// CHECK czy to potrzebne?
		throw new UnimplementedError("nie zaimplementowano");
	}
	
	public SpatialPayoff spatialPayoff() {
		return spatialPayoff;
	}
	
	public int strategies() {
		return strategies;
	}
	public int strategy(int x) {
		return strategy[x];	
	}
	public double payoff(int x) {
		return payoff[x];
	}
	public int[] neighboursStrategy(int x) {
		return neighboursStrategy[x];
	}
	public int strategyCount(int s) {
		return strategyCount[s];
	}
	
	public boolean setStrategy(int x, int newStrategy) {
		int oldStrategy = strategy[x];
		if (oldStrategy == newStrategy)
			return false;
		strategy[x] = newStrategy;
		updatePayoff(x);
		for (int y : graph.neighbours(x)) {
			--neighboursStrategy[y][oldStrategy];
			++neighboursStrategy[y][newStrategy];
			updatePayoff(y);
		}
		--strategyCount[oldStrategy];
		++strategyCount[newStrategy];
		return true;
	}
	public int setStrategy(int[] newStrategies) {
		int changes = 0;
		for (int x = 0; x < players; ++x) {
			int oldStrategy = strategy[x];
			int newStrategy = newStrategies[x];
			if (oldStrategy == newStrategy)
				continue;
			strategy[x] = newStrategy;
			for (int y : graph.neighbours(x)) {
				--neighboursStrategy[y][oldStrategy];
				++neighboursStrategy[y][newStrategy];
			}
			--strategyCount[oldStrategy];
			++strategyCount[newStrategy];
			++changes;
		}
		for (int x = 0; x < players; ++x)
			updatePayoff(x);
		return changes;
	}
	protected void updatePayoff(int x) {
		payoff[x] = spatialPayoff.payoff(strategy[x], neighboursStrategy[x]);
	}
}
