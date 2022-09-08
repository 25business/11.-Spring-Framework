package com.example.springapp.controllers;

import com.example.springapp.db.dao.PostDAO;
import com.example.springapp.models.Post;
import com.example.springapp.templating.Renderer;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class PostController {

    @GetMapping("/posts/{post_id}")
    public @ResponseBody String single_post(@PathVariable int post_id) throws SQLException, TemplateException, IOException {
        Post p = PostDAO.one(post_id);
        HashMap<String, Object> modelData = new HashMap<>();
        modelData.put("post", p);
        return Renderer.render("single_post.ftl", modelData);
    }
}
