package com.xcs.phase2.dao.other;

import com.xcs.phase2.model.other.Version;
import com.xcs.phase2.request.other.CheckVersionReq;
import com.xcs.phase2.request.other.DownloadNewVersionReq;
import com.xcs.phase2.request.other.UpdateDeleteVersionReq;
import com.xcs.phase2.response.Other.InsertNewVersionResponse;

import java.util.List;

public interface VersionDAO {

    public List<Version> CheckVersion(CheckVersionReq req);
    public Version DownloadNewVersion(DownloadNewVersionReq req);
    public InsertNewVersionResponse InsertNewVersion(Version req);
    public Boolean UpdateDeleteVersion(UpdateDeleteVersionReq req);

}
