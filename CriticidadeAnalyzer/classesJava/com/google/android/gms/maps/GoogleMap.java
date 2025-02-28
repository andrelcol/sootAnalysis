package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzan;
import com.google.android.gms.maps.internal.zzar;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzd;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class GoogleMap {
  private final IGoogleMapDelegate zzg;
  
  private UiSettings zzh;
  
  public GoogleMap(IGoogleMapDelegate paramIGoogleMapDelegate) {
    Preconditions.checkNotNull(paramIGoogleMapDelegate);
    this.zzg = paramIGoogleMapDelegate;
  }
  
  public final Marker addMarker(MarkerOptions paramMarkerOptions) {
    try {
      zzt zzt = this.zzg.addMarker(paramMarkerOptions);
      return (zzt != null) ? new Marker(zzt) : null;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate) {
    try {
      this.zzg.animateCamera(paramCameraUpdate.zzb());
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate, int paramInt, CancelableCallback paramCancelableCallback) {
    try {
      zza zza;
      IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
      IObjectWrapper iObjectWrapper = paramCameraUpdate.zzb();
      if (paramCancelableCallback == null) {
        paramCameraUpdate = null;
      } else {
        zza = new zza(paramCancelableCallback);
      } 
      iGoogleMapDelegate.animateCameraWithDurationAndCallback(iObjectWrapper, paramInt, (zzc)zza);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void clear() {
    try {
      this.zzg.clear();
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final UiSettings getUiSettings() {
    try {
      if (this.zzh == null) {
        UiSettings uiSettings = new UiSettings();
        this(this.zzg.getUiSettings());
        this.zzh = uiSettings;
      } 
      return this.zzh;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void moveCamera(CameraUpdate paramCameraUpdate) {
    try {
      this.zzg.moveCamera(paramCameraUpdate.zzb());
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final boolean setMapStyle(MapStyleOptions paramMapStyleOptions) {
    try {
      return this.zzg.setMapStyle(paramMapStyleOptions);
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMyLocationEnabled(boolean paramBoolean) {
    try {
      this.zzg.setMyLocationEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setOnMapClickListener(OnMapClickListener paramOnMapClickListener) {
    if (paramOnMapClickListener == null)
      try {
        this.zzg.setOnMapClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzy zzy = new zzy();
    this(this, (OnMapClickListener)remoteException);
    iGoogleMapDelegate.setOnMapClickListener((zzaj)zzy);
  }
  
  public final void setOnMapLongClickListener(OnMapLongClickListener paramOnMapLongClickListener) {
    if (paramOnMapLongClickListener == null)
      try {
        this.zzg.setOnMapLongClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzz zzz = new zzz();
    this(this, (OnMapLongClickListener)remoteException);
    iGoogleMapDelegate.setOnMapLongClickListener((zzan)zzz);
  }
  
  public final void setOnMarkerClickListener(OnMarkerClickListener paramOnMarkerClickListener) {
    if (paramOnMarkerClickListener == null)
      try {
        this.zzg.setOnMarkerClickListener(null);
        return;
      } catch (RemoteException remoteException) {
        throw new RuntimeRemoteException(remoteException);
      }  
    IGoogleMapDelegate iGoogleMapDelegate = this.zzg;
    zzb zzb = new zzb();
    this(this, (OnMarkerClickListener)remoteException);
    iGoogleMapDelegate.setOnMarkerClickListener((zzar)zzb);
  }
  
  public final void setTrafficEnabled(boolean paramBoolean) {
    try {
      this.zzg.setTrafficEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static interface CancelableCallback {
    void onCancel();
    
    void onFinish();
  }
  
  public static interface OnMapClickListener {
    void onMapClick(LatLng param1LatLng);
  }
  
  public static interface OnMapLongClickListener {
    void onMapLongClick(LatLng param1LatLng);
  }
  
  public static interface OnMarkerClickListener {
    boolean onMarkerClick(Marker param1Marker);
  }
  
  private static final class zza extends zzd {
    private final GoogleMap.CancelableCallback zzai;
    
    zza(GoogleMap.CancelableCallback param1CancelableCallback) {
      this.zzai = param1CancelableCallback;
    }
    
    public final void onCancel() {
      this.zzai.onCancel();
    }
    
    public final void onFinish() {
      this.zzai.onFinish();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/GoogleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */