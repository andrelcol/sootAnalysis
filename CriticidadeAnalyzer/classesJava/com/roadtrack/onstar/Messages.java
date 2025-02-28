package com.roadtrack.onstar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.BO.MessagesDispatchService;
import com.roadtrack.onstar.BO.MessagesManager;
import com.roadtrack.onstar.BO.MessagesObjects;
import com.roadtrack.onstar.BO.PreferenceRT;
import com.roadtrack.onstar.VO.StringsResourcesVO;
import com.roadtrack.onstar.adapter.OptionListAdapter;
import com.roadtrack.onstar.entities.OptionMenu;
import com.roadtrack.onstar.utils.Utilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Messages extends FragmentActivity {
  public static boolean flgInitHeader = false;
  
  private String CopyValue = "";
  
  LinearLayout LayDisableMessage;
  
  LinearLayout LayMessages;
  
  String SVED_ISDATASAVED;
  
  String SVED_TXTMESSAGE;
  
  Bundle SveInstance;
  
  private TextView aux;
  
  ProgressBar bar;
  
  private Button btnSendMessage;
  
  public Context context;
  
  private MessagesAsyncTask controlViewsMessages;
  
  private boolean isSavedData;
  
  private Date lastMessageDay;
  
  LinearLayout layProgress;
  
  View.OnCreateContextMenuListener longPressMenu;
  
  public ListView lvDatos;
  
  private List<OptionMenu> mContent = new ArrayList<OptionMenu>();
  
  View.OnCreateContextMenuListener mConversation;
  
  private StringsResourcesVO stringsResourcesVO;
  
  private EditText txtMessage;
  
  private String txtMessageData;
  
  public final Handler updateMessageHandler;
  
  private View vista;
  
  public Messages() {
    new GlobalMembers();
    this.SVED_ISDATASAVED = "SVED_isSaveData";
    this.SVED_TXTMESSAGE = "SVED_txtMessage";
    this.txtMessageData = "";
    this.isSavedData = false;
    this.controlViewsMessages = null;
    this.longPressMenu = new View.OnCreateContextMenuListener() {
        final Messages this$0;
        
        public void onCreateContextMenu(ContextMenu param1ContextMenu, View param1View, ContextMenu.ContextMenuInfo param1ContextMenuInfo) {
          param1ContextMenu.clear();
          Messages messages1 = Messages.this;
          String str1 = Utilities.getStringFromConfigList(messages1.context, messages1.stringsResourcesVO.ActionSelect, 2131689473);
          Messages messages2 = Messages.this;
          String str2 = Utilities.getStringFromConfigList(messages2.context, messages2.stringsResourcesVO.MessagePaste, 2131689525);
          param1ContextMenu.setHeaderTitle(str1);
          param1ContextMenu.add(0, 0, 0, str2);
        }
      };
    this.mConversation = new View.OnCreateContextMenuListener() {
        final Messages this$0;
        
        public void onCreateContextMenu(ContextMenu param1ContextMenu, View param1View, ContextMenu.ContextMenuInfo param1ContextMenuInfo) {
          param1ContextMenu.clear();
          Messages messages = Messages.this;
          param1ContextMenu.setHeaderTitle(Utilities.getStringFromConfigList(messages.context, messages.stringsResourcesVO.ActionSelect, 2131689473));
          messages = Messages.this;
          param1ContextMenu.add(0, 1, 1, Utilities.getStringFromConfigList(messages.context, messages.stringsResourcesVO.MessageCopy, 2131689524));
        }
      };
    this.updateMessageHandler = new Handler() {
        final Messages this$0;
        
        public void handleMessage(Message param1Message) {
          if (param1Message.what == Enums$WhatHandler.Device_OpCodes.ordinal() && param1Message.arg1 == MessagesObjects.Device_OpCodes.OutboundMsg.GetOpCode()) {
            int i = param1Message.arg2;
            if (i == -1) {
              ((OptionMenu)GlobalMembers.mHistoryList.get(i)).setSend(1);
            } else {
              if (Integer.parseInt((String)param1Message.obj) == Enums$statusGeneric.Success.GetOpCode()) {
                ((OptionMenu)GlobalMembers.mHistoryList.get(i)).setSend_complete(1);
              } else {
                Enums$statusGeneric.Failure.GetOpCode();
              } 
              MessagesManager.saveFile("chatmessages", Messages.this.context);
            } 
            Messages.this.setSavedContent(GlobalMembers.mHistoryList);
          } 
          if (param1Message.what == Enums$WhatHandler.Platinum_OpCodes.ordinal() && param1Message.arg1 == MessagesObjects.Device_OpCodes.InboundMsg.GetOpCode())
            Messages.this.RefreshCallBack(); 
        }
      };
    new Handler() {
        final Messages this$0;
        
        public void handleMessage(Message param1Message) {
          // Byte code:
          //   0: aload_0
          //   1: monitorenter
          //   2: getstatic com/roadtrack/onstar/Enums$SettingsPreference.UserServices : Lcom/roadtrack/onstar/Enums$SettingsPreference;
          //   5: ldc ''
          //   7: aload_0
          //   8: getfield this$0 : Lcom/roadtrack/onstar/Messages;
          //   11: getfield context : Landroid/content/Context;
          //   14: invokestatic GetValuePreference : (Lcom/roadtrack/onstar/Enums$SettingsPreference;Ljava/lang/Object;Landroid/content/Context;)Ljava/lang/Object;
          //   17: invokevirtual toString : ()Ljava/lang/String;
          //   20: ldc ''
          //   22: invokevirtual equals : (Ljava/lang/Object;)Z
          //   25: ifeq -> 54
          //   28: aload_0
          //   29: getfield this$0 : Lcom/roadtrack/onstar/Messages;
          //   32: getfield LayDisableMessage : Landroid/widget/LinearLayout;
          //   35: iconst_0
          //   36: invokevirtual setVisibility : (I)V
          //   39: aload_0
          //   40: getfield this$0 : Lcom/roadtrack/onstar/Messages;
          //   43: getfield LayMessages : Landroid/widget/LinearLayout;
          //   46: bipush #8
          //   48: invokevirtual setVisibility : (I)V
          //   51: goto -> 103
          //   54: aload_0
          //   55: getfield this$0 : Lcom/roadtrack/onstar/Messages;
          //   58: getfield LayMessages : Landroid/widget/LinearLayout;
          //   61: iconst_0
          //   62: invokevirtual setVisibility : (I)V
          //   65: aload_0
          //   66: getfield this$0 : Lcom/roadtrack/onstar/Messages;
          //   69: getfield LayDisableMessage : Landroid/widget/LinearLayout;
          //   72: bipush #8
          //   74: invokevirtual setVisibility : (I)V
          //   77: aload_0
          //   78: getfield this$0 : Lcom/roadtrack/onstar/Messages;
          //   81: invokestatic access$500 : (Lcom/roadtrack/onstar/Messages;)V
          //   84: aload_0
          //   85: getfield this$0 : Lcom/roadtrack/onstar/Messages;
          //   88: getfield bar : Landroid/widget/ProgressBar;
          //   91: bipush #8
          //   93: invokevirtual setVisibility : (I)V
          //   96: aload_0
          //   97: getfield this$0 : Lcom/roadtrack/onstar/Messages;
          //   100: invokestatic access$600 : (Lcom/roadtrack/onstar/Messages;)V
          //   103: aload_0
          //   104: monitorexit
          //   105: return
          //   106: astore_1
          //   107: aload_0
          //   108: monitorexit
          //   109: aload_1
          //   110: athrow
          // Exception table:
          //   from	to	target	type
          //   2	51	106	finally
          //   54	103	106	finally
        }
      };
  }
  
  private void ActivateSendButton(boolean paramBoolean) {
    this.btnSendMessage.setEnabled(paramBoolean);
    if (paramBoolean) {
      this.btnSendMessage.setOnClickListener(new View.OnClickListener() {
            final Messages this$0;
            
            public void onClick(View param1View) {
              Date date = new Date();
              if (Messages.this.lastMessageDay == null)
                Messages.access$202(Messages.this, date); 
              if (Messages.flgInitHeader || Messages.this.lastMessageDay.getDate() < date.getDate()) {
                Messages messages1 = Messages.this;
                messages1.CreateListData("HeaderDate", "", false, "", messages1.context, 0);
                Messages.flgInitHeader = false;
              } 
              String str = Messages.this.txtMessage.getText().toString();
              Messages messages = Messages.this;
              messages.CreateListData("app", str, false, "", messages.context, 1);
              MessagesManager.saveFile("chatmessages", Messages.this.context);
              Messages.this.txtMessage.setText("");
              Utilities.ServiceRunning(MessagesDispatchService.class.getName(), Messages.this.context);
            }
          });
    } else {
      this.btnSendMessage.setOnClickListener(null);
    } 
  }
  
  private void fillData() {
    try {
      ActivateSendButton(false);
      this.txtMessage.setText("");
      if (this.mContent.size() > 0) {
        GlobalMembers.mHistoryList = this.mContent;
        CreateListData("", "", false, "", this.context, 0);
      } else {
        this.lvDatos.setOnCreateContextMenuListener(this.mConversation);
        setAdapterData("Center", this.context);
        stopProgress();
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("Messages", "Error: fillData", exception.getMessage());
    } 
  }
  
  private void setAdapterData(String paramString, Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/roadtrack/onstar/adapter/OptionListAdapter
    //   5: astore_3
    //   6: aload_3
    //   7: aload_2
    //   8: getstatic com/roadtrack/onstar/BO/GlobalMembers.mHistoryList : Ljava/util/List;
    //   11: aload_1
    //   12: ldc 'Chat'
    //   14: invokespecial <init> : (Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V
    //   17: aload_3
    //   18: invokevirtual notifyDataSetChanged : ()V
    //   21: aload_0
    //   22: getfield lvDatos : Landroid/widget/ListView;
    //   25: aload_3
    //   26: invokevirtual setAdapter : (Landroid/widget/ListAdapter;)V
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	32	finally
  }
  
  private void setSavedContent(List<OptionMenu> paramList) {
    OptionListAdapter optionListAdapter = new OptionListAdapter((Context)this, paramList, "Left", "Chat");
    optionListAdapter.notifyDataSetChanged();
    this.lvDatos.setAdapter((ListAdapter)optionListAdapter);
  }
  
  @SuppressLint({"WrongViewCast"})
  private void startActions() {
    this.layProgress = (LinearLayout)this.vista.findViewById(2131296963);
    this.lvDatos = (ListView)this.vista.findViewById(2131296295);
    this.txtMessage = (EditText)this.vista.findViewById(2131296509);
    this.txtMessage.setText(this.txtMessageData);
    String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.writeMessage, 2131690533);
    this.txtMessage.setHint(str);
    this.btnSendMessage = (Button)this.vista.findViewById(2131296436);
    str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.send, 2131690359);
    this.btnSendMessage.setText(str);
    this.bar = (ProgressBar)this.vista.findViewById(2131296964);
    this.txtMessage.addTextChangedListener(new TextWatcher() {
          final Messages this$0;
          
          public void afterTextChanged(Editable param1Editable) {}
          
          public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
          
          public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
            boolean bool;
            if (param1CharSequence.toString().trim().length() > 0) {
              bool = true;
            } else {
              bool = false;
            } 
            Messages.this.ActivateSendButton(bool);
          }
        });
  }
  
  private void stopProgress() {
    this.txtMessage.setEnabled(true);
    this.bar.setVisibility(8);
    this.layProgress.setVisibility(8);
    this.lvDatos.setVisibility(0);
  }
  
  public void CreateListData(String paramString1, String paramString2, boolean paramBoolean, String paramString3, Context paramContext, int paramInt) {
    byte b;
    int i = paramString1.length();
    boolean bool = true;
    if (i == 0) {
      i = 1;
    } else {
      i = 0;
    } 
    if (paramString2.length() == 0) {
      b = 1;
    } else {
      b = 0;
    } 
    if (GlobalMembers.mHistoryList.size() <= 0)
      bool = false; 
    if ((i & b & bool) != 0) {
      setAdapterData("", paramContext);
    } else {
      List<OptionMenu> list = this.mContent;
      if (list != null && list.size() > 0)
        GlobalMembers.mHistoryList = this.mContent; 
      GlobalMembers.mHistoryList.add(fillChat(paramString1, paramString2, paramBoolean, paramString3, paramInt));
      if (paramString1.equalsIgnoreCase("external")) {
        paramString1 = "Left";
      } else if (paramString1.equalsIgnoreCase("app")) {
        paramString1 = "Right";
      } else {
        paramString1 = "Center";
      } 
      if (this.lvDatos != null) {
        if (GlobalMembers.mHistoryList.size() > 0)
          this.lvDatos.setOnCreateContextMenuListener(this.mConversation); 
        setAdapterData(paramString1, paramContext);
      } 
    } 
  }
  
  public void RefreshCallBack() {
    /* monitor enter ThisExpression{ObjectType{com/roadtrack/onstar/Messages}} */
    try {
      this.bar.setVisibility(8);
      fillData();
    } catch (Exception exception) {
      Utilities.escribeArchivo("Messages", "Error: RefreshCallBack", exception.getMessage());
    } finally {
      Exception exception;
    } 
    /* monitor exit ThisExpression{ObjectType{com/roadtrack/onstar/Messages}} */
  }
  
  public OptionMenu fillChat(String paramString1, String paramString2, boolean paramBoolean, String paramString3, int paramInt) {
    OptionMenu optionMenu = new OptionMenu();
    try {
      optionMenu.Category = "Chat";
      optionMenu.id = "0";
      if (paramInt != 0)
        if (paramInt != 1) {
          if (paramInt != 2) {
            if (paramInt == 3)
              optionMenu.send_complete = 1; 
          } else {
            optionMenu.send = 1;
          } 
        } else {
          optionMenu.waith = 1;
        }  
      if (paramBoolean) {
        if (paramString1.equals("HeaderDate")) {
          this.lastMessageDay = GlobalMembers.DateFormat.parse(paramString3);
          optionMenu.Name = paramString3;
        } else {
          optionMenu.Name = paramString3.substring(11, 16);
        } 
        optionMenu.DateMessage = paramString3;
        optionMenu.Description = paramString2;
      } else {
        String str1;
        Date date = new Date();
        this();
        String str2 = GlobalMembers.DateFormat.format(date);
        paramString3 = GlobalMembers.TimeFormat.format(date);
        optionMenu.DateMessage = str2;
        optionMenu.Name = paramString3;
        paramString3 = paramString2;
        if (paramString1.equals("HeaderDate")) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append(str2);
          stringBuilder.append(":");
          stringBuilder.append(paramString2);
          str1 = stringBuilder.toString();
        } 
        optionMenu.Description = str1;
        this.lastMessageDay = date;
      } 
      optionMenu.AplicationType = paramString1;
      return optionMenu;
    } catch (Exception exception) {
      Utilities.escribeArchivo("Messages", "Error: fillChat", exception.getMessage());
      return optionMenu;
    } 
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem) {
    int i;
    int j = paramMenuItem.getItemId();
    if (j != 0) {
      i = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
    } else {
      i = 0;
    } 
    if (j != 0) {
      if (j != 1) {
        if (j != 2) {
          if (j == 3)
            this.txtMessage.setText("pegand2"); 
        } else {
          this.txtMessage.setText("pegand1");
        } 
      } else {
        this.CopyValue = ((OptionMenu)this.lvDatos.getItemAtPosition(i)).Description;
        String str = this.CopyValue;
        if (str != null && str.length() > 0)
          this.txtMessage.setOnCreateContextMenuListener(this.longPressMenu); 
      } 
    } else {
      String str1 = this.txtMessage.getText().subSequence(0, this.txtMessage.getSelectionStart()).toString();
      String str2 = this.txtMessage.getText().subSequence(this.txtMessage.getSelectionEnd(), this.txtMessage.length()).toString();
      j = this.txtMessage.getSelectionStart();
      i = this.CopyValue.length();
      EditText editText = this.txtMessage;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append(this.CopyValue);
      stringBuilder.append(str2);
      editText.setText(stringBuilder.toString());
      this.txtMessage.setSelection(j + i);
    } 
    return super.onContextItemSelected(paramMenuItem);
  }
  
  public void onResume() {
    super.onResume();
    MainActivity.activeFrag = Messages.class.getName();
    this.context = GlobalMembers.contexGlobal;
    MainActivity.UpdateMessages = this.updateMessageHandler;
    try {
      if (this.SveInstance != null) {
        String str = this.SveInstance.getString("lastMessageDay");
        if (str != null && !str.equals(""))
          this.lastMessageDay = GlobalMembers.DateFormat.parse(str); 
        this.txtMessageData = this.SveInstance.getString(this.SVED_TXTMESSAGE);
        this.isSavedData = this.SveInstance.getBoolean(this.SVED_ISDATASAVED);
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("Messages", "Error: onResume", exception.getMessage());
    } 
    MessagesAsyncTask messagesAsyncTask = this.controlViewsMessages;
    if (messagesAsyncTask == null) {
      this.controlViewsMessages = new MessagesAsyncTask(this);
      this.controlViewsMessages.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[0]);
    } else {
      messagesAsyncTask.attach(this);
      if (this.controlViewsMessages.getProgress() < 100) {
        this.bar.setVisibility(0);
      } else {
        if (!PreferenceRT.GetValuePreference(Enums$SettingsPreference.UserServices, "", this.context).toString().equals("")) {
          this.bar.setVisibility(8);
          this.layProgress.setVisibility(8);
          this.lvDatos.setVisibility(0);
          fillData();
        } 
        this.aux = (TextView)findViewById(2131297183);
        String str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.LoginApp, 2131689512);
        this.aux.setText(str);
        this.aux = (TextView)findViewById(2131296509);
        str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.writeMessage, 2131690533);
        this.aux.setHint(str);
        str = Utilities.getStringFromConfigList(this.context, this.stringsResourcesVO.send, 2131690359);
        this.aux = (TextView)findViewById(2131296436);
        this.aux.setText(str);
        this.controlViewsMessages.detach();
      } 
    } 
    Utilities.ServiceRunning(MessagesDispatchService.class.getName(), this.context);
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {
    super.onSaveInstanceState(paramBundle);
    EditText editText = this.txtMessage;
    if (editText != null) {
      paramBundle.putString(this.SVED_TXTMESSAGE, editText.getText().toString());
      paramBundle.putBoolean(this.SVED_ISDATASAVED, true);
    } 
    Date date = this.lastMessageDay;
    if (date != null)
      paramBundle.putString("lastMessageDay", GlobalMembers.DateFormat.format(date)); 
  }
  
  public void onStop() {
    super.onStop();
    Debug.stopMethodTracing();
  }
  
  private class MessagesAsyncTask extends AsyncTask<String, Void, String> {
    int progress = 0;
    
    String response = "";
    
    final Messages this$0;
    
    public MessagesAsyncTask(Messages param1Messages1) {
      attach(param1Messages1);
    }
    
    void attach(Messages param1Messages) {}
    
    void detach() {}
    
    protected String doInBackground(String... param1VarArgs) {
      Thread thread = Thread.currentThread();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(MessagesAsyncTask.class.getSimpleName());
      stringBuilder.append(": ");
      stringBuilder.append(Thread.currentThread().getName());
      thread.setName(stringBuilder.toString());
      try {
        if (GlobalMembers.mHistoryList.size() == 0 && !Messages.this.isSavedData) {
          MessagesService messagesService = new MessagesService();
          this();
          messagesService.getMessagesData();
        } 
        this.response = "Success";
      } catch (Exception exception) {
        this.response = "Failed";
        Utilities.escribeArchivo("Messages", "Error: doInBackground", exception.getMessage());
      } 
      return this.response;
    }
    
    int getProgress() {
      return this.progress;
    }
    
    protected void onPostExecute(String param1String) {
      if (!PreferenceRT.GetValuePreference(Enums$SettingsPreference.UserServices, "", Messages.this.context).toString().equals("")) {
        Messages.this.bar.setVisibility(8);
        Messages.this.lvDatos.setVisibility(0);
        Messages.this.layProgress.setVisibility(8);
        Messages.this.fillData();
      } 
      this.progress = 100;
    }
    
    protected void onPreExecute() {
      if (Messages.this.layProgress == null);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Messages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */