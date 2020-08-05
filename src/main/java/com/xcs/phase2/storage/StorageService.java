package com.xcs.phase2.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();

    String store(MultipartFile file, String type);
}
