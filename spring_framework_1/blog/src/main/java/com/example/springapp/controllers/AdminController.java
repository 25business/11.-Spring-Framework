package com.example.springapp.controllers;

import com.example.springapp.db.dao.PostDAO;
import com.example.springapp.templating.Renderer;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class AdminController {

    @GetMapping(value = "/admin")
    public @ResponseBody String index() throws TemplateException, IOException, SQLException {
        HashMap<String, Object> objectData = new HashMap<>();
        objectData.put("posts", PostDAO.all());
        return Renderer.render("admin_panel.ftl", objectData);
    }
}
