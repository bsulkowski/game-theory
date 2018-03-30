package gametheory.test;

import java.io.*;
import java.util.*;

public class ReadDataIndex {

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("k:/symulacje/index.sim");
		ObjectInputStream ois = new ObjectInputStream(is);
		ArrayList<SimulationStatistics> index =
			(ArrayList<SimulationStatistics>) ois.readObject();

		int i = 0;
		
		for (SimulationStatistics statistics : index) {
			int size;
			if (statistics.parameters.get("graph").equals("square lattice"))
				size = (Integer) statistics.parameters.get("width")
					* (Integer) statistics.parameters.get("height");
			else
				size = (Integer) statistics.parameters.get("size");

//			if (size != 1000)
//				continue;
			
			System.out.println(++i + " " + statistics.parameters);
				
//			for (String value : statistics.average.keySet()) {
//				double average = statistics.average.get(value);
//				double squares_average = statistics.squares_average.get(value);
//				double deviation = Math.sqrt(squares_average - average * average);
//				System.out.println(value
//								+ "  = " + average / size 
//								+ "  +/- " + deviation / size);
//			}
//			System.out.println();
		}
		
		
	}

}
