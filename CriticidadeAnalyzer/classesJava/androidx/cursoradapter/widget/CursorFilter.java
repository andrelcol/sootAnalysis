package androidx.cursoradapter.widget;

import android.database.Cursor;
import android.widget.Filter;

class CursorFilter extends Filter {
  CursorFilterClient mClient;
  
  CursorFilter(CursorFilterClient paramCursorFilterClient) {
    this.mClient = paramCursorFilterClient;
  }
  
  public CharSequence convertResultToString(Object paramObject) {
    return this.mClient.convertToString((Cursor)paramObject);
  }
  
  protected Filter.FilterResults performFiltering(CharSequence paramCharSequence) {
    Cursor cursor = this.mClient.runQueryOnBackgroundThread(paramCharSequence);
    Filter.FilterResults filterResults = new Filter.FilterResults();
    if (cursor != null) {
      filterResults.count = cursor.getCount();
      filterResults.values = cursor;
    } else {
      filterResults.count = 0;
      filterResults.values = null;
    } 
    return filterResults;
  }
  
  protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults) {
    Cursor cursor = this.mClient.getCursor();
    Object object = paramFilterResults.values;
    if (object != null && object != cursor)
      this.mClient.changeCursor((Cursor)object); 
  }
  
  static interface CursorFilterClient {
    void changeCursor(Cursor param1Cursor);
    
    CharSequence convertToString(Cursor param1Cursor);
    
    Cursor getCursor();
    
    Cursor runQueryOnBackgroundThread(CharSequence param1CharSequence);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/cursoradapter/widget/CursorFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */