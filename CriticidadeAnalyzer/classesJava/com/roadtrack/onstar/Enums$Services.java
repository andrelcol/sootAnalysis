package com.roadtrack.onstar;

public enum Enums$Services {
  ActionAssistance,
  ActionCreditCardChanged,
  ActionDTC,
  ActionDicas,
  ActionEmergency,
  ActionExit,
  ActionHistorical,
  ActionIvr,
  ActionMyAccount,
  ActionMyPlan,
  ActionNotifications,
  ActionNotificationsLateral,
  ActionPID,
  ActionPaymenHistory,
  ActionRenewal,
  ActionSchedule,
  ActionSettings,
  ActionShare,
  ActionTraffic,
  ActionWheather,
  CarFinder,
  ClubOnStar,
  DTCAction,
  DTCChasis,
  DTCElectricSystem,
  DTCEnable,
  DTCNotification,
  DTCPowertrain,
  DTCSecurity,
  DTCUpdateNotifications,
  Disarm,
  DoorsLock,
  DoorsUnlock,
  EmergencyNotification,
  Favs,
  FindMe,
  FindMeNotification,
  FollowMe,
  FollowMeTracking,
  FollowMeUUx,
  Horn,
  HornF1,
  HornLigths,
  Ligths,
  Navigation,
  NavigationWithTraffic,
  NewActionForCreate,
  None(0),
  OptionMoipTicket(0),
  OptionMyCreditCard(0),
  Parking(0),
  ParkingUUx(0),
  SendPNDNavigationCommand(0),
  SpaceThree(0),
  SpaceTwo(0),
  SpaceUno(0),
  Speed(0),
  SpeedAlways(0),
  SpeedUUx(0),
  StartEngine(0),
  WebPage(0),
  geoFence(0),
  pid(0),
  plpParameters(0),
  textToPND(0),
  valet(0);
  
  private static final Enums$Services[] $VALUES;
  
  private int opcode;
  
  static {
    FindMe = new Enums$Services("FindMe", 1, 1);
    FollowMe = new Enums$Services("FollowMe", 2, 2);
    HornLigths = new Enums$Services("HornLigths", 3, 34);
    Horn = new Enums$Services("Horn", 4, 42);
    HornF1 = new Enums$Services("HornF1", 5, 66);
    Ligths = new Enums$Services("Ligths", 6, 65);
    DoorsLock = new Enums$Services("DoorsLock", 7, 35);
    DoorsUnlock = new Enums$Services("DoorsUnlock", 8, 36);
    Disarm = new Enums$Services("Disarm", 9, 37);
    Parking = new Enums$Services("Parking", 10, 38);
    Speed = new Enums$Services("Speed", 11, 39);
    SpeedUUx = new Enums$Services("SpeedUUx", 12, 62);
    SpeedAlways = new Enums$Services("SpeedAlways", 13, 6);
    StartEngine = new Enums$Services("StartEngine", 14, 51);
    CarFinder = new Enums$Services("CarFinder", 15, 56);
    textToPND = new Enums$Services("textToPND", 16, 40);
    SendPNDNavigationCommand = new Enums$Services("SendPNDNavigationCommand", 17, 45);
    geoFence = new Enums$Services("geoFence", 18, 13);
    valet = new Enums$Services("valet", 19, 52);
    plpParameters = new Enums$Services("plpParameters", 20, 54);
    pid = new Enums$Services("pid", 21, 58);
    Navigation = new Enums$Services("Navigation", 22, -7);
    ActionDTC = new Enums$Services("ActionDTC", 23, 102);
    ActionNotifications = new Enums$Services("ActionNotifications", 24, -6);
    ActionSettings = new Enums$Services("ActionSettings", 25, -11);
    ActionTraffic = new Enums$Services("ActionTraffic", 26, 106);
    ActionWheather = new Enums$Services("ActionWheather", 27, 108);
    ActionHistorical = new Enums$Services("ActionHistorical", 28, 109);
    ActionEmergency = new Enums$Services("ActionEmergency", 29, -3);
    ActionAssistance = new Enums$Services("ActionAssistance", 30, -4);
    ActionIvr = new Enums$Services("ActionIvr", 31, 112);
    ActionPID = new Enums$Services("ActionPID", 32, 113);
    DTCChasis = new Enums$Services("DTCChasis", 33, 114);
    DTCSecurity = new Enums$Services("DTCSecurity", 34, 115);
    DTCPowertrain = new Enums$Services("DTCPowertrain", 35, 116);
    DTCElectricSystem = new Enums$Services("DTCElectricSystem", 36, 117);
    DTCUpdateNotifications = new Enums$Services("DTCUpdateNotifications", 37, 118);
    ActionShare = new Enums$Services("ActionShare", 38, -8);
    NavigationWithTraffic = new Enums$Services("NavigationWithTraffic", 39, -1);
    WebPage = new Enums$Services("WebPage", 40, -5);
    SpaceUno = new Enums$Services("SpaceUno", 41, 1000);
    SpaceTwo = new Enums$Services("SpaceTwo", 42, 2000);
    SpaceThree = new Enums$Services("SpaceThree", 43, 300);
    Favs = new Enums$Services("Favs", 44, 500);
    DTCEnable = new Enums$Services("DTCEnable", 45, -2);
    FollowMeUUx = new Enums$Services("FollowMeUUx", 46, 63);
    ParkingUUx = new Enums$Services("ParkingUUx", 47, 61);
    DTCAction = new Enums$Services("DTCAction", 48, 53);
    ActionSchedule = new Enums$Services("ActionSchedule", 49, -10);
    FollowMeTracking = new Enums$Services("FollowMeTracking", 50, 27);
    ClubOnStar = new Enums$Services("ClubOnStar", 51, -19);
    ActionNotificationsLateral = new Enums$Services("ActionNotificationsLateral", 52, -12);
    ActionDicas = new Enums$Services("ActionDicas", 53, -13);
    ActionMyPlan = new Enums$Services("ActionMyPlan", 54, -14);
    ActionExit = new Enums$Services("ActionExit", 55, 403);
    ActionRenewal = new Enums$Services("ActionRenewal", 56, -18);
    ActionPaymenHistory = new Enums$Services("ActionPaymenHistory", 57, -17);
    ActionCreditCardChanged = new Enums$Services("ActionCreditCardChanged", 58, -21);
    ActionMyAccount = new Enums$Services("ActionMyAccount", 59, -15);
    OptionMyCreditCard = new Enums$Services("OptionMyCreditCard", 60, -16);
    OptionMoipTicket = new Enums$Services("OptionMoipTicket", 61, -20);
    NewActionForCreate = new Enums$Services("NewActionForCreate", 62, 28);
    FindMeNotification = new Enums$Services("FindMeNotification", 63, 201);
    DTCNotification = new Enums$Services("DTCNotification", 64, 202);
    EmergencyNotification = new Enums$Services("EmergencyNotification", 65, 203);
    $VALUES = new Enums$Services[] { 
        None, FindMe, FollowMe, HornLigths, Horn, HornF1, Ligths, DoorsLock, DoorsUnlock, Disarm, 
        Parking, Speed, SpeedUUx, SpeedAlways, StartEngine, CarFinder, textToPND, SendPNDNavigationCommand, geoFence, valet, 
        plpParameters, pid, Navigation, ActionDTC, ActionNotifications, ActionSettings, ActionTraffic, ActionWheather, ActionHistorical, ActionEmergency, 
        ActionAssistance, ActionIvr, ActionPID, DTCChasis, DTCSecurity, DTCPowertrain, DTCElectricSystem, DTCUpdateNotifications, ActionShare, NavigationWithTraffic, 
        WebPage, SpaceUno, SpaceTwo, SpaceThree, Favs, DTCEnable, FollowMeUUx, ParkingUUx, DTCAction, ActionSchedule, 
        FollowMeTracking, ClubOnStar, ActionNotificationsLateral, ActionDicas, ActionMyPlan, ActionExit, ActionRenewal, ActionPaymenHistory, ActionCreditCardChanged, ActionMyAccount, 
        OptionMyCreditCard, OptionMoipTicket, NewActionForCreate, FindMeNotification, DTCNotification, EmergencyNotification };
  }
  
  Enums$Services(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public static String GetName(int paramInt) {
    Enums$Services[] arrayOfEnums$Services = values();
    for (byte b = 0; b < arrayOfEnums$Services.length; b++) {
      if (arrayOfEnums$Services[b].GetCode() == paramInt)
        return arrayOfEnums$Services[b].toString(); 
    } 
    return "";
  }
  
  public static Enums$Services GetValue(int paramInt) {
    Enums$Services[] arrayOfEnums$Services = values();
    for (byte b = 0; b < arrayOfEnums$Services.length; b++) {
      if (arrayOfEnums$Services[b].GetCode() == paramInt)
        return arrayOfEnums$Services[b]; 
    } 
    return None;
  }
  
  public int GetCode() {
    return this.opcode;
  }
  
  public String GetCodeString() {
    return Integer.toString(this.opcode);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$Services.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */