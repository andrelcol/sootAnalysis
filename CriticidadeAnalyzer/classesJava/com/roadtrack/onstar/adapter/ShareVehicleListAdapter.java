package com.roadtrack.onstar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.onstarApplication;
import java.util.LinkedList;

public class ShareVehicleListAdapter extends BaseAdapter {
  Context context;
  
  String getDescription = "";
  
  String lastDateFindme = "";
  
  public LinkedList<UserDevicesVO> listVehiclesShare;
  
  LayoutInflater mInflater;
  
  private onstarApplication rtApp;
  
  private Typeface tf;
  
  TextView txtDescription;
  
  TextView txtPositionDescription;
  
  public ShareVehicleListAdapter(Context paramContext, LinkedList<UserDevicesVO> paramLinkedList, int paramInt) {
    this.context = paramContext;
    this.listVehiclesShare = paramLinkedList;
  }
  
  public int getCount() {
    return this.listVehiclesShare.size();
  }
  
  public Object getItem(int paramInt) {
    return this.listVehiclesShare.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  @SuppressLint({"ResourceAsColor"})
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    this.mInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
    this.getDescription = ((UserDevicesVO)this.listVehiclesShare.get(paramInt)).getName();
    ((UserDevicesVO)this.listVehiclesShare.get(paramInt)).getDeviceId();
    this.lastDateFindme = ((UserDevicesVO)this.listVehiclesShare.get(paramInt)).getDateLastFindeMe();
    this.rtApp = (onstarApplication)this.context.getApplicationContext();
    onstarApplication onstarApplication1 = this.rtApp;
    this.tf = onstarApplication.getTypeface(this.context, onstarApplication1.fontPathLouisRegular);
    String str = this.lastDateFindme;
    if (str != null && !str.equals("")) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    View view = this.mInflater.inflate(2131427511, null);
    this.txtDescription = (TextView)view.findViewById(2131297228);
    this.txtPositionDescription = (TextView)view.findViewById(2131297145);
    this.txtDescription.setTypeface(this.tf);
    this.txtPositionDescription.setTypeface(this.tf);
    this.txtDescription.setText(this.getDescription);
    if (paramInt != 0) {
      this.txtPositionDescription.setText(this.lastDateFindme);
      this.txtPositionDescription.setVisibility(0);
    } else {
      this.txtPositionDescription.setVisibility(8);
    } 
    return view;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/ShareVehicleListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */