package com.xcs.phase2.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Data
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String uploadVdo;
    private String uploadDocument;
    private String uploadPerson;
    private String uploadProduct;
    private String uploadApk;
    private String uploadOther;

}
