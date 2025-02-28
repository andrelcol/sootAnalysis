package com.roadtrack.onstar.VO;

import android.view.View;

public class GetCommandStatusVO {
  private String actionId;
  
  private View buttonSelected;
  
  private String commandStatus;
  
  public String getActionId() {
    return this.actionId;
  }
  
  public View getButtonSelected() {
    return this.buttonSelected;
  }
  
  public String getCommandStatus() {
    return this.commandStatus;
  }
  
  public void setActionId(String paramString) {
    this.actionId = paramString;
  }
  
  public void setButtonSelected(View paramView) {
    this.buttonSelected = paramView;
  }
  
  public void setCommandStatus(String paramString) {
    this.commandStatus = paramString;
  }
  
  public void setNameButton(String paramString) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/GetCommandStatusVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */