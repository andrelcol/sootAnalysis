package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class VehicleSpinnerAdapterWhite extends ArrayAdapter<String> {
  private Context mContext;
  
  private int mlayoutItem;
  
  private Spinner mspinnerInfo;
  
  private int mtvItem;
  
  private List<String> mvehicleList;
  
  public VehicleSpinnerAdapterWhite(Context paramContext, int paramInt1, int paramInt2, Spinner paramSpinner, List<String> paramList) {
    super(paramContext, paramInt1, paramList);
    this.mContext = paramContext;
    this.mlayoutItem = paramInt1;
    this.mtvItem = paramInt2;
    this.mvehicleList = paramList;
    this.mspinnerInfo = paramSpinner;
  }
  
  public View getCustomView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    paramView = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(this.mlayoutItem, paramViewGroup, false);
    TextView textView = (TextView)paramView.findViewById(this.mtvItem);
    textView.setText(this.mvehicleList.get(paramInt));
    textView.setTextColor(this.mContext.getResources().getColor(2131034285));
    if (paramInt == this.mspinnerInfo.getSelectedItemPosition())
      textView.setTextColor(this.mContext.getResources().getColor(2131034285)); 
    ((ImageView)paramView.findViewById(2131296677)).setImageDrawable(Utilities.getDrawableFromConfigList(this.mContext, DrawableResourcesVO.vehicle_leftspinner, 2131165704));
    return paramView;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    return getCustomView(paramInt, paramView, paramViewGroup);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    return getCustomView(paramInt, paramView, paramViewGroup);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/VehicleSpinnerAdapterWhite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */