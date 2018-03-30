package gametheory.spatial;

/**
 *	Na podstawie opisu z pracy
 *  "Spatial structure often inhibits the evolution of cooperation in the snowdrift game"
 *  
 *	@author Bartosz Sułkowski
 */
public class LinearReplicationDynamics extends ReplicationDynamics {
	double min, max;
	
	public LinearReplicationDynamics(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	protected double probability(double difference) {
		if (difference < min)
			return 0.0;
		if (difference > max)
			return 1.0;
		return (difference - min) / (max - min); 
	}
}
