package com.roadtrack.onstar.VO;

import java.util.LinkedList;

public class commonQueue {
  private LinkedList list = new LinkedList();
  
  public void clear() {
    this.list.clear();
  }
  
  public int count() {
    return this.list.size();
  }
  
  public Object dequeue() {
    Object object = this.list.get(0);
    this.list.remove(0);
    return object;
  }
  
  public void enqueue(Object paramObject) {
    this.list.add(paramObject);
  }
  
  public boolean isEmpty() {
    boolean bool;
    if (this.list.size() == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/commonQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */