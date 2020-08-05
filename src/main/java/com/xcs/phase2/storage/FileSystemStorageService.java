package com.xcs.phase2.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService
{
    private final Path rootLocationVdo;
    private final Path rootLocationDocument;
    private final Path rootLocationPerson;
    private final Path rootLocationProduct;
    private final Path rootLocationApk;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocationVdo = Paths.get(properties.getUploadVdo()).toAbsolutePath().normalize();
        this.rootLocationDocument = Paths.get(properties.getUploadDocument()).toAbsolutePath().normalize();
        this.rootLocationPerson = Paths.get(properties.getUploadPerson()).toAbsolutePath().normalize();
        this.rootLocationProduct = Paths.get(properties.getUploadProduct()).toAbsolutePath().normalize();
        this.rootLocationApk = Paths.get(properties.getUploadApk()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.rootLocationVdo);
            Files.createDirectories(this.rootLocationDocument);
            Files.createDirectories(this.rootLocationPerson);
            Files.createDirectories(this.rootLocationProduct);
            Files.createDirectories(this.rootLocationApk);
        } catch (Exception ex) {
            throw new StorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String store(MultipartFile file ,String type)
    {


        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try
        {
            if (file.isEmpty())
            {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = null;
            if ("document".equals(type)) {
                targetLocation = this.rootLocationDocument.resolve(fileName);
            } else if ("person".equals(type)) {
                targetLocation = this.rootLocationPerson.resolve(fileName);
            } else if ("product".equals(type)) {
                targetLocation = this.rootLocationProduct.resolve(fileName);
            } else if ("vdo".equals(type)) {
                targetLocation = this.rootLocationVdo.resolve(fileName);
            }else if ("apk".equals(type)) {
                targetLocation = this.rootLocationApk.resolve(fileName);
            }
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }
        catch (IOException e)
        {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    @Override
    public void init()
    {
        try
        {
            Files.createDirectories(this.rootLocationVdo);
            Files.createDirectories(this.rootLocationDocument);
            Files.createDirectories(this.rootLocationPerson);
            Files.createDirectories(this.rootLocationProduct);
            Files.createDirectories(this.rootLocationApk);
        }
        catch (IOException e)
        {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
