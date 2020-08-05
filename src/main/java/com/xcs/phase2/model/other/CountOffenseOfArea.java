package com.xcs.phase2.model.other;

import lombok.Data;

@Data
public class CountOffenseOfArea extends OtherModel {

    private String SUPOFFCODE;
    private String OFFCODE;
    private String OFFNAME;
    private int LAWSUIT_AMOUNT;
    private float PAYMENT_FINE;
    private float FINE;
    private float FINE_ESTIMATE;
    private float BRIBE_MONEY;
    private float REWARD_MONEY;
    private float TREASURY_MONEY;


}
