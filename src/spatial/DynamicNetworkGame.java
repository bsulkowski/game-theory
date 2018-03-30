package gametheory.spatial;

import java.util.Random;

/**
 * 	Gra na sieci z dynamicznie zmieniającymi się połączeniami.
 * 
 *	@author Bartosz Sułkowski
 */
public class DynamicNetworkGame implements SpatialModel {
	Random random = new Random();
	SpatialConfiguration configuration;
	SpatialDynamics dynamics;
	int players;

	public DynamicNetworkGame(SpatialConfiguration configuration,
						      SpatialDynamics dynamics) {
		this.configuration = configuration;
		this.dynamics = dynamics;
		players = configuration.graph().size();
	}
	
	public void advanceTime() {
		// FIXME wymyślić model i zaimplementować
	}
	
	public SpatialConfiguration configuration() {
		return configuration;
	}
}
