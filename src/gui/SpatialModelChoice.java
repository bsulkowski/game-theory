package gametheory.gui;

import gametheory.graph.GraphGenerator;
import gametheory.spatial.DynamicNetworkGame;
import gametheory.spatial.EdgeReplicatorSpatialGame;
import gametheory.spatial.InvertedReplicatorSpatialGame;
import gametheory.spatial.SemiSpatialGame;
import gametheory.spatial.SequentialSpatialGame;
import gametheory.spatial.SimultaneousSpatialGame;
import gametheory.spatial.SpatialConfiguration;
import gametheory.spatial.SpatialDynamics;
import gametheory.spatial.SpatialModel;
import gametheory.spatial.SpatialPayoff;

import java.util.Random;

public class SpatialModelChoice extends ParametrizedChoice {
	Random random = new Random();
	
	public SpatialModelChoice() {
		choiceList = new String[] {
				"mean_field",
				"semi_mean_field",
				
				"simultaneous_spatial",
				"sequential_spatial",
				"semi_spatial",
				"inverted_replicator",
				"edge_replicator",

				"dynamic_network"
		};
		choiceDefault = 3;
		parameterList = new String[][] {
				{},
				{"degree"},

				{},
				{},
				{"const_graph"},
				{"max_payoff"},
				{"max_payoff"},

				{}
		};
		parameterDefault = new Object[][] {
				{},
				{8},

				{},
				{},
				{true},
				{1.0},
				{1.0},

				{}
		};
		parameterType = new int[][] {
				{},
				{INTEGER},

				{},
				{},
				{BOOLEAN},
				{DOUBLE},
				{DOUBLE},

				{}
		};

		setDefaultValues();
	}

	public SpatialModel createSpatialModel(
			GraphGenerator generator,
			SpatialPayoff payoff,
			SpatialDynamics dynamics) {

		// FIXME PILNE zaślepka
		SpatialConfiguration configuration =
			new SpatialConfiguration(generator.createGraph(), payoff);
		for (int x = 0; x < configuration.graph().size(); ++x) {
			if (random.nextInt(2) == 0)
				configuration.setStrategy(x, 1);
		}
		
		if (choiceList[choice].equals("mean_field")) {
			// FIXME zaślepka
			return null;
//			return new MeanFieldGame(configuration, mut);
		}
		if (choiceList[choice].equals("semi_mean_field")) {
			// FIXME zaślepka
			return null;
		}
		
		if (choiceList[choice].equals("simultaneous_spatial")) {
			return new SimultaneousSpatialGame(configuration, dynamics);
		}
		if (choiceList[choice].equals("sequential_spatial")) {
			return new SequentialSpatialGame(configuration, dynamics);
		}
		if (choiceList[choice].equals("semi_spatial")) {
			return new SemiSpatialGame(configuration, dynamics, generator,
					(Boolean) getParameter("const_graph")
			);
		}
		if (choiceList[choice].equals("inverted_replicator")) {
			return new InvertedReplicatorSpatialGame(configuration,
					(Double) getParameter("max_payoff")
			);
		}
		if (choiceList[choice].equals("edge_replicator")) {
			return new EdgeReplicatorSpatialGame(configuration,
					(Double) getParameter("max_payoff")
			);
		}
		
		if (choiceList[choice].equals("dynamic_network")) {
			return new DynamicNetworkGame(configuration, dynamics);
		}
		
		return null;
	}
}
