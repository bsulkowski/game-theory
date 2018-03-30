package gametheory.gui;

import gametheory.SymmetricDualGame;
import gametheory.spatial.AveragePayoff;
import gametheory.spatial.SpatialPayoff;
import gametheory.spatial.SumPayoff;

public class SpatialPayoffChoice extends ParametrizedChoice {
	public SpatialPayoffChoice() {
		choiceList = new String[] {
				"sum",
				"average"
		};
		choiceDefault = 0;
		parameterList = new String[][] {
				{"payin", "self"},
				{"self"}
		};
		parameterDefault = new Object[][] {
				{0.0, false}, 
				{false}
		};
		parameterType = new int[][] {
				{DOUBLE, BOOLEAN}, 
				{BOOLEAN}
		};
			
		setDefaultValues();
	}

	public SpatialPayoff getSpatialPayoff(SymmetricDualGame game) {
		if (choiceList[choice].equals("sum")) {
			return new SumPayoff(
					game,
					(Double) getParameter("payin"),
					(Boolean) getParameter("self")
			);
		}
		if (choiceList[choice].equals("average")) {
			return new AveragePayoff(
					game,
					(Boolean) getParameter("self")
			);
		}

		return null;
	}
}
