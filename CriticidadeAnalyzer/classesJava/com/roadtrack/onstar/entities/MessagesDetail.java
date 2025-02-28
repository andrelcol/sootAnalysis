package com.roadtrack.onstar.entities;

import com.roadtrack.onstar.Enums;
import java.util.Date;

public class MessagesDetail {
  Date DateMessage;
  
  String DescripMessage;
  
  String IdCode;
  
  Enums.MessageOrigin IdOrigin;
  
  String TextMessage;
  
  Enums.MessageType TypeGUI;
  
  public Date getDateMessage() {
    return this.DateMessage;
  }
  
  public String getDescripMessage() {
    return this.DescripMessage;
  }
  
  public String getIdCode() {
    return this.IdCode;
  }
  
  public Enums.MessageOrigin getIdOrigin() {
    return this.IdOrigin;
  }
  
  public String getTextMessage() {
    return this.TextMessage;
  }
  
  public Enums.MessageType getTypeGUI() {
    return this.TypeGUI;
  }
  
  public void setDateMessage(Date paramDate) {
    this.DateMessage = paramDate;
  }
  
  public void setDescripMessage(String paramString) {
    this.DescripMessage = paramString;
  }
  
  public void setIdCode(String paramString) {
    this.IdCode = paramString;
  }
  
  public void setIdOrigin(Enums.MessageOrigin paramMessageOrigin) {
    this.IdOrigin = paramMessageOrigin;
  }
  
  public void setPhoneNumber(String paramString) {}
  
  public void setTextMessage(String paramString) {
    this.TextMessage = paramString;
  }
  
  public void setTypeGUI(Enums.MessageType paramMessageType) {
    this.TypeGUI = paramMessageType;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/MessagesDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */