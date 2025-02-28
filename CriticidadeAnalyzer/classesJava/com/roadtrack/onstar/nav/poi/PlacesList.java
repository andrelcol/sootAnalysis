package com.roadtrack.onstar.nav.poi;

import java.io.Serializable;
import java.util.List;

public class PlacesList implements Serializable {
  public List<Place> results;
  
  public String status;
  
  public void setNext_page_token(String paramString) {}
  
  public void setResults(List<Place> paramList) {
    this.results = paramList;
  }
  
  public void setStatus(String paramString) {
    this.status = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/nav/poi/PlacesList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */