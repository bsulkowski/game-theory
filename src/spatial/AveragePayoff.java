package gametheory.spatial;

import gametheory.SymmetricDualGame;

/**
 * 	Wypłata obliczana jako średnia wypłat.
 * 	Jeśli gracz nie ma sąsiadów, to zawsze gra sam ze sobą.
 * 
 *	@author Bartosz Sułkowski
 */
public class AveragePayoff implements SpatialPayoff {
	private SymmetricDualGame game;
	private boolean selfInteractions;
	
	private int strategies;
	
	/**
	 * 	@param game  w co grają
	 * 	@param selfInteractions  czy grają ze sobą
	 */
	public AveragePayoff(SymmetricDualGame game, boolean selfInteractions) {
		this.game = game;
		this.selfInteractions = selfInteractions;
		
		strategies = game.strategies();
	}

	public double payoff(int strategy, int[] neighboursStrategy) {
		double sum = 0.0;
		int count = 0;

		for (int s = 0; s < strategies; ++s) {
			sum += game.payoff(strategy, s) * neighboursStrategy[s];
			count += neighboursStrategy[s];
		}

		if ((selfInteractions) || (count == 0)) {
			sum += game.payoff(strategy, strategy);
			++count;
		}

		return sum / count;
	}

	public SymmetricDualGame game() {
		return game;
	}
}
