package com.roadtrack.onstar.BO;

import android.app.IntentService;
import android.content.Intent;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.VO.commonQueue;
import com.roadtrack.onstar.entities.OptionMenu;
import java.util.Hashtable;
import java.util.Iterator;

public class MessagesDispatchService extends IntentService {
  private static commonQueue inQueue;
  
  public static boolean terminate = false;
  
  public MessagesDispatchService() {
    super("MessagesDispatchService");
  }
  
  public static void ManageQueue() {
    inQueue = new commonQueue();
    if (GlobalMembers.mHistoryList.size() > 0 && GlobalMembers.waitQueueResponceMessages.count() == 0) {
      Iterator<OptionMenu> iterator = GlobalMembers.mHistoryList.iterator();
      for (byte b = 0; iterator.hasNext(); b++) {
        OptionMenu optionMenu = iterator.next();
        if (optionMenu.send_complete == 0) {
          Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
          hashtable.put(String.valueOf(b), optionMenu);
          enqueueInner((Hashtable)hashtable);
        } 
      } 
    } 
    while (inQueue.count() > 0) {
      if (terminate) {
        GlobalMembers.waitQueueResponceMessages.clear();
        terminate = false;
        break;
      } 
      Hashtable hashtable = (Hashtable)inQueue.dequeue();
      String str = hashtable.keys().nextElement();
      sendingToPlatinum(((OptionMenu)hashtable.get(str)).getDescription(), str);
      GlobalMembers.waitQueueResponceMessages.enqueue(hashtable);
    } 
  }
  
  public static void enqueueInner(Hashtable<String, Object> paramHashtable) {
    inQueue.enqueue(paramHashtable);
  }
  
  private static void sendingToPlatinum(String paramString1, String paramString2) {
    MainActivity.getmNPlatinum().textMessage(paramString1, 0);
    ((OptionMenu)GlobalMembers.mHistoryList.get(Integer.parseInt(paramString2))).setSend(1);
  }
  
  protected void onHandleIntent(Intent paramIntent) {
    ManageQueue();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/MessagesDispatchService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */