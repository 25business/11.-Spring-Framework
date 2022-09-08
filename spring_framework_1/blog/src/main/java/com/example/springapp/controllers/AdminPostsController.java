package com.example.springapp.controllers;

import com.example.springapp.db.dao.PostDAO;
import com.example.springapp.models.Post;
import com.example.springapp.templating.Renderer;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class AdminPostsController {

    @GetMapping(value = "/posts/new")
    public @ResponseBody String newPostForm() throws TemplateException, IOException {
        return Renderer.render("new_post.ftl");
    }

    @PostMapping(value = "/posts/new")
    public @ResponseBody void newPostSubmit(@ModelAttribute("title") String title,
                                              @ModelAttribute("content") String content, @ModelAttribute("category") String category,
                                              HttpServletResponse response) throws TemplateException, IOException, SQLException {

        HashMap<String, Object> post_data = new HashMap<>();
        post_data.put("title", title);
        post_data.put("content", content);
        post_data.put("category", category);
        post_data.put("published", LocalDateTime.now());
        Post p = new Post(post_data);
        PostDAO.save(p);

        response.setStatus(302);
        response.setHeader("Location","/admin");
    }

    @GetMapping(value = "/posts/delete/{post_id}")
    public @ResponseBody void delete(@PathVariable int post_id,
                                     HttpServletResponse response) throws SQLException {

        PostDAO.delete(post_id);
        response.setStatus(302);
        response.setHeader("Location","/admin");
    }
}
