package gametheory.spatial;

import java.util.Random;
import java.util.List;

/**
 *	Na podstawie opisu z pracy
 *  "Spatial structure often inhibits the evolution of cooperation in the snowdrift game"
 *  
 *	@author Bartosz Sułkowski
 */
abstract public class ReplicationDynamics implements SpatialDynamics {

    Random random = new Random();

    public int newStrategy(SpatialConfiguration configuration, int player) {
        List<Integer> neighbours = configuration.graph().neighbours(player);
        if (neighbours.size() == 0) {
            return configuration.strategy(player);
        }
        int x = neighbours.get(random.nextInt(neighbours.size()));

        if (random.nextDouble()
                < probability(configuration.payoff(x) - configuration.payoff(player))) {
            return configuration.strategy(x);
        } else {
            return configuration.strategy(player);
        }
    }

    abstract protected double probability(double difference);
}
