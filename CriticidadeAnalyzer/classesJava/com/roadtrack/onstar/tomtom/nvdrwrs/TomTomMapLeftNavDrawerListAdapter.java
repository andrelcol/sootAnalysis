package com.roadtrack.onstar.tomtom.nvdrwrs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.adapter.VehicleSpinnerAdapterWhite;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;

public class TomTomMapLeftNavDrawerListAdapter extends BaseAdapter {
  private Context context;
  
  private Spinner mSpinner;
  
  private ArrayList<TomTomMapLeftNavDrawerItem> navDrawerItems;
  
  public TomTomMapLeftNavDrawerListAdapter(Context paramContext, ArrayList<TomTomMapLeftNavDrawerItem> paramArrayList) {
    this.context = paramContext;
    this.navDrawerItems = paramArrayList;
  }
  
  public int getCount() {
    return this.navDrawerItems.size();
  }
  
  public Object getItem(int paramInt) {
    return this.navDrawerItems.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public Spinner getSpinner() {
    return this.mSpinner;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    View view = paramView;
    if (paramView == null)
      view = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2131427501, null); 
    ImageView imageView = (ImageView)view.findViewById(2131296599);
    TextView textView2 = (TextView)view.findViewById(2131297124);
    TextView textView1 = (TextView)view.findViewById(2131296483);
    Spinner spinner = (Spinner)view.findViewById(2131297224);
    onstarApplication onstarApplication = (onstarApplication)this.context;
    onstarApplication.setAppStatus(1);
    imageView.setImageDrawable(((TomTomMapLeftNavDrawerItem)this.navDrawerItems.get(paramInt)).getIconImage());
    textView2.setText(((TomTomMapLeftNavDrawerItem)this.navDrawerItems.get(paramInt)).getTitle());
    LinearLayout linearLayout = (LinearLayout)view.findViewById(2131296845);
    ArrayList<String> arrayList = new ArrayList();
    for (byte b = 0; b < onstarApplication.getmDeviceUserList().size(); b++)
      arrayList.add(((UserDevicesVO)onstarApplication.getmDeviceUserList().get(b)).getName()); 
    if (((TomTomMapLeftNavDrawerItem)this.navDrawerItems.get(paramInt)).getCounterVisibility()) {
      textView1.setText(((TomTomMapLeftNavDrawerItem)this.navDrawerItems.get(paramInt)).getCount());
    } else {
      textView1.setVisibility(8);
    } 
    if (paramInt == 3) {
      imageView.setVisibility(8);
      textView2.setVisibility(8);
      linearLayout.setVisibility(8);
      spinner.setAdapter((SpinnerAdapter)new VehicleSpinnerAdapterWhite(this.context, 2131427513, 2131297225, spinner, arrayList));
      DBFunctions dBFunctions = new DBFunctions(GlobalMembers.contexGlobal);
      spinner.setSelection(Utilities.getLastKnownVehicleSelected(GlobalMembers.contexGlobal, dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), onstarApplication));
      spinner.setSelection(Utilities.getLastKnownVehicleSelected((Context)onstarApplication, dBFunctions.getUserPreference(GlobalMembers.userLogged).getUser(), onstarApplication));
      this.mSpinner = spinner;
    } else {
      spinner.setVisibility(8);
    } 
    return view;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/tomtom/nvdrwrs/TomTomMapLeftNavDrawerListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */