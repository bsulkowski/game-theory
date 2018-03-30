package gametheory.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.*;
import java.util.ResourceBundle;

import javax.swing.*;

/**
 *	@author Bartosz Sułkowski
 */
public class ChoicePanel extends JPanel implements ActionListener, FocusListener {
	ParametrizedChoice choice;
	ResourceBundle bundle;

	int choices;
	String[] choiceName;
	JPanel[] choiceParameters;
	
	JPanel choicePane;
    JComboBox choiceCombo;
    JScrollPane scrollPane;
    JTextArea choiceInfo;
    JPanel parametersPane;
    JButton updateButton;

    public ChoicePanel(ParametrizedChoice choice, ResourceBundle bundle) {
		this.choice = choice;
		this.bundle = bundle;
		
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        choicePane = new JPanel(new BorderLayout());
//        choicePane.setLayout(new BoxLayout(choicePane, BoxLayout.Y_AXIS));
        add(choicePane);

        parametersPane = new JPanel(new CardLayout());
        parametersPane.setBorder(BorderFactory.createTitledBorder("choice.parameters"));        
        add(parametersPane);

		choices = choice.getChoiceList().size();
		choiceName = new String[choices];
		choiceParameters = new JPanel[choices];
		int oldChoice = choice.getChoice();
		for (int c = 0; c < choices; ++c) {
			choice.setChoice(c);
			String choiceId = choice.getChoiceId();
			choiceName[c] = bundle.getString(choiceId);
			choiceParameters[c] = new JPanel();
	        choiceParameters[c].setLayout(new BoxLayout(choiceParameters[c], BoxLayout.Y_AXIS));
			parametersPane.add(choiceParameters[c], choiceId);
			for (String parameter : choice.getParameterList()) {
				JPanel pane = new JPanel();
				JTextField textField = new JTextField(10);
				textField.setText("" + choice.getParameter(parameter));
				textField.addActionListener(this);
				textField.setActionCommand("parameter");
				textField.addFocusListener(this);
				textField.setName(parameter);
				String name = bundle.getString(choice.getChoiceId() + "." + parameter);
				pane.add(new JLabel(name));
				pane.add(textField);
				choiceParameters[c].add(pane);
			}
		}

        choiceCombo = new JComboBox(choiceName);
        choiceCombo.addActionListener(this);
        choiceCombo.setActionCommand("combo");
        choicePane.add(choiceCombo, BorderLayout.NORTH);
		
		choiceInfo = new JTextArea(5, 40);
		scrollPane = new JScrollPane(choiceInfo,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		choiceInfo.setEditable(false);
		choiceInfo.setLineWrap(true);
		choicePane.add(scrollPane, BorderLayout.CENTER);

		choiceCombo.setSelectedIndex(oldChoice);
    }

	public void updateChoice() {
		choice.setChoice(choiceCombo.getSelectedIndex());
		choiceInfo.setText(bundle.getString(choice.getChoiceId() + ".info"));
		CardLayout cl = (CardLayout)(parametersPane.getLayout());
		cl.show(parametersPane, choice.getChoiceId());
	}

	public String getDescription() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(choiceName[choice.getChoice()]);
		if (choice.getParameterList().size() > 0) {
			buffer.append(" [");
			for (String parameter : choice.getParameterList())
				buffer.append(bundle.getString(choice.getChoiceId() + "." + parameter)
						+ "=" + choice.getParameter(parameter) + "; ");
			buffer.setLength(buffer.length() - 2);
			buffer.append("]");
		}
		
		return buffer.toString();
	}

	public void updateParameter(JTextField textField) {
		String parameter = textField.getName();
		String string = textField.getText();
		Object value = null;
		try {
			switch (choice.getParameterType(parameter)) {
			case ParametrizedChoice.INTEGER:
				value = Integer.valueOf(string);
				break;
			case ParametrizedChoice.DOUBLE:
				value = Double.valueOf(string);
				break;
			case ParametrizedChoice.STRING:
				value = string;
				break;
			case ParametrizedChoice.BOOLEAN:
				value = Boolean.valueOf(string);
				break;
			}
		}
		catch (NumberFormatException exception) {
			value = null;
		}
		if (value != null)
			choice.setParameter(parameter, value);
		else
			textField.setText("" + choice.getParameter(parameter));
		
		// choiceInfo.setText(getDescription());
	}
	
    public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("combo")) {
			updateChoice();
		}
		else if (command.equals("parameter")) {
			updateParameter((JTextField) event.getSource());
		}
    }

	public void focusGained(FocusEvent e) {
		// NIC
	}

	public void focusLost(FocusEvent event) {
		updateParameter((JTextField) event.getSource());
	}
}
