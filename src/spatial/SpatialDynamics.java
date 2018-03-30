package gametheory.spatial;

/**
 * 	Sposób wybierania nowej strategii przez gracza.
 * 
 *	@author Bartosz Sułkowski
 */
public interface SpatialDynamics {
	/**
	 *	@param configuration  konfiguracja przestrzenna
	 *	@param player  numer gracza
	 *	@return nowa strategia
	 */
	int newStrategy(SpatialConfiguration configuration, int player);
}
