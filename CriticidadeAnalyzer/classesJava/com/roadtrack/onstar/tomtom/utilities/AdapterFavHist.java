package com.roadtrack.onstar.tomtom.utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.tomtom.activities.ActivityFavoritesHistory;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint({"NewApi"})
public class AdapterFavHist extends BaseExpandableListAdapter {
  private static onstarApplication rtApp;
  
  private static Typeface tf_LouisCondensed;
  
  FavoritesHistoryVO DeleteFavorites;
  
  private FavoritesHistoryVO FavVO;
  
  private ChildViewHolder childViewHolder;
  
  private String groupText;
  
  private GroupViewHolder groupViewHolder;
  
  private HashMap<Integer, boolean[]> mChildCheckStates;
  
  private Context mContext;
  
  private HashMap<String, List<FavoritesHistoryVO>> mListDataChild;
  
  private ArrayList<String> mListDataGroup;
  
  private ArrayList<FavoritesHistoryVO> mListDataSelected;
  
  boolean showCheckBox;
  
  private EditText titleEdit;
  
  public AdapterFavHist(Activity paramActivity, Context paramContext, ArrayList<String> paramArrayList, HashMap<String, List<FavoritesHistoryVO>> paramHashMap, boolean paramBoolean) {
    this.mContext = paramContext;
    this.mListDataGroup = paramArrayList;
    this.mListDataChild = paramHashMap;
    this.showCheckBox = paramBoolean;
    this.mChildCheckStates = (HashMap)new HashMap<Integer, boolean>();
    this.mListDataSelected = new ArrayList<FavoritesHistoryVO>();
    rtApp = (onstarApplication)this.mContext.getApplicationContext();
    onstarApplication onstarApplication1 = rtApp;
    tf_LouisCondensed = onstarApplication.getTypeface(this.mContext, onstarApplication1.fontPathLouisCondensedDemi);
  }
  
  public void clearmListDataDelete() {
    this.mListDataSelected.clear();
  }
  
  public FavoritesHistoryVO getChild(int paramInt1, int paramInt2) {
    return ((List<FavoritesHistoryVO>)this.mListDataChild.get(this.mListDataGroup.get(paramInt1))).get(paramInt2);
  }
  
  public long getChildId(int paramInt1, int paramInt2) {
    return paramInt2;
  }
  
  public View getChildView(final int mGroupPosition, final int childPosition, boolean paramBoolean, View paramView, ViewGroup paramViewGroup) {
    this.FavVO = getChild(mGroupPosition, childPosition);
    this.childViewHolder = new ChildViewHolder(this);
    this.childViewHolder.view = paramView;
    LayoutInflater layoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
    this.childViewHolder.view = layoutInflater.inflate(2131427408, null);
    this.childViewHolder.view.setBackgroundColor(ContextCompat.getColor(this.mContext, 2131034224));
    ChildViewHolder childViewHolder3 = this.childViewHolder;
    childViewHolder3.Clickedsection = (LinearLayout)childViewHolder3.view.findViewById(2131297245);
    this.titleEdit = (EditText)this.childViewHolder.view.findViewById(2131297127);
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList(this.mContext, stringsResourcesVO.deviceName, 2131689751);
    this.titleEdit.setHint(str);
    ChildViewHolder childViewHolder2 = this.childViewHolder;
    childViewHolder2.mCheckBox = (CheckBox)childViewHolder2.view.findViewById(2131296464);
    if (this.showCheckBox && mGroupPosition != 1) {
      this.childViewHolder.mCheckBox.setVisibility(0);
    } else {
      this.childViewHolder.mCheckBox.setVisibility(8);
    } 
    childViewHolder2 = this.childViewHolder;
    childViewHolder2.HoldFavVO = this.FavVO;
    childViewHolder2.imgFavoritesHistory = (ImageView)childViewHolder2.view.findViewById(2131296598);
    Drawable drawable = Utilities.getDrawableFromConfigList(this.mContext, DrawableResourcesVO.pin_ubication_medium, 2131165644);
    this.childViewHolder.imgFavoritesHistory.setImageDrawable(drawable);
    ChildViewHolder childViewHolder1 = this.childViewHolder;
    childViewHolder1.mlblcountFavorites = (TextView)childViewHolder1.view.findViewById(2131296790);
    this.childViewHolder.mlblcountFavorites.setTypeface(tf_LouisCondensed);
    this.childViewHolder.mlblcountFavorites.setTextSize(1, 20.0F);
    this.childViewHolder.mlblcountFavorites.setTextColor(Color.parseColor("#787878"));
    childViewHolder1 = this.childViewHolder;
    childViewHolder1.mChildText = (TextView)childViewHolder1.view.findViewById(2131297125);
    this.childViewHolder.mChildText.setTypeface(tf_LouisCondensed);
    this.childViewHolder.mChildText.setTextSize(1, 20.0F);
    this.childViewHolder.mChildText.setTextColor(Color.parseColor("#787878"));
    childViewHolder1 = this.childViewHolder;
    childViewHolder1.mtxtDescription = (TextView)childViewHolder1.view.findViewById(2131297176);
    this.childViewHolder.mtxtDescription.setTypeface(tf_LouisCondensed);
    this.childViewHolder.mtxtDescription.setTextSize(1, 16.0F);
    this.childViewHolder.mtxtDescription.setTextColor(Color.parseColor("#B5B5B5"));
    this.childViewHolder.mCheckBox.setOnCheckedChangeListener(null);
    this.childViewHolder.mCheckBox.setTag(Integer.valueOf(childPosition));
    if (this.mChildCheckStates.containsKey(Integer.valueOf(mGroupPosition))) {
      boolean[] arrayOfBoolean = this.mChildCheckStates.get(Integer.valueOf(mGroupPosition));
      this.childViewHolder.mCheckBox.setChecked(arrayOfBoolean[childPosition]);
    } else {
      boolean[] arrayOfBoolean = new boolean[getChildrenCount(mGroupPosition)];
      this.mChildCheckStates.put(Integer.valueOf(mGroupPosition), arrayOfBoolean);
      this.childViewHolder.mCheckBox.setChecked(false);
    } 
    this.childViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          final AdapterFavHist this$0;
          
          final int val$childPosition;
          
          final int val$groupPosition;
          
          final int val$mChildPosition;
          
          final int val$mGroupPosition;
          
          public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
            if (param1Boolean) {
              boolean[] arrayOfBoolean = (boolean[])AdapterFavHist.this.mChildCheckStates.get(Integer.valueOf(mGroupPosition));
              arrayOfBoolean[mChildPosition] = param1Boolean;
              AdapterFavHist.this.mChildCheckStates.put(Integer.valueOf(mGroupPosition), arrayOfBoolean);
              AdapterFavHist.this.childViewHolder.HoldFavVO.setCheked(true);
              AdapterFavHist.this.onListItemSelected(groupPosition, childPosition, true);
              AdapterFavHist.this.childViewHolder.view.setBackgroundColor(ContextCompat.getColor(AdapterFavHist.this.mContext, 2131034224));
            } else {
              boolean[] arrayOfBoolean = (boolean[])AdapterFavHist.this.mChildCheckStates.get(Integer.valueOf(mGroupPosition));
              arrayOfBoolean[mChildPosition] = param1Boolean;
              AdapterFavHist.this.mChildCheckStates.put(Integer.valueOf(mGroupPosition), arrayOfBoolean);
              AdapterFavHist.this.childViewHolder.HoldFavVO.setCheked(false);
              AdapterFavHist.this.onListItemSelected(groupPosition, childPosition, false);
            } 
          }
        });
    this.childViewHolder.mChildText.setText(this.FavVO.getName());
    if (mGroupPosition == 0) {
      this.childViewHolder.mtxtDescription.setVisibility(0);
      this.childViewHolder.mtxtDescription.setText(this.FavVO.getAddress());
      this.childViewHolder.mlblcountFavorites.setVisibility(0);
      TextView textView = this.childViewHolder.mlblcountFavorites;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("");
      stringBuilder.append(String.valueOf(childPosition + 1));
      textView.setText(stringBuilder.toString());
    } else {
      this.childViewHolder.mtxtDescription.setVisibility(8);
      this.childViewHolder.mlblcountFavorites.setVisibility(8);
      this.childViewHolder.imgFavoritesHistory.setVisibility(0);
    } 
    this.childViewHolder.Clickedsection.setOnClickListener(new View.OnClickListener() {
          final AdapterFavHist this$0;
          
          final int val$childPosition;
          
          final int val$mGroupPosition;
          
          public void onClick(View param1View) {
            FavoritesHistoryVO favoritesHistoryVO = AdapterFavHist.this.getChild(mGroupPosition, childPosition);
            GlobalMembers.objSendFavoritesMessage = new Object();
            GlobalMembers.objSendFavoritesMessage = favoritesHistoryVO;
            if (!AdapterFavHist.this.showCheckBox)
              ActivityFavoritesHistory.showmarkers(favoritesHistoryVO, mGroupPosition); 
          }
        });
    childViewHolder1 = this.childViewHolder;
    childViewHolder1.view.setTag(2131427408, childViewHolder1);
    return this.childViewHolder.view;
  }
  
  public int getChildrenCount(int paramInt) {
    return ((List)this.mListDataChild.get(this.mListDataGroup.get(paramInt))).size();
  }
  
  public int getCountFavorites() {
    return this.mListDataSelected.size();
  }
  
  public ArrayList<FavoritesHistoryVO> getDelectedFavorites() {
    ArrayList<FavoritesHistoryVO> arrayList = new ArrayList();
    for (byte b = 0; b < this.mListDataSelected.size(); b++)
      arrayList.add(this.mListDataSelected.get(b)); 
    return arrayList;
  }
  
  public FavoritesHistoryVO getEditFavorites() {
    return this.mListDataSelected.get(0);
  }
  
  public String getGroup(int paramInt) {
    return this.mListDataGroup.get(paramInt);
  }
  
  public int getGroupCount() {
    return this.mListDataGroup.size();
  }
  
  public long getGroupId(int paramInt) {
    return paramInt;
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup) {
    this.groupText = getGroup(paramInt);
    if (paramView == null) {
      paramView = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(2131427440, null);
      this.groupViewHolder = new GroupViewHolder(this);
      this.groupViewHolder.mGroupText = (TextView)paramView.findViewById(2131296710);
      paramView.setTag(this.groupViewHolder);
    } else {
      this.groupViewHolder = (GroupViewHolder)paramView.getTag();
    } 
    this.groupViewHolder.mGroupText.setText(this.groupText);
    return paramView;
  }
  
  public int getNumberOfCheckedItemsInGroup(int paramInt) {
    boolean[] arrayOfBoolean = this.mChildCheckStates.get(Integer.valueOf(paramInt));
    paramInt = 0;
    byte b = 0;
    if (arrayOfBoolean != null)
      for (paramInt = 0; b < arrayOfBoolean.length; paramInt = i) {
        int i = paramInt;
        if (arrayOfBoolean[b] == true)
          i = paramInt + 1; 
        b++;
      }  
    return paramInt;
  }
  
  public boolean hasStableIds() {
    return false;
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2) {
    return false;
  }
  
  public void onListItemSelected(int paramInt1, int paramInt2, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("selected:");
    stringBuilder.append(getChild(paramInt1, paramInt2).getName().toString());
    stringBuilder.append(" STATE: TRUE");
    Utilities.escribeArchivo("FAVORITES ADAPTER", "FAVORITES ADAPTER 2", stringBuilder.toString());
    this.DeleteFavorites = getChild(paramInt1, paramInt2);
    if (paramBoolean) {
      this.mListDataSelected.add(this.DeleteFavorites);
    } else {
      this.mListDataSelected.remove(this.DeleteFavorites);
    } 
    if (ActivityFavoritesHistory.mActionMode != null)
      if (getCountFavorites() <= 0) {
        ActivityFavoritesHistory.mActionMode.finish();
      } else if (getCountFavorites() == 1) {
        ActivityFavoritesHistory.MenuEdit.setVisible(true);
      } else if (getCountFavorites() >= 1) {
        ActivityFavoritesHistory.MenuEdit.setVisible(false);
      }  
  }
  
  public final class ChildViewHolder {
    LinearLayout Clickedsection;
    
    FavoritesHistoryVO HoldFavVO;
    
    ImageView imgFavoritesHistory;
    
    CheckBox mCheckBox;
    
    TextView mChildText;
    
    TextView mlblcountFavorites;
    
    TextView mtxtDescription;
    
    View view;
    
    public ChildViewHolder(AdapterFavHist this$0) {}
  }
  
  public final class GroupViewHolder {
    TextView mGroupText;
    
    public GroupViewHolder(AdapterFavHist this$0) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/tomtom/utilities/AdapterFavHist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */