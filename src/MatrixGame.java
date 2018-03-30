package gametheory;

/**
 *	Gra zadana przez macierz wypłat.
 *
 *	@author Bartosz Sułkowski
 */
public class MatrixGame implements SymmetricDualGame {
	/**
	 * 	Macierz wypłat.
	 */
	double[][] payoffMatrix;

	/**
	 * 	Tworzy grę o danej liczbie strategii
	 */
	protected MatrixGame(int strategies) {
		payoffMatrix = new double[strategies][strategies];
	}
	/**
	 *	Tworzy grę o podanej macierzy.
	 */
	public MatrixGame(double[][] matrix) {
		this(matrix.length);
		for (int s1 = 0; s1 < strategies(); ++s1)
			for (int s2 = 0; s2 < strategies(); ++s2)
				payoffMatrix[s1][s2] = matrix[s1][s2];
	}
	
	public int players() {
		return 2;
	}
	public int strategies() {
		return payoffMatrix.length;
	}
	public int strategies(int player) {
		return strategies();
	}

	public double payoff(int strategy1, int strategy2) {
		return payoffMatrix[strategy1][strategy2];
	}
	public double[] payoff(int[] configuration) {
		double[] p = new double[2];
		p[0] = payoff(configuration[0], configuration[1]);
		p[1] = payoff(configuration[1], configuration[0]);
		return p;
	}
}
