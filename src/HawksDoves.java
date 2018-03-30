package gametheory;

/**
 *	Gra w jasrzębie i gołębie.
 *
 *	@author Bartosz Sułkowski
 */
public class HawksDoves extends MatrixGame {
	public static final int HAWK = 0;
	public static final int DOVE = 1;

	public HawksDoves(double benefit, double cost) {
		super(new double[][] {{benefit / 2 - cost, benefit	  },
							  {0				 , benefit / 2}});
	}
}
