package com.roadtrack.onstar.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.fragment.app.Fragment;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.adapter.GridButtonAdapter;
import java.util.LinkedList;

public class GridHolderFragment extends Fragment {
  private LinkedList<CustomActionButton> buttons;
  
  private LinkedList<CustomActionButton> buttonsOnlyMX;
  
  private GridView gridView;
  
  private onCustomButonListener myButonListener;
  
  public GridHolderFragment() {}
  
  @SuppressLint({"ValidFragment"})
  public GridHolderFragment(LinkedList<CustomActionButton> paramLinkedList1, LinkedList<CustomActionButton> paramLinkedList2) {
    this.buttons = new LinkedList<CustomActionButton>(paramLinkedList1);
    this.buttonsOnlyMX = new LinkedList<CustomActionButton>(paramLinkedList2);
  }
  
  public void onAttach(Context paramContext) {
    super.onAttach(paramContext);
    setRetainInstance(true);
    if (paramContext instanceof onCustomButonListener) {
      this.myButonListener = (onCustomButonListener)paramContext;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramContext.toString());
    stringBuilder.append(" must implement onCustomButonListener");
    throw new ClassCastException(stringBuilder.toString());
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = paramLayoutInflater.inflate(2131427392, paramViewGroup, false);
    this.gridView = (GridView)view.findViewById(2131296572);
    this.gridView.setAdapter((ListAdapter)new GridButtonAdapter(getContext(), this.buttons));
    this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          final GridHolderFragment this$0;
          
          public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
            CustomActionButton customActionButton = (CustomActionButton)param1AdapterView.getAdapter().getItem(param1Int);
            GridHolderFragment.this.myButonListener.onActionSelected(customActionButton);
          }
        });
    boolean bool = GlobalMembers.showButtonNavigation;
    return view;
  }
  
  public static interface onCustomButonListener {
    void onActionSelected(CustomActionButton param1CustomActionButton);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/fragments/GridHolderFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */