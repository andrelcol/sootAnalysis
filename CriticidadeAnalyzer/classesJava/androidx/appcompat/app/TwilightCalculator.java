package androidx.appcompat.app;

class TwilightCalculator {
  private static TwilightCalculator sInstance;
  
  public int state;
  
  public long sunrise;
  
  public long sunset;
  
  static TwilightCalculator getInstance() {
    if (sInstance == null)
      sInstance = new TwilightCalculator(); 
    return sInstance;
  }
  
  public void calculateTwilight(long paramLong, double paramDouble1, double paramDouble2) {
    float f2 = (float)(paramLong - 946728000000L) / 8.64E7F;
    float f1 = 0.01720197F * f2 + 6.24006F;
    double d2 = f1;
    double d1 = Math.sin(d2) * 0.03341960161924362D + d2 + Math.sin((2.0F * f1)) * 3.4906598739326E-4D + Math.sin((f1 * 3.0F)) * 5.236000106378924E-6D + 1.796593063D + Math.PI;
    paramDouble2 = -paramDouble2 / 360.0D;
    paramDouble2 = ((float)Math.round((f2 - 9.0E-4F) - paramDouble2) + 9.0E-4F) + paramDouble2 + Math.sin(d2) * 0.0053D + Math.sin(2.0D * d1) * -0.0069D;
    d1 = Math.asin(Math.sin(d1) * Math.sin(0.4092797040939331D));
    paramDouble1 = 0.01745329238474369D * paramDouble1;
    paramDouble1 = (Math.sin(-0.10471975803375244D) - Math.sin(paramDouble1) * Math.sin(d1)) / Math.cos(paramDouble1) * Math.cos(d1);
    if (paramDouble1 >= 1.0D) {
      this.state = 1;
      this.sunset = -1L;
      this.sunrise = -1L;
      return;
    } 
    if (paramDouble1 <= -1.0D) {
      this.state = 0;
      this.sunset = -1L;
      this.sunrise = -1L;
      return;
    } 
    paramDouble1 = (float)(Math.acos(paramDouble1) / 6.283185307179586D);
    this.sunset = Math.round((paramDouble2 + paramDouble1) * 8.64E7D) + 946728000000L;
    this.sunrise = Math.round((paramDouble2 - paramDouble1) * 8.64E7D) + 946728000000L;
    if (this.sunrise < paramLong && this.sunset > paramLong) {
      this.state = 0;
    } else {
      this.state = 1;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/appcompat/app/TwilightCalculator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */