package source;

import java.util.Random;
import java.util.Scanner;

import org.ejml.data.DMatrixRMaj;
import org.ejml.data.SingularMatrixException;
import org.ejml.dense.row.factory.LinearSolverFactory_DDRM;
import org.ejml.interfaces.linsol.LinearSolver;
import org.ejml.ops.MatrixIO;
import org.ejml.simple.SimpleMatrix;

/**
 * This program implements the non-negative matrix factorization
 * to extract all latent features from a matrix
 * 
 * @Debarghya Nandi
 * */
public class nMatFact {

	
	
	private SimpleMatrix X = new SimpleMatrix();
	private SimpleMatrix H = new SimpleMatrix();
	private	SimpleMatrix W = new SimpleMatrix();
	private int row;
	private int col;
	private int iteration;
	private int rank;
	private double[] arr;
	private double max;
	
	
	
	
	
	
	/**
	 * parameterized Constructor
	 * @param row - number of rows
	 * @param col - number of columns
	 * @param arr - array of the elements
	 * @param iteration - number of iterations
	 * @param rank - the appx number of hidden features
	 * 
	 * */
	
	public nMatFact(int row,int col,double[] arr,int iteration,int rank) throws Exception
	{
	
		this.row = row;
		this.col=col;
		this.iteration=iteration;
		this.rank = rank;
		this.arr = new double[row*col];
		this.arr = arr;
		this.max = findMax(arr);
		X = new SimpleMatrix(row,col,true,arr);
		// the dimension of the H matrix is row X rank
		// the dimension of the W matrix is rank X column
		
		H = formMatrix(row,rank,max);
		W = formMatrix(rank,col,max);
		
		operateNMF(X,H,W);
		
		
		
	}
	
	
	/**
	 * A function to update the value of the H matrix
	 * @param SimpleMatrix X
	 * @param SimpleMatrix H
	 * @param SimpleMatrix W
	 * @param rows
	 * @param columns
	 * */
	
	public void updateHMat(SimpleMatrix X, SimpleMatrix H, SimpleMatrix W, int r, int c)
	{
		SimpleMatrix A = W.transpose().elementMult(X);
		SimpleMatrix B = W.elementMult(H);
		SimpleMatrix C = B.transpose().elementMult(W);
		
		SimpleMatrix res = A.elementDiv(C);
		double n_val;
		
		
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				n_val = H.get(i, j) * res.get(i, j);
				H.set(i, j, n_val);  
			}
		}
		
		
	}
	
	
	/**
	 * A function to update the value of the W matrix
	 * @param SimpleMatrix X
	 * @param SimpleMatrix H
	 * @param SimpleMatrix W
	 * @param rows
	 * @param columns
	 * */
	
	public void updateWMat(SimpleMatrix X, SimpleMatrix H, SimpleMatrix W, int r, int c)
	{
		SimpleMatrix A = H.elementMult(X.transpose());
		SimpleMatrix B = W.elementMult(H);
		SimpleMatrix C = B.elementMult(H.transpose());
		
		SimpleMatrix res = A.transpose().elementDiv(C);
		double n_val;
		
		
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				n_val = W.get(i, j) * res.get(i, j);
				W.set(i, j, n_val);  
			}
		}
		
		
	}
	
	
	
	
	
	/**
	 * A function to create a matrix of given dimensions,
	 * fill it up with random numbers and return it
	 * @param a - number of rows in the matrix
	 * @param b - number of cols in the matrix
	 * @return the Simple matrix with random values
	 * */
	public SimpleMatrix formMatrix(int a,int b,double max)
	{
		Random r = new Random();
		double arr[] = new double[a*b];
		for(int i=0;i<(a*b);i++)
		{
			arr[i] = r.nextDouble()*max;
		}
		
		SimpleMatrix mat = new SimpleMatrix(a,b,true,arr);
		return mat;
	}
	
	
	/**
	 * A function to find the max in an array
	 * @param the matrix
	 * @return the max value
	 * */
	
	public double findMax(double[] a)
	{
		double max=a[0];
		for(int i=0;i<a.length;i++)
		{
			if(a[i]>max)
				max=a[i];
		}
		
		return max;
	}
	
	
	/**
	 * 
	 * A function to carry out the NMF operation
	 * @param H matrix
	 * @param X matrix
	 * @param W matrix
	 * 
	 * 
	 * */
	
	public void operateNMF(SimpleMatrix X, SimpleMatrix H, SimpleMatrix W)
	{
		double grad;
		
		// update the H  && Wmatrix
		for(int i=0;i<iteration;i++)
		{
			updateHMat(X,H,W,row,rank);
		
			updateWMat(X,H,W,rank,col);
			
			grad = X.elementSum()-(W.mult(H)).elementSum(); 
			System.out.println(grad);
		}
		
		
		// create a gradient function representing the gradual decrease in error
		
		
		
		
	}
	
	
	
	
}