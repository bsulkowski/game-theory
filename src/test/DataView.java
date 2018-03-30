package gametheory.test;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class DataView extends JFrame implements ActionListener {
    DistributionView view;
    FileDialog dialog;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DataView dv = new DataView();
        dv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dv.pack();
        dv.setResizable(false);
        dv.setVisible(true);
	}
	
	public DataView() throws IOException, ClassNotFoundException {
		super("Data View");
		
        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPane);
        
        dialog = new FileDialog(this);
        dialog.setDirectory("e:/test");
        dialog.setVisible(true);
        
        File file = new File(dialog.getDirectory(), dialog.getFile());
        
		FileInputStream inputStream = new FileInputStream(file);
		ObjectInputStream stream = new ObjectInputStream(inputStream);
		SimulationData data = (SimulationData) stream.readObject();

        double[] fraction = new double[data.size()];
        Integer[] count = (Integer[]) data.getData("cooperators");
        int size = (Integer) data.parameters().get("size");
        for (int t = 0; t < data.size(); ++t)
        	fraction[t] = ((double) count[t]) / size; 
        
        view = new DistributionView(fraction);
        view.updateImage();
        
        mainPane.add(view, BorderLayout.CENTER);
        
        JButton button = new JButton("FILE");
        button.addActionListener(this);
        mainPane.add(button, BorderLayout.SOUTH);        
	}
	
	public void actionPerformed(ActionEvent event) {
		dialog.setVisible(true);
        File file = new File(dialog.getDirectory(), dialog.getFile());
        
        try {
        	FileInputStream inputStream = new FileInputStream(file);
        	ObjectInputStream stream = new ObjectInputStream(inputStream);
        	SimulationData data = (SimulationData) stream.readObject();

        	double[] fraction = new double[data.size()];
        	Integer[] count = (Integer[]) data.getData("cooperators");
        	int size = (Integer) data.parameters().get("size");
        	for (int t = 0; t < data.size(); ++t)
        		fraction[t] = ((double) count[t]) / size; 

        	view.setData(fraction);
        	view.update();
        }
        catch (Exception e) {
        	// IGNORE
        }
	}
}
