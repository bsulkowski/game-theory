package gametheory.test;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class ExtractStatistics {
	static double threshold = 0.7;
	static double grid = 0.00001;
	
	static class StatValue {
		int simulationsCount = 0;
		double majorSum = 0.0, majorSqSum = 0.0;
		int majorCount = 0;
		double minorSum = 0.0, minorSqSum = 0.0;
		int minorCount = 0;
	}
	
	static double snapToGrid(double x) {
		return Math.floor(x / grid + 0.5) * grid;
	}
	
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("k:/symulacje/index.sim");
		ObjectInputStream ois = new ObjectInputStream(is);
		ArrayList<SimulationStatistics> index =
			(ArrayList<SimulationStatistics>) ois.readObject();
		ois.close();
		is.close();

		NumberFormat format;
		format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(4);
		format.setMaximumFractionDigits(6);
		
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("game", "normalized prisoners dilemma");
//		filter.put("game", "normalized snowdrift");
//		filter.put("graph", "barabasi-albert");
//		filter.put("size", 10000);
//		filter.put("degree", 8);
		filter.put("graph", "erdos-renyi");
		filter.put("size", 10000);
		filter.put("degree", 8);
//		filter.put("graph", "original barabasi-albert");
//		filter.put("size", 10000);
//		filter.put("m0", 4);
//		filter.put("m", 4);
//		filter.put("graph", "square lattice");
		filter.put("payoff", "sum");
//		filter.put("payoff", "average");
		filter.put("dynamics", "imitation");
//		filter.put("dynamics", "normalized moran");
//		filter.put("dynamics", "global moran");
		filter.put("model", "sequential spatial game");
//		filter.put("model", "inverted sequential spatial game");

		String fileName = "pd_er_imitation";

		HashMap<List<Double>, StatValue> stats = new HashMap<List<Double>, StatValue>();
		
//		int ile = index.size();
		int juz = 0;
		
		for (SimulationStatistics statistics : index) {
			if ((++juz) % 100 == 0) {
				System.out.println(juz + "");
			}
//			if (juz % (ile / 100) == 0)
//				System.out.println(juz * 100 / ile + "%");
			
//			if (juz > 500)
//				break;
			
			boolean c = false;
			for (String parameter : filter.keySet()) {
				if (!filter.get(parameter).equals(statistics.parameters.get(parameter))) {
					c = true;
					break;
				}
			}
			if (c)
				continue;

			double temptation = snapToGrid((Double) statistics.parameters.get("temptation"));
			double punishment = snapToGrid((Double) statistics.parameters.get("punishment"));
//			double ratio = snapToGrid((Double) statistics.parameters.get("ratio"));
			
			double payin = snapToGrid((Double) statistics.parameters.get("payin"));
//			double payin = 0.0;
//			double mutationRate = snapToGrid((Double) statistics.parameters.get("mutation rate"));
//			if (mutationRate != 0.001)
//				continue;
			if (statistics.size != 10000)
				continue;

			List<Double> statKey = new ArrayList<Double>(3);
			statKey.add(payin);
			statKey.add(temptation);
			statKey.add(punishment);
			statKey.add(0.0);
//			statKey.add(0.0);
//			statKey.add(0.0);
//			statKey.add(ratio);
			
			StatValue statValue;
			if (stats.containsKey(statKey))
				statValue = stats.get(statKey);
			else {
				statValue = new StatValue();
				stats.put(statKey, statValue);
			}
			++statValue.simulationsCount;
			
//			System.out.println(statistics.parameters);
			
			is = new FileInputStream("k:/symulacje/" + statistics.file);
			ois = new ObjectInputStream(is);
			SimulationData data;
			try {
				data = (SimulationData) ois.readObject();
			}
			catch (Exception e) {
				ois.close();
				is.close();
				continue;
			}
			ois.close();
			is.close();

			Integer[] v = (Integer[]) data.getData("cooperators");
			for (int t = data.size() * 9 / 10; t < data.size(); ++t) { // UWAGA!!!
				double val = (double) v[t] / 10000; // graph size!
				
				if (val >= threshold) {
					++statValue.majorCount;
					statValue.majorSum += val;
					statValue.majorSqSum += val * val;
				}
				else {
					++statValue.minorCount;
					statValue.minorSum += val;
					statValue.minorSqSum += val * val;
				}
			}
		}

		OutputStream os = new FileOutputStream("k:/statystyki/" + fileName + ".stats");
		PrintStream ps = new PrintStream(os);
		
		ps.println("payin" + "\t"
					+ "temptation" + "\t"
					+ "punishment" + "\t"
					+ "ratio" + "\t"
					+ "simulations" + "\t"
					+ "average" + "\t"
					+ "deviation" + "\t"
					+ "major fraction" + "\t"
					+ "major average" + "\t"
					+ "major deviation" + "\t"
					+ "minor average" + "\t"
					+ "minor deviation" + "\t");
				
		
		for (List<Double> statKey : stats.keySet()) {
			StatValue statValue = stats.get(statKey);

			double payin = statKey.get(0);
			double temptation = statKey.get(1);
			double punishment = statKey.get(2);
			double ratio = statKey.get(3);
			int simulations = statValue.simulationsCount;
			double avg = (statValue.majorSum + statValue.minorSum) / (statValue.majorCount + statValue.minorCount);
//			double avg = 0.0;
			double sqAvg = (statValue.majorSqSum + statValue.minorSqSum) / (statValue.majorCount + statValue.minorCount);
			double deviation = Math.sqrt(sqAvg - avg * avg);
			double majorFraction = (double) statValue.majorCount / (statValue.majorCount + statValue.minorCount);
//			double majorFraction = 0.0;
			double majorAvg = 0.0;
			double majorDeviation = 0.0; 
			if (statValue.majorCount > 0) {
				majorAvg = statValue.majorSum / statValue.majorCount;
				double majorSqAvg = statValue.majorSqSum / statValue.majorCount;
				majorDeviation = Math.sqrt(majorSqAvg - majorAvg * majorAvg);
			}
			double minorAvg = 0.0; 
			double minorDeviation = 0.0;
			if (statValue.minorCount > 0) {
				minorAvg = statValue.minorSum / statValue.minorCount;
				double minorSqAvg = statValue.minorSqSum / statValue.minorCount;
				minorDeviation = Math.sqrt(minorSqAvg - minorAvg * minorAvg);
			}

			ps.println(format.format(payin) + "\t"
					+ format.format(temptation) + "\t"
					+ format.format(punishment) + "\t"
					+ format.format(ratio) + "\t"
					+ simulations + "\t"
					+ format.format(avg) + "\t"
					+ format.format(deviation) + "\t"
					+ format.format(majorFraction) + "\t"
					+ format.format(majorAvg) + "\t"
					+ format.format(majorDeviation) + "\t"
					+ format.format(minorAvg) + "\t"
					+ format.format(minorDeviation) + "\t");
		}
		
		ps.close();
		os.close();
		System.out.println(fileName + ".stats");
	}
	
}
