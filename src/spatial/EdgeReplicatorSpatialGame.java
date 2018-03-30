package gametheory.spatial;

import java.util.List;
import java.util.Random;

/**
 * 	Gra przestrzenna z sekwencyjną (losową) zmianą strategii przez graczy.
 * 
 *	@author Bartosz Sułkowski
 */
public class EdgeReplicatorSpatialGame implements SpatialModel {
	Random random = new Random();
	SpatialConfiguration configuration;
	double maxPayoff;
	
	int[] connection;
	int connections;

	int players;
	int changes;

	public EdgeReplicatorSpatialGame(SpatialConfiguration configuration,
			double maxPayoff) {
		this.configuration = configuration;
		this.maxPayoff = maxPayoff;
		
		players = configuration.graph().size();
		
		connections = configuration.graph().edges() * 2;
		connection = new int[connections];
		int c = 0;
		for (int x = 0; x < players; ++x) {
			List<Integer> neighbours = configuration.graph().neighbours(x);
			for (Integer y : neighbours) {
				connection[c] = y;
				++c;
			}
		}
	}
	
	public void advanceTime() {
		changes = 0;
		for (int t = 0; t < players; ++t) {
			int player = connection[random.nextInt(connections)];
			
			List<Integer> neighbours = configuration.graph().neighbours(player);
			if (neighbours.size() == 0)
				continue;
			int x = neighbours.get(random.nextInt(neighbours.size()));
			int k = neighbours.size();
			int k1 = configuration.graph().neighbours(x).size();
			if (k1 > k)
				k = k1;
			
			if (random.nextDouble()
				< (configuration.payoff(x) - configuration.payoff(player)) / (maxPayoff * k))
				if (configuration.setStrategy(
						player,
						configuration.strategy(x)
					))
						++ changes;
			
		}
	}

	public int getChanges() {
		return changes;
	}
	
	public SpatialConfiguration configuration() {
		return configuration;
	}
}
