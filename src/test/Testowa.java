package gametheory.test;

import gametheory.graph.BarabasiAlbertGraph;

public class Testowa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		gametheory.graph.Graph g = new BarabasiAlbertGraph(32, 16).createGraph();
		
		System.out.println(g.edges());
		
	}

}
