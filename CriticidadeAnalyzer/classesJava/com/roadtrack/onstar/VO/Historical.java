package com.roadtrack.onstar.VO;

import com.roadtrack.onstar.BO.GlobalMembers;
import java.util.Hashtable;

public class Historical {
  public static Hashtable<String, Object> lastStatus;
  
  public String AplicationType;
  
  public String Category;
  
  public String Description;
  
  public String Latitud;
  
  public String Longitud;
  
  public String Name;
  
  public String completion_code;
  
  public String dateTime;
  
  public String dateTimeExecution;
  
  public int deleted;
  
  public String gpsstatus;
  
  public String id;
  
  public String idAction;
  
  public int idError;
  
  public int idStatus;
  
  public int messageId;
  
  public String messageResponseId;
  
  public String requestErroId;
  
  public String responseErrorId;
  
  public double speedexcedeed;
  
  public double speedlimit;
  
  public String userName;
  
  public String vehicleId;
  
  public Historical(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, int paramInt2, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, int paramInt3, double paramDouble1, double paramDouble2, String paramString16, int paramInt4, String paramString17) {
    this.Category = paramString1;
    this.Name = paramString2;
    this.Description = paramString3;
    this.AplicationType = paramString4;
    this.Latitud = paramString5;
    this.Longitud = paramString6;
    this.id = paramString7;
    this.idStatus = paramInt1;
    this.idError = paramInt2;
    this.idAction = paramString8;
    this.vehicleId = paramString9;
    this.userName = paramString10;
    this.dateTime = paramString11;
    this.completion_code = paramString12;
    this.requestErroId = paramString13;
    this.responseErrorId = paramString14;
    this.messageResponseId = paramString15;
    this.deleted = paramInt3;
    this.speedlimit = paramDouble1;
    this.speedexcedeed = paramDouble2;
    this.gpsstatus = paramString16;
    this.messageId = paramInt4;
    this.dateTimeExecution = paramString17;
    if (!paramString7.equals("") && !paramString7.equals("-1")) {
      paramString1 = String.format("%S|%S %S", new Object[] { String.valueOf(paramInt1), paramString4, paramString2 });
      if (lastStatus == null)
        lastStatus = new Hashtable<String, Object>(); 
      if (lastStatus.size() > 0) {
        paramString2 = (String)lastStatus.get(paramString7);
        if (paramString2 != null) {
          String[] arrayOfString = paramString2.toString().split("\\|");
          if (arrayOfString[0] != null) {
            if (Integer.parseInt(arrayOfString[0]) < paramInt1 || paramInt1 == GlobalMembers.locatorErrorGetStatus_1) {
              lastStatus.remove(paramString7);
              if (paramInt2 == 0 && paramInt1 != 3 && paramInt1 != 5)
                lastStatus.put(paramString7.toString(), paramString1); 
            } 
          } else {
            lastStatus.put(paramString7.toString(), paramString1);
          } 
        } else if (paramInt2 == 0 && paramInt1 != 3 && paramInt1 != 5) {
          lastStatus.put(paramString7.toString(), paramString1);
        } 
      } else if (paramInt2 == 0 && paramInt1 != 3 && paramInt1 != 5) {
        lastStatus.put(paramString7.toString(), paramString1);
      } 
    } 
  }
  
  public String getAplicationType() {
    return this.AplicationType;
  }
  
  public String getCategory() {
    return this.Category;
  }
  
  public String getCompletion_code() {
    return this.completion_code;
  }
  
  public String getDateTime() {
    return this.dateTime;
  }
  
  public String getDateTimeExecution() {
    return this.dateTimeExecution;
  }
  
  public int getDeleted() {
    return this.deleted;
  }
  
  public String getDescription() {
    return this.Description;
  }
  
  public String getGPSStatus() {
    return this.gpsstatus;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getIdAction() {
    return this.idAction;
  }
  
  public int getIdError() {
    return this.idError;
  }
  
  public int getIdStatus() {
    return this.idStatus;
  }
  
  public String getLatitud() {
    return this.Latitud;
  }
  
  public String getLongitud() {
    return this.Longitud;
  }
  
  public int getMessageId() {
    return this.messageId;
  }
  
  public String getMessageResponseId() {
    return this.messageResponseId;
  }
  
  public String getName() {
    return this.Name;
  }
  
  public String getRequestErroId() {
    return this.requestErroId;
  }
  
  public String getResponseErrorId() {
    return this.responseErrorId;
  }
  
  public double getSpeedexcedeed() {
    return this.speedexcedeed;
  }
  
  public double getSpeedlimit() {
    return this.speedlimit;
  }
  
  public String getUserName() {
    return this.userName;
  }
  
  public String getVehicleId() {
    return this.vehicleId;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/Historical.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */