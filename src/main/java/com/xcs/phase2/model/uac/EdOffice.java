package com.xcs.phase2.model.uac;

import lombok.Data;

@Data
public class EdOffice extends UacModel {

    private int ID;
    private String OFFCODE;
    private String OFFNAME;
    private String SHORT_NAME;
    private String INDC_OFF;
    private String TAMBOL_CODE;
    private String SUPOFFCODE;
    private String BEGIN_DATE;
    private String END_DATE;
    private String UPD_USERID;
    private String UPD_DATE;

}
