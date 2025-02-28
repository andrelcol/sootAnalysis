package com.roadtrack.onstar.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.analytics.AnalyticsEventModel;
import com.roadtrack.onstar.analytics.AnalyticsHelper;
import com.roadtrack.onstar.analytics.AnalyticsHelperFactory;
import com.roadtrack.onstar.analytics.AnalyticsTypes;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.DrawableUtils;
import com.roadtrack.onstar.utils.OrientationManager;
import com.roadtrack.onstar.utils.Utilities;

public class DialogSpeed extends DialogFragment implements Parcelable {
  public static final Parcelable.Creator<DialogSpeed> CREATOR = new Parcelable.Creator<DialogSpeed>() {
      public DialogSpeed createFromParcel(Parcel param1Parcel) {
        DialogSpeed dialogSpeed = new DialogSpeed();
        DialogSpeed.dialog = (Dialog)param1Parcel.readValue(DialogSpeed.class.getClassLoader());
        return dialogSpeed;
      }
      
      public DialogSpeed[] newArray(int param1Int) {
        return new DialogSpeed[param1Int];
      }
    };
  
  private static boolean cancelable;
  
  private static Context context;
  
  public static Dialog dialog;
  
  public static DialogSpeedListener listener;
  
  private static StringsResourcesVO stringsResourcesVO;
  
  private static View view;
  
  private LinearLayout LaySpeed;
  
  private TextView action_txtSpeedValue;
  
  private Button actions_btnActive;
  
  private Button actions_btnDisable;
  
  private Button actions_btnadd;
  
  private Button actions_btnsubs;
  
  private AnalyticsHelper analyticsHelper;
  
  private EditText txtSpeedValue;
  
  private TextView txtspeedLegend;
  
  private void analyticsEvent() {
    AnalyticsEventModel analyticsEventModel = new AnalyticsEventModel("-1", "execute_action", "speed");
    this.analyticsHelper.analyticsEventGeneric((Context)requireActivity(), analyticsEventModel);
  }
  
  private void initAnalytics() {
    this.analyticsHelper = AnalyticsHelperFactory.getAnalyticsHelper(AnalyticsTypes.FIREBASE);
  }
  
  public static DialogSpeed newInstance() {
    return new DialogSpeed();
  }
  
  public Dialog createDialog_Inner() {
    stringsResourcesVO = new StringsResourcesVO();
    view = View.inflate(context, 2131427472, null);
    this.action_txtSpeedValue = (TextView)view.findViewById(2131296340);
    Drawable drawable = Utilities.getDrawableFromConfigList(context, DrawableResourcesVO.actions_speedonstar, 2131165300);
    this.action_txtSpeedValue.setCompoundDrawablesWithIntrinsicBounds(DrawableUtils.resizeDrawable(context, drawable, 120), null, null, null);
    String str = Utilities.getStringFromConfigList(context, stringsResourcesVO.defaultSpeedValue, 2131689745);
    this.txtSpeedValue = (EditText)view.findViewById(2131296307);
    this.txtSpeedValue.setText(str);
    this.LaySpeed = (LinearLayout)view.findViewById(2131296688);
    str = Utilities.getStringFromConfigList(context, stringsResourcesVO.global_lbl_accionalertavel_1, 2131689851);
    this.action_txtSpeedValue.setText(str);
    this.LaySpeed.setVisibility(0);
    str = Utilities.getStringFromConfigList(context, stringsResourcesVO.actionpluss, 2131689636);
    this.actions_btnadd = (Button)view.findViewById(2131296348);
    this.actions_btnadd.setText(str);
    str = Utilities.getStringFromConfigList(context, stringsResourcesVO.actionsubs, 2131689647);
    this.actions_btnsubs = (Button)view.findViewById(2131296349);
    this.actions_btnsubs.setText(str);
    str = Utilities.getStringFromConfigList(context, stringsResourcesVO.speedLegend, 2131690372);
    this.txtspeedLegend = (TextView)view.findViewById(2131297216);
    this.txtspeedLegend.setText(str);
    this.actions_btnadd.setVisibility(0);
    this.actions_btnsubs.setVisibility(0);
    this.txtSpeedValue.setVisibility(0);
    this.actions_btnActive = (Button)view.findViewById(2131296343);
    str = Utilities.getStringFromConfigList(context, stringsResourcesVO.actions_popup_btn_activar_1, 2131689638);
    this.actions_btnActive.setText(str);
    final TextView action_txtSpeedValue = (TextView)view.findViewById(2131296307);
    this.actions_btnadd.setOnClickListener(new View.OnClickListener() {
          final DialogSpeed this$0;
          
          final TextView val$action_txtSpeedValue;
          
          public void onClick(View param1View) {
            DialogSpeed.this.analyticsEvent();
            DialogSpeed.listener.plusButtonListener(action_txtSpeedValue);
          }
        });
    this.actions_btnsubs.setOnClickListener(new View.OnClickListener() {
          final DialogSpeed this$0;
          
          final TextView val$action_txtSpeedValue;
          
          public void onClick(View param1View) {
            DialogSpeed.this.analyticsEvent();
            DialogSpeed.listener.minusButtonListener(action_txtSpeedValue);
          }
        });
    this.actions_btnActive.setOnClickListener(new View.OnClickListener() {
          final DialogSpeed this$0;
          
          final TextView val$action_txtSpeedValue;
          
          public void onClick(View param1View) {
            DialogSpeed.this.analyticsEvent();
            DialogSpeed.listener.acceptSpeedListener(action_txtSpeedValue, true);
          }
        });
    this.actions_btnDisable = (Button)view.findViewById(2131296346);
    str = Utilities.getStringFromConfigList(context, stringsResourcesVO.actions__lbl_desactivar_1, 2131689637);
    this.actions_btnDisable.setText(str);
    if (Utilities.getSpeedCode((onstarApplication)context.getApplicationContext(), "").GetCodeString().equals(Enums.Services.SpeedAlways.GetCodeString())) {
      this.actions_btnDisable.setVisibility(0);
      this.actions_btnDisable.setOnClickListener(new View.OnClickListener() {
            final DialogSpeed this$0;
            
            final TextView val$action_txtSpeedValue;
            
            public void onClick(View param1View) {
              DialogSpeed.this.analyticsEvent();
              DialogSpeed.listener.acceptSpeedListener(action_txtSpeedValue, false);
            }
          });
    } 
    Dialog dialog = new Dialog(context, 16973840);
    dialog.setTitle(null);
    dialog.setContentView(view);
    dialog.setCancelable(cancelable);
    Window window = dialog.getWindow();
    window.setFlags(32, 32);
    window.clearFlags(2);
    return dialog;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void onCancel(DialogInterface paramDialogInterface) {
    listener.onBackSpeedListener();
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    initAnalytics();
    OrientationManager.lockOrientation((Activity)getActivity());
    dialog = createDialog_Inner();
    setRetainInstance(true);
    if (dialog.getWindow() != null)
      dialog.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(ContextCompat.getColor(context, 2131034153))); 
    return dialog;
  }
  
  public void onDestroyView() {
    if (getDialog() != null && getRetainInstance())
      getDialog().setDismissMessage(null); 
    if (!MainActivity.onFindMe.booleanValue() && !MainActivity.onFollowMe.booleanValue() && !MainActivity.onHornLights.booleanValue() && !MainActivity.onCloseDoors.booleanValue() && !MainActivity.onOpenDoors.booleanValue() && !MainActivity.onDisarmPINCODE.booleanValue() && !MainActivity.onAlertParking.booleanValue() && !MainActivity.onAlertSpeed.booleanValue() && !MainActivity.onAlertValet.booleanValue() && !MainActivity.onNotification.booleanValue() && !MainActivity.onHorn.booleanValue())
      OrientationManager.unlockOrientation((Activity)getActivity()); 
    super.onDestroyView();
  }
  
  public void onDismiss(DialogInterface paramDialogInterface) {
    listener.onBackSpeedListener();
  }
  
  public void setPreferenceDialogFragment(DialogSpeedListener paramDialogSpeedListener, Context paramContext, boolean paramBoolean, int paramInt, String paramString) {
    listener = paramDialogSpeedListener;
    context = paramContext;
    cancelable = paramBoolean;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeValue(dialog);
  }
  
  public static interface DialogSpeedListener {
    void acceptSpeedListener(TextView param1TextView, boolean param1Boolean);
    
    void minusButtonListener(TextView param1TextView);
    
    void onBackSpeedListener();
    
    void plusButtonListener(TextView param1TextView);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/DialogSpeed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */