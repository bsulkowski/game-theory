package gametheory.graph;

/**
 *	Założenie:
 *		Każde wywołanie metody createGraph zwraca nową,
 *		niezależną od innych kopię grafu.
 *
 *	@author Bartosz Sułkowski
 */
public interface GraphGenerator {
	/**
	 * 	Zwraca nowy graf.
	 */
	Graph createGraph();
}
