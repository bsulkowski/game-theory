package gametheory.spatial;

import java.util.Random;

/**
 * 	Z pewnym prawdopodobieństwem gracz wybiera losową strategię,
 * 	w przeciwnym wypadku postępuje zgodnie z inną (daną) dynamiką.
 * 
 *	@author Bartosz Sułkowski
 */
public class MutationDynamics implements SpatialDynamics {
	SpatialDynamics dynamics;
	double mutationRate;
	
	Random random = new Random();

	/**
	 * 	@param dynamics  pierwotna dynamika
	 * 	@param mutationRate  prawdopodobieństwo mutacji
	 */
	public MutationDynamics(SpatialDynamics dynamics, double mutationRate) {
		this.dynamics = dynamics;
		this.mutationRate = mutationRate;
	}

	public int newStrategy(SpatialConfiguration configuration, int player) {
		if (random.nextDouble() < mutationRate)
			return random.nextInt(configuration.strategies());
		else
			return dynamics.newStrategy(configuration, player);
	}
}
