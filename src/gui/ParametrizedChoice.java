package gametheory.gui;

import java.util.*;

abstract public class ParametrizedChoice {
	public static final int INTEGER = 0;
	public static final int DOUBLE = 1;
	public static final int STRING = 2;
	public static final int BOOLEAN = 3;
	
	String[] choiceList;
	int choiceDefault;
	String[][] parameterList;
	Object[][] parameterDefault;
	int[][] parameterType;

	Map<String, Object> parameterValue = new HashMap<String, Object>();
	int choice = 0;

	public List<String> getChoiceList() {
		return Arrays.asList(choiceList);
	}

	public String getChoiceId() {
		return choiceList[choice];
	}
	public int getChoice() {
		return choice;
	}

	public void setChoice(String choiceId) {
		for (int c = 0; c < choiceList.length; ++c)
			if (choiceList[c].equals(choiceId)) {
				choice = c;
			}
		// TODO: ... obsługa błędów
	}
	public void setChoice(int choice) {
		this.choice = choice;
		// TODO: ... obsługa błędów
	}

	public List<String> getParameterList() {
		return Arrays.asList(parameterList[choice]);
	}

	public int getParameterType(String parameter) {
		for (int p = 0; p < parameterList[choice].length; ++p)
			if (parameterList[choice][p].equals(parameter)) {
				return parameterType[choice][p];
			}
		// TODO: ... obsługa błędów
		return 0;
	}

	public Object getParameter(String parameter) {
		return parameterValue.get(choiceList[choice] + "." + parameter);
	}

	public void setParameter(String parameter, Object value) {
		// TODO: ... sprawdzanie poprawności typów
		parameterValue.put(choiceList[choice] + "." + parameter, value);
	}

	public void setDefaultValues() {
		for (int c = 0; c < choiceList.length; ++c) {
			setChoice(c);
			for (int p = 0; p < parameterList[c].length; ++p) {
				setParameter(parameterList[c][p], parameterDefault[c][p]);
			}
		}
		setChoice(choiceDefault);
	}
}
