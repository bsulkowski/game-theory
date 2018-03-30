package gametheory.spatial;

import java.util.Random;

/**
 * 	Gracz wybiera tę strategię, którą grający gracze z jego otoczenia
 *  uzyskali największą sumę wypłat.
 *  W przypadku remisu wybierana jest losowa z nich.
 *  
 *	@author Bartosz Sułkowski
 */
public class SumImitationDynamics implements SpatialDynamics {
	Random random = new Random();

	public int newStrategy(SpatialConfiguration configuration, int player) {
		int strategies = configuration.strategies();
		double[] strategyPayoff = new double[strategies];
		
		for (int s = 0; s < strategies; ++s)
			strategyPayoff[s] = 0.0;
		strategyPayoff[configuration.strategy(player)] = configuration.payoff(player);
    	for (int x : configuration.graph().neighbours(player))
			strategyPayoff[configuration.strategy(x)] += configuration.payoff(x);

    	double maxPayoff = Double.MIN_VALUE;
    	int maxStrategy = -1;
        int maxCount = 0;
    	
    	for (int s = 0; s < strategies; ++s) {
    		if (strategyPayoff[s] > maxPayoff) {
    			maxPayoff = strategyPayoff[s];
    			maxStrategy = s;
    			maxCount = 1;
    		}
    		else if (strategyPayoff[s] == maxPayoff) {
    		    ++maxCount;
    		    if (random.nextInt(maxCount) == 0)
    		        maxStrategy = s;
    		}
    	}

		return maxStrategy;
	}
}
