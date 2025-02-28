package com.roadtrack.onstar.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.roadtrack.onstar.DAO.DBFunctions;

public class GenericDialog extends Activity {
  static Context context;
  
  static Dialog dialog;
  
  boolean CancelButton = false;
  
  Button CancelButtonDialog;
  
  CheckBox CheckBoxDialog;
  
  String Content = "";
  
  TextView ContentDialog;
  
  private LinearLayout GenericDialogLayout;
  
  String IdCamping = "";
  
  boolean MoreInfo = false;
  
  Button MoreInfoDialog;
  
  boolean NotShowAgain = false;
  
  String TAG = "GenericDialog";
  
  String TitleButton = "";
  
  String Tittle = "";
  
  TextView TittleDialog;
  
  String URL_Dialog = "";
  
  private DBFunctions dbFun;
  
  String deviceID = "";
  
  boolean forceDialog = false;
  
  private void ParseBundleRecived() {
    this.IdCamping = getIntent().getExtras().getString("IdCampaign_Dialog");
    this.Tittle = getIntent().getExtras().getString("Title");
    this.Content = getIntent().getExtras().getString("Content");
    this.NotShowAgain = getIntent().getExtras().getBoolean("NotShowAgain");
    this.MoreInfo = getIntent().getExtras().getBoolean("MoreInfo");
    this.CancelButton = getIntent().getExtras().getBoolean("CancelButton");
    this.TitleButton = getIntent().getExtras().getString("TitleButton");
    this.URL_Dialog = getIntent().getExtras().getString("URL_Dialog");
    this.deviceID = getIntent().getExtras().getString("deviceID");
    this.forceDialog = getIntent().getExtras().getBoolean("forceDialog");
  }
  
  private static Animation SetAnimation(int paramInt) {
    return AnimationUtils.loadAnimation(context, paramInt);
  }
  
  public void ShowGenericDialog() {
    // Byte code:
    //   0: new android/app/Dialog
    //   3: dup
    //   4: getstatic com/roadtrack/onstar/utils/GenericDialog.context : Landroid/content/Context;
    //   7: ldc 2131755185
    //   9: invokespecial <init> : (Landroid/content/Context;I)V
    //   12: putstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   15: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   18: ldc 2131427415
    //   20: invokevirtual setContentView : (I)V
    //   23: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   26: iconst_1
    //   27: invokevirtual setCanceledOnTouchOutside : (Z)V
    //   30: aload_0
    //   31: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   34: ldc 2131296562
    //   36: invokevirtual findViewById : (I)Landroid/view/View;
    //   39: checkcast android/widget/LinearLayout
    //   42: putfield GenericDialogLayout : Landroid/widget/LinearLayout;
    //   45: new com/roadtrack/onstar/VO/StringsResourcesVO
    //   48: dup
    //   49: invokespecial <init> : ()V
    //   52: astore_2
    //   53: aload_0
    //   54: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   57: ldc 2131296749
    //   59: invokevirtual findViewById : (I)Landroid/view/View;
    //   62: checkcast android/widget/TextView
    //   65: putfield TittleDialog : Landroid/widget/TextView;
    //   68: getstatic com/roadtrack/onstar/utils/GenericDialog.context : Landroid/content/Context;
    //   71: aload_2
    //   72: getfield global_popup_lbl_aviso_1 : Ljava/lang/String;
    //   75: ldc 2131689955
    //   77: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   80: astore_3
    //   81: aload_0
    //   82: getfield TittleDialog : Landroid/widget/TextView;
    //   85: aload_3
    //   86: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   89: aload_0
    //   90: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   93: ldc 2131296748
    //   95: invokevirtual findViewById : (I)Landroid/view/View;
    //   98: checkcast android/widget/TextView
    //   101: putfield ContentDialog : Landroid/widget/TextView;
    //   104: aload_0
    //   105: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   108: ldc 2131296467
    //   110: invokevirtual findViewById : (I)Landroid/view/View;
    //   113: checkcast android/widget/CheckBox
    //   116: putfield CheckBoxDialog : Landroid/widget/CheckBox;
    //   119: getstatic com/roadtrack/onstar/utils/GenericDialog.context : Landroid/content/Context;
    //   122: aload_2
    //   123: getfield mapdownloading_popup_lbl_nomostrarmensaje_3 : Ljava/lang/String;
    //   126: ldc 2131690123
    //   128: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   131: astore_3
    //   132: aload_0
    //   133: getfield CheckBoxDialog : Landroid/widget/CheckBox;
    //   136: aload_3
    //   137: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   140: aload_0
    //   141: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   144: ldc 2131296419
    //   146: invokevirtual findViewById : (I)Landroid/view/View;
    //   149: checkcast android/widget/Button
    //   152: putfield MoreInfoDialog : Landroid/widget/Button;
    //   155: aload_0
    //   156: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   159: ldc 2131296417
    //   161: invokevirtual findViewById : (I)Landroid/view/View;
    //   164: checkcast android/widget/Button
    //   167: putfield CancelButtonDialog : Landroid/widget/Button;
    //   170: getstatic com/roadtrack/onstar/utils/GenericDialog.context : Landroid/content/Context;
    //   173: aload_2
    //   174: getfield mapdownloading_popup_btn_despues_4 : Ljava/lang/String;
    //   177: ldc 2131690116
    //   179: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   182: astore_3
    //   183: aload_0
    //   184: getfield CancelButtonDialog : Landroid/widget/Button;
    //   187: aload_3
    //   188: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   191: getstatic com/roadtrack/onstar/utils/GenericDialog.context : Landroid/content/Context;
    //   194: aload_2
    //   195: getfield global_popup_btn_ok_1 : Ljava/lang/String;
    //   198: ldc 2131689950
    //   200: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   203: astore_3
    //   204: getstatic com/roadtrack/onstar/utils/GenericDialog.context : Landroid/content/Context;
    //   207: aload_2
    //   208: getfield global_popup_btn_ok_1 : Ljava/lang/String;
    //   211: ldc 2131689950
    //   213: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   216: astore #4
    //   218: aload_0
    //   219: getfield TittleDialog : Landroid/widget/TextView;
    //   222: aconst_null
    //   223: invokevirtual equals : (Ljava/lang/Object;)Z
    //   226: ifne -> 240
    //   229: aload_0
    //   230: getfield TittleDialog : Landroid/widget/TextView;
    //   233: aload_0
    //   234: getfield Tittle : Ljava/lang/String;
    //   237: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   240: aload_0
    //   241: getfield ContentDialog : Landroid/widget/TextView;
    //   244: aconst_null
    //   245: invokevirtual equals : (Ljava/lang/Object;)Z
    //   248: ifne -> 262
    //   251: aload_0
    //   252: getfield ContentDialog : Landroid/widget/TextView;
    //   255: aload_0
    //   256: getfield Content : Ljava/lang/String;
    //   259: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   262: aload_0
    //   263: getfield NotShowAgain : Z
    //   266: ifeq -> 277
    //   269: aload_0
    //   270: getfield CheckBoxDialog : Landroid/widget/CheckBox;
    //   273: iconst_0
    //   274: invokevirtual setVisibility : (I)V
    //   277: aload_0
    //   278: getfield CancelButton : Z
    //   281: ifeq -> 292
    //   284: aload_0
    //   285: getfield CancelButtonDialog : Landroid/widget/Button;
    //   288: iconst_0
    //   289: invokevirtual setVisibility : (I)V
    //   292: aload_0
    //   293: getfield MoreInfo : Z
    //   296: istore_1
    //   297: iload_1
    //   298: ifeq -> 356
    //   301: aload_0
    //   302: getfield MoreInfoDialog : Landroid/widget/Button;
    //   305: iconst_0
    //   306: invokevirtual setVisibility : (I)V
    //   309: aload_0
    //   310: getfield TitleButton : Ljava/lang/String;
    //   313: ldc ''
    //   315: invokevirtual equals : (Ljava/lang/Object;)Z
    //   318: ifne -> 344
    //   321: aload_0
    //   322: getfield TitleButton : Ljava/lang/String;
    //   325: astore_2
    //   326: aload_2
    //   327: ifnonnull -> 333
    //   330: goto -> 344
    //   333: aload_0
    //   334: getfield MoreInfoDialog : Landroid/widget/Button;
    //   337: aload_2
    //   338: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   341: goto -> 377
    //   344: aload_0
    //   345: getfield MoreInfoDialog : Landroid/widget/Button;
    //   348: aload #4
    //   350: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   353: goto -> 377
    //   356: iload_1
    //   357: ifne -> 377
    //   360: aload_0
    //   361: getfield MoreInfoDialog : Landroid/widget/Button;
    //   364: iconst_0
    //   365: invokevirtual setVisibility : (I)V
    //   368: aload_0
    //   369: getfield MoreInfoDialog : Landroid/widget/Button;
    //   372: aload #4
    //   374: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   377: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   380: new com/roadtrack/onstar/utils/GenericDialog$1
    //   383: dup
    //   384: aload_0
    //   385: invokespecial <init> : (Lcom/roadtrack/onstar/utils/GenericDialog;)V
    //   388: invokevirtual setOnDismissListener : (Landroid/content/DialogInterface$OnDismissListener;)V
    //   391: aload_0
    //   392: getfield MoreInfoDialog : Landroid/widget/Button;
    //   395: new com/roadtrack/onstar/utils/GenericDialog$2
    //   398: dup
    //   399: aload_0
    //   400: aload_3
    //   401: invokespecial <init> : (Lcom/roadtrack/onstar/utils/GenericDialog;Ljava/lang/String;)V
    //   404: invokevirtual setOnClickListener : (Landroid/view/View$OnClickListener;)V
    //   407: getstatic com/roadtrack/onstar/googleMaps/ActivityMapViewerG.isRouteInProgress : Z
    //   410: ifeq -> 443
    //   413: aload_0
    //   414: getfield forceDialog : Z
    //   417: ifeq -> 423
    //   420: goto -> 443
    //   423: aload_0
    //   424: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   427: ldc '2'
    //   429: aload_0
    //   430: getfield IdCamping : Ljava/lang/String;
    //   433: aload_0
    //   434: getfield deviceID : Ljava/lang/String;
    //   437: invokevirtual UpdateStatusOfShowGenericDialog : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   440: goto -> 469
    //   443: getstatic com/roadtrack/onstar/utils/GenericDialog.dialog : Landroid/app/Dialog;
    //   446: invokevirtual show : ()V
    //   449: aload_0
    //   450: getfield GenericDialogLayout : Landroid/widget/LinearLayout;
    //   453: iconst_0
    //   454: invokevirtual setVisibility : (I)V
    //   457: aload_0
    //   458: getfield GenericDialogLayout : Landroid/widget/LinearLayout;
    //   461: ldc 2130771983
    //   463: invokestatic SetAnimation : (I)Landroid/view/animation/Animation;
    //   466: invokevirtual startAnimation : (Landroid/view/animation/Animation;)V
    //   469: return
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    context = (Context)this;
    this.dbFun = new DBFunctions(context);
    Utilities.escribeArchivo(this.TAG, "onCreate", "Starting");
    ParseBundleRecived();
    ShowGenericDialog();
  }
  
  protected void onNewIntent(Intent paramIntent) {
    super.onNewIntent(paramIntent);
  }
  
  protected void onStop() {
    super.onStop();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/GenericDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */