package gametheory.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class IndexConvert {
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("k:/symulacje/index.sim");
		ObjectInputStream ois = new ObjectInputStream(is);
		ArrayList<SimulationStatistics> index =
			(ArrayList<SimulationStatistics>) ois.readObject();
		ois.close();
		is.close();
		System.out.println("index.sim");
		System.out.flush();
		
		OutputStream os = new FileOutputStream("k:/symulacje/" + "index2.sim");
		ObjectOutputStream oos = new ObjectOutputStream(os);
		for (int i = 0; i < index.size() - 200; ++i)
			oos.writeObject(index.get(i));
//		for (SimulationStatistics s : index) {
//			oos.writeObject(s);
//		}
		oos.close();
		os.close();
		System.out.println("index2.sim");
		System.out.flush();
	}
}
