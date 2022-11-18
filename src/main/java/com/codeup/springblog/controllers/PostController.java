package com.codeup.springblog.controllers;
import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {


    @GetMapping
    public String allPosts(Model model){
        Post post1 = new Post(1, "First","This is my first post!!");
        Post post2 = new Post(2, "Second","This is my second post!!");
        List<Post> allPosts = new ArrayList<>(List.of(post1, post2));
        model.addAttribute("allPosts", allPosts);
        return "/posts/index";
    }
    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        Post post1 = new Post(1, "First","This is my first post!!");
        Post post2 = new Post(2, "Second","This is my second post!!");
        Post post3 = new Post(3, "y0", "hey hey hey");
        model.addAttribute("post", post3);
        List<Post> allPosts = new ArrayList<>(List.of(post1, post2, post3));
        Post post = null;
        for (Post userPost : allPosts){
            if (userPost.getId() == id){
                post = userPost;
            }
        }
        model.addAttribute("post", post);
//        model.addAttribute("postId", id);
        return "posts/show";
    }
    @GetMapping("/create")
    @ResponseBody
    public String createPost(){
        return "Here is the form to create a post!";
    }
}
