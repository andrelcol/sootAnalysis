package com.roadtrack.onstar.custom.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.roadtrack.onstar.custom.ListButton;
import java.util.LinkedList;

public class ListButtonAdapter extends BaseAdapter {
  private LinkedList<ListButton> listButtons;
  
  public ListButtonAdapter(LinkedList<ListButton> paramLinkedList) {
    this.listButtons = paramLinkedList;
  }
  
  public int getCount() {
    return this.listButtons.size();
  }
  
  public Object getItem(int paramInt) {
    return this.listButtons.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    return (View)this.listButtons.get(paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/custom/adapter/ListButtonAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */