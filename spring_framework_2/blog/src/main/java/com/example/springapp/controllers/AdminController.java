package com.example.springapp.controllers;

import com.example.springapp.db.dao.PostDAO;
import com.example.springapp.models.User;
import com.example.springapp.templating.Renderer;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class AdminController {

    @GetMapping(value = "/admin")
    public @ResponseBody String index(HttpSession session,
                                      HttpServletResponse response) throws TemplateException, IOException, SQLException {
        User user = (User)session.getAttribute("admin");
        if(user == null) {
            response.setStatus(302);
            response.setHeader("Location","/admin/login?error=NoLogin");
        }

        HashMap<String, Object> objectData = new HashMap<>();
        objectData.put("posts", PostDAO.all());
        return Renderer.render("admin_panel.ftl", objectData);
    }
}
