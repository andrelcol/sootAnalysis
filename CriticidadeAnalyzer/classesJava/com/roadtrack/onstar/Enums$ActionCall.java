package com.roadtrack.onstar;

public enum Enums$ActionCall {
  Answer,
  Conference_Attach,
  Conference_Detach,
  Hang_Up,
  Hold_Call,
  None(0),
  Reject(0),
  Switch(0),
  Toggle_Audio(0),
  Un_Hold_Call(0);
  
  private static final Enums$ActionCall[] $VALUES;
  
  private int opcode;
  
  static {
    Answer = new Enums$ActionCall("Answer", 1, 1);
    Reject = new Enums$ActionCall("Reject", 2, 2);
    Hang_Up = new Enums$ActionCall("Hang_Up", 3, 3);
    Switch = new Enums$ActionCall("Switch", 4, 4);
    Toggle_Audio = new Enums$ActionCall("Toggle_Audio", 5, 5);
    Conference_Attach = new Enums$ActionCall("Conference_Attach", 6, 6);
    Conference_Detach = new Enums$ActionCall("Conference_Detach", 7, 7);
    Hold_Call = new Enums$ActionCall("Hold_Call", 8, 8);
    Un_Hold_Call = new Enums$ActionCall("Un_Hold_Call", 9, 9);
    $VALUES = new Enums$ActionCall[] { None, Answer, Reject, Hang_Up, Switch, Toggle_Audio, Conference_Attach, Conference_Detach, Hold_Call, Un_Hold_Call };
  }
  
  Enums$ActionCall(int paramInt1) {
    this.opcode = paramInt1;
  }
  
  public int GetOpCode() {
    return this.opcode;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$ActionCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */