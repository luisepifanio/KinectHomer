/* Generated By:JJTree: Do not edit this line. ASTIntFlatExpr.java Version 4.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package uk.ac.stir.cs.homer.homerPolicyServer.overlap.JaCoP.fz;

public
class ASTIntFlatExpr extends SimpleNode {
  public ASTIntFlatExpr(int id) {
    super(id);
  }

  public ASTIntFlatExpr(Parser p, int id) {
    super(p, id);
  }

    // My additions
    int value;

    public void setInt(int i) {
//   	if (i <= JaCoP.core.Constants.MaxInt && i >= JaCoP.core.Constants.MinInt)
	    value = i;
//   	else {
//   	    System.err.println("Error: Too large or too small integer " + i + 
//   			       "; execution aborted");
//  	    System.exit(0);
//   	}
    }
    public int getInt() {
	return value;
    }

    public String toString() {
	return super.toString() + ": " + value;
    }
}
/* JavaCC - OriginalChecksum=c6d9e8b614a5d7a17f9a1b5ab4da61c7 (do not edit this line) */
