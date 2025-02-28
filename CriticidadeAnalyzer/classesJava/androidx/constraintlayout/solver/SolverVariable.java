package androidx.constraintlayout.solver;

import java.util.Arrays;

public class SolverVariable {
  private static int uniqueErrorId = 1;
  
  public float computedValue;
  
  int definitionId = -1;
  
  public int id = -1;
  
  ArrayRow[] mClientEquations = new ArrayRow[8];
  
  int mClientEquationsCount = 0;
  
  private String mName;
  
  Type mType;
  
  public int strength = 0;
  
  float[] strengthVector = new float[7];
  
  public int usageInRowCount = 0;
  
  public SolverVariable(Type paramType, String paramString) {
    this.mType = paramType;
  }
  
  static void increaseErrorId() {
    uniqueErrorId++;
  }
  
  public final void addToRow(ArrayRow paramArrayRow) {
    int i = 0;
    while (true) {
      int j = this.mClientEquationsCount;
      if (i < j) {
        if (this.mClientEquations[i] == paramArrayRow)
          return; 
        i++;
        continue;
      } 
      ArrayRow[] arrayOfArrayRow = this.mClientEquations;
      if (j >= arrayOfArrayRow.length)
        this.mClientEquations = Arrays.<ArrayRow>copyOf(arrayOfArrayRow, arrayOfArrayRow.length * 2); 
      arrayOfArrayRow = this.mClientEquations;
      i = this.mClientEquationsCount;
      arrayOfArrayRow[i] = paramArrayRow;
      this.mClientEquationsCount = i + 1;
      return;
    } 
  }
  
  public final void removeFromRow(ArrayRow paramArrayRow) {
    int i = this.mClientEquationsCount;
    byte b2 = 0;
    for (byte b1 = 0; b1 < i; b1++) {
      if (this.mClientEquations[b1] == paramArrayRow) {
        while (b2 < i - b1 - 1) {
          ArrayRow[] arrayOfArrayRow = this.mClientEquations;
          int j = b1 + b2;
          arrayOfArrayRow[j] = arrayOfArrayRow[j + 1];
          b2++;
        } 
        this.mClientEquationsCount--;
        return;
      } 
    } 
  }
  
  public void reset() {
    this.mName = null;
    this.mType = Type.UNKNOWN;
    this.strength = 0;
    this.id = -1;
    this.definitionId = -1;
    this.computedValue = 0.0F;
    this.mClientEquationsCount = 0;
    this.usageInRowCount = 0;
  }
  
  public void setType(Type paramType, String paramString) {
    this.mType = paramType;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("");
    stringBuilder.append(this.mName);
    return stringBuilder.toString();
  }
  
  public final void updateReferencesWithNewDefinition(ArrayRow paramArrayRow) {
    int i = this.mClientEquationsCount;
    for (byte b = 0; b < i; b++) {
      ArrayRow[] arrayOfArrayRow = this.mClientEquations;
      (arrayOfArrayRow[b]).variables.updateFromRow(arrayOfArrayRow[b], paramArrayRow, false);
    } 
    this.mClientEquationsCount = 0;
  }
  
  public enum Type {
    CONSTANT, ERROR, SLACK, UNKNOWN, UNRESTRICTED;
    
    private static final Type[] $VALUES;
    
    static {
      ERROR = new Type("ERROR", 3);
      UNKNOWN = new Type("UNKNOWN", 4);
      $VALUES = new Type[] { UNRESTRICTED, CONSTANT, SLACK, ERROR, UNKNOWN };
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/SolverVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */