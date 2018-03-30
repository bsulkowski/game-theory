package gametheory.spatial;

/**
 * 	Model gry przestrzennej (na sieci).
 * 
 *	@author Bartosz Sułkowski
 */
public interface SpatialModel {
	/**
	 * 	Wykonuje jedną turę symulacji.
	 */
	void advanceTime();
	/**
	 * 	@return aktualna konfiguracja
	 */
	SpatialConfiguration configuration();
}
