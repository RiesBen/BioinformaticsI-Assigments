/**
 * Base class for computing a global alignment using the Needleman Wunsch algorithm
 * @author Daniel Huson
 */
package exampleCode.bioinfo.align;

/** 
 * Base class for computing a global alignment using the Needleman Wunsch algorithm
 */
public class NeedlemanWunschAffineBase {
    private int gapOpenPenalty=2;
    private int gapExtensionPenalty=1;
    private int matchScore=1;
    private int mismatchScore= -1;
    private String sequence1=null;
    private String sequence2=null;
    private String aligned1=null;
    private String aligned2=null;
    private int score=0;
    final static byte HorizontalM=1;
    final static byte VerticalM=2;
    final static byte DiagonalM=3;
    final static byte HorizontalI=4;
    final static byte VerticalI=5;
    final static byte DiagonalI=6;
    /**
     * get gap extension penalty
     * @return                  penalty
     */
    public int getGapExtensionPenalty() {
        return gapExtensionPenalty;
    }

    /**
     * set gap extension penalty
     * @param gapExtensionPenalty
     */

    public void setGapExtensionPenalty(int gapExtensionPenalty) {
        this.gapExtensionPenalty = gapExtensionPenalty;
    }

    /**
     * get gap open penalty
     * @return             gap open penalty
     */
    public int getGapOpenPenalty() {
        return gapOpenPenalty;
    }

    /**
     * set gap open penalty
     * @param gapOpenPenalty
     */
    public void setGapOpenPenalty(int gapOpenPenalty) {
        this.gapOpenPenalty = gapOpenPenalty;
    }

    /**
     * get match score
     * @return        match score
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
     * @return           mismatch score
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
     * @return       sequence 1
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
     * get sequence 2
     * @return       sequence 2
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
     * @return                          aligned sequence 1
     */
    public String getAligned1() {
        return aligned1;
    }

    /**
     * set aligned sequence 1
     * @param aligned1
     */
    protected void setAligned1(String aligned1) {
        this.aligned1 = aligned1;
    }

    /**
     * get aligned version of sequence 2
     * @return                          aligned sequence 2
     */
    public String getAligned2() {
        return aligned2;
    }

    /**
     * set aligned sequence 2
     * @param aligned2
     */
    protected void setAligned2(String aligned2) {
        this.aligned2 = aligned2;
    }

    /**
     * get the computed score
     * @return               score
     */
    public int getScore() {
        return score;
    }

    /**
     * set the computed score
     * @param score
     */
    protected void setScore(int score) {
        this.score = score;
    }

    /**
     * returns the match score, if a=b, mismatch score, else
     */
    protected int match (char a,char b)
    {
        if(a==b)
            return getMatchScore();
        else
            return getMismatchScore();
    }

        /**
     * returns the maximum of a, b and c
     * @param a
     * @param b
     * @return
     */
    protected int max (int a,int b)
    {
        return Math.max(a,b);
    }
    /**
     * returns the maximum of a, b and c
     * @param a
     * @param b
     * @param c
     * @param d
     * @return
     */
    protected int max (int a,int b,int c,int d)
    {
        return Math.max(a,Math.max(b,Math.max(c,d)));
    }
}