package com.xcs.phase2.request.other;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class UpdateDeleteVersionReq extends Request {

    private int VERSION_ID;
}
