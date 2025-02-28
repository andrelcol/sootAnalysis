package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import java.util.HashMap;
import java.util.List;

public class listExpandibleAdapter extends BaseExpandableListAdapter {
  private List<String> _listDataHeader;
  
  private SparseBooleanArray mSelectedItemsIds;
  
  private EditText titleEdit;
  
  public listExpandibleAdapter(Context paramContext, List<String> paramList, HashMap<String, List<FavoritesHistoryVO>> paramHashMap, Enums.TypeItem paramTypeItem, boolean paramBoolean) {}
  
  public Object getChild(int paramInt1, int paramInt2) {
    return null;
  }
  
  public long getChildId(int paramInt1, int paramInt2) {
    return paramInt2;
  }
  
  public Object getChildObject(int paramInt1, int paramInt2) {
    return null;
  }
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup) {
    Object object = getChild(paramInt1, paramInt2);
    if (object == null)
      return paramView; 
    object = object;
    object = paramView.findViewById(2131296273);
    object = paramView.findViewById(2131297125);
    this.titleEdit = (EditText)paramView.findViewById(2131297127);
    this.titleEdit.setSelectAllOnFocus(true);
    object = paramView.findViewById(2131296790);
    TextView textView = (TextView)paramView.findViewById(2131297176);
    return null;
  }
  
  public int getChildrenCount(int paramInt) {
    return 0;
  }
  
  public Object getGroup(int paramInt) {
    return this._listDataHeader.get(paramInt);
  }
  
  public int getGroupCount() {
    return this._listDataHeader.size();
  }
  
  public long getGroupId(int paramInt) {
    return paramInt;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup) {
    return null;
  }
  
  public int getSelectedCount() {
    return this.mSelectedItemsIds.size();
  }
  
  public SparseBooleanArray getSelectedIds() {
    return this.mSelectedItemsIds;
  }
  
  public boolean hasStableIds() {
    return false;
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2) {
    return true;
  }
  
  public void remove(FavoritesHistoryVO paramFavoritesHistoryVO) {}
  
  public void removeSelection() {
    this.mSelectedItemsIds = new SparseBooleanArray();
    notifyDataSetChanged();
  }
  
  public void selectView(int paramInt1, int paramInt2, boolean paramBoolean) {
    if (paramBoolean) {
      this.mSelectedItemsIds.put(paramInt2, paramBoolean);
    } else {
      this.mSelectedItemsIds.delete(paramInt2);
    } 
    notifyDataSetChanged();
  }
  
  public void setActionModeIndicator(boolean paramBoolean) {
    notifyDataSetChanged();
  }
  
  public void toggleSelection(int paramInt1, int paramInt2) {
    selectView(paramInt1, paramInt2, this.mSelectedItemsIds.get(paramInt2) ^ true);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/listExpandibleAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */