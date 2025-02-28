package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.VO.PaymentHistoryResponseO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.List;

public class PaymentHistoryAdapter extends BaseAdapter {
  private Context context;
  
  private TextView lbl_iph_date;
  
  private TextView lbl_iph_description;
  
  private TextView lbl_iph_monthly_price;
  
  private TextView lbl_iph_status;
  
  private List<PaymentHistoryResponseO> paymentHistoryList;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tfLouis;
  
  public PaymentHistoryAdapter(Context paramContext, List<PaymentHistoryResponseO> paramList) {
    this.context = paramContext;
    this.paymentHistoryList = paramList;
  }
  
  private void formattedFont() {
    onstarApplication onstarApplication1 = this.rtApp;
    this.tfLouis = onstarApplication.getTypeface(this.context, onstarApplication1.fontPathLouisRegular);
    this.lbl_iph_description.setTypeface(this.tfLouis);
    this.lbl_iph_monthly_price.setTypeface(this.tfLouis);
    this.lbl_iph_date.setTypeface(this.tfLouis);
    this.lbl_iph_status.setTypeface(this.tfLouis);
  }
  
  public int getCount() {
    return this.paymentHistoryList.size();
  }
  
  public Object getItem(int paramInt) {
    return this.paymentHistoryList.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    byte b1;
    StringBuilder stringBuilder2;
    this.stringsResourcesVO = new StringsResourcesVO();
    LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
    byte b2 = 0;
    View view = layoutInflater.inflate(2131427425, paramViewGroup, false);
    this.rtApp = (onstarApplication)this.context.getApplicationContext();
    this.lbl_iph_description = (TextView)view.findViewById(2131296752);
    this.lbl_iph_monthly_price = (TextView)view.findViewById(2131296753);
    this.lbl_iph_date = (TextView)view.findViewById(2131296751);
    this.lbl_iph_status = (TextView)view.findViewById(2131296754);
    formattedFont();
    this.lbl_iph_description.setText(((PaymentHistoryResponseO)this.paymentHistoryList.get(paramInt)).getChpres5());
    try {
      b1 = Integer.parseInt(((PaymentHistoryResponseO)this.paymentHistoryList.get(paramInt)).getChpres2());
    } catch (NumberFormatException numberFormatException) {
      b1 = b2;
    } 
    String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_signomoneda, 2131690316);
    String str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_aunpago2, 2131690282);
    if (b1 > 1) {
      stringBuilder2 = new StringBuilder();
      stringBuilder2.append(((PaymentHistoryResponseO)this.paymentHistoryList.get(paramInt)).getChpres2());
      stringBuilder2.append("x ");
      stringBuilder2.append(str1);
      stringBuilder2.append(((PaymentHistoryResponseO)this.paymentHistoryList.get(paramInt)).getChpres6());
      str1 = stringBuilder2.toString();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append(((PaymentHistoryResponseO)this.paymentHistoryList.get(paramInt)).getChpres6());
      stringBuilder.append(" ");
      stringBuilder.append((String)stringBuilder2);
      str1 = stringBuilder.toString();
    } 
    this.lbl_iph_monthly_price.setText(str1);
    String str3 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_histohoras, 2131690293);
    TextView textView = this.lbl_iph_date;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(((PaymentHistoryResponseO)this.paymentHistoryList.get(paramInt)).getChpres4());
    stringBuilder1.append(str3);
    textView.setText(stringBuilder1.toString());
    this.lbl_iph_status.setText(((PaymentHistoryResponseO)this.paymentHistoryList.get(paramInt)).getChpres3());
    if (paramInt % 2 == 0) {
      this.lbl_iph_description.setBackgroundColor(ContextCompat.getColor(this.context, 2131034150));
      this.lbl_iph_monthly_price.setBackgroundColor(ContextCompat.getColor(this.context, 2131034150));
      this.lbl_iph_date.setBackgroundColor(ContextCompat.getColor(this.context, 2131034150));
      this.lbl_iph_status.setBackgroundColor(ContextCompat.getColor(this.context, 2131034150));
    } 
    return view;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/PaymentHistoryAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */