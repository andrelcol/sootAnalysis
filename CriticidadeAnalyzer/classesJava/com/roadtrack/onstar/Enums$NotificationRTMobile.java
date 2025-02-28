package com.roadtrack.onstar;

public enum Enums$NotificationRTMobile {
  Alertas, BackgroundApp, Mantenimiento, MensajesBO, Navegacion, comandos, notification_event;
  
  private static final Enums$NotificationRTMobile[] $VALUES;
  
  static {
    MensajesBO = new Enums$NotificationRTMobile("MensajesBO", 1);
    comandos = new Enums$NotificationRTMobile("comandos", 2);
    Mantenimiento = new Enums$NotificationRTMobile("Mantenimiento", 3);
    BackgroundApp = new Enums$NotificationRTMobile("BackgroundApp", 4);
    Alertas = new Enums$NotificationRTMobile("Alertas", 5);
    notification_event = new Enums$NotificationRTMobile("notification_event", 6);
    $VALUES = new Enums$NotificationRTMobile[] { Navegacion, MensajesBO, comandos, Mantenimiento, BackgroundApp, Alertas, notification_event };
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$NotificationRTMobile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */