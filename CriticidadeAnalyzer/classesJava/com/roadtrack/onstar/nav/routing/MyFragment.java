package com.roadtrack.onstar.nav.routing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MyFragment extends FragmentActivity {
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Toast.makeText((Context)getApplication(), "Consumed by activity", 0).show();
  }
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.add(16908290, new ParentFragment());
    fragmentTransaction.commit();
  }
  
  public static class NestedFragment extends Fragment {
    public void onActivityResult(int param1Int1, int param1Int2, Intent param1Intent) {
      super.onActivityResult(param1Int1, param1Int2, param1Intent);
      Toast.makeText((Context)getActivity(), "Consumed by nested fragment", 0).show();
    }
    
    public final View onCreateView(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, Bundle param1Bundle) {
      Button button = new Button((Context)getActivity());
      button.setText("Click Here!");
      button.setOnClickListener(new View.OnClickListener() {
            final MyFragment.NestedFragment this$0;
            
            public void onClick(View param2View) {
              Toast.makeText((Context)MyFragment.NestedFragment.this.getActivity(), "Menu en nested fragment", 0).show();
            }
          });
      return (View)button;
    }
  }
  
  class null implements View.OnClickListener {
    final MyFragment.NestedFragment this$0;
    
    public void onClick(View param1View) {
      Toast.makeText((Context)this.this$0.getActivity(), "Menu en nested fragment", 0).show();
    }
  }
  
  public static class ParentFragment extends Fragment {
    public void onActivityCreated(Bundle param1Bundle) {
      super.onActivityCreated(param1Bundle);
      FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
      fragmentTransaction.add(2131296479, new MyFragment.NestedFragment());
      fragmentTransaction.commit();
    }
    
    public void onActivityResult(int param1Int1, int param1Int2, Intent param1Intent) {
      super.onActivityResult(param1Int1, param1Int2, param1Intent);
      Toast.makeText((Context)getActivity(), "Consumed by parent fragment", 0).show();
    }
    
    public final View onCreateView(LayoutInflater param1LayoutInflater, ViewGroup param1ViewGroup, Bundle param1Bundle) {
      return param1LayoutInflater.inflate(2131427412, param1ViewGroup, false);
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/nav/routing/MyFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */