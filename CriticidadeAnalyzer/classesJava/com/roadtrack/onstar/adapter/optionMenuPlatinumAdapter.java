package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.roadtrack.onstar.onstarApplication;

public class optionMenuPlatinumAdapter extends BaseAdapter {
  public String[] MenuOption;
  
  Context context;
  
  private String getAplicationType = "";
  
  private String getDescription = "";
  
  ViewHolder holder;
  
  LayoutInflater mInflater;
  
  private onstarApplication rtApp;
  
  private Typeface tf;
  
  public optionMenuPlatinumAdapter(Context paramContext, String[] paramArrayOfString) {
    this.context = paramContext;
    this.MenuOption = paramArrayOfString;
  }
  
  private void setHolderControls(String paramString, ViewHolder paramViewHolder, View paramView) {
    LinearLayout linearLayout2 = (LinearLayout)paramView.findViewById(2131296283);
    LinearLayout linearLayout1 = (LinearLayout)paramView.findViewById(2131296821);
    linearLayout2.setVisibility(8);
    linearLayout1.setVisibility(0);
    paramViewHolder.txtHeaderDate = (TextView)paramView.findViewById(2131297184);
    paramViewHolder.txtHeaderDate.setText("");
    paramViewHolder.txtTitle = (TextView)paramView.findViewById(2131297175);
    this.rtApp = (onstarApplication)this.context.getApplicationContext();
    onstarApplication onstarApplication1 = this.rtApp;
    this.tf = onstarApplication.getTypeface(this.context, onstarApplication1.fontPathLouisRegular);
    paramViewHolder.txtTitle.setTypeface(this.tf);
    paramViewHolder.txtTitle.setText("");
  }
  
  private View setInflaterLayout(String paramString) {
    this.mInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
    return this.mInflater.inflate(2131427421, null);
  }
  
  public int getCount() {
    String[] arrayOfString = this.MenuOption;
    return (arrayOfString != null) ? arrayOfString.length : 0;
  }
  
  public Object getItem(int paramInt) {
    return this.MenuOption[paramInt];
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    this.mInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
    this.getDescription = this.MenuOption[paramInt].toString();
    if (paramView == null) {
      paramView = setInflaterLayout(this.getAplicationType);
      this.holder = new ViewHolder();
      paramView.setTag(this.holder);
    } else {
      this.holder = (ViewHolder)paramView.getTag();
    } 
    setHolderControls(this.getAplicationType, this.holder, paramView);
    this.holder.txtTitle.setText(this.getDescription);
    this.getAplicationType = "";
    this.getDescription = "";
    return paramView;
  }
  
  private class ViewHolder {
    TextView txtHeaderDate;
    
    TextView txtTitle;
    
    private ViewHolder(optionMenuPlatinumAdapter this$0) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/optionMenuPlatinumAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */