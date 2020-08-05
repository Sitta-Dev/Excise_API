package com.xcs.phase2.model.uac;

import lombok.Data;

import java.util.List;

@Data
public class Module extends UacModel {

    private int MODULE_ID;
    private String MODULE_CODE;
    private String MODULE_NAME;
    private int SEQUENCE;
    private int IS_ACTIVE;
    private List<ModuleDetail> ModuleDetail;

}
