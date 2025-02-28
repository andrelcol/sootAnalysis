package com.roadtrack.onstar.entities;

import java.io.Serializable;

public class OptionMenu implements Serializable {
  public String AplicationType;
  
  public String Category;
  
  public String DateMessage;
  
  public String Description;
  
  public String Name;
  
  public String id;
  
  public int send;
  
  public int send_complete;
  
  public int waith;
  
  public String getAplicationType() {
    return this.AplicationType;
  }
  
  public String getCategory() {
    return this.Category;
  }
  
  public String getDateMessage() {
    return this.DateMessage;
  }
  
  public String getDescription() {
    return this.Description;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.Name;
  }
  
  public int getSend() {
    return this.send;
  }
  
  public int getSend_complete() {
    return this.send_complete;
  }
  
  public int getWaith() {
    return this.waith;
  }
  
  public void setSend(int paramInt) {
    this.send = paramInt;
  }
  
  public void setSend_complete(int paramInt) {
    this.send_complete = paramInt;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/OptionMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */