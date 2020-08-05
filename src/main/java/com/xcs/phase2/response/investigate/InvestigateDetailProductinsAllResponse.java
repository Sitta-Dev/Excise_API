package com.xcs.phase2.response.investigate;

import lombok.Data;

import java.util.List;

@Data
public class InvestigateDetailProductinsAllResponse extends InvestigateResponse {

    private String IsSuccess;
    private String Msg;
    private List<InvestigateDetailProductResponse> InvestigateDetailProduct;
}
