package com.taller.wallpaper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.taller.wallpaper.model.Response;

@ControllerAdvice
public class FileException {

    private static final String MESSAGE_ERROR_SIZE = "Archivos muy pesados";

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response> handleMaxSizeException(MaxUploadSizeExceededException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(MESSAGE_ERROR_SIZE));
    }
}
