package com.roadtrack.onstar.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.MenuItem;
import androidx.fragment.app.DialogFragment;
import com.roadtrack.onstar.VO.FavoritesHistoryVO;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.utils.Utilities;

@SuppressLint({"NewApi"})
public class ConfirmEraseDialog extends DialogFragment {
  private MenuItem item;
  
  private listExpandibleAdapter listAdapter;
  
  private ActionMode mode;
  
  private SparseBooleanArray selected;
  
  private String title;
  
  public static ConfirmEraseDialog newInstance() {
    return new ConfirmEraseDialog();
  }
  
  public Dialog onCreateDialog(Bundle paramBundle) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    StringsResourcesVO stringsResourcesVO = new StringsResourcesVO();
    String str = Utilities.getStringFromConfigList((Context)getActivity(), stringsResourcesVO.BtnDelete, 2131689483);
    builder.setMessage(this.title).setPositiveButton(str, new DialogInterface.OnClickListener() {
          final ConfirmEraseDialog this$0;
          
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            for (param1Int = ConfirmEraseDialog.this.selected.size() - 1; param1Int >= 0; param1Int--) {
              if (ConfirmEraseDialog.this.selected.valueAt(param1Int))
                ConfirmEraseDialog.this.listAdapter.remove((FavoritesHistoryVO)ConfirmEraseDialog.this.listAdapter.getChildObject(0, ConfirmEraseDialog.this.selected.keyAt(param1Int))); 
            } 
            ConfirmEraseDialog.this.item.setVisible(false);
            ConfirmEraseDialog.this.mode.finish();
            param1DialogInterface.cancel();
          }
        });
    return (Dialog)builder.create();
  }
  
  public void setValues(String paramString, SparseBooleanArray paramSparseBooleanArray, MenuItem paramMenuItem, ActionMode paramActionMode, listExpandibleAdapter paramlistExpandibleAdapter) {
    this.title = paramString;
    this.selected = paramSparseBooleanArray;
    this.item = paramMenuItem;
    this.mode = paramActionMode;
    this.listAdapter = paramlistExpandibleAdapter;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/adapter/ConfirmEraseDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */