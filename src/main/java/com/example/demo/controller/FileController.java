package com.example.demo.controller;

import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping(value= "/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value= "/upload", method= RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file){
        System.out.println(file.getOriginalFilename());
        ModelAndView modelAndView = new ModelAndView("redirect:/addWithLogo");
        if(fileService.uploadFile(file)){
            modelAndView.addObject("filename", file.getOriginalFilename());
        }
        return modelAndView;
    }
}
