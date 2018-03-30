package gametheory;

/**
 *	Gra w dylemat więźnia.
 *
 *	@author Bartosz Sułkowski
 */
public class PrisonersDilemma extends MatrixGame {
	public static final int DEFECT = 0;
	public static final int COOPERATE = 1;

	public PrisonersDilemma(double temptation, double punishment) {
		this(temptation, 1.0, punishment, 0.0);
	}
	public PrisonersDilemma(double temptation, double reward,
			double punishment, double suckersPayoff) {
		super(new double[][] {{punishment   , temptation},
							  {suckersPayoff, reward    }});
	}
}
