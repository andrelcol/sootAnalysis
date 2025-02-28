package androidx.appcompat.app;

import androidx.appcompat.view.ActionMode;

public interface AppCompatCallback {
  void onSupportActionModeFinished(ActionMode paramActionMode);
  
  void onSupportActionModeStarted(ActionMode paramActionMode);
  
  ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/app/AppCompatCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */