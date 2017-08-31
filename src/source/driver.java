package source;

import java.util.Scanner;


/**
 * Driver program for NMF algorithm
 * @Debarghya Nandi
 * 
 * */



public class driver {

	public static void main(String[] args) throws Exception
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter dimensions of the matrix.");
		System.out.println("Enter rows:");
		int row = input.nextInt();
		System.out.println("Enter columns:");
		int col = input.nextInt();
		
		int numOfElements = row*col;
		double[] ele = new double[numOfElements];
		//double max = findMax(ele);
		
		System.out.println("Enter elements into the matrix");
		for(int i=0;i<numOfElements;i++)
		{
			ele[i] = input.nextDouble();
		}
		int iterations;
		System.out.println("Enter number of iterations:");
		iterations = input.nextInt();
		
		int rank;
		System.out.println("Enter rank :");
		rank = input.nextInt();
		
		
		nMatFact obj = new nMatFact(row,col,ele,iterations,rank);
		
		
		
	}
	
	
}
