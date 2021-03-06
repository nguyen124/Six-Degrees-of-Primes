/*
 * This class has functions related to generate the Graph of Prime Numbers 
 * Author: Gurupur Prashant Prabhu
 */

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.TreeMap;

public class Generator {

	/*
	 * This function generate the graph of prime numbers, using adjacent list
	 * for example: 
	 * 2, 3, 5, 7, 
	 * 3, 2, 5, 7, 13, 23, 43, 53, 73, 83, 103, 503,2003, 4003, 5003, 70003, 
	 * 5, 2, 3, 7, 
	 * 7, 2, 3, 5, 17, 37, 47, 67, 97, 107,307, 607, 907, 4007, 6007, 9007, 10007, 90007, 
	 * ... 
	 * all digits after the first digit of every single line is 1 
	 * digit away different with the first one
	 * 
	 * Input: void 
	 * Output: TreeMap<Integer,ArrayList>
	 */
	public static TreeMap<Integer, ArrayList<Integer>> generateGraph() {
		// We using TreeMap data structure of java to represent graph
		TreeMap<Integer, ArrayList<Integer>> graph = new TreeMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> primes = new ArrayList<Integer>();

		for (int i = 0; i < PrimesGraph.LIMIT; i++) {
			if (PrimeService.isPrime(i)) {
				primes.add(i);
			}
		}
		for (int i = 0; i < primes.size(); i++) {
			int prime = primes.get(i);
			ArrayList<Integer> neighbours = new ArrayList<Integer>();
			for (int j = 0; j < primes.size(); j++) {
				int neighbour = primes.get(j);
				if (PrimeService.isOneDigitAwayDiffer(prime, neighbour)) {
					neighbours.add(neighbour);
				}
			}
			graph.put(prime, neighbours);
		}
		return graph;
	}

	/*
	 * This is just a test function, write out the graph into the file to see if
	 * we construct the graph correctly or not 
	 * 
	 * Input: path of the output file
	 * Output: file contain the graph of primes
	 */
	public static void writeGraphOfPrimesToFile() {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 0; i < PrimesGraph.LIMIT; i++) {
			if (PrimeService.isPrime(i)) {
				primes.add(i);
			}
		}
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("output.txt")));
			for (int i = 0; i < primes.size(); i++) {
				int prime = primes.get(i);
				writer.write(prime + ", ");
				for (int j = 0; j < primes.size(); j++) {

					int neighbour = primes.get(j);
					if (PrimeService.isOneDigitAwayDiffer(prime, neighbour)) {
						writer.write(neighbour + ", ");
					}
				}
				writer.write("\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
