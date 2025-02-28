package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.roadtrack.onstar.async_tasks.intefaces.Gmt_Interface;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.List;

public class Gmt_Adapter extends BaseAdapter implements Filterable {
  private Context mContext;
  
  private List<Gmt_Getters_Setters> mGmt_Getters_Setters = new ArrayList<Gmt_Getters_Setters>();
  
  private Gmt_Interface mGmt_Interface;
  
  private ItemFilter mItemFilter = new ItemFilter();
  
  private List<Gmt_Getters_Setters> mOriginalGmt_Getters_Setters = new ArrayList<Gmt_Getters_Setters>();
  
  private onstarApplication rtApp;
  
  private Typeface typeface;
  
  public Gmt_Adapter(Context paramContext, List<Gmt_Getters_Setters> paramList, Gmt_Interface paramGmt_Interface) {
    this.mContext = paramContext;
    this.mGmt_Getters_Setters = paramList;
    this.mOriginalGmt_Getters_Setters = paramList;
    this.mGmt_Interface = paramGmt_Interface;
  }
  
  public int getCount() {
    return this.mGmt_Getters_Setters.size();
  }
  
  public Filter getFilter() {
    return this.mItemFilter;
  }
  
  public Object getItem(int paramInt) {
    return this.mGmt_Getters_Setters.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    holder holder1;
    holder holder2;
    LayoutInflater layoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
    if (paramView == null) {
      holder1 = new holder();
      View view = layoutInflater.inflate(2131427422, paramViewGroup, false);
      this.rtApp = (onstarApplication)this.mContext.getApplicationContext();
      onstarApplication onstarApplication1 = this.rtApp;
      this.typeface = onstarApplication.getTypeface(this.mContext, onstarApplication1.fontPathLouisRegular);
      holder.access$202(holder1, (TextView)view.findViewById(2131296563));
      holder1.textViewGmtLabel.setTypeface(this.typeface);
      holder.access$302(holder1, (TextView)view.findViewById(2131296564));
      holder1.textViewGmtValue.setTypeface(this.typeface);
      holder.access$402(holder1, (TextView)view.findViewById(2131296565));
      holder1.textViewGmtZone.setTypeface(this.typeface);
      view.setTag(holder1);
    } else {
      holder holder = (holder)holder1.getTag();
      holder2 = holder1;
      holder1 = holder;
    } 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(((Gmt_Getters_Setters)this.mGmt_Getters_Setters.get(paramInt)).getGmtLabel());
    stringBuilder1.append(" ");
    String str1 = stringBuilder1.toString();
    float f = ((Gmt_Getters_Setters)this.mGmt_Getters_Setters.get(paramInt)).getGmtValue();
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("");
    stringBuilder2.append(Utilities.FormatHour(f));
    String str2 = stringBuilder2.toString();
    holder1.textViewGmtLabel.setText(str1);
    holder1.textViewGmtValue.setText(str2);
    holder1.textViewGmtZone.setText(((Gmt_Getters_Setters)this.mGmt_Getters_Setters.get(paramInt)).getGmtZone());
    return (View)holder2;
  }
  
  private class ItemFilter extends Filter {
    final Gmt_Adapter this$0;
    
    private ItemFilter() {}
    
    protected Filter.FilterResults performFiltering(CharSequence param1CharSequence) {
      String str = param1CharSequence.toString().toLowerCase();
      Filter.FilterResults filterResults = new Filter.FilterResults();
      List<Gmt_Getters_Setters> list = Gmt_Adapter.this.mGmt_Getters_Setters;
      int i = list.size();
      ArrayList arrayList = new ArrayList(i);
      for (byte b = 0; b < i; b++) {
        if (((Gmt_Getters_Setters)list.get(b)).getGmtZone().toLowerCase().contains(str))
          arrayList.add(list.get(b)); 
      } 
      filterResults.values = arrayList;
      filterResults.count = arrayList.size();
      return filterResults;
    }
    
    protected void publishResults(CharSequence param1CharSequence, Filter.FilterResults param1FilterResults) {
      param1CharSequence = param1CharSequence.toString().trim();
      ArrayList arrayList = (ArrayList)param1FilterResults.values;
      if (param1CharSequence != null && param1CharSequence.toString().isEmpty()) {
        Gmt_Adapter gmt_Adapter = Gmt_Adapter.this;
        Gmt_Adapter.access$502(gmt_Adapter, gmt_Adapter.mOriginalGmt_Getters_Setters);
        Gmt_Adapter.this.mGmt_Interface.onFilteredChange(Gmt_Adapter.this.mGmt_Getters_Setters);
      } else {
        Gmt_Adapter.access$502(Gmt_Adapter.this, arrayList);
        Gmt_Adapter.this.mGmt_Interface.onFilteredChange(Gmt_Adapter.this.mGmt_Getters_Setters);
      } 
      Gmt_Adapter.this.notifyDataSetChanged();
    }
  }
  
  private class holder {
    private TextView textViewGmtLabel;
    
    private TextView textViewGmtValue;
    
    private TextView textViewGmtZone;
    
    private holder(Gmt_Adapter this$0) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/Gmt_Adapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */