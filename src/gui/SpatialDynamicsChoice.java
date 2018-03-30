package gametheory.gui;

import gametheory.spatial.BestResponseDynamics;
import gametheory.spatial.ExponentialReplicationDynamics;
import gametheory.spatial.ImitationDynamics;
import gametheory.spatial.IrregularReplicationDynamics;
import gametheory.spatial.LinearReplicationDynamics;
import gametheory.spatial.MutationDynamics;
import gametheory.spatial.ProportionalDynamics;
import gametheory.spatial.SpatialDynamics;
import gametheory.spatial.SumImitationDynamics;

public class SpatialDynamicsChoice extends ParametrizedChoice {
	public SpatialDynamicsChoice() {
		choiceList = new String[] {
				"best_response",
				"imitation",

				"replication",
				"irregular_replication",
                                "exponential_replication",

				"sum_imitation",
				"proportional"
		};
		choiceDefault = 1;
		parameterList = new String[][] {
				{"mutations"},
				{"mutations"},

                                {"min", "max"},
				{"max"},
                                {"epsilon"},
				
				{"mutations"},
				{}
		};
		parameterDefault = new Object[][] {
				{0.001},
				{0.001},

				{0.0, 1.0},
				{1.0},
                                {0.1},
				
				{0.001},
				{}
		};
		parameterType = new int[][] {
				{DOUBLE},
				{DOUBLE},

				{DOUBLE, DOUBLE},
				{DOUBLE},
				{DOUBLE},
				
				{DOUBLE},
				{}
		};
		
		setDefaultValues();
	}

	public SpatialDynamics getSpatialDynamics() {
		if (choiceList[choice].equals("best_response")) {
			return new MutationDynamics(
					new BestResponseDynamics(),
					(Double) getParameter("mutations")
			);
		}
		if (choiceList[choice].equals("imitation")) {
			return new MutationDynamics(
					new ImitationDynamics(),
					(Double) getParameter("mutations")
			);
		}

		if (choiceList[choice].equals("replication")) {
			return new LinearReplicationDynamics(
					(Double) getParameter("min"),
					(Double) getParameter("max")
			);
		}
		if (choiceList[choice].equals("irregular_replication")) {
			return new IrregularReplicationDynamics(
					(Double) getParameter("max")
			);
		}
		if (choiceList[choice].equals("exponential_replication")) {
			return new ExponentialReplicationDynamics(
					(Double) getParameter("epsilon")
			);
		}

		if (choiceList[choice].equals("sum_imitation")) {
			return new MutationDynamics(
					new SumImitationDynamics(),
					(Double) getParameter("mutations")
			);
		}
		if (choiceList[choice].equals("proportional")) {
			return new ProportionalDynamics();
		}
		return null;
	}
}
