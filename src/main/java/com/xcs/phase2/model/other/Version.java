package com.xcs.phase2.model.other;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Version extends OtherModel {

    private int VERSION_ID;
    private String	FILE_PATH;
    private int VERSION_TYPE;
    private String	VERSION_NAME;
    private int	IS_ACTIVE;


    private MultipartFile FILE;


}
