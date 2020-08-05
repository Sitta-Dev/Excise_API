package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareinsAllResponse extends CompareResponse{

    private String IsSuccess;
    private String Msg;
    private int COMPARE_ID;
    private List<CompareMappingResponse> CompareMapping;

}
