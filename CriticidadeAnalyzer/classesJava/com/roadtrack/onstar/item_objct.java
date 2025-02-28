package com.roadtrack.onstar;

public class item_objct {
  private int icono;
  
  private String titulo;
  
  public item_objct(String paramString, int paramInt) {
    this.titulo = paramString;
    this.icono = paramInt;
  }
  
  public int getIcono() {
    return this.icono;
  }
  
  public String getTitulo() {
    return this.titulo;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/item_objct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */