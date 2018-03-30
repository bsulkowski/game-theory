package gametheory.test;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class ChartDistribution {

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

		
		int cut = 0;
		
//		int N = 9;
//		double Gmin = 0.1;
//		double Gstep = 0.1;
		int N = 9;
		double Gmin = 1.1;
		double Gstep = 0.1;
//		int N = 21;
//		double Gmin = -0.5;
//		double Gstep = 0.1;
//		int N = 11;
//		double Gmin = 0.4;
//		double Gstep = 0.01;

		double Gmax = Gmin + (N - 1) * Gstep;
		double Gerr = 0.000001;


//		for (int I = 0; I <= 20; ++I) {
		
//		double payin =   -0.5 + I * 0.1;
//		double ratio =           0.1;
//		double temptation =      1.5;
//		double punishment = 0.0;
//		String fileName = "sd_sq_x_" + format.format(payin);
		String fileName = "pda_sq_x";

		
		BufferedImage image = new BufferedImage(width + 2 * border, height + 2 * border, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = image.getGraphics(); 
		graphics.setColor(Color.BLUE);
		graphics.fillRect(0, 0, width + 2 * border, height + 2 * border);
		
		double[] cooperation = new double[N];
		double[] cooperation_squares = new double[N];
		double[] cooperation_deviation = new double[N];
		int[] count = new int[N];
		for (int i = 0; i < N; ++i) {
			cooperation[i] = 0.0;
			cooperation_squares[i] = 0.0;
			cooperation_deviation[i] = 0.0;
			count[i] = 0;
		}
		
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
//			if (Math.abs(temptation - (Double) statistics.parameters.get("temptation")) > 0.000001)
//				continue;
//			if (Math.abs(punishment - (Double) statistics.parameters.get("punishment")) > 0.000001)
//				continue;
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
			
			double average = statistics.average.get("cooperators");
			double squares_average = statistics.squares_average.get("cooperators");
			
			for (int i = 0; i < N; ++i) {
				if (Math.abs(Gmin + Gstep * i - (Double) statistics.parameters.get("temptation")) > Gerr)
						continue;
//				if (Math.abs(0.0  - (Double) statistics.parameters.get("punishment")) > Gerr)
//					continue;
				if (Math.abs(Gmin -1.0 + Gstep * i - (Double) statistics.parameters.get("punishment")) > Gerr)
					continue;
//				if (Math.abs(Gmin + Gstep * i - (Double) statistics.parameters.get("ratio")) > Gerr)
//					continue;
				cooperation[i] += average / size;
				cooperation_squares[i] += squares_average / ((double) size * size);
				++count[i];
			}
		}
		

		for (int i = 0; i < N; ++i) {
			cooperation[i] /= count[i];
			cooperation_squares[i] /= count[i];
			cooperation_deviation[i] = Math.sqrt(cooperation_squares[i] - cooperation[i] * cooperation[i]);
		}


		for (int i = 0; i < N; ++i) {
			graphics.setColor(Color.BLACK);
			int x = width * i / (N - 1);
			graphics.drawLine(x + border, border, x + border, height + border);
			graphics.setColor(Color.WHITE);
			graphics.drawString(format.format(Gmin + i * Gstep), x + border, height + border + border / 2);
		}
		for (int i = 0; i <= 10; ++i) {
			graphics.setColor(Color.BLACK);
			int y = height - height * i / 10;
			graphics.drawLine(border, y + border, width + border, y + border); 
			graphics.setColor(Color.WHITE);
			graphics.drawString(format.format((double) i / 10), border / 4, y + border);
		}

		
		
		graphics.setColor(Color.YELLOW);
		
		for (int i = 0; i < N - cut; ++i) {
			int x = (int) (width * i / (N - 1)) + border;
			int y = (int) (height - cooperation[i] * height) + border;
			int r = 3; 
			int d = (int) (cooperation_deviation[i] * height);
			graphics.drawLine(x, y - d, x, y + d); 
			graphics.drawLine(x - r, y - d, x + r, y - d); 
			graphics.drawLine(x - r, y + d, x + r, y + d); 
			graphics.fillOval(x - r, y - r, 2 * r + 1, 2 * r + 1);
		}

		for (int i = 0; i < N - 1 - cut; ++i) {
			int x0 = (int) (width * i / (N - 1)) + border;
			int y0 = (int) (height - cooperation[i] * height) + border;
			int x1 = (int) (width * (i + 1) / (N - 1)) + border;
			int y1 = (int) (height - cooperation[i + 1] * height) + border;
			graphics.drawLine(x0, y0, x1, y1);
		}
		
		ImageIO.write(image, "png", new File("l:/chart/" + fileName + ".png"));
		System.out.println(fileName);
//	}
	}
	
}
