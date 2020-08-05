package com.xcs.phase2.response.compare;

import lombok.Data;

import java.util.List;

@Data
public class CompareDetailStaffinsAllResponse extends CompareResponse{

    private String IsSuccess;
    private String Msg;
    private List<CompareStaffResponse> CompareStaff;
}
