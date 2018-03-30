package gametheory.spatial;

import java.util.List;
import java.util.Random;

/**
 * 	Gra przestrzenna z sekwencyjną (losową) zmianą strategii przez graczy.
 * 
 *	@author Bartosz Sułkowski
 */
public class SequentialSpatialGame implements SpatialModel {
	Random random = new Random();
	SpatialConfiguration configuration;
	SpatialDynamics dynamics;

	int players;
	int changes;

	public SequentialSpatialGame(SpatialConfiguration configuration,
								   SpatialDynamics dynamics) {
		this.configuration = configuration;
		this.dynamics = dynamics;
		
		players = configuration.graph().size();
	}
	
	public void advanceTime() {
		changes = 0;
		for (int t = 0; t < players; ++t) {
			int player = random.nextInt(players);

			// UWAGA! TESTY!!!
//			List<Integer> neighbours = configuration.graph().neighbours(player);
//			if (neighbours.size() > 0)
//				for (int i = 0; i < 10; ++i) {
//					neighbours = configuration.graph().neighbours(player);
//					player = neighbours.get(random.nextInt(neighbours.size()));
//				}
			
			if (configuration.setStrategy(
				player,
				dynamics.newStrategy(configuration, player)
			))
				++ changes;
		}
	}

	public int getChanges() {
		return changes;
	}
	
	public SpatialConfiguration configuration() {
		return configuration;
	}
}
