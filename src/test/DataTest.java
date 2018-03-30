package gametheory.test;

import java.io.*;

public class DataTest {
	public static void main(String[] args) throws IOException {
		SimulationData data = new SimulationData(10);
		
		data.parameters().put("nazwa", "Kwadraty liczb");
		data.parameters().put("time stamp", System.currentTimeMillis());
		
		Integer[] tablica = new Integer[data.size()]; 
		for (int i = 0; i < data.size(); ++i)
			tablica[i] = i*i;
		
		data.putData("kwadrat", tablica);
		
		FileOutputStream outputStream = new FileOutputStream("e:/test/kwadraty.sim");
		ObjectOutputStream stream = new ObjectOutputStream(outputStream);
		stream.writeObject(data);
		outputStream.close();
	}
}
