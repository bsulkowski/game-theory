package gametheory.test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;

public class SimpleChart extends JFrame {
	BufferedImage image;
	JLabel label;
	Graphics graphics;
	
	static int width = 800;
	static int height = 600;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SimpleChart chart = new SimpleChart();
		chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chart.pack();
		chart.setResizable(false);
		chart.setVisible(true);
		chart.loadData();
	}

	public SimpleChart() {
		super("Simple Chart");

		image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		graphics = image.getGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, width, height);
		
		label = new JLabel(new ImageIcon(image));
		
		this.getContentPane().add(label);
	}

	public void loadData() {
		File path = new File("e:/test/");
		File[] files = path.listFiles();
		
		for (int i = 0; i < files.length; ++i) {
			try {
				FileInputStream inputStream = new FileInputStream(files[i]);
				ObjectInputStream stream = new ObjectInputStream(inputStream);
				SimulationData data = (SimulationData) stream.readObject();

				double temptation = (Double) data.parameters().get("temptation"); 

				System.out.println("temptation = " + temptation);
				System.out.flush();
				
				if (Math.abs(temptation - 1.5) > 0.000001)
					continue;
				
				Integer[] cooperators = (Integer[]) data.getData("cooperators");
				double payin = (Double) data.parameters().get("payin");
				int players = (Integer) data.parameters().get("size");
				
				double sum = 0;
				double sum_squares = 0;
				int count = 0;
				for (int t = 0; t < cooperators.length; ++t) {
					double x = (double) cooperators[t] / players;
//					if (x > 0.7) continue;
					sum += x;
					sum_squares += x * x;
					++count;
				}

				double average = sum / count;
				double squares_average = sum_squares / count; 				
				double standard_deviation = Math.sqrt(squares_average - average * average);

				System.out.println("average = " + average + " payin = " + payin);
				
				int x = (int) (payin * width);
				int y = (int) (height - average * height);
				int r = 1; 
				int d = (int) (standard_deviation * height);
				graphics.setColor(Color.BLUE);
				graphics.drawLine(x, y - d, x, y + d); 
				graphics.drawLine(x - r, y - d, x + r, y - d); 
				graphics.drawLine(x - r, y + d, x + r, y + d); 
				graphics.setColor(Color.WHITE);
				graphics.fillRect(x - r, y - r, 2 * r + 1, 2 * r + 1);
				
				label.repaint();

				stream.close();
				inputStream.close();
			}
			catch (Exception e) {
				// IGNORE
			}
		}
	}
}
