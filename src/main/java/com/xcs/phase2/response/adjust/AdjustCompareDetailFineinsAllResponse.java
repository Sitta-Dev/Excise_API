package com.xcs.phase2.response.adjust;

import lombok.Data;

import java.util.List;

@Data
public class AdjustCompareDetailFineinsAllResponse extends AdjustResponse{

    private String IsSuccess;
    private String Msg;
    private List<AdjustCompareDetailFineResponse> AdjustCompareDetailFine;
}
