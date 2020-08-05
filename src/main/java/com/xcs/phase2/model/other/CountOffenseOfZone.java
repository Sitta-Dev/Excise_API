package com.xcs.phase2.model.other;

import lombok.Data;

@Data
public class CountOffenseOfZone extends OtherModel {

    private int ID;
    private int COUNT;
    private String SUPOFFCODE;
    private String OFFCODE;
    private String OFFNAME;
    private String SHORT_NAME;
    private String INDC_OFF;
    private int ZONE_ID;
    private String ZONE_OFFCODE;
    private String ZONE_OFFNAME;
    private String ZONE_SHORT_NAME;
    private String ZONE_INDC_OFF;

}
