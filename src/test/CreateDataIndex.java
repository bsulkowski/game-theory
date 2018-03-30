package gametheory.test;

import java.io.*;
import java.util.*;

public class CreateDataIndex {

	public static void main(String[] args) throws Exception {
		ArrayList<SimulationStatistics> index;
		try {
			InputStream iis = new FileInputStream("k:/symulacje/" + "index.sim");
			ObjectInputStream iois = new ObjectInputStream(iis);
			index =	(ArrayList<SimulationStatistics>) iois.readObject();
			iois.close();
			iis.close();
		} catch (FileNotFoundException e) {
			index = new ArrayList<SimulationStatistics>(); 
		}		

		HashSet<String> names = new HashSet(index.size() + 1);
		for (SimulationStatistics s : index) {
			names.add(s.file);
		}
		names.add("index.sim");
		
		
		File path = new File("k:/symulacje/");
		File[] files = path.listFiles();

		for (int i = 0; i < files.length; ++i) {
			if (!files[i].isFile())
				continue;
			if (names.contains(files[i].getName()))
				continue;
			
			InputStream is = new FileInputStream(files[i]);
			ObjectInputStream ois = new ObjectInputStream(is);
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

			SimulationStatistics statistics = new SimulationStatistics();
			statistics.parameters = data.parameters();
			statistics.size = data.size();
			statistics.average = new HashMap<String, Double>();
			statistics.squares_average = new HashMap<String, Double>();
			statistics.file = files[i].getName();
			names.add(statistics.file);

			for (String value : data.getValues()) {
				double sum = 0.0;
				double squares_sum = 0.0;
				Integer[] v = (Integer[]) data.getData(value);
				for (int t = data.size() / 10; t < data.size(); ++t) { // UWAGA!
					sum += v[t];
					squares_sum += ((double) v[t]) * ((double) v[t]);
				}
				statistics.average.put(value, sum / (data.size() * 9 / 10));
				statistics.squares_average.put(value, squares_sum / (data.size() * 9 / 10));
			}
			
			index.add(statistics);

			if (index.size() % 100 == 0) {
				OutputStream os = new FileOutputStream("k:/symulacje/" + "index.sim");
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(index);
				oos.close();
				os.close();

				System.out.println(index.size() + " " + statistics.file);
			}
		}
		
		OutputStream os = new FileOutputStream("k:/symulacje/" + "index.sim");
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(index);
		oos.close();
		os.close();
		
		System.out.println("index.sim");
	}

}
