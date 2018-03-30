package gametheory.gui;

import java.util.ArrayList;

/**
 *	@author Bartosz Sułkowski
 */
public class Statistics {
	ArrayList<Double> values;
	double sum;
	double squaresSum;
	double min, max;

	double cut, sum0, count0;
	
	public Statistics() {
		values = new ArrayList<Double>();
		sum = 0;
		squaresSum = 0;
		min = Double.MAX_VALUE;
		max = Double.MIN_VALUE;
		
		cut = 0.5;
		count0 = 0;
		sum0 = 0;
	}
	
	public void addValue(double value) {
		values.add(value);
		sum += value;
		squaresSum += value * value;
		if (value < min)
			min = value;
		if (value > max)
			max = value;

//		cut = mean0() + mean1() / 2; 
//		cut = min() + max() / 2; 
		cut = mean(); 
		
		sum0 = 0;
		count0 = 0;
		for (int i = 0; i < size(); ++i) {
			value = getValue(i);
			if (value < cut) {
				sum0 += value;
				++count0;
			}
		}
	}
	public double getValue(int index) {
		return values.get(index);
	}
	public int size() {
		return values.size();
	}
	
	public double mean() {
		return sum / size();
	}
	public double standardDeviation() {
		double mean = mean();
		double variance = squaresSum / size() - mean * mean;
		return Math.sqrt(variance);
	}
	public double min() {
		return min;
	}
	public double max() {
		return max;
	}

	public double mean0() {
		if (count0 > 0)
			return sum0 / count0;
		else
			return min();
	}
	public double mean1() {
		if (count0 < size())
			return (sum - sum0) / (size() - count0);
		else
			return max();
	}
}
