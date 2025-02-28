package com.roadtrack.onstar.viewTutorial;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import com.roadtrack.onstar.MainActivity;
import com.roadtrack.onstar.googleMaps.ActivityMapViewerG;
import com.roadtrack.onstar.googleMaps.MapsFragment;
import com.roadtrack.onstar.pid.PIDActivity;
import com.roadtrack.onstar.pid.RemoteDiagnosticActivity;
import java.util.ArrayList;
import java.util.List;

public class ShowViewElement extends FrameLayout {
  private int NUM0 = 0;
  
  private int NUM1 = 1;
  
  private int NUM2 = 2;
  
  private int NUM4 = 4;
  
  private Activity activity;
  
  ClassElements classElements;
  
  private boolean isOnClick;
  
  public List<Integer> listX;
  
  public List<Integer> listXF;
  
  public List<Integer> listY;
  
  public List<Integer> listYF;
  
  private View mContentBox;
  
  private float mDownX;
  
  private float mDownY;
  
  private Paint mPaint;
  
  int numPaginas = this.NUM0;
  
  int pages;
  
  private ShowViewElement show;
  
  private int x;
  
  private int xf;
  
  private int y;
  
  private int yf;
  
  public ShowViewElement(Activity paramActivity, Fragment paramFragment, ClassElements paramClassElements) {
    super((Context)paramActivity);
    this.classElements = paramClassElements;
    this.listX = new ArrayList<Integer>();
    this.listY = new ArrayList<Integer>();
    this.listXF = new ArrayList<Integer>();
    this.listYF = new ArrayList<Integer>();
    setTargetDimens();
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mPaint.setColor(-2013265665);
    this.mPaint.setStrokeWidth(this.NUM4);
    CreateRelative(paramActivity, paramFragment);
  }
  
  public ShowViewElement(Context paramContext, Activity paramActivity, ClassElements paramClassElements) {
    super(paramContext);
    this.classElements = paramClassElements;
    this.listX = new ArrayList<Integer>();
    this.listY = new ArrayList<Integer>();
    this.listXF = new ArrayList<Integer>();
    this.listYF = new ArrayList<Integer>();
    setTargetDimens();
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mPaint.setColor(-2013265665);
    this.mPaint.setStrokeWidth(this.NUM4);
    CreateRelative(paramContext, paramActivity);
  }
  
