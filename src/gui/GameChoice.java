package gametheory.gui;

import gametheory.*;

public class GameChoice extends ParametrizedChoice {
	public GameChoice() {
		choiceList = new String[] {
				"prisoners_dilemma",
				"snowdrift",
				"hawks_doves",
				
				"rock_scissors_paper",
				"generic_2"
		};
		choiceDefault = 0;
		parameterList = new String[][] {
				{"t", "r", "p", "s"},
				{"r"},
				{"b", "c"},
				
				{},
				{"p00", "p01", "p10", "p11"}
		};
		parameterDefault = new Object[][] {
				{1.5, 1.0, 0.0, 0.0},
				{0.5},
				{1.0, 0.25},

				{},
				{0.0, 0.0, 0.0, 0.0}
		};
		parameterType = new int[][] {
				{DOUBLE, DOUBLE, DOUBLE, DOUBLE},
				{DOUBLE},
				{DOUBLE, DOUBLE},

				{},
				{DOUBLE, DOUBLE, DOUBLE, DOUBLE}
		};
		
		setDefaultValues();
	}

	public SymmetricDualGame getGame() {
		if (choiceList[choice].equals("prisoners_dilemma")) {
			return new PrisonersDilemma(
					(Double) getParameter("t"),
					(Double) getParameter("r"),
					(Double) getParameter("p"),
					(Double) getParameter("s")
			);
		}
		if (choiceList[choice].equals("snowdrift")) {
			return new SnowDrift(
					(Double) getParameter("r")
			);
		}
		if (choiceList[choice].equals("hawks_doves")) {
			return new HawksDoves(
					(Double) getParameter("b"),
					(Double) getParameter("c")
			);
		}
		if (choiceList[choice].equals("rock_scissors_paper")) {
			return new RockScissorsPaper();
		}
		if (choiceList[choice].equals("generic_2")) {
			return new MatrixGame(new double[][] {
					{(Double) getParameter("p00"), (Double) getParameter("p01")},
					{(Double) getParameter("p10"), (Double) getParameter("p11")}
			});
		}

		return null;
	}
}
