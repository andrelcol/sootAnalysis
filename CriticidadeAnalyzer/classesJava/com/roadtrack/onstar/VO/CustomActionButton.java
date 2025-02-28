package com.roadtrack.onstar.VO;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.roadtrack.onstar.BO.GlobalMembers;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.R;
import com.roadtrack.onstar.onstarApplication;
import com.roadtrack.onstar.utils.Utilities;
import java.io.Serializable;

public class CustomActionButton extends LinearLayout implements Serializable {
  public static float WATER_MARK_ALPHA = 0.4F;
  
  private int _action_service_status = 1;
  
  private int _current_status = 0;
  
  private int _dialog_drawable = -1;
  
  private String _dialog_message = "";
  
  private String _dialog_title = "";
  
  private transient Drawable _drw_action_image = null;
  
  private boolean _has_dialog = false;
  
  private boolean _is_progress_showing = false;
  
  private String _str_action_title = "";
  
  private int _txt_color = Color.parseColor("#013e7d");
  
  private float _txt_size = 0.0F;
  
  private float alpha_level = 1.0F;
  
  int badgeNumber = 0;
  
  private transient Context context;
  
  private transient FrameLayout fl_progress_bar;
  
  private transient FrameLayout fl_redpoint;
  
  private transient FrameLayout fl_status_image;
  
  private transient ImageView imgv_main_image;
  
  private transient ImageView imgv_redpoint_image;
  
  private transient ImageView imgv_status_image;
  
  private transient TextView lbl_button_title;
  
  private Enums.Services myService;
  
  private transient ProgressBar pb_action_button;
  
  private onstarApplication rtApp;
  
  private int style = 0;
  
  public CustomActionButton(Context paramContext) {
    super(paramContext);
    this.context = paramContext;
    init(null);
  }
  
  public CustomActionButton(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    init(paramAttributeSet);
  }
  
  public CustomActionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    this.context = paramContext;
    init(paramAttributeSet);
  }
  
  private void changeLabel() {
    TextView textView = this.lbl_button_title;
    if (textView != null && this._str_action_title != null) {
      try {
        textView.setTextColor(this._txt_color);
        if (get_txt_size() != 0.0F && get_txt_size() > 0.0F)
          this.lbl_button_title.setTextSize(this._txt_size); 
      } catch (Exception exception) {}
      this.lbl_button_title.setAlpha(this.alpha_level);
      this.lbl_button_title.setText(this._str_action_title);
    } 
  }
  
  private void changeMainImage() {
    ImageView imageView = this.imgv_main_image;
    if (imageView != null && this._drw_action_image != null) {
      imageView.setAlpha(this.alpha_level);
      this.imgv_main_image.setImageDrawable(this._drw_action_image);
    } 
  }
  
  public static int getStatusFromResource(int paramInt) {
    byte b = 2;
    switch (paramInt) {
      default:
        b = 3;
        break;
      case 2131165277:
      case 2131165581:
        b = 5;
        break;
      case 2131165275:
      case 2131165301:
        b = 6;
        break;
      case 2131165274:
      case 2131165684:
        b = 7;
        break;
      case 2131165271:
      case 2131165632:
        b = 4;
        break;
      case 2131165280:
      case 2131165626:
        break;
    } 
    return b;
  }
  
  private void init(AttributeSet paramAttributeSet) {
    LinearLayout.inflate(getContext(), 2131427399, (ViewGroup)this);
    this.rtApp = (onstarApplication)this.context.getApplicationContext();
    this.imgv_main_image = (ImageView)findViewById(2131296645);
    this.fl_progress_bar = (FrameLayout)findViewById(2131296542);
    this.pb_action_button = (ProgressBar)findViewById(2131296949);
    this.fl_status_image = (FrameLayout)findViewById(2131296543);
    this.imgv_status_image = (ImageView)findViewById(2131296647);
    this.lbl_button_title = (TextView)findViewById(2131296730);
    this.imgv_redpoint_image = (ImageView)findViewById(2131296646);
    this.fl_redpoint = (FrameLayout)findViewById(2131296544);
    this.pb_action_button.getIndeterminateDrawable().setColorFilter(-7829368, PorterDuff.Mode.MULTIPLY);
    onstarApplication onstarApplication1 = this.rtApp;
    onstarApplication.getTypeface(this.context, onstarApplication1.fontPathLouisRegular);
    if (paramAttributeSet != null) {
      TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.customActionButton, 0, 0);
      try {
        this._str_action_title = typedArray.getString(2);
        this._drw_action_image = typedArray.getDrawable(0);
        this._action_service_status = typedArray.getInteger(1, this._action_service_status);
        this._txt_color = typedArray.getColor(3, this._txt_color);
      } catch (Exception exception) {}
    } 
    set_action_service_status(this._action_service_status);
    set_str_action_title(this._str_action_title);
    set_drw_action_image(this._drw_action_image);
  }
  
  public void dismissProgressBar() {
    if (this._action_service_status == 1) {
      this.fl_progress_bar.setVisibility(4);
      this.pb_action_button.setVisibility(4);
      this._current_status = 0;
      set_is_progress_showing(false);
    } 
  }
  
  public int getBadgeNumber() {
    return this.badgeNumber;
  }
  
  public ImageView getImgv_status_image() {
    return this.imgv_status_image;
  }
  
  public Enums.Services getMyService() {
    return this.myService;
  }
  
  public ProgressBar getPb_action_button() {
    return this.pb_action_button;
  }
  
  public int getStyle() {
    return this.style;
  }
  
  public int get_action_service_status() {
    return this._action_service_status;
  }
  
  public int get_current_status() {
    return this._current_status;
  }
  
  public int get_dialog_drawable() {
    return this._dialog_drawable;
  }
  
  public String get_dialog_message() {
    return this._dialog_message;
  }
  
  public String get_dialog_title() {
    return this._dialog_title;
  }
  
  public Drawable get_drw_action_image() {
    return this._drw_action_image;
  }
  
  public boolean get_is_progress_showing() {
    return this._is_progress_showing;
  }
  
  public String get_str_action_title() {
    return this._str_action_title;
  }
  
  public int get_txt_color() {
    return this._txt_color;
  }
  
  public float get_txt_size() {
    return this._txt_size;
  }
  
  public void setBadgeNumber(int paramInt) {
    this.badgeNumber = paramInt;
  }
  
  public void setHideLabel(boolean paramBoolean) {
    if (paramBoolean) {
      this.lbl_button_title.setVisibility(8);
    } else {
      this.lbl_button_title.setVisibility(0);
    } 
  }
  
  public void setImgv_status_image(ImageView paramImageView) {
    this.imgv_status_image = paramImageView;
  }
  
  public void setIntent(boolean paramBoolean) {}
  
  public void setMapActive(boolean paramBoolean) {}
  
  public void setMyService(Enums.Services paramServices) {
    this.myService = paramServices;
  }
  
  public void setPb_action_button(ProgressBar paramProgressBar) {
    this.pb_action_button = paramProgressBar;
  }
  
  public void setStyle(int paramInt) {
    this.style = paramInt;
  }
  
  public void set_action_service_status(int paramInt) {
    this._action_service_status = paramInt;
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          this._action_service_status = 99;
          this.alpha_level = 1.0F;
          changeMainImage();
          changeLabel();
        } else {
          this.imgv_main_image.setVisibility(8);
          this.fl_progress_bar.setVisibility(8);
          this.pb_action_button.setVisibility(8);
          this.fl_status_image.setVisibility(8);
          this.imgv_status_image.setVisibility(8);
          this.lbl_button_title.setVisibility(8);
          setClickable(false);
          setEnabled(false);
        } 
      } else {
        this.alpha_level = 1.0F;
        changeMainImage();
        changeLabel();
      } 
    } else {
      this.alpha_level = 0.5F;
      changeMainImage();
      changeLabel();
    } 
  }
  
  public void set_dialog_drawable(int paramInt) {
    if (this._has_dialog) {
      this._dialog_drawable = paramInt;
    } else {
      this._dialog_drawable = -1;
    } 
  }
  
  public void set_dialog_message(String paramString) {
    if (this._has_dialog) {
      this._dialog_message = paramString;
    } else {
      this._dialog_message = "";
    } 
  }
  
  public void set_dialog_title(String paramString) {
    if (this._has_dialog) {
      this._dialog_title = paramString;
    } else {
      this._dialog_title = "";
    } 
  }
  
  public void set_drw_action_image(Drawable paramDrawable) {
    this._drw_action_image = paramDrawable;
    changeMainImage();
  }
  
  public void set_has_dialog(boolean paramBoolean) {
    this._has_dialog = paramBoolean;
  }
  
  public void set_is_progress_showing(boolean paramBoolean) {
    this._is_progress_showing = paramBoolean;
  }
  
  public void set_str_action_title(String paramString) {
    this._str_action_title = paramString;
    changeLabel();
  }
  
  public void set_txt_color(int paramInt) {
    if (paramInt != this._txt_color) {
      this._txt_color = paramInt;
      changeLabel();
    } 
  }
  
  public void set_txt_size(float paramFloat) {
    this._txt_size = paramFloat;
  }
  
  public void showActionStatus(int paramInt) {
    Animation animation = AnimationUtils.loadAnimation(this.context, 2130771983);
    if (this._action_service_status == 1) {
      Drawable drawable;
      switch (paramInt) {
        default:
          return;
        case 21:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this.fl_redpoint.setVisibility(0);
          this.imgv_redpoint_image.setVisibility(0);
          this.imgv_redpoint_image.setImageBitmap(Utilities.paintRedPointDTC(GlobalMembers.ctxBase, getBadgeNumber(), 2131165513, false));
          this.imgv_redpoint_image.startAnimation(animation);
          this._current_status = paramInt;
        case 20:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          this._current_status = paramInt;
        case 19:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this.fl_redpoint.setVisibility(0);
          this.imgv_redpoint_image.setVisibility(0);
          this.imgv_redpoint_image.setImageBitmap(Utilities.paintRedPoint(GlobalMembers.ctxBase, GlobalMembers.notificaciones, GlobalMembers.redPoint));
          this.imgv_redpoint_image.startAnimation(animation);
          this._current_status = paramInt;
        case 18:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          this.imgv_status_image.setVisibility(0);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.timeout_azul_sintexto_fondo, 2131165687);
          this.imgv_status_image.setImageDrawable(drawable);
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 17:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.actions_status_car_movement_tips_sintexto, 2131165302);
          this.imgv_status_image.setImageDrawable(drawable);
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 16:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.no_ejecutado_azul_sintexto_fondo, 2131165583);
          this.imgv_status_image.setImageDrawable(drawable);
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 15:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_alerta_sintexto_fondo, 2131165618);
          this.imgv_status_image.setImageDrawable(drawable);
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 14:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_sintexto_fondo, 2131165624);
          this.imgv_status_image.setImageDrawable(drawable);
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 13:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_azul_sintexto_fondo, 2131165628);
          this.imgv_status_image.setImageDrawable(drawable);
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 12:
          showProgressBar();
        case 11:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this.imgv_status_image.setVisibility(4);
          this.fl_redpoint.setVisibility(4);
          this._current_status = paramInt;
        case 10:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this._current_status = paramInt;
          this.fl_redpoint.setVisibility(0);
          this.imgv_redpoint_image.setVisibility(0);
          this.imgv_redpoint_image.setImageBitmap(Utilities.paintRedPoint(GlobalMembers.ctxBase, GlobalMembers.totalNotificaciones, GlobalMembers.redPoint));
        case 9:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          this._current_status = paramInt;
        case 8:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this._current_status = paramInt;
          this.fl_redpoint.setVisibility(0);
          this.imgv_redpoint_image.setVisibility(0);
          this.imgv_redpoint_image.setImageBitmap(Utilities.paintRedPointDTC(GlobalMembers.ctxBase, getBadgeNumber(), 2131165513, false));
        case 7:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          this.imgv_status_image.setVisibility(0);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.action_status_communication_failure_azul_fondo, 2131165274);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.timeout_azul_fondo, 2131165684);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 6:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.action_status_not_available, 2131165275);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.actions_status_car_movement, 2131165301);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 5:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.action_status_not_executed_azul_fondo, 2131165277);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.no_ejecutado_azul_fondo, 2131165581);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 4:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_alerta_fondo, 2131165632);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_alerta_fondo, 2131165632);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 3:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_executado_fondo, 2131165620);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_executado_fondo, 2131165620);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 2:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.action_status_send_wait_azul_fondo, 2131165280);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_azul_fondo, 2131165626);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this.imgv_status_image.startAnimation(animation);
          this._current_status = paramInt;
        case 1:
          showProgressBar();
        case 0:
          break;
      } 
      dismissProgressBar();
      this.fl_status_image.setVisibility(4);
      this.imgv_status_image.setVisibility(4);
      this.fl_redpoint.setVisibility(4);
      this._current_status = paramInt;
    } 
  }
  
  public void showActionStatusNoAni(int paramInt) {
    if (this._action_service_status == 1) {
      Drawable drawable;
      switch (paramInt) {
        default:
          return;
        case 21:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this.fl_redpoint.setVisibility(0);
          this.imgv_redpoint_image.setVisibility(0);
          this.imgv_redpoint_image.setImageBitmap(Utilities.paintRedPointDTC(GlobalMembers.ctxBase, getBadgeNumber(), 2131165513, false));
          this._current_status = paramInt;
        case 20:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          this._current_status = paramInt;
        case 19:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this.fl_redpoint.setVisibility(0);
          this.imgv_redpoint_image.setVisibility(0);
          this.imgv_redpoint_image.setImageBitmap(Utilities.paintRedPoint(GlobalMembers.ctxBase, GlobalMembers.notificaciones, GlobalMembers.redPoint));
          this._current_status = paramInt;
        case 18:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          this.imgv_status_image.setVisibility(0);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.timeout_azul_sintexto_fondo, 2131165687);
          this.imgv_status_image.setImageDrawable(drawable);
          this._current_status = paramInt;
        case 17:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.actions_status_car_movement_tips_sintexto, 2131165302);
          this.imgv_status_image.setImageDrawable(drawable);
          this._current_status = paramInt;
        case 16:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.no_ejecutado_azul_sintexto_fondo, 2131165583);
          this.imgv_status_image.setImageDrawable(drawable);
          this._current_status = paramInt;
        case 15:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_alerta_sintexto_fondo, 2131165618);
          this.imgv_status_image.setImageDrawable(drawable);
          this._current_status = paramInt;
        case 14:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_sintexto_fondo, 2131165624);
          this.imgv_status_image.setImageDrawable(drawable);
          this._current_status = paramInt;
        case 13:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_azul_sintexto_fondo, 2131165628);
          this.imgv_status_image.setImageDrawable(drawable);
          this._current_status = paramInt;
        case 12:
          showProgressBar();
        case 11:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this.imgv_status_image.setVisibility(4);
          this.fl_redpoint.setVisibility(4);
          this._current_status = paramInt;
        case 10:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this._current_status = paramInt;
          this.fl_redpoint.setVisibility(0);
          this.imgv_redpoint_image.setVisibility(0);
          this.imgv_redpoint_image.setImageBitmap(Utilities.paintRedPoint(GlobalMembers.ctxBase, GlobalMembers.totalNotificaciones, GlobalMembers.redPoint));
        case 9:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          this._current_status = paramInt;
        case 8:
          dismissProgressBar();
          this.fl_status_image.setVisibility(4);
          this._current_status = paramInt;
          this.fl_redpoint.setVisibility(0);
          this.imgv_redpoint_image.setVisibility(0);
          this.imgv_redpoint_image.setImageBitmap(Utilities.paintRedPointDTC(GlobalMembers.ctxBase, getBadgeNumber(), 2131165513, false));
        case 7:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          this.imgv_status_image.setVisibility(0);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.action_status_communication_failure_azul_fondo, 2131165274);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.timeout_azul_fondo, 2131165684);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this._current_status = paramInt;
        case 6:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.action_status_not_available, 2131165275);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.actions_status_car_movement, 2131165301);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this._current_status = paramInt;
        case 5:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.action_status_not_executed_azul_fondo, 2131165277);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.no_ejecutado_azul_fondo, 2131165581);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this._current_status = paramInt;
        case 4:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_alerta_fondo, 2131165632);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_alerta_fondo, 2131165632);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this._current_status = paramInt;
        case 3:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_executado_fondo, 2131165620);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_2azul_executado_fondo, 2131165620);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this._current_status = paramInt;
        case 2:
          dismissProgressBar();
          this.fl_status_image.setVisibility(0);
          this.imgv_status_image.setVisibility(0);
          this.fl_redpoint.setVisibility(4);
          if (getStyle() == 0) {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.action_status_send_wait_azul_fondo, 2131165280);
            this.imgv_status_image.setImageDrawable(drawable);
          } else {
            drawable = Utilities.getDrawableFromConfigList(this.context, DrawableResourcesVO.palomita_azul_fondo, 2131165626);
            this.imgv_status_image.setImageDrawable(drawable);
          } 
          this._current_status = paramInt;
        case 1:
          showProgressBar();
        case 0:
          break;
      } 
      dismissProgressBar();
      this.fl_status_image.setVisibility(4);
      this.imgv_status_image.setVisibility(4);
      this.fl_redpoint.setVisibility(4);
      this._current_status = paramInt;
    } 
  }
  
  public void showProgressBar() {
    if (this._action_service_status == 1) {
      this.fl_status_image.setVisibility(4);
      this.fl_redpoint.setVisibility(4);
      this.imgv_status_image.setVisibility(4);
      this.fl_progress_bar.setVisibility(0);
      this.pb_action_button.setVisibility(0);
      this._current_status = 1;
      set_is_progress_showing(true);
    } 
  }
  
  public String toString() {
    return this._str_action_title;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/VO/CustomActionButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */