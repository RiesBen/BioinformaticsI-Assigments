package exampleCode;

import java.util.ArrayList;

/**
 * Base class for the Needleman-Wunsch Algorithm
 * Bioinformatics 1, WS 15/16
 * Dr. Kay Nieselt and Alexander Seitz
 */
public class NeedlemanWunschBase {
	
    public GapPenalty gapPenalty = new GapPenalty();
    private int matchScore = 1;
    private int mismatchScore = -1;
    private String sequence1 = null;
    private String sequence2 = null;
    private ArrayList<String> aligned1 = new ArrayList<String>(); 
    private ArrayList<String> aligned2 = new ArrayList<String>();
    private int score = 0;
    
    /**
     * get numberOfAlignments
     * @return numberOfAlignments
     */
    public int getNumberOfAlignments() {
        return aligned1.size();
    }   
    
    /**
     * get match score
     * @return match score
     */

    public int getMatchScore() {
        return matchScore;
    }

    /**
     * set match score
     * @param matchScore
     */
    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    /**
     * get mismatch score
     * @return mismatch score
     */
    public int getMismatchScore() {
        return mismatchScore;
    }

    /**
     * set mismatch score
     * @param mismatchScore
     */
    public void setMismatchScore(int mismatchScore) {
        this.mismatchScore = mismatchScore;
    }

    /**
     * get sequence 1
     * @return  sequence 1
     */
    public String getSequence1() {
        return sequence1;
    }

    /**
     * set sequence 1
     * @param sequence1
     */
    public void setSequence1(String sequence1) {
        this.sequence1 = sequence1;
    }

    /**
     * get sequence 2cted int max (int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
     * @return sequence 2
     */
    public String getSequence2() {
        return sequence2;
    }

    /**
     * set sequence 2
     * @param sequence2
     */
    public void setSequence2(String sequence2) {
        this.sequence2 = sequence2;
    }

    /**
     * get aligned version of sequence 1
     * @param i 
     * @return  aligned sequence 1
     */
    public String getAligned1(int i) {
        return aligned1.get(i);
    }

    /**
     * set aligned sequence 1
     * @param aligned1
     */
    protected void addAligned1(String aligned1) {
    	this.aligned1.add(aligned1);
    }

    /**
     * get aligned version of sequence 2
     * @param i 
     * @return aligned sequence 2
     */
    public String getAligned2(int i) {
    	 return aligned2.get(i);
    }

    /**
     * set aligned sequence 2
     * @param aligned2
     */
    protected void addAligned2(String aligned2) {
    	this.aligned2.add(aligned2);
    }

    /**
     * get computed score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * set computed score
     * @param score
     */
    protected void setScore(int score) {
        this.score = score;
    }

    /**
     * if char a is equal to char b
     * return the match score
	 * else return mismatch score
     */
    protected int match (char a,char b) {
        if(a==b)
            return getMatchScore();
        return getMismatchScore();
    }

    /**
     * return the maximum of a, b and c
     * @param a
     * @param b
     * @param c
     * @return
     */
    protected int max (int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
    /**
     * reverse a string sequence
     * @param seq
     * @return seq in reverse order
     */
    protected String reverse(String seq) {
    	StringBuffer buf = new StringBuffer(seq);
    	return buf.reverse().toString();
    }
}