  private void CreateRelative(final Activity act, final Fragment fragment) {
    this.mContentBox = LayoutInflater.from(getContext()).inflate(2131427484, (ViewGroup)this, true).findViewById(2131296481);
    this.mContentBox.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
    Drawing drawing = new Drawing((Context)act);
    drawing.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
    ((RelativeLayout)this.mContentBox).addView(drawing);
    ((ViewGroup)act.getWindow().getDecorView()).addView((View)this);
    this.activity = act;
    this.mContentBox.setOnTouchListener(new View.OnTouchListener() {
          final ShowViewElement this$0;
          
          final Activity val$act;
          
          final Fragment val$fragment;
          
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            int i = MotionEventCompat.getActionMasked(param1MotionEvent);
            if (i != 0) {
              if (i != 1) {
                if (i != 2)
                  return false; 
                if (ShowViewElement.this.isOnClick && (Math.abs(ShowViewElement.this.mDownX - param1MotionEvent.getX()) > 10.0F || Math.abs(ShowViewElement.this.mDownY - param1MotionEvent.getY()) > 10.0F)) {
                  float f = param1MotionEvent.getX();
                  ShowViewElement.this.setPageView(f, act);
                  ShowViewElement.access$002(ShowViewElement.this, false);
                } 
                return true;
              } 
              if (ShowViewElement.this.isOnClick)
                ShowViewElement.this.removeView(fragment); 
              return true;
            } 
            ShowViewElement.access$102(ShowViewElement.this, param1MotionEvent.getX());
            ShowViewElement.access$202(ShowViewElement.this, param1MotionEvent.getY());
            ShowViewElement.access$002(ShowViewElement.this, true);
            return true;
          }
        });
  }
  
  private void CreateRelative(Context paramContext, final Activity act) {
    this.mContentBox = LayoutInflater.from(getContext()).inflate(2131427484, (ViewGroup)this, true).findViewById(2131296481);
    this.mContentBox.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
    Drawing drawing = new Drawing(paramContext);
    drawing.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
    ((RelativeLayout)this.mContentBox).addView(drawing);
    ((ViewGroup)act.getWindow().getDecorView()).addView((View)this);
    this.activity = act;
    this.mContentBox.setOnTouchListener(new View.OnTouchListener() {
          final ShowViewElement this$0;
          
          final Activity val$act;
          
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            int i = MotionEventCompat.getActionMasked(param1MotionEvent);
            if (i != 0) {
              if (i != 1) {
                if (i != 2)
                  return false; 
                if (ShowViewElement.this.isOnClick && (Math.abs(ShowViewElement.this.mDownX - param1MotionEvent.getX()) > 10.0F || Math.abs(ShowViewElement.this.mDownY - param1MotionEvent.getY()) > 10.0F)) {
                  float f = param1MotionEvent.getX();
                  ShowViewElement.this.setPageView(f, act);
                  ShowViewElement.access$002(ShowViewElement.this, false);
                } 
                return true;
              } 
              if (ShowViewElement.this.isOnClick)
                ShowViewElement.this.removeView(act, true); 
              return true;
            } 
            ShowViewElement.access$102(ShowViewElement.this, param1MotionEvent.getX());
            ShowViewElement.access$202(ShowViewElement.this, param1MotionEvent.getY());
            ShowViewElement.access$002(ShowViewElement.this, true);
            return true;
          }
        });
  }
  
  public static void setValues(ClassElements paramClassElements, View paramView, int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, boolean paramBoolean1, int paramInt4, int paramInt5, boolean paramBoolean2) {
    paramClassElements.getListTarget().add(paramView);
    paramClassElements.getListPosition().add(Integer.valueOf(paramInt1));
    paramClassElements.getListString().add(paramString1);
    paramClassElements.getListTipo().add(paramString2);
    paramClassElements.getListPosiciones().add(Integer.valueOf(paramInt2));
    paramClassElements.getListLargeDica().add(Integer.valueOf(paramInt3));
    paramClassElements.getListFlag().add(Boolean.valueOf(paramBoolean1));
    paramClassElements.getListPositionText().add(Integer.valueOf(paramInt4));
    paramClassElements.getListWidthArea().add(Integer.valueOf(paramInt5));
    paramClassElements.getListType().add(Boolean.valueOf(paramBoolean2));
  }
  
  public int getActivityPosition(Activity paramActivity) {
    int i = this.NUM0;
    if (paramActivity.getClass().equals(MainActivity.class)) {
      i = ((MainActivity)paramActivity).getPosition();
    } else if (paramActivity.getClass().equals(ActivityMapViewerG.class)) {
      i = ((ActivityMapViewerG)paramActivity).getPosition();
    } else if (paramActivity.getClass().equals(PIDActivity.class)) {
      i = ((PIDActivity)paramActivity).getPosition();
    } 
    return i;
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2) {
      Activity activity = this.activity;
      if (activity != null)
        removeView(activity, true); 
    } 
  }
  
  public void removeView(Activity paramActivity, boolean paramBoolean) {
    MainActivity mainActivity;
    ((ViewGroup)paramActivity.getWindow().getDecorView()).removeView((View)this);
    if (paramActivity.getClass().equals(MainActivity.class)) {
      mainActivity = (MainActivity)paramActivity;
      mainActivity.setStateTutorial(false);
      mainActivity.setStateTutorialMenu(false);
      if (paramBoolean)
        mainActivity.setDisableOrientation(); 
      mainActivity.setPosition(0);
      mainActivity.setPressTuto(true);
    } else {
      ActivityMapViewerG activityMapViewerG;
      if (mainActivity.getClass().equals(ActivityMapViewerG.class)) {
        activityMapViewerG = (ActivityMapViewerG)mainActivity;
        activityMapViewerG.setStateTutorial(false);
        if (paramBoolean)
          activityMapViewerG.setDisableOrientation(); 
        activityMapViewerG.setPressTuto(true);
        activityMapViewerG.setPosition(0);
      } else {
        PIDActivity pIDActivity;
        if (activityMapViewerG.getClass().equals(PIDActivity.class)) {
          pIDActivity = (PIDActivity)activityMapViewerG;
          pIDActivity.setStateTutorial(false);
          pIDActivity.setDisableOrientation();
          pIDActivity.setPressTuto(true);
        } else if (pIDActivity.getClass().equals(RemoteDiagnosticActivity.class)) {
          RemoteDiagnosticActivity remoteDiagnosticActivity = (RemoteDiagnosticActivity)pIDActivity;
          remoteDiagnosticActivity.setStateTutorial(false);
          remoteDiagnosticActivity.setDisableOrientation();
          remoteDiagnosticActivity.setPressTuto(true);
        } 
      } 
    } 
  }
  
  public void removeView(Fragment paramFragment) {
    ((ViewGroup)this.activity.getWindow().getDecorView()).removeView((View)this);
    if (paramFragment.getClass().equals(MapsFragment.class)) {
      MapsFragment mapsFragment = (MapsFragment)paramFragment;
      mapsFragment.setPressTuto(true);
      mapsFragment.setPosition(0);
    } 
  }
  
  public void setNumPaginas(int paramInt) {
    this.numPaginas = paramInt;
  }
  
  public void setPageView(float paramFloat, Activity paramActivity) {
    float f = this.mDownX;
    if (f < paramFloat) {
      if (this.numPaginas > this.NUM0) {
        int i = getActivityPosition(paramActivity) - 1;
        removeView(paramActivity, false);
        if (paramActivity.getClass().equals(MainActivity.class)) {
          MainActivity mainActivity = (MainActivity)paramActivity;
          mainActivity.setPosition(i);
          ClassElements classElements = mainActivity.setViewsFlag();
          this.show = new ShowViewElement(paramActivity.getApplicationContext(), paramActivity, classElements);
        } else if (paramActivity.getClass().equals(ActivityMapViewerG.class)) {
          ActivityMapViewerG activityMapViewerG = (ActivityMapViewerG)paramActivity;
          activityMapViewerG.setPosition(i);
          ClassElements classElements = activityMapViewerG.setViews();
          this.show = new ShowViewElement(paramActivity.getApplicationContext(), paramActivity, classElements);
          activityMapViewerG.setShow(this.show);
        } else if (paramActivity.getClass().equals(PIDActivity.class)) {
          PIDActivity pIDActivity = (PIDActivity)paramActivity;
          pIDActivity.setPosition(i);
          ClassElements classElements = pIDActivity.setViews();
          this.show = new ShowViewElement(paramActivity.getApplicationContext(), paramActivity, classElements);
        } else if (paramActivity.getClass().equals(RemoteDiagnosticActivity.class)) {
          RemoteDiagnosticActivity remoteDiagnosticActivity = (RemoteDiagnosticActivity)paramActivity;
          remoteDiagnosticActivity.setPosition(i);
          ClassElements classElements = remoteDiagnosticActivity.setViews();
          this.show = new ShowViewElement(paramActivity.getApplicationContext(), paramActivity, classElements);
        } 
        this.show.setPages(this.pages);
        this.show.setNumPaginas(i);
      } 
    } else if (f > paramFloat) {
      int i = this.pages;
      int j = this.NUM1;
      if (getActivityPosition(paramActivity) < i - j) {
        i = getActivityPosition(paramActivity) + 1;
        removeView(paramActivity, false);
        if (paramActivity.getClass().equals(MainActivity.class)) {
          MainActivity mainActivity = (MainActivity)paramActivity;
          mainActivity.setPosition(i);
          ClassElements classElements = mainActivity.setViewsFlag();
          this.show = new ShowViewElement(paramActivity.getApplicationContext(), paramActivity, classElements);
        } else if (paramActivity.getClass().equals(ActivityMapViewerG.class)) {
          ActivityMapViewerG activityMapViewerG = (ActivityMapViewerG)paramActivity;
          activityMapViewerG.setPosition(i);
          ClassElements classElements = activityMapViewerG.setViews();
          this.show = new ShowViewElement(paramActivity.getApplicationContext(), paramActivity, classElements);
          activityMapViewerG.setShow(this.show);
        } else if (paramActivity.getClass().equals(PIDActivity.class)) {
          PIDActivity pIDActivity = (PIDActivity)paramActivity;
          pIDActivity.setPosition(i);
          ClassElements classElements = pIDActivity.setViews();
          this.show = new ShowViewElement(paramActivity.getApplicationContext(), paramActivity, classElements);
        } else if (paramActivity.getClass().equals(RemoteDiagnosticActivity.class)) {
          RemoteDiagnosticActivity remoteDiagnosticActivity = (RemoteDiagnosticActivity)paramActivity;
          remoteDiagnosticActivity.setPosition(i);
          ClassElements classElements = remoteDiagnosticActivity.setViews();
          this.show = new ShowViewElement(paramActivity.getApplicationContext(), paramActivity, classElements);
        } 
        this.show.setPages(this.pages);
        this.show.setNumPaginas(i);
      } 
    } 
  }
  
  public void setPages(int paramInt) {
    this.pages = paramInt;
  }
  
  public void setTargetDimens() {
    for (View view : this.classElements.getListTarget()) {
      int[] arrayOfInt = new int[this.NUM2];
      view.getLocationInWindow(arrayOfInt);
      int i = this.NUM0;
      new Rect(arrayOfInt[i], arrayOfInt[this.NUM1], arrayOfInt[i] + view.getWidth(), arrayOfInt[this.NUM1] + view.getHeight());
      this.x = arrayOfInt[this.NUM0];
      this.y = arrayOfInt[this.NUM1];
      this.xf = view.getWidth();
      this.yf = view.getHeight();
      this.listX.add(Integer.valueOf(this.x));
      this.listY.add(Integer.valueOf(this.y));
      this.listXF.add(Integer.valueOf(this.xf));
      this.listYF.add(Integer.valueOf(this.yf));
    } 
  }
  
  public class Drawing extends View {
    private Paint Redpaint;
    
    private Paint Whitepaint = new Paint();
    
    final ShowViewElement this$0;
    
    public Drawing(Context param1Context) {
      super(param1Context);
      this.Whitepaint.setColor(Color.argb(180, 255, 255, 255));
      this.Whitepaint.setStrokeWidth(10.0F);
      this.Whitepaint.setStyle(Paint.Style.FILL_AND_STROKE);
      this.Redpaint = new Paint();
      this.Redpaint.setColor(Color.argb(180, 255, 255, 255));
    }
    
    protected void onDraw(Canvas param1Canvas) {
      for (byte b = 0; b < ShowViewElement.this.listX.size(); b++) {
        float f1;
        if (((Boolean)ShowViewElement.this.classElements.getListType().get(b)).booleanValue()) {
          f1 = (float)(((Integer)ShowViewElement.this.listY.get(b)).intValue() + ((Integer)ShowViewElement.this.listYF.get(b)).intValue() * 1.5D);
        } else {
          f1 = (((Integer)ShowViewElement.this.listY.get(b)).intValue() + ((Integer)ShowViewElement.this.listYF.get(b)).intValue() / ShowViewElement.this.NUM2);
        } 
        Context context = getContext();
        float f2 = (((Integer)ShowViewElement.this.listX.get(b)).intValue() + ((Integer)ShowViewElement.this.listXF.get(b)).intValue() / ShowViewElement.this.NUM2 + ((Integer)ShowViewElement.this.classElements.getListPosiciones().get(b)).intValue());
        ShowViewElement showViewElement = ShowViewElement.this;
        int i = showViewElement.getActivityPosition(showViewElement.activity);
        int j = ((Integer)ShowViewElement.this.classElements.getListPosition().get(b)).intValue();
        showViewElement = ShowViewElement.this;
        Tutorial tutorial = new Tutorial(context, f2, f1, i, j, showViewElement.pages, showViewElement.classElements, showViewElement.activity);
        ShowViewElement.this.addView(tutorial);
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/viewTutorial/ShowViewElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */