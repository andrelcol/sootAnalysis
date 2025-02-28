package com.roadtrack.onstar.custom;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.Enums;

public class ListButton extends LinearLayout {
  private int _action_service_status = 1;
  
  private float alpha_level = 1.0F;
  
  private ImageView arrowView;
  
  private Context context;
  
  private TextView descriptionText;
  
  private ImageView iconView;
  
  private Enums.Services myService;
  
  private TextView titleText;
  
  private LinearLayout wrapper;
  
  public ListButton(Context paramContext) {
    super(paramContext);
    this.context = paramContext;
    this.myService = Enums.Services.None;
    init(paramContext);
  }
  
  private void init(Context paramContext) {
    LinearLayout.inflate(getContext(), 2131427439, (ViewGroup)this);
    this.wrapper = (LinearLayout)findViewById(2131297256);
    this.iconView = (ImageView)findViewById(2131296600);
    this.arrowView = (ImageView)findViewById(2131296377);
    this.titleText = (TextView)findViewById(2131297128);
    this.descriptionText = (TextView)findViewById(2131296495);
  }
  
  public ImageView getArrowView() {
    return this.arrowView;
  }
  
  public TextView getDescriptionText() {
    return this.descriptionText;
  }
  
  public ImageView getIconView() {
    return this.iconView;
  }
  
  public Enums.Services getMyService() {
    return this.myService;
  }
  
  public TextView getTitleText() {
    return this.titleText;
  }
  
  public int get_action_service_status() {
    return this._action_service_status;
  }
  
  public void setArrowView(ImageView paramImageView) {
    this.arrowView = paramImageView;
  }
  
  public void setDescriptionText(TextView paramTextView) {
    this.descriptionText = paramTextView;
  }
  
  public void setIconView(ImageView paramImageView) {
    this.iconView = paramImageView;
  }
  
  public void setMyService(Enums.Services paramServices) {
    this.myService = paramServices;
  }
  
  public void setTitleText(TextView paramTextView) {
    this.titleText = paramTextView;
  }
  
  public void set_action_service_status(int paramInt) {
    this._action_service_status = paramInt;
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          this._action_service_status = 99;
          this.alpha_level = 1.0F;
          this.wrapper.setAlpha(this.alpha_level);
        } else {
          this.iconView.setVisibility(8);
          this.arrowView.setVisibility(8);
          this.titleText.setVisibility(8);
          this.descriptionText.setVisibility(8);
        } 
      } else {
        this.alpha_level = 1.0F;
        this.wrapper.setAlpha(this.alpha_level);
      } 
    } else {
      this.alpha_level = 1.0F;
      this.wrapper.setBackgroundColor(ContextCompat.getColor(this.context, 2131034284));
      this.wrapper.setAlpha(this.alpha_level);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/custom/ListButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */