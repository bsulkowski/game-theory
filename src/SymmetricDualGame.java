package gametheory;

/**
 *	Symetryczna gra dwuosobowa.
 *
 *	@author Bartosz Sułkowski
 */
public interface SymmetricDualGame extends Game {
	/**
	 *	Wypłata dla grającego strategią 1 przeciw grającemu strategią 2.
	 */
	double payoff(int strategy1, int strategy2);
	
	/**
	 * 	Liczba możliwych strategii.
	 */
	int strategies();
}
