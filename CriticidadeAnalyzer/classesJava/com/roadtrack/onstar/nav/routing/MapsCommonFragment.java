package com.roadtrack.onstar.nav.routing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.ManagerBluetooth;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;

public class MapsCommonFragment extends FragmentActivity implements FavoritesHistory.FavoritesHistoryListener {
  private static final String TAG = MapsCommonFragment.class.getSimpleName();
  
  private String KEY_SPINNER_POSITION = "spinnerposition";
  
  private LinearLayout edit_favoritespopup;
  
  protected Bundle mSavedInstanceState;
  
  private void changeFragment(Fragment paramFragment) {
    try {
      if (this.mSavedInstanceState == null) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(2131296549, paramFragment);
        fragmentTransaction.commit();
      } 
      ManagerBluetooth.updateContext((Context)this);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: changeFragment", exception.getMessage());
    } 
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
    if (paramMotionEvent.getAction() == 0) {
      Intent intent = new Intent();
      intent.setAction("GlobalTouchService");
      intent.putExtra("ACTION_EXTRA", "usuario_activo_map");
      sendBroadcast(intent);
    } 
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  public void finish() {
    DBFunctions dBFunctions = new DBFunctions(GlobalMembers.contexGlobal);
    onstarApplication onstarApplication = (onstarApplication)getApplicationContext();
    Intent intent = new Intent();
    intent.putExtra(this.KEY_SPINNER_POSITION, Utilities.getLastKnownVehicleSelected(getApplicationContext(), dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), onstarApplication));
    setResult(-1, intent);
    super.finish();
  }
  
  public void onChildClick(FavoritesHistoryVO paramFavoritesHistoryVO) {
    GlobalMembers.objSendFavoritesMessage = new Object();
    GlobalMembers.objSendFavoritesMessage = paramFavoritesHistoryVO;
    finish();
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.mSavedInstanceState = paramBundle;
    setContentView(2131427434);
    getActionBar().hide();
    Intent intent = new Intent();
    intent.setAction("GlobalTouchService");
    intent.putExtra("ACTION_EXTRA", "usuario_activo_map");
    sendBroadcast(intent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    LinearLayout linearLayout;
    if (paramInt == 4) {
      this.edit_favoritespopup = (LinearLayout)findViewById(2131296507);
      linearLayout = this.edit_favoritespopup;
      if (linearLayout != null && linearLayout.getVisibility() == 0) {
        this.edit_favoritespopup.setVisibility(8);
        return false;
      } 
      finish();
      return true;
    } 
    return super.onKeyUp(paramInt, (KeyEvent)linearLayout);
  }
  
  protected void onPause() {
    super.onPause();
  }
  
  public void onResume() {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/nav/routing/MapsCommonFragment}} */
    try {
      super.onResume();
      ManagerBluetooth.updateContext((Context)this);
    } catch (Exception exception) {
      Utilities.escribeArchivo(TAG, "Error: onResume", exception.getMessage());
    } finally {
      Exception exception;
    } 
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/nav/routing/MapsCommonFragment}} */
  }
  
  public void onResumeFragments() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial onResumeFragments : ()V
    //   6: aload_0
    //   7: invokevirtual getIntent : ()Landroid/content/Intent;
    //   10: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   13: astore_1
    //   14: aload_0
    //   15: ldc 2131296549
    //   17: invokevirtual findViewById : (I)Landroid/view/View;
    //   20: ifnull -> 50
    //   23: aload_1
    //   24: ifnonnull -> 31
    //   27: aload_0
    //   28: invokevirtual finish : ()V
    //   31: invokestatic newInstance : ()Lcom/roadtrack/onstar/nav/routing/FavoritesHistory;
    //   34: astore_2
    //   35: aload_2
    //   36: aload_0
    //   37: invokevirtual setListener : (Lcom/roadtrack/onstar/nav/routing/MapsCommonFragment;)V
    //   40: aload_2
    //   41: aload_1
    //   42: invokevirtual setArguments : (Landroid/os/Bundle;)V
    //   45: aload_0
    //   46: aload_2
    //   47: invokespecial changeFragment : (Landroidx/fragment/app/Fragment;)V
    //   50: aload_0
    //   51: monitorexit
    //   52: return
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	53	finally
    //   27	31	53	finally
    //   31	50	53	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/nav/routing/MapsCommonFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */