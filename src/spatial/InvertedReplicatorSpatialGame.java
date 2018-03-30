package gametheory.spatial;

import java.util.List;
import java.util.Random;

/**
 * 	Gra przestrzenna z sekwencyjną (losową) zmianą strategii przez graczy.
 * 
 *	@author Bartosz Sułkowski
 */
public class InvertedReplicatorSpatialGame implements SpatialModel {
	Random random = new Random();
	SpatialConfiguration configuration;
	double maxPayoff;

	int players;
	int changes;

	public InvertedReplicatorSpatialGame(SpatialConfiguration configuration,
			double maxPayoff) {
		this.configuration = configuration;
		this.maxPayoff = maxPayoff;
		
		players = configuration.graph().size();
	}
	
	public void advanceTime() {
		changes = 0;
		for (int t = 0; t < players; ++t) {
			int player = random.nextInt(players);
			
			List<Integer> neighbours = configuration.graph().neighbours(player);
			if (neighbours.size() == 0)
				continue;
			int x = neighbours.get(random.nextInt(neighbours.size()));
			int k = neighbours.size();
			int k1 = configuration.graph().neighbours(x).size();
			if (k1 > k)
				k = k1;
			
			if (random.nextDouble()
				< (configuration.payoff(player) - configuration.payoff(x)) / (maxPayoff * k))
				if (configuration.setStrategy(
						x,
						configuration.strategy(player)
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
