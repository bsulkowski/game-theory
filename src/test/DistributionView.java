package gametheory.test;

import java.awt.*;

import gametheory.gui.BufferedImageView;

public class DistributionView extends BufferedImageView {
	public static final int RESOLUTION = 100; 
	
	Graphics graphics;

	int[] density;
	int N;
	
	public DistributionView(double[] data) {
		super(300, 200);
		graphics = image.createGraphics();
        setData(data);
	}

	public void setData(double[] data) {
        density = new int[RESOLUTION + 1];
        for (int i = 0; i < RESOLUTION; ++i)
        	density[i] = 0;
        for (int t = 0; t < data.length; ++t)
        	++density[(int) (data[t] * RESOLUTION)];
        density[RESOLUTION - 1] += density[RESOLUTION];
        
        N = 0;
        for (int i = 0; i < RESOLUTION; ++i)
        	if (density[i] > N)
        		N = density[i];
	}
	
	public void updateImage() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(Color.YELLOW);
		for (int i = 0; i < RESOLUTION; ++i) {
			int h = height * density[i] / N;
	        graphics.fillRect(width * i / RESOLUTION,
	        				height - h,
	        				width / RESOLUTION,
	        				h);
		}
	}
}
