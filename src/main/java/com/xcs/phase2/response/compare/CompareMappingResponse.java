package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareMappingResponse extends CompareResponse{

    private int COMPARE_MAPPING_ID;
    private List<CompareDetailResponse> CompareDetail;
}
