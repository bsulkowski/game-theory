package gametheory.gui;

import gametheory.spatial.SpatialConfiguration;
import gametheory.spatial.SpatialModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *	@author Bartosz Su≈Çkowski
 */
public class Simulation extends JFrame implements Runnable, ActionListener {
	SpatialModel model;
	Statistics[] strategyStatistics;
	Histogram histogram;
	Distribution distribution;
	
	int strategies;
	int time;
	
	volatile Thread thread;
	
	JLabel label;
	
	Locale locale;
	ResourceBundle messages;
	
	Random random = new Random();
	
	public Simulation(SpatialModel model, Locale locale) {
		this.locale = locale;
		messages = ResourceBundle.getBundle("gametheory.gui.messages", locale);
		
		setTitle(messages.getString("simulation.title"));
		
		this.model = model;
		strategies = model.configuration().strategies();
		strategyStatistics = new Statistics[strategies];
		for (int s = 0; s < strategies; ++s)
			strategyStatistics[s] = new Statistics();
		time = 0;
		
		JPanel mainPane = new JPanel(new BorderLayout());
		add(mainPane);
		
		JPanel buttonPane = new JPanel(new GridLayout(1, 0));
		mainPane.add(buttonPane, BorderLayout.PAGE_START);
		
		JButton button0 = new JButton("Start / Stop");
		button0.setActionCommand("start");
		button0.addActionListener(this);
		buttonPane.add(button0);
		
		JButton button1 = new JButton("Next");
		button1.setActionCommand("next");
		button1.addActionListener(this);
		buttonPane.add(button1);
		
		JPanel sPane = new JPanel(new GridLayout(1, 2));
		buttonPane.add(sPane);
		
		label = new JLabel("time elapsed: 0", JLabel.CENTER);
		buttonPane.add(label);
		
		JButton button2 = new JButton("Reset");
		button2.setActionCommand("reset");
		button2.addActionListener(this);
		buttonPane.add(button2);
		
		histogram = new Histogram(strategyStatistics[1]);
		mainPane.add(histogram, BorderLayout.CENTER);
		distribution = new Distribution(strategyStatistics[1]);
		mainPane.add(distribution, BorderLayout.SOUTH);

		pack();
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				thread = null;
			}
		});

		update();
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("reset")) {
			for (int s = 0; s < strategies; ++s)
				strategyStatistics[s] = new Statistics();
			histogram.setStatistics(strategyStatistics[1]);
			distribution.setStatistics(strategyStatistics[1]);
			time = 0;

			// FIXME prowizorka: za≥oøenie, øe sπ 2 strategie
			SpatialConfiguration configuration = model.configuration();
			for (int x = 0; x < configuration.graph().size(); ++x) {
				if (random.nextInt(2) == 0)
					configuration.setStrategy(x, 1);
				else
					configuration.setStrategy(x, 0);
			}
			
			update();
		}
		else if (cmd.equals("next")) {
			advanceTime();
		}
		else if (cmd.equals("start")) {
			if (thread == null) {
				thread = new Thread(this);
				thread.start();
			} else
				thread = null;
		}
	}
	
	public void update() {
		SpatialConfiguration configuration = model.configuration();
		for (int s = 0; s < strategies; ++s)
			strategyStatistics[s].addValue((double) configuration.strategyCount(s)
										   / configuration.graph().size());
		histogram.update();
		distribution.update();
		label.setText("time elapsed: " + time);
	}
	
	public void run() {
		Thread thisThread = thread;
		while (thread == thisThread) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// nic
			}
			advanceTime();
		}
	}
	
	public void advanceTime() {
		model.advanceTime();
		++time;
		update();
	}
}
