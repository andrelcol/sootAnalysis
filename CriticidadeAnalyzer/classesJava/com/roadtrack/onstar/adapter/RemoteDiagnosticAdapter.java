package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.RemoteDiagnosticVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;

public class RemoteDiagnosticAdapter extends BaseAdapter {
  Context context;
  
  List<RemoteDiagnosticVO> items_info = new ArrayList<RemoteDiagnosticVO>();
  
  private onstarApplication rtApp;
  
  private Typeface tf;
  
  public RemoteDiagnosticAdapter(List<RemoteDiagnosticVO> paramList, Context paramContext) {
    this.items_info = paramList;
    this.context = paramContext;
  }
  
  private Drawable getDTCImageDrawable(int paramInt) {
    switch (paramInt) {
      default:
        return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_engine, 2131165464);
      case 8:
        return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_abs, 2131165458);
      case 7:
        return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_traction, 2131165470);
      case 6:
        return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_onstar, 2131165465);
      case 5:
        return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_battery, 2131165461);
      case 4:
        return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_engine, 2131165464);
      case 3:
        return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_emissions, 2131165463);
      case 2:
        return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_electricsystem, 2131165462);
      case 1:
        break;
    } 
    return Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_airbag, 2131165459);
  }
  
  public int getCount() {
    return this.items_info.size();
  }
  
  public Object getItem(int paramInt) {
    return this.items_info.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    View view;
    RemoteDiagnosticHolder remoteDiagnosticHolder;
    LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
    if (paramView == null) {
      RemoteDiagnosticHolder remoteDiagnosticHolder1 = new RemoteDiagnosticHolder(this);
      View view1 = layoutInflater.inflate(2131427426, paramViewGroup, false);
      this.rtApp = (onstarApplication)this.context.getApplicationContext();
      onstarApplication onstarApplication1 = this.rtApp;
      this.tf = onstarApplication.getTypeface(this.context, onstarApplication1.fontPathLouisRegular);
      remoteDiagnosticHolder1.imgv_dtc_group = (ImageView)view1.findViewById(2131296640);
      remoteDiagnosticHolder1.imgv_dtc_status = (ImageView)view1.findViewById(2131296641);
      remoteDiagnosticHolder1.lbl_dtc_group_title = (TextView)view1.findViewById(2131296739);
      remoteDiagnosticHolder1.lbl_dtc_group_description = (TextView)view1.findViewById(2131296738);
      remoteDiagnosticHolder1.lbl_dtc_group_title.setTypeface(this.tf);
      remoteDiagnosticHolder1.lbl_dtc_group_description.setTypeface(this.tf);
      remoteDiagnosticHolder = remoteDiagnosticHolder1;
      view = view1;
    } else {
      remoteDiagnosticHolder = (RemoteDiagnosticHolder)view.getTag();
    } 
    view.setTag(remoteDiagnosticHolder);
    remoteDiagnosticHolder.imgv_dtc_group.setImageDrawable(getDTCImageDrawable(((RemoteDiagnosticVO)this.items_info.get(paramInt)).getDtcGroupId()));
    int i = null.$SwitchMap$com$roadtrack$onstar$Enums$dtcStatus[Enums.dtcStatus.getValue(((RemoteDiagnosticVO)this.items_info.get(paramInt)).getDtcStatusId()).ordinal()];
    if (i != 1) {
      if (i != 2) {
        if (i == 3) {
          Drawable drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_status_warning, 2131165469);
          remoteDiagnosticHolder.imgv_dtc_status.setImageDrawable(drawable);
        } 
      } else {
        Drawable drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_status_ok, 2131165468);
        remoteDiagnosticHolder.imgv_dtc_status.setImageDrawable(drawable);
      } 
    } else {
      Drawable drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.ic_dtc_status_error, 2131165467);
      remoteDiagnosticHolder.imgv_dtc_status.setImageDrawable(drawable);
    } 
    remoteDiagnosticHolder.lbl_dtc_group_title.setText(((RemoteDiagnosticVO)this.items_info.get(paramInt)).getDtcTitle());
    remoteDiagnosticHolder.lbl_dtc_group_description.setText(((RemoteDiagnosticVO)this.items_info.get(paramInt)).getDtcDescription());
    return view;
  }
  
  public class RemoteDiagnosticHolder {
    public ImageView imgv_dtc_group;
    
    public ImageView imgv_dtc_status;
    
    public TextView lbl_dtc_group_description;
    
    public TextView lbl_dtc_group_title;
    
    public RemoteDiagnosticHolder(RemoteDiagnosticAdapter this$0) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/RemoteDiagnosticAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */