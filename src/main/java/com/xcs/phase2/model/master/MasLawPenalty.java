package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasLawPenalty extends MasterProductModel {

    private int PENALTY_ID;
    private int SECTION_ID;
    private String PENALTY_DESC;
    private int IS_FINE_PRISON;
    private int IS_FINE;
    private float FINE_RATE_MIN;
    private float FINE_RATE_MAX;
    private float FINE_MIN;
    private float FINE_MAX;
    private int IS_IMPRISON;
    private float PRISON_RATE_MIN;
    private float PRISON_RATE_MAX;
    private int IS_TAX_PAID;
    private int IS_ACTIVE;


}
