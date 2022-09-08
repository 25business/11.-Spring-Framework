package com.example.springapp.controllers;

import com.example.springapp.db.dao.PostDAO;
import com.example.springapp.models.Post;
import com.example.springapp.templating.Renderer;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class AdminPostsController {

    @GetMapping(value = "/admin/posts/new")
    public @ResponseBody String newPostForm() throws TemplateException, IOException {
        return Renderer.render("new_post.ftl");
    }

    @PostMapping(value = "/admin/posts/new")
    public @ResponseBody void newPostSubmit(@ModelAttribute("title") String title,
                                              @ModelAttribute("content") String content,
                                            @ModelAttribute("category") String category,
                                              @RequestParam("main_image") MultipartFile image,
                                              HttpServletResponse response) throws TemplateException, IOException, SQLException {

        image.transferTo(new File(System.getenv("JAVA_RESOURCES") + "/spring_blog/images/" + image.getOriginalFilename()));

        HashMap<String, Object> post_data = new HashMap<>();
        post_data.put("title", title);
        post_data.put("content", content);
        post_data.put("category", category);
        post_data.put("published", LocalDateTime.now());
        post_data.put("main_image", image.getOriginalFilename());
        Post p = new Post(post_data);
        PostDAO.save(p);

        response.setStatus(302);
        response.setHeader("Location","/admin");
    }

    @GetMapping(value = "/admin/posts/delete/{post_id}")
    public @ResponseBody void delete(@PathVariable int post_id,
                                     HttpServletResponse response) throws SQLException {

        PostDAO.delete(post_id);
        response.setStatus(302);
        response.setHeader("Location","/admin");
    }

    @GetMapping("/admin/posts/edit/{post_id}")
    public @ResponseBody String edit_post_form(@PathVariable int post_id) throws SQLException, TemplateException, IOException {
       Post p = PostDAO.one(post_id);
       HashMap<String, Object> objectModel = new HashMap<>();
       objectModel.put("post", p);
       return Renderer.render("edit_post_form.ftl", objectModel);
    }

    @PostMapping("/admin/posts/edit/{post_id}")
    public @ResponseBody void edit_post_submit(@PathVariable int post_id,
                                                 @ModelAttribute("title") String title,
                                                 @ModelAttribute("content") String content,
                                                 @ModelAttribute("category") String category,
                                                 HttpServletResponse response) throws SQLException, TemplateException, IOException {
        Post p = new Post();
        p.setPosts_id(post_id);
        p.setTitle(title); p.setCategory(category); p.setContent(content);
        PostDAO.update(p);
        response.setStatus(302);
        response.setHeader("Location","/posts/" + post_id);
    }


}
