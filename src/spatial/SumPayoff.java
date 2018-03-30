package gametheory.spatial;

import gametheory.SymmetricDualGame;

/**
 * 	Wypłata obliczana jako suma wypłat.
 * 
 *	@author Bartosz Sułkowski
 */
public class SumPayoff implements SpatialPayoff {
	private SymmetricDualGame game;
	private double payin;
	private boolean selfInteractions;
	
	private int strategies;

	/**
	 * 	@param game  w co grają
	 * 	@param payin  koszt udziału w grze
	 * 	@param selfInteractions  czy grają ze sobą
	 */
	public SumPayoff(SymmetricDualGame game, double payin, boolean selfInteractions) {
		this.game = game;
		this.payin = payin;
		this.selfInteractions = selfInteractions;
		
		strategies = game.strategies();
	}
	public SumPayoff(SymmetricDualGame game, double payin) {
		this(game, payin, false);
	}
	public SumPayoff(SymmetricDualGame game) {
		this(game, 0.0);
	}

	public double payoff(int strategy, int[] neighboursStrategy) {
		double sum = 0.0;

		for (int s = 0; s < strategies; ++s)
			sum += (game.payoff(strategy, s) - payin) * neighboursStrategy[s];

		if (selfInteractions)
			sum += game.payoff(strategy, strategy) - payin;
			 
		return sum;
	}

	public SymmetricDualGame game() {
		return game;
	}
}
