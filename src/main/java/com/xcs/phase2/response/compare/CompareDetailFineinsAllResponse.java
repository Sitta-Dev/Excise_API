package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareDetailFineinsAllResponse extends CompareResponse{

    private String IsSuccess;
    private String Msg;
    private List<CompareDetailFineResponse> CompareDetailFine;
}
