package sit.int221.ppclothes.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sit.int221.ppclothes.services.Filepath;

@RestController
public class ImageController {
    @Autowired
    Filepath File;

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("img") MultipartFile img) {
        String Value = "success";
        try {
            File.saveImage(img,img.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            Value = "error";
        }
        return Value;
    }

    @GetMapping(value = "/getImage/{name}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public byte[] getImage(@PathVariable String name) throws IOException {
        return File.getFile(name);
    }

    @DeleteMapping("/deleteImage")
    public String deleteImage(@RequestParam String name) {
        String Value = "Delete already.";
        try {
            File.deleteImage(name);
        } catch (Exception e) {
            e.printStackTrace();
            Value = "Can't delete.";
        }
        return Value;
    }


}