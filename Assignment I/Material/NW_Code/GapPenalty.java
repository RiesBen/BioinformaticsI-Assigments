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
     * get gap penalty type
     * @return 0 if linear, 1 else
     */
    public int getPenaltyType() {
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
      * @param penaltyType
      */
     public void setPenaltyType(int penaltyType) {
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

     /**
      * get initialize gap costs
      * @param index
      * @return cost
      */
     public int getInitCost(int index) {
    	if(affine) return -(index-1)*this.gapExtension - this.gapOpening;
    	return -index*this.gapOpening;
     }
}
