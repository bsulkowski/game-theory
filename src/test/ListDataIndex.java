package gametheory.test;

import java.io.*;
import java.util.*;

public class ListDataIndex {

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("g:/gametheory/index.sim");
		ObjectInputStream ois = new ObjectInputStream(is);
		ArrayList<SimulationStatistics> index =
			(ArrayList<SimulationStatistics>) ois.readObject();

		TreeMap<String, TreeSet<Object>> availableParameters =
			new TreeMap<String, TreeSet<Object>>();

		HashMap<String, Object> filter = new HashMap<String, Object>();
//		filter.put("game", "normalized prisoners dilemma");
//		filter.put("game", "normalized snowdrift");
//		filter.put("graph", "barabasi-albert");
//		filter.put("size", 10000);
//		filter.put("degree", 4);
//		filter.put("graph", "erdos-renyi");
		filter.put("size", 100000);
//		filter.put("degree", 8);
//		filter.put("graph", "original barabasi-albert");
//		filter.put("size", 10000);
//		filter.put("m0", 4);
//		filter.put("m", 4);
//		filter.put("graph", "square lattice");
//		filter.put("payoff", "sum");
//		filter.put("payoff", "average");
//		filter.put("dynamics", "imitation");
//		filter.put("dynamics", "normalized moran");
//		filter.put("dynamics", "global moran");
//		filter.put("model", "sequential spatial game");
//		filter.put("model", "inverted sequential spatial game");
	
		
		for (SimulationStatistics statistics : index) {
			if (statistics.size != 10000)
				continue;
			
			boolean c = false;
			for (String parameter : filter.keySet()) {
				if (!filter.get(parameter).equals(statistics.parameters.get(parameter))) {
					c = true;
					break;
				}
			}
			if (c)
				continue;
			
			for (String parameter : statistics.parameters.keySet()) {
				if (!availableParameters.containsKey(parameter)) {
					availableParameters.put(parameter, new TreeSet<Object>());
				}
				availableParameters.get(parameter).add(statistics.parameters.get(parameter));
			}
		}
		
		for(String parameter : availableParameters.keySet()) {
			System.out.println(parameter + " = ");
			for (Object o : availableParameters.get(parameter)) {
				System.out.println("\t" + o);
			}
		}
	
		
	}

}
