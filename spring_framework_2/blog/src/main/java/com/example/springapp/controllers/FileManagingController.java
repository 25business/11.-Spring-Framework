package com.example.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileManagingController {

    @GetMapping("/upload")
    public @ResponseBody String upload_form() {
        return """
                <form action="/upload" method="post"
                enctype="multipart/form-data">
                    <input type="file" name="datoteka" />
                    <button type="submit">Upload</button>
                </form>
                """;
    }

    @PostMapping("/upload")
    public @ResponseBody String upload_file(@RequestParam("datoteka")MultipartFile datoteka) throws IOException {
        String filename = datoteka.getOriginalFilename();
        datoteka.transferTo(new File(System.getenv("JAVA_RESOURCES") + "/" + filename));
        return "File uploaded!";
    }

}
