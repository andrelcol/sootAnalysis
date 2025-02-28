package com.roadtrack.onstar.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.roadtrack.onstar.mapData.ClassMapData;
import java.util.ArrayList;

public class AdapterDialogMultiCheckBox extends ArrayAdapter<AdapterDialogMultiCheckBox.ViewHolder> {
  private Activity activity;
  
  public CompoundButton.OnCheckedChangeListener checkListener;
  
  private int contador;
  
  private ArrayList<ClassMapData> listSeleccionado = new ArrayList<ClassMapData>();
  
  private int resource;
  
  public AdapterDialogMultiCheckBox(Context paramContext, int paramInt, ArrayList<ClassMapData> paramArrayList) {
    super(paramContext, paramInt);
    new ArrayList();
    this.checkListener = new CompoundButton.OnCheckedChangeListener() {
        final AdapterDialogMultiCheckBox this$0;
        
        public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
          if (AdapterDialogMultiCheckBox.this.contador < 2) {
            if (param1CompoundButton.isChecked()) {
              ((ClassMapData)AdapterDialogMultiCheckBox.this.listSeleccionado.get(param1CompoundButton.getId())).setValue(param1Boolean);
              AdapterDialogMultiCheckBox.access$208(AdapterDialogMultiCheckBox.this);
            } else {
              ((ClassMapData)AdapterDialogMultiCheckBox.this.listSeleccionado.get(param1CompoundButton.getId())).setValue(param1Boolean);
              AdapterDialogMultiCheckBox.access$202(AdapterDialogMultiCheckBox.this, 0);
            } 
          } else {
            if (((ClassMapData)AdapterDialogMultiCheckBox.this.listSeleccionado.get(param1CompoundButton.getId())).isValue())
              AdapterDialogMultiCheckBox.access$210(AdapterDialogMultiCheckBox.this); 
            param1CompoundButton.setChecked(false);
            ((ClassMapData)AdapterDialogMultiCheckBox.this.listSeleccionado.get(param1CompoundButton.getId())).setValue(false);
          } 
        }
      };
    this.activity = (Activity)paramContext;
    this.resource = paramInt;
    this.listSeleccionado = paramArrayList;
  }
  
  public int getCount() {
    return this.listSeleccionado.size();
  }
  
  public ArrayList<ClassMapData> getListSeleccionado() {
    return this.listSeleccionado;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    View view = this.activity.getLayoutInflater().inflate(this.resource, null);
    ViewHolder viewHolder = new ViewHolder();
    ViewHolder.access$002(viewHolder, (CheckBox)view.findViewById(2131296450));
    ViewHolder.access$102(viewHolder, (TextView)view.findViewById(2131297162));
    viewHolder.lugarMapa.setText(((ClassMapData)this.listSeleccionado.get(paramInt)).getData());
    viewHolder.seleccionador.setId(paramInt);
    if (((ClassMapData)this.listSeleccionado.get(paramInt)).isValue()) {
      viewHolder.seleccionador.setChecked(true);
    } else {
      viewHolder.seleccionador.setChecked(false);
    } 
    viewHolder.seleccionador.setOnCheckedChangeListener(this.checkListener);
    return view;
  }
  
  static class ViewHolder {
    private TextView lugarMapa;
    
    private CheckBox seleccionador;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/AdapterDialogMultiCheckBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */