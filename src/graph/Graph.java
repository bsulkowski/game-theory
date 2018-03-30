package gametheory.graph;

import java.util.List;

/**
 *	Założenie:
 *		Może być zmieniany przez używające go obiekty.
 *
 *	@author Bartosz Sułkowski
 */
public interface Graph {
	int size();
	int edges();
	/**
	 * 	Zwracanej listy nie powinno się bezpośrednio zmieniać!
	 */
	List<Integer> neighbours(int x);
	int degree(int x);
	boolean hasEdge(int x, int y);
	
	/**
	 * 	Dodaje krawędź. Dopuszczalne są wielokrotne krawędzie i pętle.
	 */
	void addEdge(int x, int y);
	/**
	 *  Usuwa krawędź, jeśli istnieje i zwraca, czy się udało.
	 */
	boolean removeEdge(int x, int y);
	
	/**
	 *	Jeśli się zmniejsza, to są usuwane wierzchołki wraz z krawędziami;
	 * 	w p.p. dodawane są nowe, niepołączone z innymi wierzchołki.
	 */
	void setSize(int n);
	/**
	 * 	Usuwa wszystkie połączenia wychodzące z danego wierzchołka.
	 */
	void disconnectVertex(int x);
	/**
	 * 	Zamienia wierzchołki x i y miejscami.
	 */
	void swapVertices(int x, int y);
}
