package gametheory.gui;

import java.awt.*;
import java.text.*;

/**
 *	@author Bartosz Sułkowski
 */
public class Histogram extends BufferedImageView {
	static final Color BACKGROUND = Color.BLACK;
	static final Color GRAPH      = Color.YELLOW;
	static final Color AXIS_MINOR = Color.DARK_GRAY;
	static final Color AXIS_MAJOR = Color.GRAY;
	static final Color AXIS_BOUND = Color.RED;
	static final Color AXIS_MEAN  = Color.BLUE;

//	static final Color BACKGROUND = Color.WHITE;
//	static final Color GRAPH      = Color.BLUE;
//	static final Color AXIS_MINOR = Color.LIGHT_GRAY;
//	static final Color AXIS_MAJOR = Color.GRAY;
//	static final Color AXIS_BOUND = Color.BLACK;
//	static final Color AXIS_MEAN  = Color.RED;

//	static final Color BACKGROUND = Color.BLUE;
//	static final Color GRAPH      = Color.WHITE;
//	static final Color AXIS_MINOR = Color.DARK_GRAY;
//	static final Color AXIS_MAJOR = Color.BLACK;
//	static final Color AXIS_BOUND = Color.RED;
//	static final Color AXIS_MEAN  = Color.YELLOW;

    Statistics statistics;

    Graphics graphics;
	NumberFormat format;
    
    public Histogram(Statistics statistics) {
        super(600, 200);
        graphics = image.createGraphics();
		format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(6);
        setStatistics(statistics);
    }
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
        update();
	}
	
    public void updateImage() {
//        int width = getWidth();
//        int height = getHeight();
		int yResolution = 10;
		int xStep = 100;

		int x, y, t;
		
		// tło
        graphics.setColor(BACKGROUND);
        graphics.fillRect(0, 0, width, height);

		// siatka pionowa
		for (int i = 1; i < yResolution; ++i) {
			y = height - (i * height) / yResolution;
			if ((i == 1) || (i == yResolution - 1))
		        graphics.setColor(AXIS_BOUND);
			else if (i % 2 == 0)
				graphics.setColor(AXIS_MINOR);
			else
				graphics.setColor(AXIS_MAJOR);
            graphics.drawLine(0, y, width, y);
        }

		if (statistics.size() == 0)
			return;

		// siatka pozioma i podpisy
		x = width - statistics.size() % xStep;
		t = (statistics.size() / xStep) * xStep;
		while ((x > 0) && (t >= 0)) {
			graphics.setColor(AXIS_MINOR);
			graphics.drawLine(x, 0, x, height);
			if (x > xStep) {
				graphics.setColor(AXIS_MAJOR);
	            graphics.drawString(t + "", x + 5, 15);
			}
			x -= xStep;
			t -= xStep;
		}

		// szczególny przypadek, gdy (min == max)
		if (statistics.min() == statistics.max()) {
			y = height / 2;
			graphics.setColor(AXIS_MAJOR);
            graphics.drawString(format.format(statistics.getValue(0)), 10, y - 5);
	        graphics.setColor(GRAPH);
			if (statistics.size() < width )
				graphics.drawLine(width - statistics.size(), y, width, y);
			else
				graphics.drawLine(0, y, width, y);
			return;
		}
		
		LinearScale scale = new LinearScale(
				statistics.min(), statistics.max(),
				height - height / yResolution, height / yResolution); 

		// linia średniej
		graphics.setColor(AXIS_MEAN);
		y = (int) scale.point(statistics.mean());
        graphics.drawLine(0, y, width, y);

		y = (int) scale.point(statistics.mean0());
        graphics.drawLine(0, y, width, y);
		y = (int) scale.point(statistics.mean1());
        graphics.drawLine(0, y, width, y);

		// podpisy pionowe
        graphics.setColor(AXIS_MAJOR);
		for (int i = 1; i < yResolution; i += 2) {
			y = height - (i * height) / yResolution;
			double value = scale.pointBack(y);
            graphics.drawString(format.format(value), 10, y - 5);
		}
		
		// właściwy wykres
		if (statistics.size() < width) {
			x = width - statistics.size();
			t = 0;
		}
		else {
			x = 0;
			t = statistics.size() - width;
		}
	    graphics.setColor(GRAPH);
		int y0;
		y = (int) scale.point(statistics.getValue(t));
		++x;
		++t;
		while (x < width) {
			y0 = y;
            y = (int) scale.point(statistics.getValue(t));
            graphics.drawLine(x - 1, y0, x, y);
			++x;
			++t;
        } 
    }
}
