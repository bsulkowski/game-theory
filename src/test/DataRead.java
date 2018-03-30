package gametheory.test;

import java.io.*;

public class DataRead {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInputStream inputStream = new FileInputStream("e:/test/1182604827656.sim");
		ObjectInputStream stream = new ObjectInputStream(inputStream);
		
		SimulationData data = (SimulationData) stream.readObject();

		for (String key : data.parameters().keySet())
			System.out.println(key + " = " + data.parameters().get(key));

//		for (String value : data.getValues()) {
//			Integer[] d = (Integer[]) data.getData(value);
//			for (int i = 0; i < data.size(); ++i)
//				System.out.println(value + "[" + i + "] = " + d[i]);
//		}
	}
}
