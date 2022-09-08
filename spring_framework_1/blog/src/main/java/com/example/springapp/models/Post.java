package com.example.springapp.models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;

public class Post implements Serializable {
    private int posts_id;
    private String title;
    private String content;
    private String category;
    private String main_image;
    private LocalDateTime published;

    public Post() {}
    public Post(HashMap<String, Object> data) {
        Set<String> keys = data.keySet();
        keys.forEach(key -> {
            switch (key) {
                case "posts_id" -> this.posts_id = (int) data.get("posts_id");
                case "title" -> this.title = (String) data.get("title");
                case "content" -> this.content = (String) data.get("content");
                case "category" -> this.category = (String) data.get("category");
                case "main_image" -> this.main_image = (String) data.get("main_image");
                case "published" -> this.published = (LocalDateTime) data.get("published");
            }
        });
    }

    public static Post fromResultSet(ResultSet rs) throws SQLException {
        Post p = new Post();
        p.posts_id = rs.getInt("posts_id");
        p.title = rs.getString("title");
        p.category = rs.getString("category");
        p.main_image = rs.getString("main_image");
        p.content = rs.getString("content");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        p.published = LocalDateTime.parse(rs.getString("published"), dtf);
        return p;
    }

    public int getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(int posts_id) {
        this.posts_id = posts_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public void setPublished(LocalDateTime published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Post{" +
                "posts_id=" + posts_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                ", main_image='" + main_image + '\'' +
                ", published=" + published +
                '}';
    }
}
