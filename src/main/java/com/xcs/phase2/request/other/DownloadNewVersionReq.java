package com.xcs.phase2.request.other;

import com.xcs.phase2.request.Request;
import lombok.Data;

@Data
public class DownloadNewVersionReq extends Request {

    private String VERSION_ID;
}
