package gametheory.experiment;

import gametheory.test.SimulationData;

import java.io.*;

public class SimpleStatistics {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File path = new File("e:/test/");
		File[] files = path.listFiles();
		
		for (int i = 0; i < files.length; ++i) {
			if (!files[i].isFile())
				continue;
			FileInputStream inputStream = new FileInputStream(files[i]);
			ObjectInputStream stream = new ObjectInputStream(inputStream);
			SimulationData data = (SimulationData) stream.readObject();
			stream.close();
			inputStream.close();
			
			int sum = 0;
			Integer[] count = (Integer[]) data.getData("cooperators");
			for (int t = 0; t < data.size(); ++t) {
				sum += count[t];
			}
			
			System.out.println(files[i].getName()
							+ wypisz(data, "payin")
							+ wypisz(data, "temptation")
							+ wypisz(data, "ratio")
					 		+ " \tcooperation= " + sum / data.size());
		}
	}	

	static String wypisz(SimulationData data, String parameter) {
		Object value = data.parameters().get(parameter);
		if (value == null)
			return "";
		Double x = (Double) value;
		Integer r = new Integer((int) (x * 1000));
		return " \t" + parameter + "= " + r.toString(); 
	}
}
