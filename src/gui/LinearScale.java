package gametheory.gui;


/**
 *	@author Bartosz Sułkowski
 */
public class LinearScale {
    double a1, a2, b1, b2;
	
    public LinearScale(double a1, double a2, double b1, double b2) {
		this.a1 = a1;
		this.a2 = a2;
		this.b1 = b1;
		this.b2 = b2;
    }
	
    public double point(double a) {
		return (a - a1)/(a2 - a1)*(b2 - b1) + b1;
    }
	
    public double pointBack(double b) {
		return (b - b1)/(b2 - b1)*(a2 - a1) + a1;
    }
	
    public double length(double a) {
		return a/(a2 - a1)*(b2 - b1);
    }
	
    public double lengthBack(double b) {
		return b/(b2 - b1)*(a2 - a1);
    }
	
    public LinearScale move(double a) {
		return new LinearScale(a1 + a, a2 + a, b1, b2);
    }
	
    public LinearScale moveBack(double b) {
		return new LinearScale(a1, a2, b1 + b, b2 + b);
    }
	
    public LinearScale zoom(double z) {
		double s = (a1 + a2) / 2;
		double d = (a1 - a2) / 2;
		return new LinearScale(s + z * d, s - z * d, b1, b2);
    }
    public LinearScale zoomBack(double z) {
		double s = (b1 + b2) / 2;
		double d = (b1 - b2) / 2;
		return new LinearScale(a1, a2, s + z * d, s - z * d);
    }
}
