package com.xcs.phase2.response.master;

import com.xcs.phase2.model.master.MasOffice;
import lombok.Data;

import java.util.List;

@Data
public class MasOfficeResponse extends MasterResponse {

    private List<MasOffice> RESPONSE_DATA;
}
