/*
 * Benjamin Schroeder
 * Jonas Ditz
 */
package exampleCode;

import java.util.Stack;

/**
 * Needleman-Wunsch Algorithm
 * Bioinformatics 1, WS 15/16
 * Dr. Kay Nieselt and Alexander Seitz
 */
public class NeedlemanWunsch extends NeedlemanWunschBase {
  
	int[][] matrix = null;
	int[][] tracebackMatrix = null;
	
	/*
	 * this function is called for the first time with two empty stacks
	 * and the end indices of the matrix
	 * 
	 * the function computes a traceback over the matrix, it calls itself recursively
	 * for each sequence, it pushes the aligned character (a,c,g,t or -)
	 * on a stack (use java.util.Stack with the function push()) 
	 * 
	 */
	protected void traceback(Stack<Character> s1, Stack<Character> s2, int i, int j) {
		int direction = tracebackMatrix[i-1][j-1];
		
		switch (direction){
		case 0:	// end of the global alignment
			/*
			 * \TODO format the aligned sequences, such that they are in the fasta-format!
			 * i.e. remove any unsupported fasta characters, such as "," " " or "[" ...
			 * think of a clever object oriented way to do so!
			 */
			
			String aligned1 = s1.toString();
			String aligned2 = s2.toString();
			
			this.addAligned1(reverse(aligned1));
			this.addAligned2(reverse(aligned2)); 
			break;
		case 1: // upper cell
			s1.push('-');
			s2.push(this.getSequence2().charAt(i-2));
			this.traceback(s1, s2, i-1, j);
			break;
		case 2: // upperLeft cell
			s1.push(this.getSequence1().charAt(j-2));
			s2.push(this.getSequence2().charAt(i-2));
			this.traceback(s1, s2, i-1, j-1);
			break;
		case 3: // upper + upperLeft cell
			s1.push('-');
			s2.push(this.getSequence2().charAt(i-2));
			this.traceback(s1, s2, i-1, j);
			break;
		case 4: //left cell
			s1.push(this.getSequence1().charAt(j-2));
			s2.push('-');
			this.traceback(s1, s2, i, j-1);
			break;
		case 5: // left + upper cell
			s1.push(this.getSequence1().charAt(j-2));
			s2.push('-');
			this.traceback(s1, s2, i, j-1);
			break;
		case 6: // left + upperLeft cell
			s1.push(this.getSequence1().charAt(j-2));
			s2.push('-');
			this.traceback(s1, s2, i, j-1);
			break;
		case 7: // all 3 cells
			s1.push('-');
			s2.push(this.getSequence2().charAt(i-2));
			this.traceback(s1, s2, i-1, j);
			break;
		}
	}
	
    /**
     * computes the matrix for the Needleman-Wunsch Algorithm
     */
    public void compute() {
    	
    	/*
    	 * \TODO this function computes the NW-algorithm with linear gap-costs
    	 * - first make yourself familiar with this function and the functions used to compute the resulting alignment!
    	 * 
    	 * - modify the functions used in this class such that the NW algorithm is modular
    	 *   i.e. the following criteria should be fulfilled: 
    	 *   	- it should be easy to replace the linear gap cost function with an affine gap cost function
    	 *      - the initialization step, fill and traceback should be modular, to allow
    	 *        to switch between different algorithms later (NW, SW, OverlapAlignment etc.)
    	 * 
    	 * - you are allowed to change the class structure, if you think that it is necessary!
    	 *   (make sure to use object oriented programming concepts, i.e. use objects to abstract your code 
    	 *   	-> don't do everything in a single class)
    	 * 
    	 */
    	
    	// Set the number of rows and columns
    	int rows = this.getSequence2().length() + 1; // number of rows
    	int columns = this.getSequence1().length() + 1; // number of columns
    	
    	// Strings, which will be aligned
    	String sequence1 = this.getSequence1();
    	String sequence2 = this.getSequence2();
    	
    	// Set up the score- and the traceback-matrix
    	matrix = new int[rows][columns];
    	tracebackMatrix = new int[rows][columns];
    	
    	// fill the first row and first column of matrix and tracebackMatrix
    	for(int i = 0; i < rows; i++){
    		matrix[i][0] = -i * this.getGapPenalty();
    		tracebackMatrix[i][0] = 1;	// see explanation below
    	}
    	
    	for(int j = 0; j < columns; j++){
    		matrix[0][j] = -j * this.getGapPenalty();
    		tracebackMatrix[0][j] = 4; // see explanation below
    	}
    	
    	tracebackMatrix[0][0] = 0;
    	
    	// Fill matrix and traceback matrix
    	for(int i = 1; i < rows; i++){
    		for(int j = 1; j < columns; j++){
    			int a = matrix[i-1][j-1] + this.match(sequence1.charAt(j-1), sequence2.charAt(i-1));
    			int b = matrix[i][j-1] - this.getGapPenalty();
    			int c = matrix[i-1][j] - this.getGapPenalty();
    			int max = this.max(a, b, c);
    			
    			// fill cell of the scoring matrix
    			matrix[i][j] = max; 
    			
    			// fill cell of the traceback matrix
    			tracebackMatrix[i][j] = this.fillTracebackMatrix(a, b, c);
    		}
    	}
    	
    	// set the alignment score
    	this.setScore(matrix[rows-1][columns-1]);
    	
    	// call the traceback function
    	this.traceback(new Stack<Character>(),
    			new Stack<Character>(), 
    			rows, columns);
     }
    
	/**
	 * return the maximizing cell(s)
	 * 1 , if the maximizing cell is the upper cell
	 * 2 , if the maximizing cell is the left-upper cell
	 * 4 , if the maximizing cell is the left cell
	 * if there are more than one maximizing cells, the values are summed up
	 *
	 * @param upperLeft
	 * @param left
	 * @param upper
	 * @return code for the maximizing cell(s)
	 */
	private int fillTracebackMatrix(int upperLeft, int left, int upper){
		int max = this.max(upperLeft, left, upper);
		
		if(upperLeft == left && left == upper)
			return 7;
		else if (upper == upperLeft && upperLeft == max)
			return 3;
		else if (upper == left && left == max)
			return 5;
		else if (upperLeft == left && left == max)
			return 6;
		else if(upper == max)
			return 1;
		else if(upperLeft == max)
			return 2;
		else
			return 4;
	}
}
