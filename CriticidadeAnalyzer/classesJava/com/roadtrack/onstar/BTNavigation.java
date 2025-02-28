package com.roadtrack.onstar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.ManagerNotificationPlatinum;
import com.roadtrack.onstar.VO.TbtManeuverInfoVO;
import com.roadtrack.onstar.utils.Converters;
import java.util.Calendar;

public class BTNavigation extends Activity {
  private Button btncancelnav;
  
  private Button btnmute;
  
  private Button btnstopnav;
  
  private Context context;
  
  private ImageView imagenes;
  
  private TbtManeuverInfoVO tbtManeuverInfo;
  
  private TextView txtETAval;
  
  private TextView txtTitle;
  
  private TextView txtcalleactual;
  
  private TextView txtcallenext;
  
  private TextView txtdistancia;
  
  private TextView txtdistanciadest;
  
  private TextView txtisnewval;
  
  private TextView txtisurgentval;
  
  private TextView txttimedest;
  
  private void actualizagui() {
    StringBuilder stringBuilder1;
    this.txtcalleactual.setText(this.tbtManeuverInfo.getCurrentStreetName());
    this.txtcallenext.setText(this.tbtManeuverInfo.getNextStreetName());
    this.imagenes.setImageResource(getImageDrawable(this.tbtManeuverInfo.getSingType()));
    this.txtisnewval.setText(getBitConvert(this.tbtManeuverInfo.getIsNewManeuver()));
    Integer integer2 = Integer.valueOf(Integer.parseInt(this.tbtManeuverInfo.getDistanceManeuver().trim()));
    if (integer2.intValue() > 1000) {
      this.txtdistancia.setText(Converters.mtsToKm(this.tbtManeuverInfo.getDistanceManeuver().trim()));
    } else if (integer2.intValue() >= 0) {
      TextView textView1 = this.txtdistancia;
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(integer2);
      stringBuilder1.append(" m");
      textView1.setText(stringBuilder1.toString());
    } 
    if (integer2.intValue() < 0)
      this.txtisurgentval.setText(getBitConvert(this.tbtManeuverInfo.getIsUrgentManeuver())); 
    integer2 = Integer.valueOf(Integer.parseInt(this.tbtManeuverInfo.getDistanceToDestination().trim()));
    if (integer2.intValue() > 1000) {
      this.txtdistanciadest.setText(Converters.mtsToKm(this.tbtManeuverInfo.getDistanceToDestination().trim()));
    } else if (integer2.intValue() >= 0) {
      TextView textView1 = this.txtdistanciadest;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(integer2);
      stringBuilder.append(" m");
      textView1.setText(stringBuilder.toString());
    } 
    if (integer2.intValue() < 0)
      this.txttimedest.setText(this.tbtManeuverInfo.getTimeToDestination().trim()); 
    Integer integer1 = Integer.valueOf(Integer.valueOf(Integer.parseInt(this.tbtManeuverInfo.getTimeToDestination().trim())).intValue() / 60);
    TextView textView = this.txttimedest;
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(integer1);
    stringBuilder2.append(" min");
    textView.setText(stringBuilder2.toString());
    Calendar calendar = Calendar.getInstance();
    int i = calendar.get(11);
    int j = calendar.get(12);
    int k = integer1.intValue() + j;
    if (k > 60) {
      i += k / 60;
      j = mod(k, 60);
      if (j < 10) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0");
        stringBuilder.append(j);
        String str = stringBuilder.toString();
      } else {
        String str = "";
      } 
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(k);
      stringBuilder.append(" ");
      String str = stringBuilder.toString();
    } 
    if (integer1.intValue() < 0) {
      TextView textView1 = this.txtETAval;
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append(i);
      stringBuilder1.append(":");
      stringBuilder1.append(j);
      textView1.setText(stringBuilder1.toString());
    } else {
      TextView textView1 = this.txtETAval;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(i);
      stringBuilder.append(":");
      stringBuilder.append((String)stringBuilder1);
      textView1.setText(stringBuilder.toString());
    } 
    this.btnmute.setOnClickListener(new View.OnClickListener() {
          final BTNavigation this$0;
          
          public void onClick(View param1View) {
            ManagerNotificationPlatinum managerNotificationPlatinum = new ManagerNotificationPlatinum(BTNavigation.this.context);
            if (GlobalMembers.tbtMuteStatus == 1) {
              managerNotificationPlatinum.tbtMuteRouteInstructions(0);
              GlobalMembers.tbtMuteStatus = 0;
            } else {
              managerNotificationPlatinum.tbtMuteRouteInstructions(1);
              GlobalMembers.tbtMuteStatus = 1;
            } 
          }
        });
    this.btncancelnav.setOnClickListener(new View.OnClickListener() {
          ManagerNotificationPlatinum MNP = new ManagerNotificationPlatinum(BTNavigation.this.context);
          
          final BTNavigation this$0;
          
          public void onClick(View param1View) {
            this.MNP.tbtStopNav(2);
            BTNavigation.this.finish();
          }
        });
    this.btnstopnav.setOnClickListener(new View.OnClickListener() {
          ManagerNotificationPlatinum MNP = new ManagerNotificationPlatinum(BTNavigation.this.context);
          
          final BTNavigation this$0;
          
          public void onClick(View param1View) {
            this.MNP.tbtStopNav(1);
            Toast.makeText(GlobalMembers.contexGlobal, "Para detener la Navegacion vaya al menu Principal, Todos los Comandos, Control de Navegacion, Detener la Navegacion", 1).show();
            BTNavigation.this.finish();
          }
        });
  }
  
  private String getBitConvert(int paramInt) {
    String str;
    if (paramInt != 0) {
      if (paramInt != 1) {
        str = null;
      } else {
        str = "yes";
      } 
    } else {
      str = "no";
    } 
    return str;
  }
  
  private int getImageDrawable(int paramInt) {
    int j = 2131165426;
    int i = j;
    switch (paramInt) {
      default:
        i = j;
        break;
      case 15:
        i = 2131165419;
        break;
      case 13:
        i = 2131165432;
        break;
      case 12:
        i = 2131165428;
        break;
      case 11:
        i = 2131165425;
        break;
      case 10:
        i = 2131165424;
        break;
      case 9:
        i = 2131165423;
        break;
      case 8:
        i = 2131165422;
        break;
      case 7:
        i = 2131165421;
        break;
      case 6:
        i = 2131165420;
        break;
      case 5:
        i = 2131165433;
        break;
      case 4:
        i = 2131165427;
        break;
      case 3:
        i = 2131165431;
        break;
      case 2:
        i = 2131165429;
        break;
      case 1:
        i = 2131165430;
        break;
      case 0:
      case 14:
        break;
    } 
    return i;
  }
  
  private int mod(int paramInt1, int paramInt2) {
    int i = paramInt1 % paramInt2;
    paramInt1 = i;
    if (i < 0)
      paramInt1 = i + paramInt2; 
    return paramInt1;
  }
  
  public static void setCustomEventListener(IupdateguiListener paramIupdateguiListener) {
    GlobalMembers.ListenerUUI = paramIupdateguiListener;
  }
  
  public static void setupdatetitlelistener(Iupdatetitle paramIupdatetitle) {
    GlobalMembers.BTNavListenerTitle = paramIupdatetitle;
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    GlobalMembers.EstadoAppGlobal = 2;
    setContentView(2131427387);
    this.context = (Context)this;
    this.txtTitle = (TextView)findViewById(2131297192);
    if (GlobalMembers.str555tmp.length() == 0)
      this.txtTitle.setText(GlobalMembers.str555tmp); 
    this.imagenes = (ImageView)findViewById(2131296299);
    this.txtdistancia = (TextView)findViewById(2131297178);
    this.txtcalleactual = (TextView)findViewById(2131297173);
    this.txtcallenext = (TextView)findViewById(2131297174);
    this.txtdistanciadest = (TextView)findViewById(2131297177);
    this.txttimedest = (TextView)findViewById(2131297190);
    this.txtisnewval = (TextView)findViewById(2131297213);
    this.txtisurgentval = (TextView)findViewById(2131297215);
    this.txtETAval = (TextView)findViewById(2131297181);
    this.btnmute = (Button)findViewById(2131297072);
    this.btncancelnav = (Button)findViewById(2131297071);
    this.btnstopnav = (Button)findViewById(2131296410);
    this.tbtManeuverInfo = GlobalMembers.tbtManeouverInfo;
    actualizagui();
    setCustomEventListener(new IupdateguiListener(this) {
        
        });
    setupdatetitlelistener(new Iupdatetitle() {
          final BTNavigation this$0;
          
          public void onEvent() {
            BTNavigation.this.txtTitle.setText(GlobalMembers.str555tmp);
            BTNavigation.this.txtTitle.setTextColor(-16777216);
            BTNavigation.this.txtTitle.setTypeface(null, 1);
          }
        });
    GlobalMembers.EstadoAppGlobal = 2;
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4) {
      MainActivity.getmNPlatinum().tbtDetenerNavegagion(1);
      Toast.makeText(GlobalMembers.contexGlobal, "Para detener la Navegacion vaya al menu Principal, Todos los Comandos, Control de Navegacion, Detener la Navegacion", 1).show();
      GlobalMembers.isTbtWorking = false;
      GlobalMembers.EstadoAppGlobal = 0;
      finish();
    } 
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public static interface IupdateguiListener {}
  
  public static interface Iupdatetitle {
    void onEvent();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BTNavigation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */