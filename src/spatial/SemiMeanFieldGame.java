package gametheory.spatial;

import gametheory.graph.GraphGenerator;

import java.util.Random;

// FIXME zaprojektować i napisać
/**
 * 	Model według pomysłu prof. Miękisza 
 * 
 *	@author Bartosz Sułkowski
 */
public class SemiMeanFieldGame implements SpatialModel {
	Random random = new Random();
	SpatialConfiguration configuration;
	SpatialDynamics dynamics;
	GraphGenerator generator;
	int players;

	public SemiMeanFieldGame(SpatialConfiguration configuration,
									 SpatialDynamics dynamics,
									 GraphGenerator generator) {
		this.configuration = configuration;
		this.dynamics = dynamics;
		this.generator = generator;
		players = configuration.graph().size();
	}
	
	public void advanceTime() {
		for (int t = 0; t < players; ++t) {
			int player = random.nextInt(players);
			configuration.setStrategy(
				player,
				dynamics.newStrategy(configuration, player)
			);
		}
	}
	
	public SpatialConfiguration configuration() {
		return configuration;
	}
}
