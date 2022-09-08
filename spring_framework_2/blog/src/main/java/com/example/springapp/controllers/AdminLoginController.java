package com.example.springapp.controllers;

import com.example.springapp.models.User;
import com.example.springapp.templating.Renderer;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AdminLoginController {

    @GetMapping("/admin/login")
    public @ResponseBody String login() throws TemplateException, IOException {
        return Renderer.render("login.ftl");
    }
    @PostMapping("/admin/login")
    public @ResponseBody void login_submit(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            HttpSession session, HttpServletResponse response) {
        if(username.equals("admin") && password.equals("1234")) {
            User user = new User("admin", "1234");
            session.setAttribute("admin", user);
            response.setStatus(302);
            response.setHeader("Location", "/admin");
        } else {
            response.setStatus(302);
            response.setHeader("Location", "/admin/login?error=InvalidLogin");
        }
    }

    @GetMapping("/admin/logout")
    public @ResponseBody void logout(HttpSession session,
                                     HttpServletResponse response) {
        session.removeAttribute("admin");
        response.setStatus(302);
        response.setHeader("Location", "/admin/login");
    }
}
