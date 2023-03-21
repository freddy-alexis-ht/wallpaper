package com.taller.wallpaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.taller.wallpaper.model.Response;
import com.taller.wallpaper.services.IUploadFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private IUploadFile fileService;

    @PostMapping
    public ResponseEntity<Response> uploadFiles(@RequestParam(name = "files") List<MultipartFile> files) throws Exception{
        fileService.save(files);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new Response("Archivos cargados correctamente"));
    }
    
}
