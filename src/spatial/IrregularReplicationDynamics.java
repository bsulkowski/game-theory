package gametheory.spatial;

import java.util.Random;
import java.util.List;

/**
 *	Na podstawie opisu z pracy
 *  "Scale-free networks provide a unifying framework for the emergence of cooperation"
 *  
 *	@author Bartosz Sułkowski
 */
public class IrregularReplicationDynamics implements SpatialDynamics {
	Random random = new Random();
	double max;

	public IrregularReplicationDynamics(double max) {
		this.max = max;
	}
	
	public int newStrategy(SpatialConfiguration configuration, int player) {
		List<Integer> neighbours = configuration.graph().neighbours(player);
		if (neighbours.size() == 0)
			return configuration.strategy(player);
		int x = neighbours.get(random.nextInt(neighbours.size()));
		int k = neighbours.size();
		int k1 = configuration.graph().neighbours(x).size();
		if (k1 > k)
			k = k1;

		if (random.nextDouble()
			< (configuration.payoff(x) - configuration.payoff(player)) / (max * k))
			return configuration.strategy(x);
		else
			return configuration.strategy(player);
	}
}

