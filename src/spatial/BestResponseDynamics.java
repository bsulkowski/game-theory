package gametheory.spatial;

import java.util.Random;

/**
 * 	Gracz wybiera najlepszą kontrstrategię na obecne strategie sąsiadów.
 * 	Jeśli jest kilka równoopłacalnych możliwości, to wybiera losową z nich.
 * 
 *	@author Bartosz Sułkowski
 */
public class BestResponseDynamics implements SpatialDynamics {
	Random random = new Random();

	public int newStrategy(SpatialConfiguration configuration, int player) {
		SpatialPayoff spatialPayoff = configuration.spatialPayoff();
		int[] neighboursStrategy = configuration.neighboursStrategy(player);
        int strategies = configuration.strategies();

    	double maxPayoff = Double.MIN_VALUE;
    	int maxStrategy = -1;
        int maxCount = 0;
    	
    	for (int s = 0; s < strategies; ++s) {
    		double payoff = spatialPayoff.payoff(s, neighboursStrategy);
    		if (payoff > maxPayoff) {
    			maxPayoff = payoff;
    			maxStrategy = s;
    			maxCount = 1;
    		}
    		else if (payoff == maxPayoff) {
    		    ++maxCount;
    		    if (random.nextInt(maxCount) == 0)
    		        maxStrategy = s;
    		}
    	}
    	
    	return maxStrategy;
	}
}
