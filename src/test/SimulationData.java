package gametheory.test;

import java.io.Serializable;
import java.util.*;

public class SimulationData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Object> parameters;
	private HashMap<String, Object[]> values;
	private int size;

	public SimulationData(int size) {
		parameters = new HashMap<String, Object>();
		values = new HashMap<String, Object[]>();
		this.size = size;
	}
	
	public Map<String, Object> parameters() {
		return parameters;
	}
	public Set<String> getValues() {
		return values.keySet();
	}
	public int size() {
		return size;
	}
	
	public Object[] getData(String value) {
		return values.get(value);
	}
	public void putData(String value, Object[] data) {
		values.put(value, data);
	}
}
