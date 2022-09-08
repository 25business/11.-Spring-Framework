package com.example.springapp.db.dao;

import com.example.springapp.db.MySQLConnection;
import com.example.springapp.models.Post;

import java.sql.*;
import java.util.ArrayList;

public class PostDAO {

    public static ArrayList<Post> all() throws SQLException {
        Connection connection = MySQLConnection.get();
        ArrayList<Post> posts = new ArrayList<>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM posts");
        while(rs.next()) {
            posts.add(Post.fromResultSet(rs));
        }
        return posts;
    }

    public static Post one(int post_id) throws SQLException {
        Connection connection = MySQLConnection.get();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM posts WHERE posts_id = " + post_id);
        rs.next();
        return Post.fromResultSet(rs);
    }

    public static int save(Post post) throws SQLException {
        Connection connection = MySQLConnection.get();
        PreparedStatement st = connection.prepareStatement("""
    INSERT INTO posts VALUES (NULL, ?, ?, ?, ?, ?)""");
        st.setObject(1, post.getTitle());
        st.setObject(2, post.getContent());
        st.setObject(3, post.getPublished());
        st.setObject(4, post.getCategory());
        st.setObject(5, post.getMain_image());
        return st.executeUpdate();
    }

    public static int delete(int post_id) throws SQLException {
        Connection connection = MySQLConnection.get();
        Statement st = connection.createStatement();
        return st.executeUpdate("DELETE FROM posts WHERE posts_id = " + post_id);
    }

    public static int update(Post post) throws SQLException {
        Connection connection = MySQLConnection.get();
        PreparedStatement ps = connection.prepareStatement("UPDATE posts SET title = ?, content = ?, category = ? WHERE posts_id = ?");
        ps.setObject(1, post.getTitle());
        ps.setObject(2, post.getContent());
        ps.setObject(3, post.getCategory());
        ps.setObject(4, post.getPosts_id());
        return ps.executeUpdate();
    }
}
