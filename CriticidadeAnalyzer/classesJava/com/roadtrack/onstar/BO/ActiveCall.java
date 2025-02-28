package com.roadtrack.onstar.BO;

import android.content.Context;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.entities.ActiveCallEntity;
import com.roadtrack.onstar.utils.Utilities;

public class ActiveCall {
  public ActiveCall(Context paramContext) {}
  
  public ActiveCallEntity getActiveCall(String[] paramArrayOfString) {
    try {
      return new ActiveCallEntity(Enums.ActionCall.None, Integer.valueOf(paramArrayOfString[0]).intValue(), Integer.valueOf(paramArrayOfString[1]).intValue(), Enums.DirectionCall.GetValue(Integer.valueOf(paramArrayOfString[2]).intValue()), Enums.DeviceCall.GetValue(Integer.valueOf(paramArrayOfString[3]).intValue()), Integer.valueOf(paramArrayOfString[4]).intValue(), Enums.AudioChannelCall.GetValue(Integer.valueOf(paramArrayOfString[5]).intValue()), Enums.ConferenceCall.GetValue(Integer.valueOf(paramArrayOfString[6]).intValue()), Enums.CallState.GetValue(Integer.valueOf(paramArrayOfString[7]).intValue()), paramArrayOfString[8], paramArrayOfString[9], Enums.CallNumberType.GetValue(Integer.valueOf(paramArrayOfString[10]).intValue()));
    } catch (Exception exception) {
      Utilities.escribeArchivo("ActiveCall", "Error: ActiveCallStatus", exception.getMessage());
      return new ActiveCallEntity();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/ActiveCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */