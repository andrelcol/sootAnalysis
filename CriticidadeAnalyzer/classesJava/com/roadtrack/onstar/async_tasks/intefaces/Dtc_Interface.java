package com.roadtrack.onstar.async_tasks.intefaces;

import com.roadtrack.onstar.VO.RemoteDiagnosticVO;
import java.util.List;

public interface Dtc_Interface {
  void onFail(String paramString);
  
  void onRunning();
  
  void onSuccess(List<RemoteDiagnosticVO> paramList);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/async_tasks/intefaces/Dtc_Interface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */