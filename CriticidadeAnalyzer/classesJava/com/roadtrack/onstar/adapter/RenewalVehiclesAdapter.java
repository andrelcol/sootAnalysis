package com.roadtrack.onstar.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.VO.VehicleCatalogVO;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.util.LinkedList;

public class RenewalVehiclesAdapter extends BaseAdapter {
  private Context context;
  
  private TextView lbl_rvi_date;
  
  private TextView lbl_rvi_expiration;
  
  private TextView lbl_rvi_vehicle_name;
  
  private onstarApplication rtApp;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private Typeface tfLouis;
  
  private Typeface tfLouisBold;
  
  private LinkedList<VehicleCatalogVO> vehicleCatalogVOS;
  
  public RenewalVehiclesAdapter(LinkedList<VehicleCatalogVO> paramLinkedList, Context paramContext) {
    this.vehicleCatalogVOS = paramLinkedList;
    this.context = paramContext;
    this.stringsResourcesVO = new StringsResourcesVO();
  }
  
  private void formattedFont() {
    onstarApplication onstarApplication1 = this.rtApp;
    this.tfLouis = onstarApplication.getTypeface(this.context, onstarApplication1.fontPathLouisRegular);
    onstarApplication1 = this.rtApp;
    this.tfLouisBold = onstarApplication.getTypeface(this.context, onstarApplication1.fontPathLouisBold);
    this.lbl_rvi_vehicle_name.setTypeface(this.tfLouisBold);
    this.lbl_rvi_expiration.setTypeface(this.tfLouis);
    this.lbl_rvi_date.setTypeface(this.tfLouis);
  }
  
  public int getCount() {
    return this.vehicleCatalogVOS.size();
  }
  
  public Object getItem(int paramInt) {
    return this.vehicleCatalogVOS.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    View view = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2131427479, paramViewGroup, false);
    this.rtApp = (onstarApplication)this.context.getApplicationContext();
    this.lbl_rvi_vehicle_name = (TextView)view.findViewById(2131296783);
    this.lbl_rvi_expiration = (TextView)view.findViewById(2131296782);
    this.lbl_rvi_date = (TextView)view.findViewById(2131296781);
    formattedFont();
    this.lbl_rvi_vehicle_name.setText(((VehicleCatalogVO)this.vehicleCatalogVOS.get(paramInt)).getVehicleName());
    boolean bool = ((VehicleCatalogVO)this.vehicleCatalogVOS.get(paramInt)).getStatus_renewal_account().toString().equals(Enums.statusRenewalAccount.AlmostExpired.toString());
    String str = "";
    if (bool) {
      String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_popup_lbl_vencera, 2131690344);
      this.lbl_rvi_expiration.setText(str1);
      String str2 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_lbl_validez, 2131690324);
      str1 = str;
      if (((VehicleCatalogVO)this.vehicleCatalogVOS.get(paramInt)).getDateExpire() != null) {
        str1 = str;
        if (!((VehicleCatalogVO)this.vehicleCatalogVOS.get(paramInt)).getDateExpire().isEmpty()) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(str2);
          stringBuilder.append(" ");
          stringBuilder.append(((VehicleCatalogVO)this.vehicleCatalogVOS.get(paramInt)).getDateExpire());
          str1 = stringBuilder.toString();
        } 
      } 
      this.lbl_rvi_date.setText(str1);
    } else if (((VehicleCatalogVO)this.vehicleCatalogVOS.get(paramInt)).getStatus_renewal_account().toString().equals(Enums.statusRenewalAccount.Normal.toString())) {
      String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_popup_lbl_activo, 2131690327);
      this.lbl_rvi_expiration.setText(str1);
      this.lbl_rvi_date.setText("");
    } else {
      String str1 = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.renovacion_popup_lbl_expiro, 2131690332);
      this.lbl_rvi_expiration.setText(str1);
      this.lbl_rvi_expiration.setTextColor(ContextCompat.getColor(this.context, 2131034259));
      this.lbl_rvi_date.setText(((VehicleCatalogVO)this.vehicleCatalogVOS.get(paramInt)).getDateExpire());
      this.lbl_rvi_date.setTextColor(ContextCompat.getColor(this.context, 2131034259));
    } 
    return view;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/RenewalVehiclesAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */