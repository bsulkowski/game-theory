package gametheory.experiment;

import gametheory.test.SimulationData;

import java.io.*;
import java.util.Map;

public class DataProbe {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File path = new File("e:/test/");
		File[] files = path.listFiles();
		
		for (int i = 0; i < files.length; ++i) {
			if (!files[i].isFile())
				continue;
			FileInputStream inputStream = new FileInputStream(files[i]);
			ObjectInputStream stream = new ObjectInputStream(inputStream);
			SimulationData data;
			try {
				data = (SimulationData) stream.readObject();
			} catch (ClassCastException e) {
				continue;
			}
			stream.close();
			inputStream.close();

			System.out.print(".");
			
			Map<String, Object> par = data.parameters();
			if (!par.get("graph").equals("erdos-renyi"))
				continue;
			if (!par.get("payoff").equals("average"))
				continue;

			System.out.println("");
			System.out.print(par);
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
