package com.roadtrack.onstar.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.roadtrack.onstar.BO.GlobalMembers;

public class CallSOS {
  public static void SendCall(Context paramContext, String paramString) {
    try {
      Intent intent = new Intent();
      this("android.intent.action.CALL");
      intent.addFlags(268435456);
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("tel:");
      stringBuilder.append(paramString);
      intent.setData(Uri.parse(stringBuilder.toString()));
      GlobalMembers.contexGlobal.startActivity(intent);
    } catch (Exception exception) {
      Utilities.escribeArchivo("CallSOS", "Error: SendCall", exception.toString());
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/CallSOS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */