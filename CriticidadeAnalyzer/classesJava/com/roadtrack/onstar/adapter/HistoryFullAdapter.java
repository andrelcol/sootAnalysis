package com.roadtrack.onstar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.MapDownload;
import com.roadtrack.onstar.DAO.DBFunctions;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.DrawableResourcesVO;
import com.roadtrack.onstar.VO.Historical;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressLint({"NewApi"})
public class HistoryFullAdapter extends BaseAdapter {
  public static Handler handlerFinishing = new Handler() {
      public void handleMessage(Message param1Message) {
        HistoryFullAdapter.finishActionMode();
      }
    };
  
  public static Object mActionMode;
  
  private static String prefix = "";
  
  public String AplicationList;
  
  public List<Historical> MenuOption;
  
  private String TAG = "HistoryFullAdapter";
  
  private Context context;
  
  private String getAplicationType = "";
  
  private String getCategory = "";
  
  private String getDateTime = "";
  
  private String getDescription = "";
  
  private int getErrorId = 0;
  
  private String getName = "";
  
  private String getuserName = "";
  
  private ViewHolder holder;
  
  private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
      final HistoryFullAdapter this$0;
      
      public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
        DBFunctions dBFunctions;
        int i = Integer.parseInt(param1ActionMode.getTag().toString());
        Historical historical = (Historical)HistoryFullAdapter.this.getItem(i);
        i = param1MenuItem.getItemId();
        null = true;
        switch (i) {
          default:
            return false;
          case 2131296895:
            dBFunctions = new DBFunctions(HistoryFullAdapter.this.context);
            if (HistoryFullAdapter.this.numPage == 0) {
              dBFunctions.deleteAllHistorical(historical.getVehicleId());
              dBFunctions.deleteAllFavorite();
              GlobalMembers.deleteAll = true;
            } else if (HistoryFullAdapter.this.numPage == 1) {
              dBFunctions.deleteAllHistorical(historical.getVehicleId());
            } else if (HistoryFullAdapter.this.numPage == 2) {
              dBFunctions.deleteAllFavorite();
            } 
            HistoryFullAdapter.this.MenuOption.clear();
            HistoryFullAdapter.this.notifyDataSetChanged();
            param1ActionMode.finish();
            return SYNTHETIC_LOCAL_VARIABLE_4;
          case 2131296894:
            break;
        } 
        SparseBooleanArray sparseBooleanArray = HistoryFullAdapter.this.getSelectedIds();
        for (i = sparseBooleanArray.size() - 1; i >= 0; i--) {
          if (sparseBooleanArray.valueAt(i)) {
            historical = (Historical)HistoryFullAdapter.this.getItem(sparseBooleanArray.keyAt(i));
            HistoryFullAdapter.this.removeData(historical, sparseBooleanArray.keyAt(i));
          } 
        } 
        param1ActionMode.finish();
        return SYNTHETIC_LOCAL_VARIABLE_4;
      }
      
      public boolean onCreateActionMode(ActionMode param1ActionMode, Menu param1Menu) {
        param1ActionMode.getMenuInflater().inflate(2131492864, param1Menu);
        return true;
      }
      
      public void onDestroyActionMode(ActionMode param1ActionMode) {
        HistoryFullAdapter.access$902(HistoryFullAdapter.this, new SparseBooleanArray());
        HistoryFullAdapter.this.notifyDataSetChanged();
        HistoryFullAdapter.mActionMode = null;
      }
      
      public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
        return false;
      }
    };
  
  private SparseBooleanArray mSelectedItemsIds;
  
  private int numPage;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tf;
  
  public HistoryFullAdapter(Context paramContext, List<Historical> paramList, String paramString1, String paramString2, int paramInt) {
    this.context = paramContext;
    this.MenuOption = paramList;
    this.AplicationList = paramString2;
    this.numPage = paramInt;
    this.mSelectedItemsIds = new SparseBooleanArray();
  }
  
  public static void finishActionMode() {
    Object object = mActionMode;
    if (object != null) {
      ((ActionMode)object).finish();
      mActionMode = null;
    } 
  }
  
  private String getActionInfo(ActionRow paramActionRow, String paramString) {
    String str;
    this.stringsResourcesVO = new StringsResourcesVO();
    Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.must_download_map, 2131690137);
    if (paramString.equals("Historical DoorsUnlock")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_accionabrirpuertas_1, 2131689842);
    } else if (paramString.equals("Historical DoorsLock")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_accioncerrarpuertas_1, 2131689854);
    } else if (paramString.equals("Historical Parking")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.historystartparked, 2131689997);
    } else if (paramString.equals("Historical Speed")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.historystartspeed, 2131689998);
    } else if (paramString.equals("Historical Speed Always")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.historystartspeed, 2131689998);
    } else if (paramString.equals("Historical HornLigths")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_accionlucesybocina_1, 2131689879);
    } else if (paramString.equals("Historical Horn")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_accionalertabocina_1, 2131689844);
    } else if (paramString.equals("Historical valet")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_accionalertavalet_1, 2131689849);
    } else if (paramString.equals("Historical Disarm")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.historydisablepincode, 2131689991);
    } else if (paramString.equals("Historical FollowMe")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.historystartfollowme, 2131689995);
    } else if (paramString.equals("Historical FindMe")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.historystartlocation, 2131689996);
    } else if (paramString.equals("Historical SendPNDNavigationCommand")) {
      str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.historysendingroute, 2131689994);
    } else {
      str = paramString;
      if (paramString.equals("NavigationHistory"))
        str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_navegacion_1, 2131689929); 
    } 
    int i = null.$SwitchMap$com$roadtrack$onstar$adapter$HistoryFullAdapter$ActionRow[paramActionRow.ordinal()];
    if (i != 1) {
      if (i != 2)
        str = ""; 
    } else {
      str = String.valueOf(2131165296);
    } 
    return str;
  }
  
  private void onListItemSelected(int paramInt) {
    toggleSelection(paramInt);
    Object object = mActionMode;
    if (object != null) {
      object = object;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(String.valueOf(getSelectedCount()));
      stringBuilder.append(" seleccionados");
      object.setTitle(stringBuilder.toString());
    } 
  }
  
  private void removeData(Historical paramHistorical, int paramInt) {
    DBFunctions dBFunctions = new DBFunctions(this.context);
    if (paramHistorical == null)
      return; 
    int i = Integer.valueOf(paramHistorical.getId()).intValue();
    if (paramHistorical.getAplicationType().equals(Enums.TypeItem.NavigationHistory.toString())) {
      dBFunctions.deleteFavorite(i);
      Toast.makeText(this.context, "Se ha eliminado correctamente", 1).show();
      this.MenuOption.remove(paramInt);
      notifyDataSetChanged();
    } else {
      dBFunctions.deleteHistorical(i);
      this.MenuOption.remove(paramInt);
      if (this.numPage == 0)
        GlobalMembers.deleteIndividual = true; 
      notifyDataSetChanged();
    } 
  }
  
  private int setEnummFragmentNumber(String paramString) {
    return 0;
  }
  
  private void setHolderControls(String paramString, ViewHolder paramViewHolder, View paramView) {
    LinearLayout linearLayout1 = (LinearLayout)paramView.findViewById(2131296283);
    LinearLayout linearLayout2 = (LinearLayout)paramView.findViewById(2131296821);
    if (paramString.equals("HeaderDate")) {
      linearLayout1.setVisibility(0);
      linearLayout2.setVisibility(8);
      paramViewHolder.txtHeaderDate = (TextView)paramView.findViewById(2131296492);
      paramViewHolder.txtHeaderDate.setText(this.getName);
      ((TextView)paramView.findViewById(2131297184)).setText("");
    } else {
      try {
        this.tf = onstarApplication.getTypeface(this.context, this.rtApp.fontPathLouisRegular);
        linearLayout1.setVisibility(0);
        linearLayout2.setVisibility(0);
        paramViewHolder.txtHeaderDate = (TextView)paramView.findViewById(2131297184);
        paramViewHolder.txtHeaderDate.setTypeface(this.tf);
        paramViewHolder.txtHeaderDate.setText("");
        paramViewHolder.txtTitle = (TextView)paramView.findViewById(2131297175);
        paramViewHolder.txtTitle.setTypeface(this.tf);
        paramViewHolder.txtTitle.setText("");
        paramViewHolder.txtFecForFull = (TextView)paramView.findViewById(2131296492);
        paramViewHolder.txtFecForFull.setText("");
        paramViewHolder.txtFecForFull.setTypeface(this.tf);
        paramViewHolder.txtFecForFull.setVisibility(0);
        paramViewHolder.txtlineDesc1 = (TextView)paramView.findViewById(2131296819);
        paramViewHolder.txtlineDesc1.setTypeface(this.tf);
        paramViewHolder.txtlineDesc1.setText("");
        paramViewHolder.txtlineDesc1.setVisibility(0);
        paramViewHolder.btnImageDescrip = (ImageView)paramView.findViewById(2131296403);
        paramViewHolder.btnImageStatus = (ImageView)paramView.findViewById(2131296404);
        paramViewHolder.LayHistory = (LinearLayout)paramView.findViewById(2131296284);
        paramViewHolder.txtNameVehicle = (TextView)paramView.findViewById(2131297185);
        paramViewHolder.txtNameVehicle.setTypeface(this.tf);
        paramViewHolder.txtStatusAction = (TextView)paramView.findViewById(2131297186);
        paramViewHolder.txtStatusAction.setTypeface(this.tf);
      } catch (Exception exception) {
        Utilities.escribeArchivo(this.TAG, "Error: err adapter hist", exception.getMessage());
      } 
    } 
  }
  
  private View setInflaterLayout(String paramString) {
    return View.inflate(this.context, 2131427421, null);
  }
  
  private void startingMap(double paramDouble1, double paramDouble2, String paramString) {
    if (paramDouble1 == 0.0D || paramDouble2 == 0.0D);
  }
  
  private void toggleSelection(int paramInt) {
    selectView(paramInt, this.mSelectedItemsIds.get(paramInt) ^ true);
  }
  
  public int getCount() {
    return this.MenuOption.size();
  }
  
  public Object getItem(int paramInt) {
    return this.MenuOption.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public int getSelectedCount() {
    return this.mSelectedItemsIds.size();
  }
  
  public SparseBooleanArray getSelectedIds() {
    return this.mSelectedItemsIds;
  }
  
  public View getView(final int position, View paramView, ViewGroup paramViewGroup) {
    View view;
    Context context = this.context;
    String str = prefix;
    int i = 0;
    new MapDownload(context, str, false);
    this.getAplicationType = ((Historical)this.MenuOption.get(position)).getAplicationType();
    this.getCategory = ((Historical)this.MenuOption.get(position)).getCategory();
    this.getDescription = ((Historical)this.MenuOption.get(position)).getDescription();
    this.getName = ((Historical)this.MenuOption.get(position)).getName();
    this.getuserName = ((Historical)this.MenuOption.get(position)).getUserName();
    this.getErrorId = ((Historical)this.MenuOption.get(position)).idError;
    this.getDateTime = ((Historical)this.MenuOption.get(position)).dateTime;
    if (paramView == null) {
      view = setInflaterLayout(this.getAplicationType);
      this.holder = new ViewHolder();
      view.setTag(this.holder);
    } else {
      setEnummFragmentNumber(this.getAplicationType);
      if (!this.AplicationList.equals(this.getCategory))
        paramView = setInflaterLayout(this.getAplicationType); 
      this.holder = (ViewHolder)paramView.getTag();
      view = paramView;
      if (this.holder == null) {
        this.holder = new ViewHolder();
        paramView.setTag(this.holder);
        view = paramView;
      } 
    } 
    ViewHolder viewHolder = this.holder;
    if (viewHolder != null) {
      setHolderControls(this.getAplicationType, viewHolder, view);
      TextView textView = this.holder.txtTitle;
      if (textView != null) {
        textView.setText(((Historical)this.MenuOption.get(position)).getAplicationType());
        if (this.AplicationList.equals("History"))
          this.holder.txtTitle.setVisibility(0); 
      } 
      textView = this.holder.txtDesc;
      if (textView != null) {
        textView.setText(this.getDescription);
        if (this.getAplicationType.equalsIgnoreCase("app") || this.getAplicationType.equalsIgnoreCase("external"))
          this.holder.txtTitle.setText(this.getName); 
      } 
      if (this.holder.txtFecForFull != null)
        if (this.AplicationList.equals("History")) {
          this.holder.txtFecForFull.setText(((Historical)this.MenuOption.get(position)).getAplicationType());
        } else {
          this.holder.txtFecForFull.setText(((Historical)this.MenuOption.get(position)).getUserName());
        }  
      textView = this.holder.txtId;
      if (textView != null)
        textView.setText(this.getAplicationType); 
      if (this.holder.txtHeaderDate != null && !this.getAplicationType.equals("HeaderDate"))
        if (this.AplicationList.equals("History")) {
          this.holder.txtHeaderDate.setVisibility(8);
        } else {
          this.holder.txtHeaderDate.setText(this.getName);
        }  
      if (this.holder.btnImageDescrip != null) {
        String[] arrayOfString = this.getDescription.split("\\|");
        if (this.getuserName.equals(String.valueOf(Enums.historical.navigation.GetOpCode()))) {
          this.holder.txtTitle.setVisibility(0);
          this.holder.txtTitle.setText(this.getDescription);
          this.holder.txtHeaderDate.setVisibility(8);
          this.holder.txtNameVehicle.setText(this.getName);
          this.holder.txtNameVehicle.setVisibility(0);
          this.holder.btnImageDescrip.setVisibility(8);
          Drawable drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.wizar_notificaciones, 2131165707);
          this.holder.btnImageDescrip.setImageDrawable(drawable);
          this.holder.btnImageStatus.setVisibility(8);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.response_ok, 2131165650);
          this.holder.btnImageStatus.setImageDrawable(drawable);
          this.holder.txtStatusAction.setText("OK");
          this.holder.LayHistory.setOnClickListener(new View.OnClickListener() {
                final HistoryFullAdapter this$0;
                
                final int val$position;
                
                public void onClick(View param1View) {
                  // Byte code:
                  //   0: getstatic com/roadtrack/onstar/adapter/HistoryFullAdapter.mActionMode : Ljava/lang/Object;
                  //   3: ifnull -> 20
                  //   6: aload_0
                  //   7: getfield this$0 : Lcom/roadtrack/onstar/adapter/HistoryFullAdapter;
                  //   10: aload_0
                  //   11: getfield val$position : I
                  //   14: invokestatic access$100 : (Lcom/roadtrack/onstar/adapter/HistoryFullAdapter;I)V
                  //   17: goto -> 181
                  //   20: aload_0
                  //   21: getfield this$0 : Lcom/roadtrack/onstar/adapter/HistoryFullAdapter;
                  //   24: aload_0
                  //   25: getfield val$position : I
                  //   28: invokevirtual getItem : (I)Ljava/lang/Object;
                  //   31: checkcast com/roadtrack/onstar/VO/Historical
                  //   34: astore #8
                  //   36: aload #8
                  //   38: invokevirtual getLatitud : ()Ljava/lang/String;
                  //   41: astore #6
                  //   43: aload #8
                  //   45: invokevirtual getLongitud : ()Ljava/lang/String;
                  //   48: astore #7
                  //   50: aload #6
                  //   52: ifnull -> 68
                  //   55: aload #6
                  //   57: astore_1
                  //   58: aload #6
                  //   60: ldc ''
                  //   62: invokevirtual equals : (Ljava/lang/Object;)Z
                  //   65: ifeq -> 71
                  //   68: ldc '0.0'
                  //   70: astore_1
                  //   71: aload #7
                  //   73: ifnull -> 90
                  //   76: aload #7
                  //   78: astore #6
                  //   80: aload #7
                  //   82: ldc ''
                  //   84: invokevirtual equals : (Ljava/lang/Object;)Z
                  //   87: ifeq -> 94
                  //   90: ldc '0.0'
                  //   92: astore #6
                  //   94: aload_1
                  //   95: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Double;
                  //   98: invokevirtual doubleValue : ()D
                  //   101: dstore #4
                  //   103: aload #6
                  //   105: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Double;
                  //   108: invokevirtual doubleValue : ()D
                  //   111: dstore_2
                  //   112: aload #8
                  //   114: invokevirtual getDateTime : ()Ljava/lang/String;
                  //   117: iconst_0
                  //   118: invokestatic niceDate : (Ljava/lang/String;I)Ljava/lang/String;
                  //   121: astore #6
                  //   123: new java/lang/StringBuilder
                  //   126: dup
                  //   127: invokespecial <init> : ()V
                  //   130: astore_1
                  //   131: aload_1
                  //   132: aload_0
                  //   133: getfield this$0 : Lcom/roadtrack/onstar/adapter/HistoryFullAdapter;
                  //   136: getstatic com/roadtrack/onstar/adapter/HistoryFullAdapter$ActionRow.GETACTIONNAME : Lcom/roadtrack/onstar/adapter/HistoryFullAdapter$ActionRow;
                  //   139: aload #8
                  //   141: getfield AplicationType : Ljava/lang/String;
                  //   144: invokestatic access$200 : (Lcom/roadtrack/onstar/adapter/HistoryFullAdapter;Lcom/roadtrack/onstar/adapter/HistoryFullAdapter$ActionRow;Ljava/lang/String;)Ljava/lang/String;
                  //   147: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                  //   150: pop
                  //   151: aload_1
                  //   152: ldc '\\n'
                  //   154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                  //   157: pop
                  //   158: aload_1
                  //   159: aload #6
                  //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
                  //   164: pop
                  //   165: aload_1
                  //   166: invokevirtual toString : ()Ljava/lang/String;
                  //   169: astore_1
                  //   170: aload_0
                  //   171: getfield this$0 : Lcom/roadtrack/onstar/adapter/HistoryFullAdapter;
                  //   174: dload #4
                  //   176: dload_2
                  //   177: aload_1
                  //   178: invokestatic access$300 : (Lcom/roadtrack/onstar/adapter/HistoryFullAdapter;DDLjava/lang/String;)V
                  //   181: return
                }
              });
          this.holder.LayHistory.setOnLongClickListener(new View.OnLongClickListener() {
                final HistoryFullAdapter this$0;
                
                final int val$position;
                
                public boolean onLongClick(View param1View) {
                  if (HistoryFullAdapter.mActionMode != null)
                    return false; 
                  HistoryFullAdapter.mActionMode = HistoryFullAdapter.this.holder.LayHistory.startActionMode(HistoryFullAdapter.this.mActionModeCallback);
                  ((ActionMode)HistoryFullAdapter.mActionMode).setTag(Integer.valueOf(position));
                  param1View.setSelected(true);
                  return true;
                }
              });
          try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            this("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(this.getDateTime);
            simpleDateFormat = new SimpleDateFormat();
            this("dd/MM/yyyy HH:mm");
            this.getDateTime = simpleDateFormat.format(date);
          } catch (Exception exception) {
            Utilities.escribeArchivo(this.TAG, "Error: fecha complete hist", exception.getMessage());
          } 
          this.holder.txtlineDesc1.setText(this.getDescription);
          this.holder.txtFecForFull.setText(this.getDateTime);
          this.holder.txtTitle.setVisibility(8);
          this.holder.txtStatusAction.setVisibility(8);
        } else if (this.getuserName.equals(String.valueOf(Enums.historical.commands.GetOpCode()))) {
          this.holder.LayHistory.setOnClickListener(new View.OnClickListener() {
                final HistoryFullAdapter this$0;
                
                final int val$position;
                
                public void onClick(View param1View) {
                  if (HistoryFullAdapter.mActionMode != null)
                    HistoryFullAdapter.this.onListItemSelected(position); 
                }
              });
          this.holder.LayHistory.setOnLongClickListener(new View.OnLongClickListener() {
                final HistoryFullAdapter this$0;
                
                final int val$position;
                
                public boolean onLongClick(View param1View) {
                  if (HistoryFullAdapter.mActionMode != null)
                    return false; 
                  HistoryFullAdapter.mActionMode = HistoryFullAdapter.this.holder.LayHistory.startActionMode(HistoryFullAdapter.this.mActionModeCallback);
                  ((ActionMode)HistoryFullAdapter.mActionMode).setTag(Integer.valueOf(position));
                  param1View.setSelected(true);
                  return true;
                }
              });
          if (exception.length > 0) {
            Drawable drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.yellowcircle, 2131165709);
            this.stringsResourcesVO = new StringsResourcesVO();
            String str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_main_lbl_seleccionado_1, 2131689938);
            String str4 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_accionstatusejecutado_1, 2131689888);
            String str3 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.global_lbl_accionstatusnoejecutado_1, 2131689900);
            if (this.getErrorId == Enums.actionStatus.None.GetCode()) {
              drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.yellowcircle, 2131165709);
            } else if (this.getErrorId == Enums.actionStatus.Success.GetCode()) {
              drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.response_ok, 2131165650);
              str2 = str4;
            } else if (this.getErrorId == Enums.actionStatus.Failure.GetCode()) {
              drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.response_nok, 2131165649);
              str2 = str3;
            } else {
              str2 = "En espera";
            } 
            this.holder.txtStatusAction.setText(str2);
            this.holder.btnImageStatus.setImageDrawable(drawable);
            this.holder.btnImageDescrip.setVisibility(8);
            this.holder.txtNameVehicle.setText(this.getName);
            this.holder.txtNameVehicle.setVisibility(0);
            try {
              int j = Integer.parseInt(getActionInfo(ActionRow.GETIMAGE, this.getAplicationType));
              this.holder.btnImageDescrip.setBackgroundResource(j);
            } catch (Exception exception1) {
              Utilities.escribeArchivo(this.TAG, "Error: Error en el formato", exception1.getMessage());
            } 
          } else {
            Drawable drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.yellowcircle, 2131165709);
            this.holder.btnImageStatus.setImageDrawable(drawable);
          } 
          this.holder.btnImageStatus.setVisibility(8);
          this.holder.txtTitle.setVisibility(8);
          String str1 = getActionInfo(ActionRow.GETACTIONNAME, this.getAplicationType);
          this.holder.txtlineDesc1.setText(str1);
          this.holder.txtFecForFull.setText(this.getDescription);
        } 
      } 
      this.getAplicationType = "";
      this.getCategory = "";
      this.getDescription = "";
      this.getName = "";
      this.getuserName = "";
      this.getErrorId = 0;
    } 
    if (this.mSelectedItemsIds.get(position))
      i = -1724598812; 
    view.setBackgroundColor(i);
    return view;
  }
  
  public void selectView(int paramInt, boolean paramBoolean) {
    if (paramBoolean) {
      this.mSelectedItemsIds.put(paramInt, paramBoolean);
    } else {
      this.mSelectedItemsIds.delete(paramInt);
    } 
    notifyDataSetChanged();
  }
  
  private enum ActionRow {
    GETACTIONNAME, GETIMAGE;
    
    private static final ActionRow[] $VALUES;
    
    static {
      $VALUES = new ActionRow[] { GETIMAGE, GETACTIONNAME };
    }
  }
  
  private class ViewHolder {
    LinearLayout LayHistory;
    
    ImageView btnImageDescrip;
    
    ImageView btnImageStatus;
    
    TextView txtDesc;
    
    TextView txtFecForFull;
    
    TextView txtHeaderDate;
    
    TextView txtId;
    
    TextView txtNameVehicle;
    
    TextView txtStatusAction;
    
    TextView txtTitle;
    
    TextView txtlineDesc1;
    
    private ViewHolder(HistoryFullAdapter this$0) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/HistoryFullAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */