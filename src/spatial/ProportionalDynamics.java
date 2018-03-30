package gametheory.spatial;

import java.util.Random;

/**
 * 	Gracz wybiera strategię proporcjonalnie do sumy wypłat,
 *  którą uzyskali grający nią gracze z jego otoczenia.
 * 
 *	@author Bartosz Sułkowski
 */
public class ProportionalDynamics implements SpatialDynamics {
	Random random = new Random();

	// FIXME wszystko się psuje, gdy któraś wypłata jest ujemna lub suma = 0
	public int newStrategy(SpatialConfiguration configuration, int player) {
		int strategies = configuration.strategies();
		double[] strategyPayoff = new double[strategies];
		
		for (int s = 0; s < strategies; ++s)
			strategyPayoff[s] = 0.0;
		strategyPayoff[configuration.strategy(player)] = configuration.payoff(player);
    	for (int x : configuration.graph().neighbours(player))
			strategyPayoff[configuration.strategy(x)] += configuration.payoff(x);

		double sum = 0.0;
		for (int s = 0; s < strategies; ++s)
			sum += strategyPayoff[s];
		
		double p = random.nextDouble() * sum;
		for (int s = 0; s < strategies; ++s) {
			p -= strategyPayoff[s];
			if (p < 0)
				return s;
		}
		return strategies - 1;
	}
}
