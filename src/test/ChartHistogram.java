package gametheory.test;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class ChartHistogram {

	public static void main(String[] args) throws Exception {
		int border = 50;
		int width = 800 - 2 * border;
		int height = 600 - 2 * border;
		InputStream is = new FileInputStream("l:/test/index.sim");
		ObjectInputStream ois = new ObjectInputStream(is);
		ArrayList<SimulationStatistics> index =
			(ArrayList<SimulationStatistics>) ois.readObject();

		NumberFormat format;
		format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(1);
		format.setMaximumFractionDigits(3);
		
		
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("game", "normalized prisoners dilemma");
//		filter.put("game", "normalized snowdrift");
//		filter.put("graph", "barabasi-albert");
//		filter.put("graph", "erdos-renyi");
		filter.put("graph", "square lattice");
//		filter.put("size", 10000);
//		filter.put("degree", 8);
//		filter.put("payoff", "sum");
//		filter.put("payoff", "average");
		filter.put("dynamics", "imitation");
		filter.put("model", "sequential spatial game");

		
//		int cut = 0;
		
//		int N = 9;
//		double Gmin = 0.1;
//		double Gstep = 0.1;
//		int N = 9;
//		double Gmin = 1.1;
//		double Gstep = 0.1;
//		int N = 21;
//		double Gmin = -0.5;
//		double Gstep = 0.1;
//		int N = 11;
//		double Gmin = 0.4;
//		double Gstep = 0.01;

//		double Gmax = Gmin + (N - 1) * Gstep;
//		double Gerr = 0.000001;


//		for (int I = 0; I <= 10; ++I) {
			for (int J = 0; J <= 8; ++J) {
		
//		String fileName = "sd_sq_x_" + format.format(payin);
//		double payin = -0.5 + I * 0.2;
//		double ratio = 0.1 + J * 0.1;
//		double payin = 0.4 + I * 0.01;
		double temptation = 1.1 + J * 0.1;
//		double punishment = temptation - 1.0;
		double punishment = 0.0;
//		String fileName = "sd_ba_" + format.format(ratio) + "_" + format.format(payin) + "_HIST";
		String fileName = "pd_sq_" + format.format(temptation) + "_HIST";

		int count = 0;

		for (SimulationStatistics statistics : index) {
			boolean c = false;
			for (String parameter : filter.keySet()) {
				if (!filter.get(parameter).equals(statistics.parameters.get(parameter))) {
					c = true;
					break;
				}
			}
			if (c)
				continue;
			if (Math.abs(temptation - (Double) statistics.parameters.get("temptation")) > 0.000001)
				continue;
			if (Math.abs(punishment - (Double) statistics.parameters.get("punishment")) > 0.000001)
				continue;
//			if (Math.abs(ratio - (Double) statistics.parameters.get("ratio")) > 0.000001)
//				continue;
//			if (Math.abs(payin - (Double) statistics.parameters.get("payin")) > 0.000001)
//				continue;
			if (Math.abs(0.001 - (Double) statistics.parameters.get("mutation rate")) > 0.000001)
				continue;
			if (statistics.size != 10000)
				continue;

			int size;
			if (statistics.parameters.get("graph").equals("square lattice"))
				size = (Integer) statistics.parameters.get("width")
					* (Integer) statistics.parameters.get("width");
			else
				size = (Integer) statistics.parameters.get("size");

//			System.out.println(statistics.parameters);

			InputStream is1 = new FileInputStream("l:/test/" + statistics.file);
			ObjectInputStream ois1 = new ObjectInputStream(is1);
			SimulationData data = (SimulationData) ois1.readObject();
			ois1.close();
			is1.close();
		
			int N = data.size();
			Integer[] cooperators = (Integer[]) data.getData("cooperators");

			BufferedImage image = new BufferedImage(width + 2 * border, height + 2 * border, BufferedImage.TYPE_3BYTE_BGR);
			Graphics graphics = image.getGraphics(); 
			graphics.setColor(Color.BLUE);
			graphics.fillRect(0, 0, width + 2 * border, height + 2 * border);
			
			int xres = 10;
			int yres = 10;
			
			for (int i = 0; i <= xres; ++i) {
				graphics.setColor(Color.BLACK);
				int x = width * i / xres;
				graphics.drawLine(x + border, border, x + border, height + border);
				graphics.setColor(Color.WHITE);
				graphics.drawString(" " + (i * N / xres), x + border, height + border + border / 2);
			}
			for (int i = 0; i <= yres; ++i) {
				graphics.setColor(Color.BLACK);
				int y = height - height * i / yres;
				graphics.drawLine(border, y + border, width + border, y + border); 
				graphics.setColor(Color.WHITE);
				graphics.drawString(format.format((double) i / yres), border / 4, y + border);
			}

			graphics.setColor(Color.YELLOW);

			int x0 = 0 + border;
			int y0 = height - cooperators[0] * height / size + border;
			
			for (int i = 1; i < N; ++i) {
				int x1 = (int) (width * i / N) + border;
				int y1 = (int) (height - cooperators[i] * height / size) + border;
				graphics.drawLine(x0, y0, x1, y1);
				x0 = x1;
				y0 = y1;
			}

			ImageIO.write(image, "png", new File("l:/chart/" + fileName + count + ".png"));
			System.out.println(fileName + count);
			++count;
		}
//			}	
	}
	}
	
}
