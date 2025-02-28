package androidx.constraintlayout.solver.widgets;

import java.util.HashSet;
import java.util.Iterator;

public class ResolutionNode {
  HashSet<ResolutionNode> dependents = new HashSet<ResolutionNode>(2);
  
  int state = 0;
  
  public void addDependent(ResolutionNode paramResolutionNode) {
    this.dependents.add(paramResolutionNode);
  }
  
  public void didResolve() {
    this.state = 1;
    Iterator<ResolutionNode> iterator = this.dependents.iterator();
    while (iterator.hasNext())
      ((ResolutionNode)iterator.next()).resolve(); 
  }
  
  public void invalidate() {
    this.state = 0;
    Iterator<ResolutionNode> iterator = this.dependents.iterator();
    while (iterator.hasNext())
      ((ResolutionNode)iterator.next()).invalidate(); 
  }
  
  public boolean isResolved() {
    int i = this.state;
    boolean bool = true;
    if (i != 1)
      bool = false; 
    return bool;
  }
  
  public void reset() {
    this.state = 0;
    this.dependents.clear();
  }
  
  public void resolve() {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/ResolutionNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */