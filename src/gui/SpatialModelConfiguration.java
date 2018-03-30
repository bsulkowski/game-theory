package gametheory.gui;

import gametheory.SymmetricDualGame;
import gametheory.graph.GraphGenerator;
import gametheory.spatial.SpatialDynamics;
import gametheory.spatial.SpatialModel;
import gametheory.spatial.SpatialPayoff;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *	@author Bartosz Sułkowski
 */
public class SpatialModelConfiguration extends JFrame implements ActionListener, FocusListener {
	GameChoice gameChoice;
	GraphChoice graphChoice;
	SpatialPayoffChoice payoffChoice;
	SpatialDynamicsChoice dynamicsChoice;
	SpatialModelChoice modelChoice;

	ChoicePanel[] choicePanel;
	JLabel[] choiceDescription;

	JPanel introPane;
	JPanel simulationPane;
	
	Locale locale;
	ResourceBundle messages;	
	
	public static void main(String[] args) {
        SpatialModelConfiguration launcher =
			new SpatialModelConfiguration(Locale.getDefault());
        launcher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launcher.setSize(600, 400);
        launcher.setResizable(false);
        launcher.setVisible(true);
	}
	
	public SpatialModelConfiguration(Locale locale) {
		this.locale = locale;
		messages = ResourceBundle.getBundle("gametheory.gui.messages", locale);
		setTitle(messages.getString("main.title"));
		
		gameChoice = new GameChoice();
		graphChoice = new GraphChoice();
		payoffChoice = new SpatialPayoffChoice(); 
		dynamicsChoice = new SpatialDynamicsChoice(); 
		modelChoice = new SpatialModelChoice(); 
		
		addComponents();
	}
	
	void addComponents() {
        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPane);
		
        JTabbedPane pane = new JTabbedPane();
        mainPane.add(pane, BorderLayout.CENTER);

		JTextArea mainInfo = new JTextArea(5, 40);
		JScrollPane scrollPane = new JScrollPane(mainInfo,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainInfo.setEditable(false);
		mainInfo.setLineWrap(true);
		mainInfo.setText(messages.getString("main.info"));
		pane.addTab(messages.getString("intro"), scrollPane);

		choicePanel = new ChoicePanel[5];
		
		choicePanel[0] = new ChoicePanel(gameChoice,
				ResourceBundle.getBundle("gametheory.gui.game", locale));
		pane.addTab(messages.getString("game"), choicePanel[0]);
		choicePanel[1] = new ChoicePanel(graphChoice,
				ResourceBundle.getBundle("gametheory.gui.graph", locale));
		pane.addTab(messages.getString("graph"), choicePanel[1]);
		choicePanel[2] = new ChoicePanel(payoffChoice,
				ResourceBundle.getBundle("gametheory.gui.payoff", locale));
		pane.addTab(messages.getString("payoff"), choicePanel[2]);
		choicePanel[3] = new ChoicePanel(dynamicsChoice,
				ResourceBundle.getBundle("gametheory.gui.dynamics", locale));
		pane.addTab(messages.getString("dynamics"), choicePanel[3]);
		choicePanel[4] = new ChoicePanel(modelChoice,
				ResourceBundle.getBundle("gametheory.gui.model", locale));
		pane.addTab(messages.getString("model"), choicePanel[4]);

		simulationPane = new JPanel();
		simulationPane.setLayout(new BoxLayout(simulationPane, BoxLayout.Y_AXIS));

		choiceDescription = new JLabel[5];
		for (int i = 0; i < 5; ++i) {
			choiceDescription[i] = new JLabel();
			simulationPane.add(choiceDescription[i]);
		}
		
		JButton startButton = new JButton(messages.getString("main.start"));
		startButton.addActionListener(this);
		simulationPane.add(startButton);
		
		pane.addTab(messages.getString("simulation"), simulationPane);
//		mainPane.add(simulationPane, BorderLayout.SOUTH);
		pane.addFocusListener(this);
	}
	
	public void actionPerformed(ActionEvent event) {
        startSimulation();
	}

	public void startSimulation() {
		SymmetricDualGame game = gameChoice.getGame();
		GraphGenerator generator = graphChoice.getGraphGenerator(null);
		SpatialPayoff payoff = payoffChoice.getSpatialPayoff(game);
		SpatialDynamics dynamics = dynamicsChoice.getSpatialDynamics();
		SpatialModel model = modelChoice.createSpatialModel(generator, payoff, dynamics);
        
        Simulation simulation = new Simulation(model, locale);
        simulation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        simulation.setVisible(true);
    }

	public void focusGained(FocusEvent event) {
		JTabbedPane pane = (JTabbedPane) event.getSource();
		
		for (int i = 0; i < 5; ++i) {
			choiceDescription[i].setText(
					pane.getTitleAt(i + 1) + ": " + choicePanel[i].getDescription());
		}
	}

	public void focusLost(FocusEvent event) {
		// NIC
	}
}
