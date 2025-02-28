package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class VehiculeSpinnerAdapter extends ArrayAdapter<String> {
  private Context mContext;
  
  private int mivItem;
  
  private int mlayoutItem;
  
  private Spinner mspinnerInfo;
  
  private int mtvItem;
  
  private List<String> mvehicleList;
  
  private int spinnerType;
  
  private List<VehicleCatalogVO> vehicleCatalogVOs;
  
  public VehiculeSpinnerAdapter(Context paramContext, int paramInt1, int paramInt2, int paramInt3, Spinner paramSpinner, List<String> paramList) {
    super(paramContext, paramInt1, paramList);
    this.mContext = paramContext;
    this.mlayoutItem = paramInt1;
    this.mtvItem = paramInt2;
    this.mivItem = paramInt3;
    this.mvehicleList = paramList;
    this.mspinnerInfo = paramSpinner;
    this.spinnerType = 0;
  }
  
  public View getCustomView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    paramView = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(this.mlayoutItem, paramViewGroup, false);
    TextView textView = (TextView)paramView.findViewById(this.mtvItem);
    ImageView imageView = (ImageView)paramView.findViewById(this.mivItem);
    textView.setText(this.mvehicleList.get(paramInt));
    textView.setTextColor(this.mContext.getResources().getColor(2131034152));
    if (paramInt == this.mspinnerInfo.getSelectedItemPosition())
      textView.setTextColor(this.mContext.getResources().getColor(2131034152)); 
    int i = this.spinnerType;
    if (i != 0) {
      if (i == 1) {
        UserDevicesVO userDevicesVO = ((onstarApplication)this.mContext.getApplicationContext()).getmDeviceUserList().get(paramInt);
        List<VehicleCatalogVO> list = this.vehicleCatalogVOs;
        if (list != null && list.size() == this.mvehicleList.size()) {
          if (!((VehicleCatalogVO)this.vehicleCatalogVOs.get(paramInt)).getStatus_renewal_account().equals(Enums.statusRenewalAccount.Normal.toString()) && !userDevicesVO.getSeguroApp().equals("1")) {
            imageView.setImageDrawable(Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.ic_selector_spinner, 2131165515));
            imageView.setVisibility(0);
          } else {
            imageView.setImageDrawable(Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.vehicle_leftspinner, 2131165704));
            imageView.setVisibility(8);
          } 
        } else {
          imageView.setVisibility(8);
        } 
      } 
    } else {
      imageView.setVisibility(8);
    } 
    return paramView;
  }
  
  public View getCustomView_down(int paramInt, View paramView, ViewGroup paramViewGroup) {
    paramView = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(this.mlayoutItem, paramViewGroup, false);
    TextView textView = (TextView)paramView.findViewById(this.mtvItem);
    textView.setText(this.mvehicleList.get(paramInt));
    textView.setTextColor(this.mContext.getResources().getColor(2131034152));
    textView.setCompoundDrawables(null, null, null, null);
    if (paramInt == this.mspinnerInfo.getSelectedItemPosition())
      textView.setTextColor(this.mContext.getResources().getColor(2131034152)); 
    if (this.spinnerType == 1) {
      ImageView imageView = (ImageView)paramView.findViewById(this.mivItem);
      UserDevicesVO userDevicesVO = ((onstarApplication)this.mContext.getApplicationContext()).getmDeviceUserList().get(paramInt);
      List<VehicleCatalogVO> list = this.vehicleCatalogVOs;
      if (list != null && list.size() == this.mvehicleList.size())
        if (!((VehicleCatalogVO)this.vehicleCatalogVOs.get(paramInt)).getStatus_renewal_account().equals(Enums.statusRenewalAccount.Normal.toString()) && !userDevicesVO.getSeguroApp().equals("1")) {
          imageView.setImageDrawable(Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.ic_spinner_renewal, 2131165519));
        } else {
          imageView.setImageDrawable(Utilities.getDrawableFromConfigList(getContext(), DrawableResourcesVO.vehicle_leftspinner, 2131165704));
        }  
    } 
    return paramView;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    return getCustomView_down(paramInt, paramView, paramViewGroup);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    return getCustomView(paramInt, paramView, paramViewGroup);
  }
  
  public void setDropDownViewResource(int paramInt) {
    super.setDropDownViewResource(paramInt);
  }
  
  public void setSpinnerType(int paramInt) {
    this.spinnerType = paramInt;
  }
  
  public void setVehicleCatalogVOs(List<VehicleCatalogVO> paramList) {
    this.vehicleCatalogVOs = paramList;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/VehiculeSpinnerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */