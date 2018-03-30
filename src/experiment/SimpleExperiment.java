package gametheory.experiment;

import java.io.*;
import java.util.*;

import gametheory.*;
import gametheory.graph.*;
import gametheory.spatial.*;
import gametheory.test.*;


public class SimpleExperiment {
	
	public static void main(String[] args) throws IOException {
		int N = 10000;
		int tick = N / 50;
		Random random = new Random();
		Queue<SimulationData> tasks = new LinkedList<SimulationData>();
		SimulationData data;

//		for (int T = 2; T <= 2; ++T) {
////			if (T == 2)
////				continue;
//			double temptation = 1.1 + 0.2 * T;
//			for (int P = -5; P <= 15; ++P) {
//				double payin = P * 0.1;
//			
//				for (int I = 1; I <= 10; ++I) {
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized prisoners dilemma");
//					data.parameters().put("temptation", temptation);
//					data.parameters().put("punishment", 0.0);
//
//					data.parameters().put("graph", "barabasi-albert");
//					data.parameters().put("size", 10000);
//					data.parameters().put("degree", 8);
//
//					data.parameters().put("payoff", "sum");
//					data.parameters().put("payin", payin);
//
//					if (payin < 0)
//						data.parameters().put("max diff", temptation - payin);
//					else if (payin > temptation)
//						data.parameters().put("max diff", payin);
//					else
//						data.parameters().put("max diff", temptation);
//					
//					tasks.add(data);
//				}
//			}
//		}

//		for (int T = 2; T <= 2; ++T) {
////			if (T == 2)
////				continue;
//			double temptation = 1.1 + 0.2 * T;
//			for (int P = -5; P <= 15; ++P) {
//				double payin = P * 0.1;
//			
//				for (int I = 1; I <= 10; ++I) {
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized prisoners dilemma");
//					data.parameters().put("temptation", temptation);
//					data.parameters().put("punishment", 0.0);
//
//					data.parameters().put("graph", "erdos-renyi");
//					data.parameters().put("size", 10000);
//					data.parameters().put("degree", 8);
//
//					data.parameters().put("payoff", "sum");
//					data.parameters().put("payin", payin);
//
//					if (payin < 0)
//						data.parameters().put("max diff", temptation - payin);
//					else if (payin > temptation)
//						data.parameters().put("max diff", payin);
//					else
//						data.parameters().put("max diff", temptation);
//					
//					tasks.add(data);
//				}
//			}
//		}

//		for (int R = 1; R <= 1; ++R) {
////			if (R == 2)
////				continue;
//			double ratio = 0.1 + 0.2 * R;
//			for (int P = 11; P <= 15; ++P) {
//				double payin = P * 0.1;
//			
//				for (int I = 1; I <= 10; ++I) {
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized snowdrift");
//					data.parameters().put("ratio", ratio);
//
//					data.parameters().put("graph", "barabasi-albert");
//					data.parameters().put("size", 10000);
//					data.parameters().put("degree", 8);
//
//					data.parameters().put("payoff", "sum");
//					data.parameters().put("payin", payin);
//
//					if (payin < 0)
//						data.parameters().put("max diff", 1.0 + ratio - payin);
//					else if (payin > 1.0 + ratio)
//						data.parameters().put("max diff", payin);
//					else
//						data.parameters().put("max diff", 1.0 + ratio);
//					
//					tasks.add(data);
//				}
//			}
//		}

//		for (int R = 2; R <= 2; ++R) {
////			if (R == 2)
////				continue;
//			double ratio = 0.1 + 0.2 * R;
//			for (int P = 9; P <= 15; ++P) {
//				double payin = P * 0.1;
//			
//				for (int I = 1; I <= 10; ++I) {
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized snowdrift");
//					data.parameters().put("ratio", ratio);
//
//					data.parameters().put("graph", "barabasi-albert");
//					data.parameters().put("size", 10000);
//					data.parameters().put("degree", 8);
//
//					data.parameters().put("payoff", "sum");
//					data.parameters().put("payin", payin);
//
//					if (payin < 0)
//						data.parameters().put("max diff", 1.0 + ratio - payin);
//					else if (payin > 1.0 + ratio)
//						data.parameters().put("max diff", payin);
//					else
//						data.parameters().put("max diff", 1.0 + ratio);
//					
//					tasks.add(data);
//				}
//			}
//		}
		
//		for (int R = 4; R <= 4; ++R) {
////			if (R == 2)
////				continue;
//			double ratio = 0.1 + 0.2 * R;
//			for (int P = 12; P <= 15; ++P) {
//				double payin = P * 0.1;
//			
//				for (int I = 1; I <= 10; ++I) {
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized snowdrift");
//					data.parameters().put("ratio", ratio);
//
//					data.parameters().put("graph", "barabasi-albert");
//					data.parameters().put("size", 10000);
//					data.parameters().put("degree", 8);
//
//					data.parameters().put("payoff", "sum");
//					data.parameters().put("payin", payin);
//
//					if (payin < 0)
//						data.parameters().put("max diff", 1.0 + ratio - payin);
//					else if (payin > 1.0 + ratio)
//						data.parameters().put("max diff", payin);
//					else
//						data.parameters().put("max diff", 1.0 + ratio);
//					
//					tasks.add(data);
//				}
//			}
//		}
		
		
//		for (int R = 1; R <= 1; ++R) {
////			if (R == 2)
////				continue;
//			double ratio = 0.1 + 0.2 * R;
//			for (int P = 7; P <= 15; ++P) {
//				double payin = P * 0.1;
//				for (int I = 1; I <= 10; ++I) {
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized snowdrift");
//					data.parameters().put("ratio", ratio);
//
//					data.parameters().put("graph", "erdos-renyi");
//					data.parameters().put("size", 10000);
//					data.parameters().put("degree", 8);
//
//					data.parameters().put("payoff", "sum");
//					data.parameters().put("payin", payin);
//
//					if (payin < 0)
//						data.parameters().put("max diff", 1.0 + ratio - payin);
//					else if (payin > 1.0 + ratio)
//						data.parameters().put("max diff", payin);
//					else
//						data.parameters().put("max diff", 1.0 + ratio);
//					
//					tasks.add(data);
//				}
//			}
//		}

//		for (int R = 2; R <= 2; ++R) {
////			if (R == 2)
////				continue;
//			double ratio = 0.1 + 0.2 * R;
//			for (int P = -5; P <= 15; ++P) {
//				double payin = P * 0.1;
//				for (int I = 1; I <= 10; ++I) {
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized snowdrift");
//					data.parameters().put("ratio", ratio);
//
//					data.parameters().put("graph", "erdos-renyi");
//					data.parameters().put("size", 10000);
//					data.parameters().put("degree", 8);
//
//					data.parameters().put("payoff", "sum");
//					data.parameters().put("payin", payin);
//
//					if (payin < 0)
//						data.parameters().put("max diff", 1.0 + ratio - payin);
//					else if (payin > 1.0 + ratio)
//						data.parameters().put("max diff", payin);
//					else
//						data.parameters().put("max diff", 1.0 + ratio);
//					
//					tasks.add(data);
//				}
//			}
//		}
		
		
//		for (int I = 1; I <= 9; ++I) {
//			for (int P = -5; P <= 15; ++P) {
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized snowdrift");
//				data.parameters().put("ratio", 0.5);
//
//				data.parameters().put("graph", "barabasi-albert");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				double payin = P * 0.1;
//				data.parameters().put("payin", payin);
//
//				if (payin < 0)
//					data.parameters().put("max diff", 1.5 - payin);
//				else if (payin > 1.5)
//					data.parameters().put("max diff", payin);
//				else
//					data.parameters().put("max diff", 1.5);
//
//				tasks.add(data);
//			}
//		}
//		for (int I = 1; I <= 9; ++I) {
//			for (int P = -5; P <= 15; ++P) {
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized prisoners dilemma");
//				data.parameters().put("temptation", 1.5);
//				data.parameters().put("punishment", 0.0);
//
//				data.parameters().put("graph", "erdos-renyi");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				double payin = P * 0.1;
//				data.parameters().put("payin", payin);
//
//				if (payin < 0)
//					data.parameters().put("max diff", 1.5 - payin);
//				else if (payin > 1.5)
//					data.parameters().put("max diff", payin);
//				else
//					data.parameters().put("max diff", 1.5);
//
//				tasks.add(data);
//			}
//		}

		for (int I = 1; I <= 10; ++I) {
			for (int P = -5; P <= 15; ++P) {
				double payin = P * 0.1;
				double temptation = 1.5;

				data = new SimulationData(N);

				data.parameters().put("game", "normalized prisoners dilemma");
				data.parameters().put("temptation", temptation);
				data.parameters().put("punishment", 0.0);

				data.parameters().put("graph", "barabasi-albert");
				data.parameters().put("size", 10000);
				data.parameters().put("degree", 8);

				data.parameters().put("payoff", "sum");
				data.parameters().put("payin", payin);

				if (payin < 0)
					data.parameters().put("max diff", temptation - payin);
				else if (payin > temptation)
					data.parameters().put("max diff", payin);
				else
					data.parameters().put("max diff", temptation);

				tasks.add(data);
			}
		}

		
//		for (int I = 1; I <= 9; ++I) {
//			for (int P = -5; P <= 15; ++P) {
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized snowdrift");
//				data.parameters().put("ratio", 0.5);
//
//				data.parameters().put("graph", "erdos-renyi");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				double payin = P * 0.1;
//				data.parameters().put("payin", payin);
//
//				if (payin < 0)
//					data.parameters().put("max diff", 1.5 - payin);
//				else if (payin > 1.5)
//					data.parameters().put("max diff", payin);
//				else
//					data.parameters().put("max diff", 1.5);
//
//				tasks.add(data);
//			}
//		}

//		for (int T = 0; T <= 20; ++T) {
//			double temptation = T * 0.05 + 1.0;
//			for (int I = 1; I <= 10; ++I) {
//			
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized prisoners dilemma");
//				data.parameters().put("temptation", temptation);
//				data.parameters().put("punishment", 0.0);
//
//				data.parameters().put("graph", "barabasi-albert");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				data.parameters().put("max diff", temptation);
//
//				tasks.add(data);
//			}
//		}
//		for (int T = 0; T <= 20; ++T) {
//			double temptation = T * 0.05 + 1.0;
//			for (int I = 1; I <= 10; ++I) {
//			
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized prisoners dilemma");
//				data.parameters().put("temptation", temptation);
//				data.parameters().put("punishment", 0.0);
//
//				data.parameters().put("graph", "erdos-renyi");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				data.parameters().put("max diff", temptation);
//
//				tasks.add(data);
//			}
//		}
//		for (int I = 1; I <= 10; ++I) {
//			for (int R = 0; R <= 20; ++R) {
//				double ratio = R * 0.05;
//			
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized snowdrift");
//				data.parameters().put("ratio", ratio);
//
//				data.parameters().put("graph", "barabasi-albert");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				data.parameters().put("max diff", 1.0 + ratio);
//
//				tasks.add(data);
//			}
//		}
//		for (int I = 1; I <= 10; ++I) {
//			for (int R = 0; R <= 20; ++R) {
//				double ratio = R * 0.05;
//			
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized snowdrift");
//				data.parameters().put("ratio", ratio);
//
//				data.parameters().put("graph", "barabasi-albert");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 4);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				data.parameters().put("max diff", 1.0 + ratio);
//
//				tasks.add(data);
//			}
//		}
		
//		for (int T = 0; T <= 20; ++T) {
//			double temptation = T * 0.05 + 1.0;
//			
//			for (int I = 1; I <= 10; ++I) {
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized prisoners dilemma");
//				data.parameters().put("temptation", temptation);
//				data.parameters().put("punishment", 0.0);
//
//				data.parameters().put("graph", "erdos-renyi");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				data.parameters().put("max diff", temptation);
//
//				tasks.add(data);
//			}
//		}

//		for (int R = 0; R <= 20; ++R) {
//			for (int I = 1; I <= 10; ++I) {
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized snowdrift");
//				double ratio = R * 0.05;
//				data.parameters().put("ratio", ratio);
//
//				data.parameters().put("graph", "barabasi-albert");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				data.parameters().put("max diff", ratio + 1.0);
//
//				tasks.add(data);
//			}
//		}

//		for (int T = 0; T <= 20; ++T) {
//			for (int I = 1; I <= 10; ++I) {
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized prisoners dilemma");
//				double temptation = T * 0.05 + 1.0;
//				data.parameters().put("temptation", temptation);
//				data.parameters().put("punishment", 0.0);
//
//				data.parameters().put("graph", "erdos-renyi");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				data.parameters().put("max diff", temptation);
//
//				tasks.add(data);
//			}
//		}

//		for (int R = 12; R <= 20; ++R) {
//			double ratio = R * 0.05;
//			
//			for (int I = 1; I <= 10; ++I) {
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized snowdrift");
//				data.parameters().put("ratio", ratio);
//
//				data.parameters().put("graph", "erdos-renyi");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				data.parameters().put("max diff", ratio + 1.0);
//
//				tasks.add(data);
//			}
//		}

		
		// ok. 20h
//		for (int I = 1; I <= 2; ++I) { // x3
//			for (int P = -15; P <= 15; ++P) { // x21
//					if (((P < -5) || (P > 5)) && (P % 2 == 0))
//						continue;
//						
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized prisoners dilemma");
//					data.parameters().put("temptation", 1.5);
//					data.parameters().put("punishment", 0.0);
//
//					data.parameters().put("graph", "barabasi-albert");
//					data.parameters().put("size", 100000); // UWAGA!
//					data.parameters().put("degree", 8);
//					
//					data.parameters().put("payoff", "sum");
//					data.parameters().put("payin", 0.45 + P * 0.01);
//					
//					tasks.add(data);
//			}
//		}


//			for (int M = 0; M <= 0; ++M) { // x2 UWAGA
//				for (int P = 4; P <= 4; ++P) { // x5 UWAGA
//					for (int T = 2; T <= 10; ++T) { // x21 UWAGA
//
//						data = new SimulationData(N);
//
//						data.parameters().put("game", "normalized prisoners dilemma");
//						data.parameters().put("temptation", 1.5 + T * 0.05);
//						data.parameters().put("punishment", 0.0);
//
//						if (M == 0) {
//							data.parameters().put("graph", "erdos-renyi");
//							data.parameters().put("size", 10000);
//							data.parameters().put("degree", 8);
//						}
//						if (M == 1) {
//							data.parameters().put("graph", "barabasi-albert");
//							data.parameters().put("size", 10000);
//							data.parameters().put("degree", 8);
//						}
//
//						if (P < 4) {
//							data.parameters().put("payoff", "sum");
//							data.parameters().put("payin", 0.0 + P * 0.5);
//						}
//						else {
//							data.parameters().put("payoff", "average");
//						}
//
//						tasks.add(data);
//					}
//				}
//			}
		
		// ok. 10h
//		for (int I = 1; I <= 2; ++I) {
//		for (int M = 0; M <= 0; ++M) { // x2 UWAGA
//			for (int P = 0; P <= 4; ++P) { // x5
//				for (int T = -10; T <= 10; ++T) { // x21
//
//					data = new SimulationData(N);
//
//					data.parameters().put("game", "normalized prisoners dilemma");
//					data.parameters().put("temptation", 1.5 + T * 0.05);
//					data.parameters().put("punishment", 0.0);
//
//					if (M == 0) {
//						data.parameters().put("graph", "erdos-renyi");
//						data.parameters().put("size", 10000);
//						data.parameters().put("degree", 8);
//					}
//					if (M == 1) {
//						data.parameters().put("graph", "barabasi-albert");
//						data.parameters().put("size", 10000);
//						data.parameters().put("degree", 8);
//					}
//
//					if (P < 4) {
//						data.parameters().put("payoff", "sum");
//						data.parameters().put("payin", 0.0 + P * 0.5);
//					}
//					else {
//						data.parameters().put("payoff", "average");
//					}
//
//					tasks.add(data);
//				}
//			}
//		}
//		}

		// ok. 10h
//		for (int I = 1; I <= 10; ++I) { // x10
//			for (int T = -10; T <= 10; ++T) { // x21
//
//				data = new SimulationData(N);
//
//				data.parameters().put("game", "normalized prisoners dilemma");
//				data.parameters().put("temptation", 1.5 + T * 0.05);
//				data.parameters().put("punishment", 0.0);
//
//				data.parameters().put("graph", "square lattice");
//				data.parameters().put("width", 100);
//				data.parameters().put("height", 100);
//				data.parameters().put("diagonal", true);
//
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0);
//
//				tasks.add(data);
//			}
//		}

		// ok. 10h
//		for (int I = 1; I <= 2; ++I) { // x2
//			for (int M = 0; M <= 0; ++M) { // x2 UWAGA
//				for (int T = -2; T <= 2; ++T) { // x5
//					for (int P = -15; P <= 15; ++P) { // x21
//						if (((P < -5) || (P > 5)) && (P % 2 == 0))
//							continue;
//
//						data = new SimulationData(N);
//
//						data.parameters().put("game", "normalized prisoners dilemma");
//						data.parameters().put("temptation", 1.5 + T * 0.2);
//						data.parameters().put("punishment", 0.0);
//
//						if (M == 0) {
//							data.parameters().put("graph", "erdos-renyi");
//							data.parameters().put("size", 10000);
//							data.parameters().put("degree", 8);
//						}
//						if (M == 1) {
//							data.parameters().put("graph", "barabasi-albert");
//							data.parameters().put("size", 10000);
//							data.parameters().put("degree", 8);
//						}
//
//						data.parameters().put("payoff", "sum");
//						data.parameters().put("payin", 0.7 + P * 0.1);
//
//						tasks.add(data);
//					}
//				}
//			}
//		}
		
//		for (int M = 0; M <= 2; ++M) {
//			for (int G = 0; G <= 2; ++G) {
//				for (int P = 1; P <= 9; ++P) {
//					data = new SimulationData(N);
//					
//					if (G == 0) { 
//						data.parameters().put("game", "normalized prisoners dilemma");
//						data.parameters().put("temptation", 1.0 + 0.1 * P);
//						data.parameters().put("punishment", 0.0);
//					}
//					if (G == 1) {
//						data.parameters().put("game", "normalized prisoners dilemma");
//						data.parameters().put("temptation", 1.0 + 0.1 * P);
//						data.parameters().put("punishment", 0.0 + 0.1 * P);
//					}
//					if (G == 2) {
//						data.parameters().put("game", "normalized snowdrift");
//						data.parameters().put("ratio", 0.1 * P);
//					}					
//					
//					if (M == 0) {
//						data.parameters().put("graph", "square lattice");
//						data.parameters().put("width", 100);
//						data.parameters().put("height", 100);
//						data.parameters().put("diagonal", true);
//						data.parameters().put("payoff", "sum");
//						data.parameters().put("payin", 0.0);
//					}
//					if (M == 1) {
//						data.parameters().put("graph", "erdos-renyi");
//						data.parameters().put("size", 10000);
//						data.parameters().put("degree", 8);
//						data.parameters().put("payoff", "average");
//					}
//					if (M == 2) {
//						data.parameters().put("graph", "barabasi-albert");
//						data.parameters().put("size", 10000);
//						data.parameters().put("degree", 8);
//						data.parameters().put("payoff", "average");
//					}
//						
//					tasks.add(data);
//				}
//			}
//		}
		
//		for (int T = 1; T <= 9; ++T) {
//			for (int G = -5; G <= 15; ++G) {
//				data = new SimulationData(N);
//				data.parameters().put("game", "normalized prisoners dilemma");
//				data.parameters().put("temptation", 1.0 + 1.0 * T / 10);
//				data.parameters().put("punishment", 0.0);
//				data.parameters().put("graph", "erdos-renyi");
//				data.parameters().put("size", 10000);
//				data.parameters().put("degree", 8);
//				data.parameters().put("payoff", "sum");
//				data.parameters().put("payin", 0.0 + G * 0.1);
//				tasks.add(data);
//			}
//		}
		
		while ((data = tasks.poll()) != null) {  
				System.out.println("");
				Integer[] cooperators = new Integer[N];
				Integer[] changes = new Integer[N];

//				SequentialSpatialGame model;
				InvertedReplicatorSpatialGame model;
				
				try {
					
				SymmetricDualGame game;
				String gameDescription = (String) data.parameters().get("game");
				System.out.println("game= " + gameDescription);
				if (gameDescription.equals("normalized prisoners dilemma")) {
					double temptation = (Double) data.parameters().get("temptation");
					double punishment = (Double) data.parameters().get("punishment");
					System.out.println("temptation= " + temptation);
					System.out.println("punishment= " + punishment);
					game = new PrisonersDilemma(temptation, punishment);
				}
				else if (gameDescription.equals("normalized snowdrift")) {
					double ratio = (Double) data.parameters().get("ratio");
					System.out.println("ratio= " + ratio);
					game = new SnowDrift(ratio);
				}
				else {
					System.out.println("ERROR: unrecognized game");
					continue;
				}
				
				Graph graph; 
				String graphDescription = (String) data.parameters().get("graph");
				System.out.println("graph= " + graphDescription);
				if (graphDescription.equals("erdos-renyi")) {
					int size = (Integer) data.parameters().get("size");
					int degree = (Integer) data.parameters().get("degree");
					System.out.println("size= " + size);
					System.out.println("degree= " + degree);
					graph = new ErdosRenyiGraph(size, degree).createGraph();
				}
				else if (graphDescription.equals("barabasi-albert")) {
					int size = (Integer) data.parameters().get("size");
					int degree = (Integer) data.parameters().get("degree");
					System.out.println("size= " + size);
					System.out.println("degree= " + degree);
					graph = new BarabasiAlbertGraph(size, degree).createGraph();
				}
				else if (graphDescription.equals("original barabasi-albert")) {
					int size = (Integer) data.parameters().get("size");
					int m0 = (Integer) data.parameters().get("m0");
					int m = (Integer) data.parameters().get("m");
					System.out.println("size= " + size);
					System.out.println("m0= " + m0);
					System.out.println("m= " + m);
					graph = new BarabasiAlbertStandardGraph(size, m0, m).createGraph();
				}
				else if (graphDescription.equals("square lattice")) {
					int width = (Integer) data.parameters().get("width");
					int height = (Integer) data.parameters().get("height");
					boolean diagonal = (Boolean) data.parameters().get("diagonal");
					System.out.println("width= " + width);
					System.out.println("height= " + height);
					System.out.println("diagonal= " + diagonal);
					graph = new SquareLattice(width, height, diagonal).createGraph();
				}
				else {
					System.out.println("ERROR: unrecognized graph");
					continue;
				}

				SpatialPayoff payoff; 
				String payoffDescription = (String) data.parameters().get("payoff");
				System.out.println("payoff= " + payoffDescription);
				if (payoffDescription.equals("sum")) {
					double payin = (Double) data.parameters().get("payin");
					System.out.println("payin= " + payin);
					payoff = new SumPayoff(game, payin);
				}
				else if (payoffDescription.equals("average")) {
					payoff = new AveragePayoff(game, false);
				}
				else {
					System.out.println("ERROR: unrecognized payoff");
					continue;
				}
				
				SpatialConfiguration configuration = new SpatialConfiguration(graph, payoff);
				for (int x = 0; x < configuration.graph().size(); ++x) {
					if (random.nextInt(2) == 0)
						configuration.setStrategy(x, 1);
				}
//				data.parameters().put("dynamics", "imitation");
//				data.parameters().put("mutation rate", 0.001);
//				SpatialDynamics dynamics = new MutationDynamics(new ImitationDynamics(), 0.001);
//				data.parameters().put("dynamics", "normalized moran");
//				double max_diff = (Double) data.parameters().get("max diff");				
//				SpatialDynamics dynamics = new IrregularReplicationDynamics(max_diff);
//				data.parameters().put("dynamics", "global moran");
//				double max_diff = (Double) data.parameters().get("max diff");
//				int max_degree = 0;
//				for (int x = 0; x < configuration.graph().size(); ++x) {
//					int degree = configuration.graph().degree(x);
//					if (degree > max_degree)
//						max_degree = degree;
//				}
//				SpatialDynamics dynamics = new LinearReplicationDynamics(0, max_diff * max_degree);
//				System.out.println("max diff= " + max_diff);
//				System.out.println("max degree= " + max_degree);
				
//				data.parameters().put("model", "sequential spatial game");
//				model = new SequentialSpatialGame(configuration, dynamics);

				data.parameters().put("dynamics", "normalized moran");
				double max_diff = (Double) data.parameters().get("max diff");				
				data.parameters().put("model", "inverted sequential spatial game");
				model = new InvertedReplicatorSpatialGame(configuration, max_diff);
				}
				catch (NullPointerException e) {
					System.out.println("ERROR: missing parameters");
					continue;
				}
				catch (ClassCastException e) {
					System.out.println("ERROR: invalid parameters");
					continue;
				}

				
				System.out.print("[");
				System.out.flush();
				
				cooperators[0] = model.configuration().strategyCount(1);
				changes[0] = 0;
				for (int t = 1; t < N; ++t) {
					model.advanceTime();
					if (t % tick == 0) {
						System.out.print("#");
						System.out.flush();
					}
					cooperators[t] = model.configuration().strategyCount(1);
					changes[t] = model.getChanges();
				}

				data.putData("cooperators", cooperators);
				data.putData("changes", changes);
				saveResults(data);

				System.out.println("]");
				System.out.flush();
		}
		System.out.println("");
		System.out.println("TASKS COMPLETED");
	}		
	
	public static void saveResults(SimulationData data) throws IOException {
		OutputStream stream = new FileOutputStream("k:/symulacje/" + System.currentTimeMillis() + ".sim");
		ObjectOutputStream output = new ObjectOutputStream(stream);
		output.writeObject(data);
		output.close();
		stream.close();
	}
}
