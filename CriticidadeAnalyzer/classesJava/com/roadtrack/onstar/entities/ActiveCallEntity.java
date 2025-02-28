package com.roadtrack.onstar.entities;

import com.roadtrack.onstar.Enums;

public class ActiveCallEntity {
  private Enums.ActionCall action;
  
  private int callIndex;
  
  private Enums.DeviceCall device;
  
  private Enums.CallNumberType numberType;
  
  private Enums.CallState state;
  
  public ActiveCallEntity() {}
  
  public ActiveCallEntity(Enums.ActionCall paramActionCall, int paramInt1, int paramInt2, Enums.DirectionCall paramDirectionCall, Enums.DeviceCall paramDeviceCall, int paramInt3, Enums.AudioChannelCall paramAudioChannelCall, Enums.ConferenceCall paramConferenceCall, Enums.CallState paramCallState, String paramString1, String paramString2, Enums.CallNumberType paramCallNumberType) {
    this.action = paramActionCall;
    this.device = paramDeviceCall;
    this.callIndex = paramInt3;
    this.state = paramCallState;
    this.numberType = paramCallNumberType;
  }
  
  public Enums.ActionCall getAction() {
    return this.action;
  }
  
  public int getCallIndex() {
    return this.callIndex;
  }
  
  public Enums.DeviceCall getDevice() {
    return this.device;
  }
  
  public Enums.CallNumberType getNumberType() {
    return this.numberType;
  }
  
  public Enums.CallState getState() {
    return this.state;
  }
  
  public void setAction(Enums.ActionCall paramActionCall) {
    this.action = paramActionCall;
  }
  
  public void setCallIndex(int paramInt) {
    this.callIndex = paramInt;
  }
  
  public void setDevice(Enums.DeviceCall paramDeviceCall) {
    this.device = paramDeviceCall;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/entities/ActiveCallEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */