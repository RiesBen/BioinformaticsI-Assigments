package specificValues;

public abstract class BasicTables {
	public final static String allOption = "all";
	public final static String primerOption = "primer";
	public final static String cloningVectorOption = "cloningVectors";
	public final static String proteinConstructOption= "proteinConstructs";	

	public String getAllOption(){
		return allOption;
	}

	public String getprimerOption(){
		return primerOption;
	}
	
	public String getCloningVectorOption(){
		return cloningVectorOption;
	}
	
	public String getProteinConstructOption(){
		return proteinConstructOption;
	}
}