package gametheory.spatial;

import gametheory.graph.GraphGenerator;

import java.util.Random;

/**
 *	Gra pół-przestrzenna, w której sieć połączeń jest losowana co turę.
 *	Gracze zmieniają swoje strategie sekwencyjnie.
 *
 *	@author Bartosz Sułkowski
 */
public class SemiSpatialGame implements SpatialModel {
	Random random = new Random();
	SpatialConfiguration configuration;
	SpatialDynamics dynamics;
	GraphGenerator generator;
	boolean constGraph;	
	
	int players;

	// TODO możliwość samego mieszania graczy, bez losowania grafu
	public SemiSpatialGame(SpatialConfiguration configuration,
			SpatialDynamics dynamics,
			GraphGenerator generator,
			boolean constGraph) {
		this.configuration = configuration;
		this.dynamics = dynamics;
		this.generator = generator;
		this.constGraph = constGraph;
		
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
		// FIXME dodać losowe mieszanie graczy
		configuration.setGraph(generator.createGraph());
	}
	
	public SpatialConfiguration configuration() {
		return configuration;
	}
}
