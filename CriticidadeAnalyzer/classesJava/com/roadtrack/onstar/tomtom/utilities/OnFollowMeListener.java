package com.roadtrack.onstar.tomtom.utilities;

import com.roadtrack.onstar.VO.FollowMePointsVO;
import java.util.ArrayList;

public class OnFollowMeListener {
  public static OnFollowMeObjectListener onFollowMeObjectListener;
  
  public OnFollowMeListener(OnFollowMeObjectListener paramOnFollowMeObjectListener) {
    onFollowMeObjectListener = paramOnFollowMeObjectListener;
  }
  
  public static interface OnFollowMeObjectListener {
    void onFollowMe(ArrayList<FollowMePointsVO> param1ArrayList);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/tomtom/utilities/OnFollowMeListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */