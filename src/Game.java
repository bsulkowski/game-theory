package gametheory;

/**
 *	Ogólna teoriogrowa definicja gry.
 *
 *	@author Bartosz Sułkowski
 */
public interface Game {
	/**
	 *	Liczba graczy.
	 */
	int players();
	/**
	 * 	Liczba strategii danego gracza.
	 */
	int strategies(int player);

	/**
	 * 	Wypłaty dla danej konfiguracji.
	 */
	double[] payoff(int[] configuration);
}
