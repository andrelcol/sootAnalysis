package com.roadtrack.onstar;

public enum Enums$RoutingStatus {
  Active, DestinationFailure, InActive, RouteFailure;
  
  private static final Enums$RoutingStatus[] $VALUES;
  
  static {
    Active = new Enums$RoutingStatus("Active", 1);
    DestinationFailure = new Enums$RoutingStatus("DestinationFailure", 2);
    RouteFailure = new Enums$RoutingStatus("RouteFailure", 3);
    $VALUES = new Enums$RoutingStatus[] { InActive, Active, DestinationFailure, RouteFailure };
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$RoutingStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */