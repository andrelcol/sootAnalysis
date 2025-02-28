package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.Cache;
import java.util.ArrayList;

public class WidgetContainer extends ConstraintWidget {
  protected ArrayList<ConstraintWidget> mChildren = new ArrayList<ConstraintWidget>();
  
  public void add(ConstraintWidget paramConstraintWidget) {
    this.mChildren.add(paramConstraintWidget);
    if (paramConstraintWidget.getParent() != null)
      ((WidgetContainer)paramConstraintWidget.getParent()).remove(paramConstraintWidget); 
    paramConstraintWidget.setParent(this);
  }
  
  public ConstraintWidgetContainer getRootConstraintContainer() {
    ConstraintWidgetContainer constraintWidgetContainer;
    ConstraintWidget constraintWidget = getParent();
    if (this instanceof ConstraintWidgetContainer) {
      constraintWidgetContainer = (ConstraintWidgetContainer)this;
    } else {
      constraintWidgetContainer = null;
    } 
    while (constraintWidget != null) {
      ConstraintWidget constraintWidget1 = constraintWidget.getParent();
      if (constraintWidget instanceof ConstraintWidgetContainer)
        constraintWidgetContainer = (ConstraintWidgetContainer)constraintWidget; 
      constraintWidget = constraintWidget1;
    } 
    return constraintWidgetContainer;
  }
  
  public void layout() {
    updateDrawPosition();
    ArrayList<ConstraintWidget> arrayList = this.mChildren;
    if (arrayList == null)
      return; 
    int i = arrayList.size();
    for (byte b = 0; b < i; b++) {
      ConstraintWidget constraintWidget = this.mChildren.get(b);
      if (constraintWidget instanceof WidgetContainer)
        ((WidgetContainer)constraintWidget).layout(); 
    } 
  }
  
  public void remove(ConstraintWidget paramConstraintWidget) {
    this.mChildren.remove(paramConstraintWidget);
    paramConstraintWidget.setParent(null);
  }
  
  public void removeAllChildren() {
    this.mChildren.clear();
  }
  
  public void reset() {
    this.mChildren.clear();
    super.reset();
  }
  
  public void resetSolverVariables(Cache paramCache) {
    super.resetSolverVariables(paramCache);
    int i = this.mChildren.size();
    for (byte b = 0; b < i; b++)
      ((ConstraintWidget)this.mChildren.get(b)).resetSolverVariables(paramCache); 
  }
  
  public void setOffset(int paramInt1, int paramInt2) {
    super.setOffset(paramInt1, paramInt2);
    paramInt2 = this.mChildren.size();
    for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++)
      ((ConstraintWidget)this.mChildren.get(paramInt1)).setOffset(getRootX(), getRootY()); 
  }
  
  public void updateDrawPosition() {
    super.updateDrawPosition();
    ArrayList<ConstraintWidget> arrayList = this.mChildren;
    if (arrayList == null)
      return; 
    int i = arrayList.size();
    for (byte b = 0; b < i; b++) {
      ConstraintWidget constraintWidget = this.mChildren.get(b);
      constraintWidget.setOffset(getDrawX(), getDrawY());
      if (!(constraintWidget instanceof ConstraintWidgetContainer))
        constraintWidget.updateDrawPosition(); 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/solver/widgets/WidgetContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */