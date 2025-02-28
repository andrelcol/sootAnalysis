package com.roadtrack.onstar;

public enum Enums$actionSyncFavorite {
  Add,
  Delete(0),
  None(0);
  
  private static final Enums$actionSyncFavorite[] $VALUES;
  
  static {
    Add = new Enums$actionSyncFavorite("Add", 1, 1);
    None = new Enums$actionSyncFavorite("None", 2, 2);
    $VALUES = new Enums$actionSyncFavorite[] { Delete, Add, None };
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$actionSyncFavorite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */