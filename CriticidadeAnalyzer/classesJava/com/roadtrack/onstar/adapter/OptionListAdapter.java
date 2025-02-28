package com.roadtrack.onstar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.roadtrack.onstar.entities.OptionMenu;
import java.util.List;

public class OptionListAdapter extends BaseAdapter {
  static int ENNUM_FRAGMENT_NUMBER;
  
  public String AplicationList;
  
  public List<OptionMenu> MenuOption;
  
  boolean SaveMessageFlag = false;
  
  ImageView btn_sendDevice;
  
  ImageView btn_send_completeDevice;
  
  ImageView btn_send_waith;
  
  Context context;
  
  String getAplicationType = "";
  
  String getCategory = "";
  
  String getDescription = "";
  
  String getName = "";
  
  ViewHolder holder;
  
  LayoutInflater mInflater;
  
  public OptionListAdapter(Context paramContext, List<OptionMenu> paramList, String paramString1, String paramString2) {
    this.context = paramContext;
    this.MenuOption = paramList;
    this.AplicationList = paramString2;
  }
  
  @SuppressLint({"WrongViewCast"})
  private void saveChat(ViewHolder paramViewHolder, View paramView) {
    paramViewHolder.btn_send = (ImageView)paramView.findViewById(2131296436);
    ImageView imageView = paramViewHolder.btn_send;
    if (imageView != null)
      imageView.setVisibility(0); 
  }
  
  private int setEnummFragmentNumber(String paramString) {
    if (this.AplicationList.equals("Chat")) {
      ENNUM_FRAGMENT_NUMBER = 0;
    } else if (this.AplicationList.equals("DealerMaintenance")) {
      ENNUM_FRAGMENT_NUMBER = 1;
    } else if (this.AplicationList.equals("Settings")) {
      ENNUM_FRAGMENT_NUMBER = 2;
    } else if (this.AplicationList.equals("History")) {
      ENNUM_FRAGMENT_NUMBER = 3;
    } 
    if ((paramString.equals("HeaderDate") & (this.getCategory.equals("Chat") ^ true)) != 0)
      ENNUM_FRAGMENT_NUMBER = 4; 
    return 0;
  }
  
  private void setHolderControls(String paramString, ViewHolder paramViewHolder, View paramView) {
    int i = ENNUM_FRAGMENT_NUMBER;
    if (i != 0) {
      if (i != 1) {
        if (i != 2)
          if (i != 3) {
            if (i == 4) {
              LinearLayout linearLayout = (LinearLayout)paramView.findViewById(2131296283);
              if (paramString.equals("HeaderDate")) {
                ((LinearLayout)paramView.findViewById(2131296821)).setVisibility(8);
                linearLayout.setVisibility(0);
                paramViewHolder.txtHeaderDate = (TextView)paramView.findViewById(2131296492);
              } 
            } 
          } else {
            LinearLayout linearLayout1 = (LinearLayout)paramView.findViewById(2131296283);
            LinearLayout linearLayout2 = (LinearLayout)paramView.findViewById(2131296821);
            if (paramString.equals("HeaderDate")) {
              linearLayout1.setVisibility(0);
              linearLayout2.setVisibility(8);
              paramViewHolder.txtHeaderDate = (TextView)paramView.findViewById(2131296492);
              paramViewHolder.txtHeaderDate.setText(this.getName);
              ((TextView)paramView.findViewById(2131297184)).setText("");
            } else {
              linearLayout1.setVisibility(8);
              linearLayout2.setVisibility(0);
              paramViewHolder.txtHeaderDate = (TextView)paramView.findViewById(2131297184);
              paramViewHolder.txtHeaderDate.setText("");
              paramViewHolder.txtTitle = (TextView)paramView.findViewById(2131297175);
              paramViewHolder.txtTitle.setText("");
            } 
          }  
      } else {
        paramViewHolder.txtId = (TextView)paramView.findViewById(2131296606);
        paramViewHolder.txtHeaderDate = (TextView)paramView.findViewById(2131296313);
        paramViewHolder.txtTitle = (TextView)paramView.findViewById(2131296270);
        paramViewHolder.txtDesc = (TextView)paramView.findViewById(2131297096);
      } 
    } else {
      LinearLayout linearLayout3 = (LinearLayout)paramView.findViewById(2131296283);
      LinearLayout linearLayout2 = (LinearLayout)paramView.findViewById(2131296821);
      LinearLayout linearLayout1 = (LinearLayout)paramView.findViewById(2131296822);
      if (paramString.equalsIgnoreCase("HeaderDate")) {
        linearLayout3.setVisibility(0);
        linearLayout2.setVisibility(8);
        linearLayout1.setVisibility(8);
        paramViewHolder.txtHeaderDate = (TextView)paramView.findViewById(2131296492);
      } else {
        if (paramString.equalsIgnoreCase("external")) {
          linearLayout3.setVisibility(8);
          linearLayout2.setVisibility(0);
          linearLayout1.setVisibility(8);
          paramViewHolder.txtTitle = (TextView)paramView.findViewById(2131296310);
          paramViewHolder.txtDesc = (TextView)paramView.findViewById(2131297102);
        } else if (paramString.equalsIgnoreCase("app")) {
          linearLayout3.setVisibility(8);
          linearLayout2.setVisibility(8);
          linearLayout1.setVisibility(0);
          paramViewHolder.txtTitle = (TextView)paramView.findViewById(2131296311);
          paramViewHolder.txtDesc = (TextView)paramView.findViewById(2131297103);
          this.btn_sendDevice = (ImageView)paramView.findViewById(2131296428);
          this.btn_sendDevice.setVisibility(8);
          this.btn_send_completeDevice = (ImageView)paramView.findViewById(2131296429);
          this.btn_send_completeDevice.setVisibility(8);
          this.btn_send_waith = (ImageView)paramView.findViewById(2131296430);
          this.btn_send_waith.setVisibility(8);
        } 
        if (this.SaveMessageFlag)
          saveChat(paramViewHolder, paramView); 
      } 
    } 
  }
  
  private View setInflaterLayout(String paramString) {
    View view;
    this.mInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
    setEnummFragmentNumber(paramString);
    int i = ENNUM_FRAGMENT_NUMBER;
    paramString = null;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i == 4)
              view = this.mInflater.inflate(2131427421, null); 
          } else {
            view = this.mInflater.inflate(2131427421, null);
          } 
        } else {
          view = this.mInflater.inflate(2131427483, null);
        } 
      } else {
        view = this.mInflater.inflate(2131427445, null);
      } 
    } else {
      this.SaveMessageFlag = true;
      view = this.mInflater.inflate(2131427407, null);
    } 
    return view;
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
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    this.mInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
    this.getAplicationType = ((OptionMenu)this.MenuOption.get(paramInt)).getAplicationType();
    this.getCategory = ((OptionMenu)this.MenuOption.get(paramInt)).getCategory();
    this.getDescription = ((OptionMenu)this.MenuOption.get(paramInt)).getDescription();
    this.getName = ((OptionMenu)this.MenuOption.get(paramInt)).getName();
    if (paramView == null) {
      paramView = setInflaterLayout(this.getAplicationType);
      this.holder = new ViewHolder();
      paramView.setTag(this.holder);
    } else {
      setEnummFragmentNumber(this.getAplicationType);
      if (!this.AplicationList.equals(this.getCategory))
        paramView = setInflaterLayout(this.getAplicationType); 
      this.holder = (ViewHolder)paramView.getTag();
    } 
    setHolderControls(this.getAplicationType, this.holder, paramView);
    if (this.AplicationList.equals("Chat") && this.getAplicationType.equalsIgnoreCase("app")) {
      if (((OptionMenu)this.MenuOption.get(paramInt)).getWaith() == 1) {
        this.btn_send_waith.setVisibility(0);
        this.btn_sendDevice.setVisibility(8);
        this.btn_send_completeDevice.setVisibility(8);
      } 
      if (((OptionMenu)this.MenuOption.get(paramInt)).getSend() == 1) {
        this.btn_send_waith.setVisibility(8);
        this.btn_sendDevice.setVisibility(0);
        this.btn_send_completeDevice.setVisibility(8);
      } 
      if (((OptionMenu)this.MenuOption.get(paramInt)).getSend_complete() == 1) {
        this.btn_send_waith.setVisibility(8);
        this.btn_sendDevice.setVisibility(0);
        this.btn_send_completeDevice.setVisibility(0);
      } 
    } 
    TextView textView = this.holder.txtTitle;
    if (textView != null)
      textView.setText(this.getDescription); 
    textView = this.holder.txtDesc;
    if (textView != null) {
      textView.setText(this.getDescription);
      if (this.getAplicationType.equalsIgnoreCase("app") || this.getAplicationType.equalsIgnoreCase("external"))
        this.holder.txtTitle.setText(this.getName); 
    } 
    textView = this.holder.txtId;
    if (textView != null)
      textView.setText(this.getAplicationType); 
    if (this.holder.txtHeaderDate != null)
      if (this.getAplicationType.equals("HeaderDate")) {
        String str2 = this.getDescription.split(":")[0];
        TextView textView1 = this.holder.txtHeaderDate;
        String str1 = str2;
        if (str2.equals(""))
          str1 = this.getName; 
        textView1.setText(str1);
      } else {
        this.holder.txtHeaderDate.setText(this.getName);
      }  
    this.getAplicationType = "";
    this.getCategory = "";
    this.getDescription = "";
    this.getName = "";
    return paramView;
  }
  
  private class ViewHolder {
    ImageView btn_send;
    
    TextView txtDesc;
    
    TextView txtHeaderDate;
    
    TextView txtId;
    
    TextView txtTitle;
    
    private ViewHolder(OptionListAdapter this$0) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/OptionListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */