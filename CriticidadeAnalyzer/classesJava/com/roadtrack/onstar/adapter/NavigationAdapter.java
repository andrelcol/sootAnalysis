package com.roadtrack.onstar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.roadtrack.onstar.item_objct;
import java.util.ArrayList;

public class NavigationAdapter extends BaseAdapter {
  Activity activity;
  
  ArrayList<item_objct> arrayitms;
  
  public NavigationAdapter(Activity paramActivity, ArrayList<item_objct> paramArrayList) {
    this.activity = paramActivity;
    this.arrayitms = paramArrayList;
  }
  
  public int getCount() {
    return this.arrayitms.size();
  }
  
  public Object getItem(int paramInt) {
    return this.arrayitms.get(paramInt);
  }
  
  public long getItemId(int paramInt) {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    LayoutInflater layoutInflater = this.activity.getLayoutInflater();
    if (paramView == null) {
      Fila fila = new Fila();
      item_objct item_objct = this.arrayitms.get(paramInt);
      paramView = layoutInflater.inflate(2131427404, null);
      fila.titulo_itm = (TextView)paramView.findViewById(2131297129);
      fila.titulo_itm.setText(item_objct.getTitulo());
      fila.icono = (ImageView)paramView.findViewById(2131296599);
      fila.icono.setImageResource(item_objct.getIcono());
      paramView.setTag(fila);
    } else {
      Fila fila = (Fila)paramView.getTag();
    } 
    return paramView;
  }
  
  public static class Fila {
    ImageView icono;
    
    TextView titulo_itm;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/NavigationAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */