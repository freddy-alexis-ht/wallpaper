package com.taller.wallpaper.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taller.wallpaper.services.IUploadFile;

@Service
public class IUploadFileImpl implements IUploadFile{

    private final Path rootFolder = Paths.get("uploads");

    /** Metodo para guardar la imagen en un directorio externo
     * @param file archivo de imagen a guardar
     */
    @Override
    public String copy(MultipartFile file) {
        String uniqueFileName = UUID.randomUUID().toString().concat("_").concat(file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
            Path rutaCompleta = getPath(uniqueFileName);
            Files.write(rutaCompleta, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uniqueFileName;
    }

    @Override
    public boolean delete(String filename) {
        Path rutaFoto = getPath(filename);
        File archivo = rutaFoto.toFile();
        if (archivo.exists() && archivo.canRead()) {
            if (archivo.delete()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que obtiene la ruta completa de la ubicacion del archivo
     * @param fileName
     * @return
     */
    public Path getPath(String fileName) {
        String rootPath = "C://Temp//wallpapers";
        Path rutaFoto = Paths.get(rootPath + "//" + fileName);
        return rutaFoto;
    }

    @Override
    public Resource load(String name){
        /*Path files = rootFolder.resolve(name);
        Resource resource = new UrlResource(files.toUri());*/
        return null;
    }

    @Override
    public void save(List<MultipartFile> files) throws Exception {
        for(MultipartFile file: files) {
            this.copy(file);
        }
    }

    @Override
    public Stream<Path> loadAll() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadAll'");
    }
    
}
