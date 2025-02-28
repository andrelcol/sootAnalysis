package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.roadtrack.onstar.VO.CustomActionButton;
import java.util.LinkedList;

public class GridButtonAdapter extends BaseAdapter {
  private LinkedList<CustomActionButton> buttons;
  
  public GridButtonAdapter(Context paramContext, LinkedList<CustomActionButton> paramLinkedList) {
    if (paramLinkedList != null) {
      this.buttons = new LinkedList<CustomActionButton>(paramLinkedList);
      LayoutInflater.from(paramContext);
    } 
  }
  
  public int getCount() {
    return this.buttons.size();
  }
  
  public Object getItem(int paramInt) {
    return this.buttons.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    return (View)getItem(paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/GridButtonAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */