package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.CustomActionButton;
import com.roadtrack.onstar.fragments.GridHolderFragment;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
  private int NI_NUM_ACTIONS;
  
  private HashMap<String, LinkedList<CustomActionButton>> actionsPerPage;
  
  private LinkedList<CustomActionButton> buttons;
  
  private FragmentManager fragmentManager;
  
  private Context mContext;
  
  private int numActions;
  
  private int numPages;
  
  public SectionsPagerAdapter(FragmentManager paramFragmentManager, LinkedList<CustomActionButton> paramLinkedList, Context paramContext) {
    super(paramFragmentManager);
    boolean bool1 = true;
    this.numPages = 1;
    byte b = 6;
    this.NI_NUM_ACTIONS = 6;
    this.actionsPerPage = new HashMap<String, LinkedList<CustomActionButton>>();
    this.fragmentManager = paramFragmentManager;
    this.buttons = new LinkedList<CustomActionButton>(paramLinkedList);
    this.numActions = this.buttons.size();
    this.mContext = paramContext;
    int i = (this.mContext.getResources().getConfiguration()).orientation;
    boolean bool2 = false;
    if (i == 2) {
      this.numPages = (int)Math.ceil(this.numActions / 6);
      LinkedList<CustomActionButton> linkedList = new LinkedList();
      Iterator<CustomActionButton> iterator = this.buttons.iterator();
      i = 0;
      while (true) {
        byte b1 = 1;
        while (iterator.hasNext()) {
          CustomActionButton customActionButton = iterator.next();
          if (b1 < 6) {
            linkedList.add(customActionButton);
            b1++;
            continue;
          } 
          linkedList.add(customActionButton);
          this.actionsPerPage.put(String.valueOf(i), linkedList);
          linkedList = new LinkedList<CustomActionButton>();
          i++;
        } 
        if (!this.actionsPerPage.containsKey(String.valueOf(i))) {
          while (linkedList.size() < 6) {
            CustomActionButton customActionButton = new CustomActionButton(this.mContext);
            customActionButton.set_action_service_status(2);
            linkedList.add(customActionButton);
          } 
          this.actionsPerPage.put(String.valueOf(i), linkedList);
        } 
        break;
      } 
    } 
    if ((this.mContext.getResources().getConfiguration()).orientation == 1) {
      i = b;
      if (this.numActions > 6)
        i = 8; 
      this.NI_NUM_ACTIONS = i;
      this.numPages = (int)Math.ceil(this.numActions / 9);
      LinkedList<CustomActionButton> linkedList = new LinkedList();
      Iterator<CustomActionButton> iterator = this.buttons.iterator();
      i = bool2;
      while (true) {
        byte b1 = 1;
        while (iterator.hasNext()) {
          CustomActionButton customActionButton = iterator.next();
          if (b1 < 9) {
            linkedList.add(customActionButton);
            b1++;
            continue;
          } 
          linkedList.add(customActionButton);
          this.actionsPerPage.put(String.valueOf(i), linkedList);
          linkedList = new LinkedList<CustomActionButton>();
          i++;
        } 
        if (!this.actionsPerPage.containsKey(String.valueOf(i))) {
          while (linkedList.size() < 9) {
            CustomActionButton customActionButton = new CustomActionButton(this.mContext);
            customActionButton.set_action_service_status(2);
            linkedList.add(customActionButton);
          } 
          this.actionsPerPage.put(String.valueOf(i), linkedList);
        } 
        break;
      } 
    } 
    new LinkedList();
    for (i = bool1; i <= this.numPages; i++);
  }
  
  private String makeFragmentName(int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("android:switcher:");
    stringBuilder.append(paramInt1);
    stringBuilder.append(":");
    stringBuilder.append(paramInt2);
    return stringBuilder.toString();
  }
  
  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
    super.destroyItem(paramViewGroup, paramInt, paramObject);
    ((MainActivity)this.mContext).mSectionsPagerAdapter.notifyDataSetChanged();
    if (paramInt >= getCount()) {
      Fragment fragment = (Fragment)paramObject;
      paramObject = fragment.getFragmentManager().beginTransaction();
      paramObject.remove(fragment);
      paramObject.commit();
    } 
  }
  
  public int getCount() {
    return this.numPages;
  }
  
  public Fragment getItem(int paramInt) {
    GridHolderFragment gridHolderFragment;
    String str = makeFragmentName(2131296944, paramInt);
    Fragment fragment2 = this.fragmentManager.findFragmentByTag(str);
    Fragment fragment1 = fragment2;
    if (fragment2 == null)
      gridHolderFragment = new GridHolderFragment(this.actionsPerPage.get(String.valueOf(paramInt)), this.buttons); 
    return (Fragment)gridHolderFragment;
  }
  
  public int getItemPosition(Object paramObject) {
    return -2;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/SectionsPagerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */