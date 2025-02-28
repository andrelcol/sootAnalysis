package com.roadtrack.onstar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.VO.Historical;
import com.roadtrack.onstar.VO.LocationInfoRT;
import com.roadtrack.onstar.VO.UserDevicesVO;
import com.roadtrack.onstar.adapter.HistoryFullAdapter;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FragmentPager extends Fragment {
  private static ListView lvDatos;
  
  private static onstarApplication rtApp;
  
  Context context;
  
  private int mPageNumber;
  
  private static HistoryFullAdapter CreateCompleteListData() {
    ArrayList<Historical> arrayList = new ArrayList();
    for (Historical historical : GlobalMembers.mTodayList) {
      if (historical.getVehicleId().equals(Utilities.getLastKnownDeviceSelected(rtApp, "FragmentPager").getDeviceId())) {
        String str1;
        String str2;
        Iterator<UserDevicesVO> iterator = rtApp.getmDeviceUserList().iterator();
        while (true) {
          if (iterator.hasNext()) {
            UserDevicesVO userDevicesVO = iterator.next();
            if (userDevicesVO.getDeviceId().equals(historical.getVehicleId())) {
              String str10 = userDevicesVO.getName();
              String str11 = userDevicesVO.getDeviceId();
              break;
            } 
            continue;
          } 
          str1 = "";
          str2 = "";
          break;
        } 
        String str9 = Enums$Category.Other.toString();
        String str7 = historical.getDescription();
        String str8 = String.format("%s %s", new Object[] { historical.getAplicationType(), historical.getName() });
        String str6 = String.valueOf(historical.getLatitud());
        String str5 = String.valueOf(historical.getLongitud());
        String str4 = historical.getId();
        int j = historical.idError;
        int i = Enums$historical.commands.GetOpCode();
        String str3 = historical.dateTime;
        arrayList.add(new Historical(str9, str1, str7, str8, str6, str5, str4, 0, j, "0", str2, String.valueOf(i), str3, "0", "0", "0", "0", 0, 0.0D, 0.0D, "0", 0, str3));
      } 
    } 
    List list = GlobalMembers.navigationHistoricalList;
    if (list != null)
      for (FavoritesHistoryVO favoritesHistoryVO : list) {
        String str1;
        String str2;
        Iterator<UserDevicesVO> iterator = rtApp.getmDeviceUserList().iterator();
        while (true) {
          if (iterator.hasNext()) {
            UserDevicesVO userDevicesVO = iterator.next();
            if (userDevicesVO.getDeviceId().equals(favoritesHistoryVO.getDevice_id())) {
              String str5 = userDevicesVO.getName();
              String str4 = userDevicesVO.getDeviceId();
              break;
            } 
            continue;
          } 
          str2 = "";
          str1 = "";
          break;
        } 
        String str3 = favoritesHistoryVO.getDate();
        double d1 = Double.valueOf(favoritesHistoryVO.getLatlng().split(",")[0]).doubleValue();
        double d2 = Double.valueOf(favoritesHistoryVO.getLatlng().split(",")[1]).doubleValue();
        arrayList.add(new Historical(Enums$Category.Other.toString(), str2, favoritesHistoryVO.getAddress(), Enums$TypeItem.NavigationHistory.toString(), String.valueOf(d1), String.valueOf(d2), String.valueOf(favoritesHistoryVO.getId_favs_history()), 0, 0, "0", str1, String.valueOf(Enums$historical.navigation.GetOpCode()), str3, "0", "0", "0", "0", 0, 0.0D, 0.0D, "0", 0, str3));
      }  
    sort(arrayList, true);
    HistoryFullAdapter historyFullAdapter = new HistoryFullAdapter(GlobalMembers.contexGlobal, arrayList, "Center", "History", 0);
    historyFullAdapter.notifyDataSetChanged();
    return historyFullAdapter;
  }
  
  public static HistoryFullAdapter CreateNavigationListData() {
    ArrayList<Historical> arrayList = new ArrayList();
    List list = GlobalMembers.navigationHistoricalList;
    if (list != null)
      for (FavoritesHistoryVO favoritesHistoryVO : list) {
        String str1;
        String str2;
        Iterator<UserDevicesVO> iterator = rtApp.getmDeviceUserList().iterator();
        while (true) {
          if (iterator.hasNext()) {
            UserDevicesVO userDevicesVO = iterator.next();
            if (userDevicesVO.getDeviceId().equals(favoritesHistoryVO.getDevice_id())) {
              String str4 = userDevicesVO.getName();
              String str5 = userDevicesVO.getDeviceId();
              break;
            } 
            continue;
          } 
          str1 = "";
          str2 = "";
          break;
        } 
        String str3 = favoritesHistoryVO.getDate();
        double d2 = Double.valueOf(favoritesHistoryVO.getLatlng().split(",")[0]).doubleValue();
        double d1 = Double.valueOf(favoritesHistoryVO.getLatlng().split(",")[1]).doubleValue();
        arrayList.add(new Historical(Enums$Category.Other.toString(), str1, favoritesHistoryVO.getAddress(), Enums$TypeItem.NavigationHistory.toString(), String.valueOf(d2), String.valueOf(d1), String.valueOf(favoritesHistoryVO.getId_favs_history()), 0, 0, "0", str2, String.valueOf(Enums$historical.navigation.GetOpCode()), str3, "0", "0", "0", "0", 0, 0.0D, 0.0D, "0", 0, str3));
      }  
    sort(arrayList, true);
    HistoryFullAdapter historyFullAdapter = new HistoryFullAdapter(GlobalMembers.contexGlobal, arrayList, "Center", "History", 2);
    historyFullAdapter.notifyDataSetChanged();
    return historyFullAdapter;
  }
  
  public static HistoryFullAdapter CreateTodayListData() {
    ArrayList<Historical> arrayList = new ArrayList();
    for (Historical historical : GlobalMembers.mTodayList) {
      if (historical.getVehicleId().equals(Utilities.getLastKnownDeviceSelected(rtApp, "FragmentPager").getDeviceId())) {
        String str1;
        String str2;
        Iterator<UserDevicesVO> iterator = rtApp.getmDeviceUserList().iterator();
        while (true) {
          if (iterator.hasNext()) {
            UserDevicesVO userDevicesVO = iterator.next();
            if (userDevicesVO.getDeviceId().equals(historical.getVehicleId())) {
              String str11 = userDevicesVO.getName();
              String str10 = userDevicesVO.getDeviceId();
              break;
            } 
            continue;
          } 
          str2 = "";
          str1 = "";
          break;
        } 
        String str6 = Enums$Category.Other.toString();
        String str8 = historical.getDescription();
        String str4 = String.format("%s %s", new Object[] { historical.getAplicationType(), historical.getName() });
        String str7 = String.valueOf(historical.getLatitud());
        String str9 = String.valueOf(historical.getLongitud());
        String str5 = historical.getId();
        int i = historical.idError;
        int j = Enums$historical.commands.GetOpCode();
        String str3 = historical.dateTime;
        arrayList.add(new Historical(str6, str2, str8, str4, str7, str9, str5, 0, i, "0", str1, String.valueOf(j), str3, "0", "0", "0", "0", 0, 0.0D, 0.0D, "0", 0, str3));
      } 
    } 
    sort(arrayList, true);
    HistoryFullAdapter historyFullAdapter = new HistoryFullAdapter(GlobalMembers.contexGlobal, arrayList, "Center", "History", 1);
    historyFullAdapter.notifyDataSetChanged();
    return historyFullAdapter;
  }
  
  public static Fragment create(int paramInt, ListClick paramListClick) {
    FragmentPager fragmentPager = new FragmentPager();
    Bundle bundle = new Bundle();
    bundle.putInt("page", paramInt);
    fragmentPager.setArguments(bundle);
    return fragmentPager;
  }
  
  private void createLists(int paramInt) {
    if (paramInt == 0) {
      lvDatos.setAdapter((ListAdapter)CreateCompleteListData());
      lvDatos.setChoiceMode(2);
    } else if (paramInt == 1) {
      lvDatos.setAdapter((ListAdapter)CreateTodayListData());
    } else if (paramInt == 2) {
      lvDatos.setAdapter((ListAdapter)CreateNavigationListData());
    } 
  }
  
  public static List<FavoritesHistoryVO> getHistory(Context paramContext) {
    ArrayList<?> arrayList = (new DBFunctions(paramContext)).getNavFavoriteHistory(Enums$TypeItem.Historical.toString());
    if (arrayList.size() > 0)
      Collections.sort(arrayList, new Comparator<FavoritesHistoryVO>() {
            public int compare(FavoritesHistoryVO param1FavoritesHistoryVO1, FavoritesHistoryVO param1FavoritesHistoryVO2) {
              return param1FavoritesHistoryVO1.getName().compareTo(param1FavoritesHistoryVO2.getName());
            }
          }); 
    return (List)arrayList;
  }
  
  public static void sort(List<Historical> paramList, final boolean _compareDateTime) {
    if (paramList != null)
      Collections.sort(paramList, new Comparator<Historical>() {
            final boolean val$_compareDateTime;
            
            public int compare(Historical param1Historical1, Historical param1Historical2) {
              Exception exception2 = null;
              try {
                String str;
                if (!_compareDateTime) {
                  String str1 = param1Historical1.Description;
                  str = str1;
                  try {
                    String str2 = param1Historical2.Description;
                    str = str1;
                  } catch (Exception null) {
                    Utilities.escribeArchivo("FragmentPager", "Error:  historical", exception1.getMessage());
                    exception1 = exception2;
                  } 
                } else {
                  String str2 = ((Historical)str).dateTime;
                  str = str2;
                  String str1 = ((Historical)exception1).dateTime;
                  str = str2;
                } 
              } catch (Exception exception1) {
                param1Historical1 = null;
                Utilities.escribeArchivo("FragmentPager", "Error:  historical", exception1.getMessage());
                exception1 = exception2;
              } 
              return exception1.compareTo((String)param1Historical1);
            }
          }); 
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.mPageNumber = getArguments().getInt("page");
    rtApp = (onstarApplication)getActivity().getApplicationContext();
    this.context = getActivity().getApplicationContext();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
    View view = paramLayoutInflater.inflate(2131427414, paramViewGroup, false);
    lvDatos = (ListView)view;
    List list = (new DBFunctions(this.context)).getHistoricalList(GlobalMembers.userLogged, Utilities.getLastKnownDeviceSelected(rtApp, "FragmentPager").getDeviceId());
    ArrayList arrayList = new ArrayList();
    (new LocationInfoRT()).setDeviceID(Utilities.getLastKnownDeviceSelected(rtApp, "FragmentPager").getDeviceId());
    GlobalMembers.navigationHistoricalList = arrayList;
    GlobalMembers.mTodayList = list;
    GlobalMembers.navigationHistoricalList = getHistory(GlobalMembers.contexGlobal);
    createLists(this.mPageNumber);
    return view;
  }
  
  public void onResume() {
    super.onResume();
    if (HistoryFullAdapter.mActionMode != null)
      HistoryFullAdapter.mActionMode = null; 
  }
  
  public static interface ListClick {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/FragmentPager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */