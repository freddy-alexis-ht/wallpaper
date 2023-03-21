package com.taller.wallpaper.services;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFile {
    String copy(MultipartFile file);
    boolean delete(String filename);
    /* */
    Resource load(String name);
    void save(List<MultipartFile> files) throws Exception;
    Stream<Path> loadAll() throws Exception;
    
}
