package gametheory.spatial;

/**
 * 	Gra przestrzenna z jednoczesną zmianą strategii przez graczy.
 * 
 *	@author Bartosz Sułkowski
 */
public class SimultaneousSpatialGame implements SpatialModel {
	SpatialConfiguration configuration;
	SpatialDynamics dynamics;
	
	int players;
	int[] newStrategies;

	// TODO dodać możliwość niepełnej synchronizacji
	public SimultaneousSpatialGame(SpatialConfiguration configuration,
								   SpatialDynamics dynamics) {
		this.configuration = configuration;
		this.dynamics = dynamics;
		
		players = configuration.graph().size();
		newStrategies = new int[players];
	}
	
	public void advanceTime() {
		for (int player = 0; player < players; ++player)
			newStrategies[player] = dynamics.newStrategy(configuration, player);
		configuration.setStrategy(newStrategies);
	}
	
	public SpatialConfiguration configuration() {
		return configuration;
	}
}
