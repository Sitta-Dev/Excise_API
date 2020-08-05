package com.xcs.phase2.request.other;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class CheckVersionReq extends Request {

    private int VERSION_TYPE;
}
