package com.roadtrack.onstar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.adapter.HistoryFullAdapter;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import com.roadtrack.onstar.ui.settings.SettingsNewActivity;
import com.roadtrack.onstar.utils.DialogEmpty;
import com.roadtrack.onstar.utils.Utilities;

public class HistoricalTestActivity extends FragmentActivity implements FragmentPager.ListClick {
  private static int NUM_PAGES = 3;
  
  private static String TAG = "HistoricalTestActivity";
  
  private static onstarApplication rtApp;
  
  private static Spinner spinner_menu;
  
  private TextView ActionValueMessage;
  
  private String KEY_EXIT = "exitApp";
  
  private String KEY_SPINNER_POSITION = "spinnerposition";
  
  private DBFunctions dbFun;
  
  private boolean exitApp = false;
  
  private IntentFilter followmefinish;
  
  private BroadcastReceiver followmefinishmyReceiver = new BroadcastReceiver() {
      final HistoricalTestActivity this$0;
      
      public void onReceive(Context param1Context, Intent param1Intent) {
        if (param1Intent.getExtras().getString("parameter").equals("show"))
          HistoricalTestActivity.this.followmefinisheddialog(); 
      }
    };
  
  private boolean isCheckedR1 = false;
  
  private boolean isCheckedR2 = false;
  
  private boolean isCheckedR3 = false;
  
  private ViewPager mPager;
  
  private PagerAdapter mPagerAdapter;
  
  private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
      final HistoricalTestActivity this$0;
      
      public void onReceive(Context param1Context, Intent param1Intent) {
        Utilities.showNetworkServiceData((TextView)HistoricalTestActivity.this.findViewById(2131296593), (Context)HistoricalTestActivity.this, new TextView[0]);
      }
    };
  
  private RadioButton rb1;
  
  private RadioButton rb2;
  
  private RadioButton rb3;
  
  private StringsResourcesVO stringsResourcesVO = null;
  
  private PagerTabStrip strip;
  
  private Typeface typeface;
  
  private void fillVehicleList(Spinner paramSpinner, Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: checkcast com/roadtrack/onstar/onstarApplication
    //   6: astore #5
    //   8: aload_1
    //   9: astore #4
    //   11: aload_1
    //   12: ifnonnull -> 27
    //   15: getstatic com/roadtrack/onstar/HistoricalTestActivity.spinner_menu : Landroid/widget/Spinner;
    //   18: astore #4
    //   20: goto -> 27
    //   23: astore_1
    //   24: goto -> 167
    //   27: aload #4
    //   29: ifnonnull -> 35
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: new java/util/ArrayList
    //   38: astore_1
    //   39: aload_1
    //   40: invokespecial <init> : ()V
    //   43: iconst_0
    //   44: istore_3
    //   45: iload_3
    //   46: aload #5
    //   48: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   51: invokeinterface size : ()I
    //   56: if_icmpge -> 89
    //   59: aload_1
    //   60: aload #5
    //   62: invokevirtual getmDeviceUserList : ()Ljava/util/List;
    //   65: iload_3
    //   66: invokeinterface get : (I)Ljava/lang/Object;
    //   71: checkcast com/roadtrack/onstar/VO/UserDevicesVO
    //   74: invokevirtual getName : ()Ljava/lang/String;
    //   77: invokeinterface add : (Ljava/lang/Object;)Z
    //   82: pop
    //   83: iinc #3, 1
    //   86: goto -> 45
    //   89: aload_0
    //   90: aload_0
    //   91: getfield dbFun : Lcom/roadtrack/onstar/DAO/DBFunctions;
    //   94: getstatic com/roadtrack/onstar/BO/GlobalMembers.userLogged : Ljava/lang/String;
    //   97: invokevirtual getUserPreference : (Ljava/lang/String;)Lcom/roadtrack/onstar/VO/UserPreferenceVO;
    //   100: invokevirtual getUser : ()Ljava/lang/String;
    //   103: aload #5
    //   105: invokestatic getLastKnownVehicleSelected : (Landroid/content/Context;Ljava/lang/String;Lcom/roadtrack/onstar/onstarApplication;)I
    //   108: istore_3
    //   109: new com/roadtrack/onstar/adapter/VehiculeSpinnerAdapter
    //   112: astore #6
    //   114: aload #6
    //   116: aload_2
    //   117: ldc 2131427512
    //   119: ldc 2131297225
    //   121: ldc 2131297226
    //   123: getstatic com/roadtrack/onstar/HistoricalTestActivity.spinner_menu : Landroid/widget/Spinner;
    //   126: aload_1
    //   127: invokespecial <init> : (Landroid/content/Context;IIILandroid/widget/Spinner;Ljava/util/List;)V
    //   130: aload #4
    //   132: aload #6
    //   134: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   137: aload #4
    //   139: iload_3
    //   140: invokevirtual setSelection : (I)V
    //   143: aload_2
    //   144: invokestatic setDeviceType : (Landroid/content/Context;)V
    //   147: new com/roadtrack/onstar/HistoricalTestActivity$3
    //   150: astore_1
    //   151: aload_1
    //   152: aload_0
    //   153: aload #5
    //   155: invokespecial <init> : (Lcom/roadtrack/onstar/HistoricalTestActivity;Lcom/roadtrack/onstar/onstarApplication;)V
    //   158: aload #4
    //   160: aload_1
    //   161: invokevirtual setOnItemSelectedListener : (Landroid/widget/AdapterView$OnItemSelectedListener;)V
    //   164: goto -> 179
    //   167: getstatic com/roadtrack/onstar/HistoricalTestActivity.TAG : Ljava/lang/String;
    //   170: ldc 'Error: fillVehicleList '
    //   172: aload_1
    //   173: invokevirtual getMessage : ()Ljava/lang/String;
    //   176: invokestatic escribeArchivo : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   179: aload_0
    //   180: monitorexit
    //   181: return
    //   182: astore_1
    //   183: aload_0
    //   184: monitorexit
    //   185: aload_1
    //   186: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	182	finally
    //   15	20	23	java/lang/Exception
    //   15	20	182	finally
    //   35	43	23	java/lang/Exception
    //   35	43	182	finally
    //   45	83	23	java/lang/Exception
    //   45	83	182	finally
    //   89	164	23	java/lang/Exception
    //   89	164	182	finally
    //   167	179	182	finally
  }
  
  private void followmefinisheddialog() {
    Utilities.escribeArchivo(TAG, "FOLLOWME", "FOLLOWME FINISHED: Mostrando dialogo");
    (new DialogEmpty((Activity)this, Utilities.getStringFromConfigList((Context)this, this.stringsResourcesVO.global_lbl_siguemeterminado_1, 2131689934), true)).show();
  }
  
  @SuppressLint({"NewApi"})
  private void reloadFragmentView() {
    this.mPager.setAdapter(null);
    this.mPager.setAdapter(this.mPagerAdapter);
    this.mPagerAdapter.notifyDataSetChanged();
  }
  
  private static void setDeviceType(Context paramContext) {
    String str = Utilities.getLastKnownDeviceSelected((onstarApplication)paramContext, TAG).getDeviceTypeId();
    if (str != null && !str.equals("")) {
      GlobalMembers.deviceType = str;
    } else {
      GlobalMembers.deviceType = GlobalMembers.deviceTypeP7;
    } 
  }
  
  private void setVehicleSelected() {
    UserDevicesVO userDevicesVO2 = Utilities.getLastKnownDeviceSelected(rtApp, TAG);
    UserDevicesVO userDevicesVO1 = rtApp.getmDeviceUserList().get(spinner_menu.getSelectedItemPosition());
    this.dbFun.addVehicleSelected(getApplicationContext(), this.dbFun.getUserPreference(GlobalMembers.userLogged).getUser(), userDevicesVO1);
    if (!userDevicesVO1.equals(userDevicesVO2)) {
      Utilities.updateVehicleSelected(getApplicationContext(), this.dbFun.getUserPreference(GlobalMembers.userLogged).getUser(), userDevicesVO1);
      int i = Utilities.getLastKnownVehicleSelected((Context)this, this.dbFun.getUserPreference(GlobalMembers.userLogged).getUser(), rtApp);
      spinner_menu.setSelection(i);
      RemoteDiagnosticActivity.isdialog_action_process_frame_visible = false;
    } 
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
  
  public void finish() {
    Intent intent = new Intent();
    intent.putExtra(this.KEY_SPINNER_POSITION, Utilities.getLastKnownVehicleSelected(getApplicationContext(), this.dbFun.getUserPreference(GlobalMembers.userLogged).getUser(), rtApp));
    if (this.exitApp)
      intent.putExtra(this.KEY_EXIT, "EXIT"); 
    setResult(-1, intent);
    super.finish();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(2131427420);
    new StringsResourcesVO();
    this.followmefinish = new IntentFilter();
    this.followmefinish.addAction("SHOWDIALOGFOLLOWMEFINISH");
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    rtApp = (onstarApplication)getApplicationContext();
    this.typeface = onstarApplication.getTypeface((Context)this, rtApp.fontPathLouisRegular);
    rtApp = (onstarApplication)getApplicationContext();
    this.strip = PagerTabStrip.class.cast(findViewById(2131296945));
    this.strip.setBackgroundColor(-16777216);
    this.strip.setNonPrimaryAlpha(0.5F);
    this.strip.setTabIndicatorColor(Color.rgb(231, 174, 24));
    this.strip.setTextColor(-1);
    this.strip.setGravity(3);
    this.strip.setTextSpacing(25);
    this.strip.setTextSize(1, 16.0F);
    this.mPager = (ViewPager)findViewById(2131296944);
    this.mPagerAdapter = (PagerAdapter)new ScreenTabPagerAdapter(getSupportFragmentManager());
    this.mPager.setAdapter(this.mPagerAdapter);
    this.ActionValueMessage = (TextView)findViewById(2131296257);
    this.ActionValueMessage.setTypeface(this.typeface);
    String str2 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_navigation_fav_lbl_historial_1, 2131689942);
    this.ActionValueMessage.setText(str2);
    this.dbFun = new DBFunctions((Context)this);
    this.dbFun.updateHistoricalOrphans();
    View.OnClickListener onClickListener = new View.OnClickListener() {
        final HistoricalTestActivity this$0;
        
        public void onClick(View param1View) {
          int i = ((RadioButton)param1View).getId();
          if (i != 2131296391) {
            if (i != 2131296400) {
              if (i == 2131296411)
                HistoricalTestActivity.this.mPager.setCurrentItem(0, true); 
            } else {
              if (GlobalMembers.deleteIndividual) {
                HistoricalTestActivity.this.mPager.setAdapter(null);
                HistoricalTestActivity.this.mPager.setAdapter(HistoricalTestActivity.this.mPagerAdapter);
                GlobalMembers.deleteIndividual = false;
              } 
              HistoricalTestActivity.this.mPager.setCurrentItem(1, true);
            } 
          } else {
            HistoricalTestActivity.this.mPager.setCurrentItem(2, true);
          } 
        }
      };
    this.rb1 = (RadioButton)findViewById(2131296411);
    this.rb1.setOnClickListener(onClickListener);
    this.rb1.setTypeface(this.typeface);
    this.rb2 = (RadioButton)findViewById(2131296400);
    this.rb2.setOnClickListener(onClickListener);
    this.rb2.setTypeface(this.typeface);
    this.rb3 = (RadioButton)findViewById(2131296391);
    this.rb3.setOnClickListener(onClickListener);
    this.rb3.setTypeface(this.typeface);
    String str1 = Utilities.getStringFromConfigList((Context)this, stringsResourcesVO.global_lbl_navegacion_1, 2131689929);
    this.rb3.setText(str1);
    if (paramBundle != null) {
      this.rb1.setChecked(this.isCheckedR1);
      this.rb2.setChecked(this.isCheckedR2);
      this.rb3.setChecked(this.isCheckedR3);
    } else {
      this.rb1.setChecked(true);
    } 
    registerReceiver(this.networkChangeReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  @SuppressLint({"NewApi"})
  public boolean onCreateOptionsMenu(Menu paramMenu) {
    getMenuInflater().inflate(2131492873, paramMenu);
    ActionBar actionBar = getActionBar();
    actionBar.setCustomView(2131427356);
    RelativeLayout relativeLayout = (RelativeLayout)actionBar.getCustomView();
    actionBar.setDisplayOptions(18);
    actionBar.setDisplayShowHomeEnabled(false);
    actionBar.setDisplayShowTitleEnabled(false);
    spinner_menu = (Spinner)relativeLayout.getChildAt(0);
    fillVehicleList(spinner_menu, getApplicationContext());
    setVehicleSelected();
    return true;
  }
  
  protected void onDestroy() {
    super.onDestroy();
    BroadcastReceiver broadcastReceiver = this.networkChangeReceiver;
    if (broadcastReceiver != null)
      unregisterReceiver(broadcastReceiver); 
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
    Intent intent;
    switch (paramMenuItem.getItemId()) {
      default:
        return super.onOptionsItemSelected(paramMenuItem);
      case 2131296913:
        startActivity(new Intent((Context)this, SettingsNewActivity.class));
        return true;
      case 2131296910:
        this.exitApp = true;
        finish();
        intent = new Intent((Context)this, MainActivity.class);
        intent.addFlags(67108864);
        intent.putExtra("exitApp", this.exitApp);
        startActivity(intent);
        return true;
      case 2131296907:
        startActivity(new Intent((Context)this, NotificationsActivity.class));
        return true;
      case 16908332:
        break;
    } 
    Toast.makeText((Context)rtApp, "TEST", 0).show();
    return true;
  }
  
  protected void onPause() {
    super.onPause();
    unregisterReceiver(this.followmefinishmyReceiver);
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu) {
    return true;
  }
  
  protected void onResume() {
    super.onResume();
    registerReceiver(this.followmefinishmyReceiver, this.followmefinish);
    this.mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          final HistoricalTestActivity this$0;
          
          public void onPageScrollStateChanged(int param1Int) {}
          
          public void onPageScrolled(int param1Int1, float param1Float, int param1Int2) {}
          
          public void onPageSelected(int param1Int) {
            HistoryFullAdapter.handlerFinishing.sendEmptyMessage(0);
            if (param1Int != 0) {
              if (param1Int != 1) {
                if (param1Int == 2) {
                  HistoricalTestActivity.this.rb1.setChecked(false);
                  HistoricalTestActivity.this.rb2.setChecked(false);
                  HistoricalTestActivity.this.rb3.setChecked(true);
                } 
              } else {
                HistoricalTestActivity.this.rb1.setChecked(false);
                HistoricalTestActivity.this.rb2.setChecked(true);
                HistoricalTestActivity.this.rb3.setChecked(false);
                if (GlobalMembers.deleteAll) {
                  HistoricalTestActivity.this.reloadFragmentView();
                  GlobalMembers.deleteAll = false;
                  HistoricalTestActivity.this.mPager.setCurrentItem(1);
                } 
                if (GlobalMembers.deleteIndividual) {
                  HistoricalTestActivity.this.reloadFragmentView();
                  GlobalMembers.deleteIndividual = false;
                  HistoricalTestActivity.this.mPager.setCurrentItem(1);
                } 
              } 
            } else {
              HistoricalTestActivity.this.rb1.setChecked(true);
              HistoricalTestActivity.this.rb2.setChecked(false);
              HistoricalTestActivity.this.rb3.setChecked(false);
            } 
          }
        });
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    paramBundle.putBoolean(GlobalMembers.COMPLETE_HISTORICAL, this.isCheckedR1);
    paramBundle.putBoolean(GlobalMembers.ACTIONS_HISTORICAL, this.isCheckedR2);
    paramBundle.putBoolean(GlobalMembers.NAVIGATION_HISTORICAL, this.isCheckedR3);
    super.onSaveInstanceState(paramBundle);
  }
  
  public class ScreenTabPagerAdapter extends FragmentStatePagerAdapter {
    private FragmentManager mFragmentManager;
    
    final HistoricalTestActivity this$0;
    
    public ScreenTabPagerAdapter(FragmentManager param1FragmentManager) {
      super(param1FragmentManager);
      this.mFragmentManager = param1FragmentManager;
    }
    
    private String makeFragmentName(int param1Int1, int param1Int2) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("android:switcher:");
      stringBuilder.append(param1Int1);
      stringBuilder.append(":");
      stringBuilder.append(param1Int2);
      return stringBuilder.toString();
    }
    
    public void destroyItem(View param1View, int param1Int, Object param1Object) {
      ((ViewPager)param1View).removeView((View)param1Object);
    }
    
    public int getCount() {
      return HistoricalTestActivity.NUM_PAGES;
    }
    
    public Fragment getItem(int param1Int) {
      String str = makeFragmentName(2131296944, HistoricalTestActivity.this.mPager.getCurrentItem());
      Fragment fragment2 = this.mFragmentManager.findFragmentByTag(str);
      Fragment fragment1 = fragment2;
      if (fragment2 == null)
        fragment1 = FragmentPager.create(param1Int, HistoricalTestActivity.this); 
      return fragment1;
    }
    
    public int getItemPosition(Object param1Object) {
      return -2;
    }
    
    public CharSequence getPageTitle(int param1Int) {
      String str;
      if (param1Int == 0) {
        str = "Todos";
      } else {
        str = null;
      } 
      if (param1Int == 1)
        str = "Acciones"; 
      if (param1Int == 2)
        str = "Navegacion"; 
      if (param1Int == 3)
        str = "Citas"; 
      return str;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/HistoricalTestActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */