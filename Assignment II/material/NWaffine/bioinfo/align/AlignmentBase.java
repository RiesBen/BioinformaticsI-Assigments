package exampleCode.bioinfo.align;

/**
 * Base class for computing a local alignment using the Needleman Wunsch / Smith Waterman algorithm
 */
public class AlignmentBase {
    private int gapPenalty = 1;
    private int matchScore = 1;
    private int mismatchScore = -1;
    private String sequence1 = null;
    private String sequence2 = null;
    private String aligned1 = null;
    private String aligned2 = null;
    private int score = 0;
    private ScoreMatrix scoreMatrix = null;

    private int begin1 = 0;
    private int end1 = 0;
    private int begin2 = 0;
    private int end2 = 0;

    /**
     * get gap open penalty
     * @return             gap open penalty
     */
    public int getGapPenalty() {
        return gapPenalty;
    }

    /**
     * set gap open penalty
     * @param gapPenalty
     */
    public void setGapPenalty(int gapPenalty) {
        this.gapPenalty = gapPenalty;
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
     * if score matrix set, returns score of matching a and b, otherwise returns match or mismatch penalty
     * @param a
     * @param b
     * @return score of matching a and b
     * @throws Exception                no score defined for a or b
     */
    protected int match(char a, char b) throws Exception {
        if(getScoreMatrix()==null)
        {
            if(a==b)
                return getMatchScore();
            else
                return getMismatchScore();
        }
        else
            return getScoreMatrix().getScore(a,b);
    }

    /**
     * returns the maximum of a, b and c
     * @param a
     * @param b
     * @param c
     * @param d
     * @return the maximum value
     */
    protected int max(int a, int b, int c, int d) {
        return Math.max(a, Math.max(b, Math.max(c, d)));
    }
    
    /**
     * returns the maximum of a, b and c
     * @param a
     * @param b
     * @param c
     * @return
     */
    protected int max (int a,int b,int c)
    {
        return Math.max(a,Math.max(b,c));
    }

    /**
     * get the begin of alignment in first sequence
     * @return  begin
     */
    public int getBegin1() {
        return begin1;
    }

    /**
     * set the begin of alignment in first sequence
     * @param begin1
     */
    public void setBegin1(int begin1) {
        this.begin1 = begin1;
    }

    /**
     * get end of alignment in first sequence
     * @return                               end
     */
    public int getEnd1() {
        return end1;
    }

    /**
     * set end of alignment in first sequence
     * @param end1
     */
    public void setEnd1(int end1) {
        this.end1 = end1;
    }

    /**
     * get begin of alignment in second sequence
     * @return                                  begin
     */
    public int getBegin2() {
        return begin2;
    }

    /**
     * set begin of alignment in second sequence
     * @param begin2
     */
    public void setBegin2(int begin2) {
        this.begin2 = begin2;
    }

    /**
     * get end of alignement in second sequence
     * @return                                 end
     */

    public int getEnd2() {
        return end2;
    }

    /**
     * set end of alignment in second sequence
     * @param end2
     */

    public void setEnd2(int end2) {
        this.end2 = end2;
    }

    /**
     * gets the score matrix
     * @return
     */
    public ScoreMatrix getScoreMatrix() {
        return scoreMatrix;
    }

    /**
     * sets the score matrix
     * @param scoreMatrix
     */
    public void setScoreMatrix(ScoreMatrix scoreMatrix) {
        this.scoreMatrix = scoreMatrix;
    }
}