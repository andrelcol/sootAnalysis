package com.roadtrack.onstar;

public enum Enums$oneShotErrorStatus {
  ErrorConfirmedRenewal,
  ErrorDoesntExistDocument,
  ErrorDoesntExistFolio,
  ErrorDoesntExistOrder,
  ErrorMoipClient,
  ErrorNoBillInfo,
  ErrorNoPlansInfo,
  ErrorNotActiveContract,
  ErrorNotPaymentNumber,
  ErrorOrderAlreadyExists,
  ErrorRenewalAlreadyPaid,
  ErrorRenewalConfirmation,
  NoError(0),
  NotValid(0);
  
  private static final Enums$oneShotErrorStatus[] $VALUES;
  
  private final int code;
  
  static {
    ErrorDoesntExistDocument = new Enums$oneShotErrorStatus("ErrorDoesntExistDocument", 1, 1);
    ErrorDoesntExistFolio = new Enums$oneShotErrorStatus("ErrorDoesntExistFolio", 2, 2);
    ErrorDoesntExistOrder = new Enums$oneShotErrorStatus("ErrorDoesntExistOrder", 3, 3);
    ErrorRenewalConfirmation = new Enums$oneShotErrorStatus("ErrorRenewalConfirmation", 4, 4);
    ErrorNotActiveContract = new Enums$oneShotErrorStatus("ErrorNotActiveContract", 5, 5);
    ErrorNotPaymentNumber = new Enums$oneShotErrorStatus("ErrorNotPaymentNumber", 6, 6);
    ErrorConfirmedRenewal = new Enums$oneShotErrorStatus("ErrorConfirmedRenewal", 7, 7);
    ErrorRenewalAlreadyPaid = new Enums$oneShotErrorStatus("ErrorRenewalAlreadyPaid", 8, 8);
    ErrorOrderAlreadyExists = new Enums$oneShotErrorStatus("ErrorOrderAlreadyExists", 9, 9);
    ErrorNoBillInfo = new Enums$oneShotErrorStatus("ErrorNoBillInfo", 10, 10);
    ErrorNoPlansInfo = new Enums$oneShotErrorStatus("ErrorNoPlansInfo", 11, 12);
    ErrorMoipClient = new Enums$oneShotErrorStatus("ErrorMoipClient", 12, 13);
    NotValid = new Enums$oneShotErrorStatus("NotValid", 13, -1);
    $VALUES = new Enums$oneShotErrorStatus[] { 
        NoError, ErrorDoesntExistDocument, ErrorDoesntExistFolio, ErrorDoesntExistOrder, ErrorRenewalConfirmation, ErrorNotActiveContract, ErrorNotPaymentNumber, ErrorConfirmedRenewal, ErrorRenewalAlreadyPaid, ErrorOrderAlreadyExists, 
        ErrorNoBillInfo, ErrorNoPlansInfo, ErrorMoipClient, NotValid };
  }
  
  Enums$oneShotErrorStatus(int paramInt1) {
    this.code = paramInt1;
  }
  
  public static Enums$oneShotErrorStatus getValue(int paramInt) {
    Enums$oneShotErrorStatus[] arrayOfEnums$oneShotErrorStatus = values();
    for (byte b = 0; b < arrayOfEnums$oneShotErrorStatus.length; b++) {
      if (arrayOfEnums$oneShotErrorStatus[b].value() == paramInt)
        return arrayOfEnums$oneShotErrorStatus[b]; 
    } 
    return NotValid;
  }
  
  public String toString() {
    return String.valueOf(this.code);
  }
  
  public int value() {
    return this.code;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/Enums$oneShotErrorStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */