package androidx.core.app;

import android.view.View;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback {
  public abstract void onMapSharedElements(List<String> paramList, Map<String, View> paramMap);
  
  public abstract void onSharedElementEnd(List<String> paramList, List<View> paramList1, List<View> paramList2);
  
  public abstract void onSharedElementStart(List<String> paramList, List<View> paramList1, List<View> paramList2);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/app/SharedElementCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */