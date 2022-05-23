package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class FileService {

    private String path = "src/main/webapp/upload/logo/";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if (path.equals("default")) this.path = "src/main/webapp/upload/logo/";
        this.path = path;
    }

    public boolean uploadFile(MultipartFile file){
        if (!file.isEmpty()) {
            try {
                String name = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path
                         + name)));
                stream.write(bytes);
                stream.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

}
