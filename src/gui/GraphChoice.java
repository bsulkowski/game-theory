package gametheory.gui;

import gametheory.graph.*;

public class GraphChoice extends ParametrizedChoice {
	public GraphChoice() {
		choiceList = new String[] {
				"square_lattice",
				"lattice",
				"random_grid",

				"erdos_renyi",
				"kauffman",
				"random_regular",

				"watts_strogatz",
				"barabasi_albert",
				"original_barabasi_albert",
				"random_growing"
		};
		choiceDefault = 0;
		parameterList = new String[][] {
				{"width", "height", "diagonal", "dilution"},
				{"width", "dimensions"},
				{"size", "radius"},

				{"size", "degree"},
				{"size", "degree"},
				{"size", "degree"},

				{"size", "degree", "randomness"},
				{"size", "degree"},
				{"size", "m0", "m"},
				{"size", "degree"}
		};
		parameterDefault = new Object[][] {
				{100, 100, true, 0.0},
				{100, 2},
				{10000, 1.0},

				{10000, 8.0},
				{10000, 8.0},
				{10000, 8},

				{10000, 8, 0.1},
				{10000, 8.0},
				{10000, 4, 4},
				{10000, 8.0}
		};
		parameterType = new int[][] {
				{INTEGER, INTEGER, BOOLEAN, DOUBLE},
				{INTEGER, INTEGER},
				{INTEGER, DOUBLE},

				{INTEGER, DOUBLE},
				{INTEGER, DOUBLE},
				{INTEGER, INTEGER},

				{INTEGER, INTEGER, DOUBLE},
				{INTEGER, DOUBLE},
				{INTEGER, INTEGER, INTEGER},
				{INTEGER, DOUBLE}
		};
		
		setDefaultValues();
	}
	
	public GraphGenerator getGraphGenerator(GraphGenerator generator) {
		if (choiceList[choice].equals("square_lattice")) {
			SquareLattice lattice =	new SquareLattice(
					(Integer) getParameter("width"),
					(Integer) getParameter("height"),
					(Boolean) getParameter("diagonal")
			);
			double dilution = (Double) getParameter("dilution");
			if (dilution == 0.0)
				return lattice;
			else
				return new SiteDilutedGraph(lattice, dilution);
		}
		if (choiceList[choice].equals("lattice")) {
			return new Lattice(
					(Integer) getParameter("width"),
					(Integer) getParameter("dimensions")
			);
		}
		if (choiceList[choice].equals("random_grid")) {
			return new RandomGrid(
					(Integer) getParameter("size"),
					(Double) getParameter("radius")
			);
		}

		if (choiceList[choice].equals("erdos_renyi")) {
			return new ErdosRenyiGraph(
					(Integer) getParameter("size"),
					(Double) getParameter("degree")
			);
		}
		if (choiceList[choice].equals("kauffman")) {
			return new KauffmanGraph(
					(Integer) getParameter("size"),
					(Double) getParameter("degree")
			);
		}
		if (choiceList[choice].equals("random_regular")) {
			return new RandomRegularGraph(
					(Integer) getParameter("size"),
					(Integer) getParameter("degree")
			);
		}

		if (choiceList[choice].equals("watts_strogatz")) {
			return new WattsStrogatzGraph(
					(Integer) getParameter("size"),
					(Integer) getParameter("degree"),
					(Double) getParameter("randomness")
			);

		}
		if (choiceList[choice].equals("barabasi_albert")) {
			return new BarabasiAlbertGraph(
					(Integer) getParameter("size"),
					(Double) getParameter("degree")
			);
		}
		if (choiceList[choice].equals("original_barabasi_albert")) {
			return new BarabasiAlbertStandardGraph(
					(Integer) getParameter("size"),
					(Integer) getParameter("m0"),
					(Integer) getParameter("m")
			);
		}
		if (choiceList[choice].equals("random_growing")) {
			return new RandomGrowingGraph(
					(Integer) getParameter("size"),
					(Double) getParameter("degree")
			);
		}
		return null;
	}
}
