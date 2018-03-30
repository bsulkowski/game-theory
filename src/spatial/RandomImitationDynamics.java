package gametheory.spatial;

import java.util.List;
import java.util.Random;

/**
 *	@author Bartosz Sułkowski
 */
public class RandomImitationDynamics implements SpatialDynamics {
	Random random = new Random();

	public int newStrategy(SpatialConfiguration configuration, int player) {
		List<Integer> neighbours = configuration.graph().neighbours(player);
		if (neighbours.size() == 0)
			return configuration.strategy(player);
		int x = neighbours.get(random.nextInt(neighbours.size()));
				
		if (configuration.payoff(x) > configuration.payoff(player))
			return configuration.strategy(x);
		else
			return configuration.strategy(player);
	}
}
