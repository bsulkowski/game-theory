package gametheory.gui;

import java.awt.*;
import java.text.*;


public class Distribution extends BufferedImageView {
		static final Color BACKGROUND = Color.BLACK;
		static final Color GRAPH      = Color.YELLOW;
		static final Color AXIS_MINOR = Color.DARK_GRAY;
		static final Color AXIS_MAJOR = Color.GRAY;
		static final Color AXIS_BOUND = Color.RED;
		static final Color AXIS_MEAN  = Color.BLUE;

	    Statistics statistics;
	    static final int n = 100;
	    

	    Graphics graphics;
		NumberFormat format;

	    public Distribution(Statistics statistics) {
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
	    	int[] prob = new int[n];

			for (int t = 0; t < statistics.size(); ++t) {
	    		int i = (int) (statistics.getValue(t) * n);
	    		++prob[i];
	    	}
	    	
			// tło
	        graphics.setColor(BACKGROUND);
	        graphics.fillRect(0, 0, width, height);

        	if (statistics.size() == 0)
				return;

	        int max = 0;
			for (int i = 0; i < n; ++i)
				if (prob[i] > max)
					max = prob[i];
        	
			LinearScale scale = new LinearScale(
					0, max,
					height, 0); 
			
			// właściwy wykres
		    graphics.setColor(GRAPH);
		    for (int i = 0; i < n; ++i) {
		    	int y = (int) scale.point(prob[i]);
	            graphics.fillRect(
	            	(width * i) / n, y,
	            	width / n, height - y);
	        } 
	    }
}
