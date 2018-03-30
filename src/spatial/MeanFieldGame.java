package gametheory.spatial;

import gametheory.SymmetricDualGame;
import gametheory.graph.SparseGraph;

import java.util.Random;

// FIXME napisać
/**
 * 	Model z idealnie mieszającą się populacją.
 * 
 *	@author Bartosz Sułkowski
 */
public class MeanFieldGame implements SpatialModel {
	Random random = new Random();
	SpatialConfiguration configuration;
	SymmetricDualGame game;
	int players;
	int strategies;

	public MeanFieldGame(SymmetricDualGame game, int players, double[] strategy) {
		this.game = game;
		this.players = players;
		strategies = game.strategies();		
		configuration = new SpatialConfiguration(new SparseGraph(players),
												 new SumPayoff(game, 0.0, false));
		for (int x = 0; x < players; ++x) {
			double p = random.nextDouble();
			int s = 0;
			while (s < strategies - 1) {
				p -= strategy[s];
				if (p < 0)
					break;
				++s;
			}
			configuration.setStrategy(x, s);
		}
	}
	
	public void advanceTime() {
		for (int t = 0; t < players; ++t) {
			int player = random.nextInt(players);
			// FIXME zaślepka
			configuration.setStrategy(player, 0);
		}
	}
	
	public SpatialConfiguration configuration() {
		return configuration;
	}
}
