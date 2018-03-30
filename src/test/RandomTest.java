package gametheory.test;

import gametheory.util.ProportionalRandom;

public class RandomTest {

	public static void main(String[] args) {

		ProportionalRandom r = new ProportionalRandom();
		
		for (int i = 0; i <= 10000; ++i) {
			r.addObject(i, i);
		}
		for (int i = 0; i <= 10000; ++i) {
			r.setWeight(i, i);
		}
		
//		double[] p = new double[10001];
		for (int t = 0; t < 10000; ++t) {
			int i = (Integer) r.getRandomElement();
			if (i < 1000)
				System.out.println(t + ": " + i);
		}
		
//		for (int i = 0; i <= 10; ++i) {
//			System.out.println(i + ": " + p[i]);
//		}
	}

}
