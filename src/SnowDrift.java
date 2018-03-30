package gametheory;

/**
 *	Gra w "snowdrift".
 *
 *	@author Bartosz Sułkowski
 */
public class SnowDrift extends MatrixGame {
	public static final int DEFECT = 0;
	public static final int COOPERATE = 1;

	public SnowDrift(double benefit, double cost) {
		super(new double[][] {{0   			 , benefit			 },
							  {benefit - cost, benefit - cost / 2}});
	}
	public SnowDrift(double ratio) {
		super(new double[][] {{0   		, 1 + ratio},
							  {1 - ratio, 1        }});
	}
}
