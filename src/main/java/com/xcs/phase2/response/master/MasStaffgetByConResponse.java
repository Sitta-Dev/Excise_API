package com.xcs.phase2.response.master;

import com.xcs.phase2.model.master.MasStaff;
import lombok.Data;

import java.util.List;

@Data
public class MasStaffgetByConResponse extends MasterResponse {

    private Boolean SUCCESS;
    private List<MasStaff> RESPONSE_DATA;
}
