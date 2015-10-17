package exampleCode;

/**
 * Class to implement linear and affine gap penalties
 * Bioinformatics 1, WS 15/16
 * Jonas Ditz and Benjamin Schroeder
 */
public class GapPenalty {

    private int gapOpening = 1;
    private int gapExtension = 1;
    private boolean affine = false;

    /**
     * get gap opening cost
     * @return gapOpening
     */
    public int getGapOpening() {
        return gapOpening;
    }

    /**
     * get gap extension cost
     * @return gapExtension
     */
    public int getGapExtension() {
        return gapExtension;
    }

    /**
     * get gap penalty typ
     * @return 0 if linear, 1 else
     */
    public int getPenaltyTyp() {
        if(affine) return 1;
        else return 0;
    }

     /**
      * set gap opening cost
      * @param gapOpening
      */
     public void setGapOpening(int gapOpening) {
         this.gapOpening = gapOpening;
     }

     /**
      * set gap extension cost
      * @param gapExtension
      */
     public void setGapExtension(int gapExtension) {
         this.gapExtension = gapExtension;
     }

     /**
      * set gap penalty type
      * 0 for linear, 1 for affine
      * @param penaltyTyp
      */
     public void setPenaltyTyp(int penaltyTyp) {
         if (penaltyType == 0) this.affine = false;
         else this.affine = true;
     }

     /** 
      * get gap cost for currend gap
      * @param gapOpen 
      * @return gapCost
      */
     public int getGapCost(boolean gapOpen) {
       if (affine & gapOpen) return gapOpening;
       else if (affine & !gapOpen) return gapExtension;
       else return gapOpening;
     }
    
}
