package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.entities.RegisterDevice;
import java.util.ArrayList;

public class AdapterRegistrationDevices extends BaseAdapter {
  Context _context;
  
  ArrayList<RegisterDevice> _listDevice;
  
  LinearLayout _llBtnRemove;
  
  boolean _network;
  
  TextView _tvDelet;
  
  public View.OnClickListener clickItem = new View.OnClickListener() {
      final AdapterRegistrationDevices this$0;
      
      public void onClick(View param1View) {
        AdapterRegistrationDevices adapterRegistrationDevices = AdapterRegistrationDevices.this;
        if (adapterRegistrationDevices._network)
          if (adapterRegistrationDevices.listSelected.size() == 0) {
            AdapterRegistrationDevices.this.selectValues(param1View);
          } else if (((RegisterDevice)AdapterRegistrationDevices.this._listDevice.get(Integer.parseInt(param1View.getTag().toString()))).getSelect().booleanValue()) {
            ((RegisterDevice)AdapterRegistrationDevices.this._listDevice.get(Integer.parseInt(param1View.getTag().toString()))).setSelect(Boolean.valueOf(false));
            AdapterRegistrationDevices.this._llBtnRemove.setClickable(false);
            AdapterRegistrationDevices.this._tvDelet.setAlpha(0.5F);
            param1View.setBackgroundColor(ContextCompat.getColor(AdapterRegistrationDevices.this._context, 2131034285));
            AdapterRegistrationDevices.this.listSelected.clear();
          } else {
            adapterRegistrationDevices = AdapterRegistrationDevices.this;
            ((View)adapterRegistrationDevices.listViews.get(adapterRegistrationDevices.pressSave)).setBackgroundColor(ContextCompat.getColor(AdapterRegistrationDevices.this._context, 2131034285));
            adapterRegistrationDevices = AdapterRegistrationDevices.this;
            ((RegisterDevice)adapterRegistrationDevices._listDevice.get(adapterRegistrationDevices.pressSave)).setSelect(Boolean.valueOf(false));
            AdapterRegistrationDevices.this.listSelected.clear();
            AdapterRegistrationDevices.this.selectValues(param1View);
          }  
      }
    };
  
  ArrayList<Boolean> listSelected = new ArrayList<Boolean>();
  
  ArrayList<View> listViews = new ArrayList<View>();
  
  int pressSave = 0;
  
  public AdapterRegistrationDevices(Context paramContext, ArrayList<RegisterDevice> paramArrayList, TextView paramTextView, LinearLayout paramLinearLayout, boolean paramBoolean) {
    this._context = paramContext;
    this._listDevice = paramArrayList;
    this._tvDelet = paramTextView;
    this._llBtnRemove = paramLinearLayout;
    this._network = paramBoolean;
  }
  
  public int getCount() {
    return this._listDevice.size();
  }
  
  public Object getItem(int paramInt) {
    return null;
  }
  
  public long getItemId(int paramInt) {
    return 0L;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    ViewHolder viewHolder = new ViewHolder(this);
    paramView = LayoutInflater.from(this._context).inflate(2131427442, null);
    viewHolder.llItem = (LinearLayout)paramView.findViewById(2131296852);
    viewHolder.tvDevice = (TextView)paramView.findViewById(2131297159);
    paramView.setTag(viewHolder);
    viewHolder.tvDevice.setText(((RegisterDevice)this._listDevice.get(paramInt)).getDeviceName());
    viewHolder.tvDevice.setTextColor(this._context.getResources().getColor(2131034152));
    viewHolder.llItem.setTag(Integer.valueOf(paramInt));
    viewHolder.llItem.setOnClickListener(this.clickItem);
    this.listViews.add(viewHolder.llItem);
    return paramView;
  }
  
  public void selectValues(View paramView) {
    if (((RegisterDevice)this._listDevice.get(Integer.parseInt(paramView.getTag().toString()))).getSelect().booleanValue()) {
      paramView.setBackgroundColor(ContextCompat.getColor(this._context, 2131034285));
      ((RegisterDevice)this._listDevice.get(Integer.parseInt(paramView.getTag().toString()))).setSelect(Boolean.valueOf(false));
    } else {
      paramView.setBackgroundColor(ContextCompat.getColor(this._context, 2131034212));
      ((RegisterDevice)this._listDevice.get(Integer.parseInt(paramView.getTag().toString()))).setSelect(Boolean.valueOf(true));
    } 
    for (byte b = 0; b < this._listDevice.size(); b++) {
      if (((RegisterDevice)this._listDevice.get(b)).getSelect().booleanValue())
        this.listSelected.add(((RegisterDevice)this._listDevice.get(b)).getSelect()); 
    } 
    if (this.listSelected.size() != 0) {
      this._llBtnRemove.setClickable(true);
      this._tvDelet.setAlpha(1.0F);
    } else {
      this._llBtnRemove.setClickable(false);
      this._tvDelet.setAlpha(0.5F);
    } 
    this.pressSave = Integer.parseInt(paramView.getTag().toString());
  }
  
  public class ViewHolder {
    LinearLayout llItem;
    
    TextView tvDevice;
    
    public ViewHolder(AdapterRegistrationDevices this$0) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/AdapterRegistrationDevices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */