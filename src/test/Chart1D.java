package gametheory.test;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class Chart1D {

	public static void main(String[] args) throws Exception {
		int border = 50;
		int width = 800 - 2 * border;
		int height = 600 - 2 * border;
		int r = 3; 

		InputStream is = new FileInputStream("l:/test/index.sim");
		ObjectInputStream ois = new ObjectInputStream(is);
		ArrayList<SimulationStatistics> index =
			(ArrayList<SimulationStatistics>) ois.readObject();

		NumberFormat format;
		format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(2);
		
		
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("game", "normalized prisoners dilemma");
//		filter.put("game", "normalized snowdrift");
//		filter.put("graph", "barabasi-albert");
		filter.put("graph", "erdos-renyi");
		filter.put("size", 10000);
		filter.put("degree", 8);
//		filter.put("graph", "square lattice");
		filter.put("payoff", "sum");
//		filter.put("payoff", "average");
		filter.put("dynamics", "imitation");
		filter.put("model", "sequential spatial game");

		double err = 0.000001;
		
		String fileName = "pd_er_gamma";
		
		BufferedImage image = new BufferedImage(width + 2 * border, height + 2 * border, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graphics = image.getGraphics(); 
		graphics.setColor(Color.BLUE);
		graphics.fillRect(0, 0, width + 2 * border, height + 2 * border);

		double Xmin = -0.8;
		double Xmax =  2.2;
		int Xcount = 15;
		for (int i = 0; i <= Xcount; ++i) {
			graphics.setColor(Color.BLACK);
			int x = width * i / Xcount;
			graphics.drawLine(x + border, border, x + border, height + border);
			graphics.setColor(Color.WHITE);
			graphics.drawString(format.format(Xmin + (Xmax - Xmin) * i / Xcount), x + border, height + border + border / 2);
		}
		double Ymin = 0;
		double Ymax = 10000;
		int Ycount = 10;
		for (int i = 0; i <= Ycount; ++i) {
			graphics.setColor(Color.BLACK);
			int y = height - height * i / Ycount;
			graphics.drawLine(border, y + border, width + border, y + border); 
			graphics.setColor(Color.WHITE);
			graphics.drawString(format.format(Ymin + (Ymax - Ymin) * i / Ycount), border / 4, y + border);
		}
		
		for (int T = -2; T <= 2; ++T) {
			double temptation = 1.5 + T * 0.2;
			double punishment = 0.0;
			graphics.setColor(Color.YELLOW);

			int x0 = 0;
			int y0 = 0;
			
			for (int P = -15; P <= 15; ++P) {
				if (((P < -5) || (P > 5)) && (P % 2 == 0))
					continue;
				double payin = 0.7 + P * 0.1;
				
				
				double average = 0;
				double squares_average = 0;
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
					if (Math.abs(temptation - (Double) statistics.parameters.get("temptation")) > err)
						continue;
					if (Math.abs(punishment - (Double) statistics.parameters.get("punishment")) > err)
						continue;
//					if (Math.abs(ratio - (Double) statistics.parameters.get("ratio")) > err)
//						continue;
					if (Math.abs(payin - (Double) statistics.parameters.get("payin")) > err)
						continue;

					
					if (Math.abs(0.001 - (Double) statistics.parameters.get("mutation rate")) > err)
						continue;
					if (statistics.size != 10000)
						continue;

//					int size;
//					if (statistics.parameters.get("graph").equals("square lattice"))
//						size = (Integer) statistics.parameters.get("width")
//						* (Integer) statistics.parameters.get("height");
//					else
//						size = (Integer) statistics.parameters.get("size");
//
//					if (size != 10000)
//						continue;

					System.out.println(statistics.parameters);
					
					average += statistics.average.get("cooperators"); // / size;
					squares_average += statistics.squares_average.get("cooperators"); // / size / size;
					++count;
				}
		
				average /= count;
				squares_average /= count;
				double deviation = Math.sqrt(squares_average - average * average);
				
				int x = (int) (((payin - Xmin) / (Xmax - Xmin)) * width) + border;
				int y = (int) (height - ((average - Ymin) / (Ymax - Ymin)) * height) + border;
				int d = (int) (deviation / (Ymax - Ymin) * height);

				System.out.println(payin);
				System.out.println(average);
				System.out.println(x);
				System.out.println(y);
				
//				graphics.drawLine(x, y - d, x, y + d); 
//				graphics.drawLine(x - r, y - d, x + r, y - d); 
//				graphics.drawLine(x - r, y + d, x + r, y + d); 
				graphics.fillOval(x - r, y - r, 2 * r + 1, 2 * r + 1);

				if (x0 != 0)
					graphics.drawLine(x0, y0, x, y);
				x0 = x;
				y0 = y;
			}
		}
		
		ImageIO.write(image, "png", new File("l:/chart/" + fileName + ".png"));
		System.out.println(fileName);
	}
	
}
