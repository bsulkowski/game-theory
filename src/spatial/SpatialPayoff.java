package gametheory.spatial;

import gametheory.SymmetricDualGame;

/**
 * 	Sposób liczenia łącznej wypłaty gracza na sieci.
 * 
 *	@author Bartosz Sułkowski
 */
public interface SpatialPayoff {
	/**
	 * 	@param strategy  strategia gracza
	 * 	@param neighboursStrategy  liczba sąsiadów grających poszczególnymi strategiami
	 *	@return	łączna wypłata
	 */
	double payoff(int strategy, int[] neighboursStrategy);
	/**
	 * 	@return w co się gra
	 */ 
	SymmetricDualGame game();
}
