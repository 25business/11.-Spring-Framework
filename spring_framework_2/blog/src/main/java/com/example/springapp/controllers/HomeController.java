package com.example.springapp.controllers;

import com.example.springapp.db.dao.PostDAO;
import com.example.springapp.templating.Renderer;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public @ResponseBody String index() throws TemplateException, IOException, SQLException {
        HashMap<String, Object> objectData = new HashMap<>();
        objectData.put("posts", PostDAO.all());

        return Renderer.render("home.ftl", objectData);
    }

    @GetMapping(value="/pdf", produces = "application/pdf")
    public @ResponseBody byte[] getPDF(HttpServletResponse response) throws IOException {
        File f = new File(System.getenv("JAVA_RESOURCES") + "/da5/racun.pdf");
        response.setHeader("Content-Disposition","attachment;filename=\"racun_new.pdf\"");
        return Files.readAllBytes(f.toPath());
    }
}
