package gametheory;

/**
 *	Gra w kamień-papier-nożyczki.
 *
 *	@author Bartosz Sułkowski
 */
public class RockScissorsPaper extends MatrixGame {
	public static final int ROCK = 0;
	public static final int SCISSORS = 1;
	public static final int PAPER = 2;

	public RockScissorsPaper() {
		super(new double[][] {{ 0.0 , 1.0, -1.0},
							  {-1.0,  0.0,  1.0},
							  { 1.0, -1.0,  0.0}});
	}
}
