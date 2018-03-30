package gametheory.spatial;

/**
 *  Replikacja proporcjonalna do exp(payoff / epsilon)
 *
 * @author Bartosz Sułkowski
 */
public class ExponentialReplicationDynamics extends ReplicationDynamics {
    double epsilon;

    public ExponentialReplicationDynamics(double epsilon) {
        this.epsilon = epsilon;
    }

    protected double probability(double difference) {
        return 1.0 / (1.0 + Math.exp(-difference / epsilon));
    }
}
