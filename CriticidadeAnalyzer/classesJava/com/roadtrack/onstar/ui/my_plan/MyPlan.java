package com.roadtrack.onstar.ui.my_plan;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.RenewalPlansListResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.async_tasks.intefaces.AsyncResponse;
import com.roadtrack.onstar.async_tasks.intefaces.RenewalPlans_Interface;
import com.roadtrack.onstar.async_tasks.tasks.GetMyPlanInformation_Task;
import com.roadtrack.onstar.async_tasks.tasks.GetRenewalPlansTask;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyPlan extends Activity implements View.OnClickListener {
  private static final String TAG = MyPlan.class.getSimpleName();
  
  private List<VehicleCatalogVO> arraylistVehiclesAlmostExpired = new ArrayList<VehicleCatalogVO>();
  
  private List<VehicleCatalogVO> arraylistVehiclesExpired = new ArrayList<VehicleCatalogVO>();
  
  private List<VehicleCatalogVO> arraylistVehiclesNormal = new ArrayList<VehicleCatalogVO>();
  
  private Button btnOK;
  
  private Button btn_alter_my_plan;
  
  List<MyPlanFeature> free_info = new ArrayList<MyPlanFeature>();
  
  private String global_lbl_acciondescfallared_1;
  
  private String global_lbl_conexiondered_1;
  
  private String global_popup_btn_aceptar_1;
  
  private String global_popup_btn_si_1;
  
  private ImageView imgv_feature;
  
  private ImageView imgv_header_image;
  
  private ImageView iv_plan_blue;
  
  private TextView lbl_activity_title;
  
  private TextView lbl_feature_description;
  
  private TextView lbl_feature_title;
  
  private TextView lbl_free_period;
  
  private TextView lbl_free_period_date;
  
  private TextView lbl_plan_name;
  
  private TextView lbl_plan_side_text;
  
  private TextView lbl_plan_side_text1;
  
  private TextView lbl_plan_text2;
  
  private LinearLayout lin_first_column;
  
  private LinearLayout lin_free_column;
  
  private LinearLayout lin_free_period;
  
  List<MyPlanFeature> plan_info = new ArrayList<MyPlanFeature>();
  
  private String previousClass = "";
  
  private String renovacion_lbl_serviciosdisponibles;
  
  private String renovacion_lbl_suplanactuales;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tf;
  
  private Typeface tf2;
  
  int totalVehicleExpiredOrAlmost = 0;
  
  private void cleanView() {
    this.lbl_plan_name.setText("");
    this.lbl_plan_side_text.setText("");
    this.lbl_plan_side_text1.setText("");
    this.lbl_plan_text2.setText("");
    this.lbl_free_period_date.setText("");
    this.lbl_free_period.setText("");
    this.lin_first_column.removeAllViews();
    this.lin_free_column.removeAllViews();
    this.plan_info = new ArrayList<MyPlanFeature>();
    this.free_info = new ArrayList<MyPlanFeature>();
  }
  
  private void displayFeatures(String paramString) {
    List<MyPlanFeature> list;
    if (paramString.equals("1")) {
      list = this.plan_info;
    } else {
      list = this.free_info;
    } 
    for (byte b = 0; b < list.size(); b++) {
      LinearLayout linearLayout;
      LayoutInflater layoutInflater = (LayoutInflater)getSystemService("layout_inflater");
      if (paramString.equals("1")) {
        linearLayout = (LinearLayout)layoutInflater.inflate(2131427424, (ViewGroup)this.lin_first_column, false);
      } else {
        linearLayout = (LinearLayout)linearLayout.inflate(2131427424, (ViewGroup)this.lin_free_column, false);
      } 
      Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/PFBeauSansProRegular.ttf");
      this.imgv_feature = (ImageView)linearLayout.findViewById(2131296642);
      this.lbl_feature_title = (TextView)linearLayout.findViewById(2131296745);
      this.lbl_feature_description = (TextView)linearLayout.findViewById(2131296744);
      this.lbl_feature_title.setTypeface(typeface);
      this.lbl_feature_description.setTypeface(typeface);
      this.imgv_feature.setImageDrawable(((MyPlanFeature)list.get(b)).getFeature_image());
      this.lbl_feature_title.setText(((MyPlanFeature)list.get(b)).getFeature_name());
      this.lbl_feature_description.setText(((MyPlanFeature)list.get(b)).getFeature_description());
      if (paramString.equals("1")) {
        this.lin_first_column.addView((View)linearLayout);
      } else {
        this.lin_free_column.addView((View)linearLayout);
      } 
    } 
  }
  
  private void fillFeatures(String paramString) {
    if (paramString != null && !paramString.equals("")) {
      String[] arrayOfString = paramString.split("\\*");
      if (arrayOfString.length > 1) {
        int i = arrayOfString.length;
        for (byte b = 0; b < i; b++) {
          String str = arrayOfString[b];
          if (str != null && !str.equals("")) {
            String[] arrayOfString1 = str.split("#");
            if (arrayOfString1.length == 2) {
              str = arrayOfString1[0];
              MyPlanFeature myPlanFeature = getFeatureObject(arrayOfString1[1], str);
              if (myPlanFeature != null)
                if (str.equals("1")) {
                  this.plan_info.add(myPlanFeature);
                } else {
                  this.free_info.add(myPlanFeature);
                }  
            } 
          } 
        } 
      } 
    } else {
      onResponseError();
    } 
  }
  
  private void fillInformation(String paramString) {
    if (paramString != null && !paramString.equals("")) {
      String[] arrayOfString = paramString.split("\\|");
      if (arrayOfString.length >= 5) {
        String str2 = arrayOfString[1];
        String str1 = arrayOfString[2];
        if (arrayOfString.length == 5) {
          paramString = arrayOfString[4];
          this.lin_free_period.setVisibility(8);
        } else {
          paramString = arrayOfString[5];
          arrayOfString = arrayOfString[4].split("#");
          if (arrayOfString != null && arrayOfString.length > 0) {
            this.lin_free_period.setVisibility(0);
            this.lbl_free_period_date.setText(arrayOfString[0]);
            if (arrayOfString.length > 1 && !arrayOfString[1].isEmpty())
              this.lbl_free_period.setText(arrayOfString[1]); 
          } 
        } 
        this.lbl_plan_name.setText(str2);
        str2 = this.renovacion_lbl_suplanactuales;
        this.lbl_plan_side_text.setText(str2);
        this.lbl_plan_side_text1.setText(str1);
        this.lbl_plan_text2.setText(this.renovacion_lbl_serviciosdisponibles);
        fillFeatures(paramString);
      } else {
        onResponseError();
      } 
    } else {
      onResponseError();
    } 
  }
  
  private int getFeatureImage(String paramString) {
    try {
      int i = Integer.parseInt(paramString);
      switch (i) {
        default:
          return 0;
        case 6:
          return 2131165506;
        case 5:
          return 2131165509;
        case 4:
          return 2131165511;
        case 3:
          return 2131165510;
        case 2:
          return 2131165505;
        case 1:
          break;
      } 
      return 2131165508;
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      return 0;
    } 
  }
  
  private Drawable getFeatureImageDrawable(String paramString) {
    String str = null;
    try {
      int i = Integer.parseInt(paramString);
      switch (i) {
        default:
          return (Drawable)str;
        case 6:
          return Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_plan_assistance, 2131165506);
        case 5:
          return Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_plan_navigation, 2131165509);
        case 4:
          return Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_plan_sos, 2131165511);
        case 3:
          return Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_plan_security, 2131165510);
        case 2:
          return Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_plan_app, 2131165505);
        case 1:
          break;
      } 
      return Utilities.getDrawableFromConfigList((Context)this, DrawableResourcesVO.ic_plan_diagnostic, 2131165508);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error", exception.getMessage());
      return null;
    } 
  }
  
  private MyPlanFeature getFeatureObject(String paramString1, String paramString2) {
    String[] arrayOfString;
    MyPlanFeature myPlanFeature2 = new MyPlanFeature();
    MyPlanFeature myPlanFeature1 = myPlanFeature2;
    if (paramString1 != null) {
      String str = "";
      myPlanFeature1 = myPlanFeature2;
      if (!paramString1.equals("")) {
        arrayOfString = paramString1.split("¥");
        myPlanFeature2.setFeature_section(paramString2);
        if (arrayOfString.length > 1) {
          String[] arrayOfString1 = arrayOfString[0].split(",");
          if (arrayOfString1.length == 2) {
            myPlanFeature2.setFeature_id(arrayOfString1[0]);
            myPlanFeature2.setFeature_image_id(getFeatureImage(arrayOfString1[0]));
            myPlanFeature2.setFeature_image(getFeatureImageDrawable(arrayOfString1[0]));
            myPlanFeature2.setFeature_name(arrayOfString1[1]);
            byte b = 1;
            String str1 = str;
            while (b < arrayOfString.length - 1) {
              StringBuilder stringBuilder1 = new StringBuilder();
              stringBuilder1.append("- ");
              stringBuilder1.append(arrayOfString[b]);
              stringBuilder1.append("\n");
              str1 = str1.concat(stringBuilder1.toString());
              b++;
            } 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("- ");
            stringBuilder.append(arrayOfString[arrayOfString.length - 1]);
            myPlanFeature2.setFeature_description(str1.concat(stringBuilder.toString()));
            return myPlanFeature2;
          } 
        } 
        arrayOfString = null;
      } 
    } 
    return (MyPlanFeature)arrayOfString;
  }
  
  private void onResponseError() {
    if (!this.previousClass.equals(MainActivity.class.getSimpleName())) {
      final Dialog dialog = Utilities.simpleDialog((Context)this, null, this.global_lbl_conexiondered_1, this.global_lbl_acciondescfallared_1, true, this.global_popup_btn_aceptar_1, false, this.global_popup_btn_si_1, false);
      this.btnOK = (Button)dialog.findViewById(2131296343);
      this.btnOK.setOnClickListener(new View.OnClickListener() {
            final MyPlan this$0;
            
            final Dialog val$dialog;
            
            public void onClick(View param1View) {
              dialog.dismiss();
              MyPlan.this.onBackPressed();
            }
          });
      dialog.show();
    } else {
      Toast.makeText(getApplicationContext(), this.global_lbl_acciondescfallared_1, 1).show();
      onBackPressed();
    } 
  }
  
  private void openOriginalRenewalActivity() {
    this.previousClass = OriginalRenewalActivity.class.getSimpleName();
    (new GetRenewalPlansTask((Context)this, new RenewalPlans_Interface() {
          final MyPlan this$0;
          
          public void processFinish(RenewalPlansListResponseO param1RenewalPlansListResponseO) {
            if (param1RenewalPlansListResponseO != null) {
              if (param1RenewalPlansListResponseO.getCpres5() != null && param1RenewalPlansListResponseO.getCpres5().size() > 0) {
                Intent intent = new Intent((Context)MyPlan.this, OriginalRenewalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("service_response", (Serializable)param1RenewalPlansListResponseO);
                bundle.putString("previous_class", MyPlan.class.getSimpleName());
                intent.putExtras(bundle);
                MyPlan.this.startActivity(intent);
              } else {
                MyPlan myPlan = MyPlan.this;
                Toast.makeText((Context)myPlan, myPlan.global_lbl_acciondescfallared_1, 1).show();
              } 
            } else {
              MyPlan myPlan = MyPlan.this;
              Toast.makeText((Context)myPlan, myPlan.global_lbl_acciondescfallared_1, 1).show();
            } 
          }
        })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
  }
  
  private void renewalVehiclesListFunction() {
    Intent intent = new Intent(getApplicationContext(), RenewalVehiclesListActivity.class);
    Bundle bundle = new Bundle();
    intent.addFlags(268435456);
    bundle.putSerializable("almostExpired", (Serializable)this.arraylistVehiclesAlmostExpired);
    bundle.putSerializable("expired", (Serializable)this.arraylistVehiclesExpired);
    bundle.putSerializable("normal", (Serializable)this.arraylistVehiclesNormal);
    intent.putExtras(bundle);
    startActivity(intent);
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    if (paramMotionEvent.getAction() == 0) {
      Intent intent = new Intent();
      intent.setAction("GlobalTouchService");
      intent.putExtra("ACTION_EXTRA", "usuario_activo");
      sendBroadcast(intent);
    } 
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  public void onClick(View paramView) {
    if (GlobalMembers.notificationSpinner && this.totalVehicleExpiredOrAlmost > 1) {
      renewalVehiclesListFunction();
    } else {
      openOriginalRenewalActivity();
    } 
  }
  
  protected void onCreate(Bundle paramBundle) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial onCreate : (Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: invokevirtual getActionBar : ()Landroid/app/ActionBar;
    //   9: ifnull -> 19
    //   12: aload_0
    //   13: invokevirtual getActionBar : ()Landroid/app/ActionBar;
    //   16: invokevirtual hide : ()V
    //   19: aload_0
    //   20: ldc_w 2131427368
    //   23: invokevirtual setContentView : (I)V
    //   26: aload_0
    //   27: new com/roadtrack/onstar/VO/StringsResourcesVO
    //   30: dup
    //   31: invokespecial <init> : ()V
    //   34: putfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   37: aload_0
    //   38: aload_0
    //   39: aload_0
    //   40: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   43: getfield global_popup_btn_si_1 : Ljava/lang/String;
    //   46: ldc_w 2131689952
    //   49: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   52: putfield global_popup_btn_si_1 : Ljava/lang/String;
    //   55: aload_0
    //   56: aload_0
    //   57: aload_0
    //   58: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   61: getfield global_lbl_conexiondered_1 : Ljava/lang/String;
    //   64: ldc_w 2131689912
    //   67: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   70: putfield global_lbl_conexiondered_1 : Ljava/lang/String;
    //   73: aload_0
    //   74: aload_0
    //   75: aload_0
    //   76: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   79: getfield renovacion_lbl_serviciosdisponibles : Ljava/lang/String;
    //   82: ldc_w 2131690315
    //   85: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   88: putfield renovacion_lbl_serviciosdisponibles : Ljava/lang/String;
    //   91: aload_0
    //   92: aload_0
    //   93: aload_0
    //   94: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   97: getfield renovacion_lbl_suplanactuales : Ljava/lang/String;
    //   100: ldc_w 2131690318
    //   103: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   106: putfield renovacion_lbl_suplanactuales : Ljava/lang/String;
    //   109: aload_0
    //   110: aload_0
    //   111: aload_0
    //   112: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   115: getfield global_lbl_acciondescfallared_1 : Ljava/lang/String;
    //   118: ldc_w 2131689862
    //   121: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   124: putfield global_lbl_acciondescfallared_1 : Ljava/lang/String;
    //   127: aload_0
    //   128: aload_0
    //   129: aload_0
    //   130: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   133: getfield global_popup_btn_aceptar_1 : Ljava/lang/String;
    //   136: ldc_w 2131689946
    //   139: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   142: putfield global_popup_btn_aceptar_1 : Ljava/lang/String;
    //   145: aload_0
    //   146: aload_0
    //   147: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   150: getfield global_lbl_accionmiplan_1 : Ljava/lang/String;
    //   153: ldc_w 2131689882
    //   156: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   159: astore_2
    //   160: aload_0
    //   161: aload_0
    //   162: getfield stringsResourcesVO : Lcom/roadtrack/onstar/VO/StringsResourcesVO;
    //   165: getfield miplan_lbl_alterarrenovar : Ljava/lang/String;
    //   168: ldc_w 2131690130
    //   171: invokestatic getStringFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Ljava/lang/String;
    //   174: astore_1
    //   175: aload_0
    //   176: aload_0
    //   177: ldc_w 2131296644
    //   180: invokevirtual findViewById : (I)Landroid/view/View;
    //   183: checkcast android/widget/ImageView
    //   186: putfield imgv_header_image : Landroid/widget/ImageView;
    //   189: aload_0
    //   190: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.ic_onstar_logo_header : Ljava/lang/String;
    //   193: ldc_w 2131165499
    //   196: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   199: astore_3
    //   200: aload_0
    //   201: getfield imgv_header_image : Landroid/widget/ImageView;
    //   204: aload_3
    //   205: invokevirtual setImageDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   208: aload_0
    //   209: aload_0
    //   210: ldc_w 2131296675
    //   213: invokevirtual findViewById : (I)Landroid/view/View;
    //   216: checkcast android/widget/ImageView
    //   219: putfield iv_plan_blue : Landroid/widget/ImageView;
    //   222: aload_0
    //   223: getstatic com/roadtrack/onstar/VO/DrawableResourcesVO.ic_plan_blue : Ljava/lang/String;
    //   226: ldc_w 2131165507
    //   229: invokestatic getDrawableFromConfigList : (Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/drawable/Drawable;
    //   232: astore_3
    //   233: aload_0
    //   234: getfield iv_plan_blue : Landroid/widget/ImageView;
    //   237: aload_3
    //   238: invokevirtual setImageDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   241: aload_0
    //   242: aload_0
    //   243: ldc_w 2131296766
    //   246: invokevirtual findViewById : (I)Landroid/view/View;
    //   249: checkcast android/widget/TextView
    //   252: putfield lbl_plan_name : Landroid/widget/TextView;
    //   255: aload_0
    //   256: aload_0
    //   257: ldc_w 2131296767
    //   260: invokevirtual findViewById : (I)Landroid/view/View;
    //   263: checkcast android/widget/TextView
    //   266: putfield lbl_plan_side_text : Landroid/widget/TextView;
    //   269: aload_0
    //   270: aload_0
    //   271: ldc_w 2131296746
    //   274: invokevirtual findViewById : (I)Landroid/view/View;
    //   277: checkcast android/widget/TextView
    //   280: putfield lbl_free_period : Landroid/widget/TextView;
    //   283: aload_0
    //   284: aload_0
    //   285: ldc_w 2131296769
    //   288: invokevirtual findViewById : (I)Landroid/view/View;
    //   291: checkcast android/widget/TextView
    //   294: putfield lbl_plan_text2 : Landroid/widget/TextView;
    //   297: aload_0
    //   298: aload_0
    //   299: ldc_w 2131296747
    //   302: invokevirtual findViewById : (I)Landroid/view/View;
    //   305: checkcast android/widget/TextView
    //   308: putfield lbl_free_period_date : Landroid/widget/TextView;
    //   311: aload_0
    //   312: aload_0
    //   313: ldc_w 2131296805
    //   316: invokevirtual findViewById : (I)Landroid/view/View;
    //   319: checkcast android/widget/LinearLayout
    //   322: putfield lin_free_period : Landroid/widget/LinearLayout;
    //   325: aload_0
    //   326: aload_0
    //   327: ldc_w 2131296803
    //   330: invokevirtual findViewById : (I)Landroid/view/View;
    //   333: checkcast android/widget/LinearLayout
    //   336: putfield lin_first_column : Landroid/widget/LinearLayout;
    //   339: aload_0
    //   340: aload_0
    //   341: ldc_w 2131296804
    //   344: invokevirtual findViewById : (I)Landroid/view/View;
    //   347: checkcast android/widget/LinearLayout
    //   350: putfield lin_free_column : Landroid/widget/LinearLayout;
    //   353: aload_0
    //   354: aload_0
    //   355: ldc_w 2131296768
    //   358: invokevirtual findViewById : (I)Landroid/view/View;
    //   361: checkcast android/widget/TextView
    //   364: putfield lbl_plan_side_text1 : Landroid/widget/TextView;
    //   367: aload_0
    //   368: aload_0
    //   369: ldc_w 2131296412
    //   372: invokevirtual findViewById : (I)Landroid/view/View;
    //   375: checkcast android/widget/Button
    //   378: putfield btn_alter_my_plan : Landroid/widget/Button;
    //   381: aload_0
    //   382: aload_0
    //   383: ldc_w 2131296717
    //   386: invokevirtual findViewById : (I)Landroid/view/View;
    //   389: checkcast android/widget/TextView
    //   392: putfield lbl_activity_title : Landroid/widget/TextView;
    //   395: aload_0
    //   396: aload_0
    //   397: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   400: checkcast com/roadtrack/onstar/onstarApplication
    //   403: putfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   406: aload_0
    //   407: aload_0
    //   408: aload_0
    //   409: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   412: getfield fontPathLouisRegular : Ljava/lang/String;
    //   415: invokestatic getTypeface : (Landroid/content/Context;Ljava/lang/String;)Landroid/graphics/Typeface;
    //   418: putfield tf : Landroid/graphics/Typeface;
    //   421: aload_0
    //   422: aload_0
    //   423: aload_0
    //   424: getfield rtApp : Lcom/roadtrack/onstar/onstarApplication;
    //   427: getfield fontPathLouisBold : Ljava/lang/String;
    //   430: invokestatic getTypeface : (Landroid/content/Context;Ljava/lang/String;)Landroid/graphics/Typeface;
    //   433: putfield tf2 : Landroid/graphics/Typeface;
    //   436: aload_0
    //   437: getfield lbl_activity_title : Landroid/widget/TextView;
    //   440: aload_0
    //   441: getfield tf : Landroid/graphics/Typeface;
    //   444: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   447: aload_0
    //   448: getfield lbl_activity_title : Landroid/widget/TextView;
    //   451: aload_2
    //   452: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   455: aload_0
    //   456: getfield lbl_plan_name : Landroid/widget/TextView;
    //   459: aload_0
    //   460: getfield tf : Landroid/graphics/Typeface;
    //   463: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   466: aload_0
    //   467: getfield lbl_plan_side_text : Landroid/widget/TextView;
    //   470: aload_0
    //   471: getfield tf : Landroid/graphics/Typeface;
    //   474: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   477: aload_0
    //   478: getfield lbl_plan_side_text : Landroid/widget/TextView;
    //   481: aload_0
    //   482: getfield renovacion_lbl_suplanactuales : Ljava/lang/String;
    //   485: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   488: aload_0
    //   489: getfield lbl_free_period : Landroid/widget/TextView;
    //   492: aload_0
    //   493: getfield tf2 : Landroid/graphics/Typeface;
    //   496: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   499: aload_0
    //   500: getfield lbl_plan_text2 : Landroid/widget/TextView;
    //   503: aload_0
    //   504: getfield tf : Landroid/graphics/Typeface;
    //   507: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   510: aload_0
    //   511: getfield lbl_free_period_date : Landroid/widget/TextView;
    //   514: aload_0
    //   515: getfield tf2 : Landroid/graphics/Typeface;
    //   518: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   521: aload_0
    //   522: getfield lbl_plan_side_text1 : Landroid/widget/TextView;
    //   525: aload_0
    //   526: getfield tf2 : Landroid/graphics/Typeface;
    //   529: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   532: aload_0
    //   533: getfield btn_alter_my_plan : Landroid/widget/Button;
    //   536: aload_0
    //   537: getfield tf : Landroid/graphics/Typeface;
    //   540: invokevirtual setTypeface : (Landroid/graphics/Typeface;)V
    //   543: aload_0
    //   544: getfield btn_alter_my_plan : Landroid/widget/Button;
    //   547: aload_1
    //   548: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   551: aconst_null
    //   552: astore_2
    //   553: aload_0
    //   554: invokevirtual getIntent : ()Landroid/content/Intent;
    //   557: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   560: astore_3
    //   561: aload_3
    //   562: ldc_w 'service_response'
    //   565: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   568: astore_1
    //   569: aload_0
    //   570: aload_3
    //   571: ldc_w 'previous_class'
    //   574: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   577: putfield previousClass : Ljava/lang/String;
    //   580: aload_3
    //   581: ldc_w 'plan_app'
    //   584: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   587: astore_3
    //   588: aload_3
    //   589: astore_2
    //   590: goto -> 600
    //   593: astore_1
    //   594: aconst_null
    //   595: astore_1
    //   596: aload_0
    //   597: invokespecial onResponseError : ()V
    //   600: aload_0
    //   601: aload_1
    //   602: invokespecial fillInformation : (Ljava/lang/String;)V
    //   605: aload_0
    //   606: ldc '1'
    //   608: invokespecial displayFeatures : (Ljava/lang/String;)V
    //   611: aload_0
    //   612: getfield free_info : Ljava/util/List;
    //   615: astore_1
    //   616: aload_1
    //   617: ifnull -> 636
    //   620: aload_1
    //   621: invokeinterface size : ()I
    //   626: ifle -> 636
    //   629: aload_0
    //   630: ldc_w '2'
    //   633: invokespecial displayFeatures : (Ljava/lang/String;)V
    //   636: new com/roadtrack/onstar/DAO/DBFunctions
    //   639: dup
    //   640: aload_0
    //   641: invokespecial <init> : (Landroid/content/Context;)V
    //   644: astore_3
    //   645: new com/roadtrack/onstar/VO/UserPreferenceVO
    //   648: dup
    //   649: invokespecial <init> : ()V
    //   652: pop
    //   653: aload_3
    //   654: getstatic com/roadtrack/onstar/BO/GlobalMembers.userLogged : Ljava/lang/String;
    //   657: invokevirtual getUserPreference : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   660: astore_1
    //   661: aload_0
    //   662: aload_3
    //   663: aload_1
    //   664: invokevirtual getUser : ()Ljava/lang/String;
    //   667: invokevirtual getVehiclesAlmostExpired : (Ljava/lang/String;)Ljava/util/ArrayList;
    //   670: putfield arraylistVehiclesAlmostExpired : Ljava/util/List;
    //   673: aload_0
    //   674: aload_3
    //   675: aload_1
    //   676: invokevirtual getUser : ()Ljava/lang/String;
    //   679: invokevirtual getVehiclesExpired : (Ljava/lang/String;)Ljava/util/ArrayList;
    //   682: putfield arraylistVehiclesExpired : Ljava/util/List;
    //   685: aload_0
    //   686: aload_3
    //   687: aload_1
    //   688: invokevirtual getUser : ()Ljava/lang/String;
    //   691: invokevirtual getVehiclesNormal : (Ljava/lang/String;)Ljava/util/ArrayList;
    //   694: putfield arraylistVehiclesNormal : Ljava/util/List;
    //   697: aload_0
    //   698: aload_0
    //   699: getfield arraylistVehiclesAlmostExpired : Ljava/util/List;
    //   702: invokeinterface size : ()I
    //   707: aload_0
    //   708: getfield arraylistVehiclesExpired : Ljava/util/List;
    //   711: invokeinterface size : ()I
    //   716: iadd
    //   717: aload_0
    //   718: getfield arraylistVehiclesNormal : Ljava/util/List;
    //   721: invokeinterface size : ()I
    //   726: iadd
    //   727: putfield totalVehicleExpiredOrAlmost : I
    //   730: aload_2
    //   731: ifnull -> 775
    //   734: aload_2
    //   735: ldc '1'
    //   737: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   740: ifeq -> 764
    //   743: aload_0
    //   744: getfield btn_alter_my_plan : Landroid/widget/Button;
    //   747: ldc_w 0.4
    //   750: invokevirtual setAlpha : (F)V
    //   753: aload_0
    //   754: getfield btn_alter_my_plan : Landroid/widget/Button;
    //   757: iconst_0
    //   758: invokevirtual setEnabled : (Z)V
    //   761: goto -> 783
    //   764: aload_0
    //   765: getfield btn_alter_my_plan : Landroid/widget/Button;
    //   768: aload_0
    //   769: invokevirtual setOnClickListener : (Landroid/view/View$OnClickListener;)V
    //   772: goto -> 783
    //   775: aload_0
    //   776: getfield btn_alter_my_plan : Landroid/widget/Button;
    //   779: aload_0
    //   780: invokevirtual setOnClickListener : (Landroid/view/View$OnClickListener;)V
    //   783: return
    //   784: astore_3
    //   785: goto -> 596
    // Exception table:
    //   from	to	target	type
    //   553	569	593	java/lang/Exception
    //   569	588	784	java/lang/Exception
  }
  
  protected void onResume() {
    super.onResume();
    if (this.previousClass.equals(OriginalRenewalActivity.class.getSimpleName())) {
      cleanView();
      (new GetMyPlanInformation_Task((Context)this, new AsyncResponse() {
            final MyPlan this$0;
            
            public void processFinish(String param1String) {
              MyPlan.this.fillInformation(param1String);
              MyPlan.this.displayFeatures("1");
              List<MyPlanFeature> list = MyPlan.this.free_info;
              if (list != null && list.size() > 0)
                MyPlan.this.displayFeatures("2"); 
            }
          })).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new Void[0]);
      this.previousClass = "";
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/ui/my_plan/MyPlan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */