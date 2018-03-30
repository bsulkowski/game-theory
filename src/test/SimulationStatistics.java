package gametheory.test;

import java.io.Serializable;
import java.util.*;

public class SimulationStatistics implements Serializable {
	private static final long serialVersionUID = 1L;

	Map<String, Object> parameters;
	Map<String, Double> average;
	Map<String, Double> squares_average;
	int size;
	String file;
}
