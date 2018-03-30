package gametheory.spatial;

import java.util.Random;

/**
 * 	Gracz wybiera strategię gracza ze swojego otoczenia, mającego najwyższą wypłatę.
 * 	Jeśli jest kilku graczy o równych wypłatach, to wybiera losowego z nich.
 * 
 *	@author Bartosz Sułkowski
 */
public class ImitationDynamics implements SpatialDynamics {
	Random random = new Random();

	public int newStrategy(SpatialConfiguration configuration, int player) {
    	double maxPayoff = configuration.payoff(player);
    	int maxStrategy = configuration.strategy(player);
        int maxCount = 1;
    	
    	for (int x : configuration.graph().neighbours(player)) {
    		double payoff = configuration.payoff(x);
			// CHECK może da się to zrobić szybciej
    		if (payoff > maxPayoff + 1e-9) {
    			maxPayoff = payoff;
    			maxStrategy = configuration.strategy(x);
    			maxCount = 1;
    		}
    		else if (payoff > maxPayoff - 1e-9) {
    		    ++maxCount;
    		    if (random.nextInt(maxCount) == 0)
    		        maxStrategy = configuration.strategy(x);
    		}
    	}
    	
    	return maxStrategy;
	}
}
