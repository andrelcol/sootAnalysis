package com.roadtrack.onstar;

public enum Enums$responcesGCM {
  DTCNotification,
  EmergencyNotification,
  FindMeNotification,
  commands_acc,
  follow_acc,
  none_acc(0),
  region_acc(0),
  speed_parking_acc(6),
  updatemap_acc(6),
  valet_alert(6);
  
  private static final Enums$responcesGCM[] $VALUES;
  
  private int accCode;
  
  static {
    follow_acc = new Enums$responcesGCM("follow_acc", 2, 12);
    commands_acc = new Enums$responcesGCM("commands_acc", 3, 10);
    region_acc = new Enums$responcesGCM("region_acc", 4, 13);
    updatemap_acc = new Enums$responcesGCM("updatemap_acc", 5, 7);
    valet_alert = new Enums$responcesGCM("valet_alert", 6, 52);
    FindMeNotification = new Enums$responcesGCM("FindMeNotification", 7, 201);
    DTCNotification = new Enums$responcesGCM("DTCNotification", 8, 202);
    EmergencyNotification = new Enums$responcesGCM("EmergencyNotification", 9, 203);
    $VALUES = new Enums$responcesGCM[] { none_acc, speed_parking_acc, follow_acc, commands_acc, region_acc, updatemap_acc, valet_alert, FindMeNotification, DTCNotification, EmergencyNotification };
  }
  
  Enums$responcesGCM(int paramInt1) {
    this.accCode = paramInt1;
  }
  
  public int GetResponcesGCM() {
    return this.accCode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$responcesGCM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */