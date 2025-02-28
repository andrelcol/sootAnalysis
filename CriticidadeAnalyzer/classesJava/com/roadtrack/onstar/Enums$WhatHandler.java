package com.roadtrack.onstar;

public enum Enums$WhatHandler {
  Device_OpCodes, ErrorChekSum, Maintenance_comunication, Messages_comunication, NoConnectionBTAndGCM, Platinum_OpCodes, SendChunkToP8, SendMessagePlatinum;
  
  private static final Enums$WhatHandler[] $VALUES;
  
  static {
    Device_OpCodes = new Enums$WhatHandler("Device_OpCodes", 1);
    NoConnectionBTAndGCM = new Enums$WhatHandler("NoConnectionBTAndGCM", 2);
    Messages_comunication = new Enums$WhatHandler("Messages_comunication", 3);
    Maintenance_comunication = new Enums$WhatHandler("Maintenance_comunication", 4);
    SendMessagePlatinum = new Enums$WhatHandler("SendMessagePlatinum", 5);
    SendChunkToP8 = new Enums$WhatHandler("SendChunkToP8", 6);
    ErrorChekSum = new Enums$WhatHandler("ErrorChekSum", 7);
    $VALUES = new Enums$WhatHandler[] { Platinum_OpCodes, Device_OpCodes, NoConnectionBTAndGCM, Messages_comunication, Maintenance_comunication, SendMessagePlatinum, SendChunkToP8, ErrorChekSum };
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$WhatHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */