package com.codeup.springblog.controllers;
import com.codeup.springblog.models.Post;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostsRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostsRepository postsDao;

    private final UserRepository usersDao;

    private final EmailService emailService;



    public PostController(PostsRepository postsDao, UserRepository usersDao, EmailService emailService){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    @GetMapping
    public String allPosts(Model model){
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("loggedInUser", loggedInUser);
        List<Post> allPosts = postsDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "/posts/index";
    }
    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }
    @GetMapping("/create")
    public String createPost(Model model){
//        List<User> users = usersDao.findAll();
//        model.addAttribute("users", users);
        model.addAttribute("post", new Post());
        return "posts/create";
    }
    @PostMapping("/create")
    public String submitPost(@ModelAttribute Post post, Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("loggedInUser", loggedInUser);
        if (loggedInUser.getId() == 0) {
        return "redirect:/login";
        }
//        Post posts = postsDao.findById(postId);
//        Post post = new Post(title, body, user);
        User user = usersDao.findById(loggedInUser.getId());
        post.setUser(user);
        postsDao.save(post);
        emailService.prepareAndSend(user, post.getTitle(), post.getBody());
        return "redirect:/posts";
    }
    @GetMapping("/users")
    public String showUsersForm(Model model){
        List<User> users = usersDao.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "posts/users";
    }
    @PostMapping("/users")
    public String insertUser(@ModelAttribute User user){
//        User user = new User();
//        user.setEmail(email);
//        user.setUsername(username);
//        user.setPassword(password);
        usersDao.save(user);
        return "posts/users";
    }

    @GetMapping("/{id}/edit")
    public String showEditPostForm(@PathVariable long id, Model model){
        System.out.println("I got it");
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        ).getId();
        if (currentUserId == 0) {
            return "redirect:/login";
        }
        Post post = postsDao.findById(id);
        if (post.getUser().getId() != currentUserId){;
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@ModelAttribute Post post){
        long currentUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        ).getId();
        if (currentUserId == 0) {
            return "redirect:/login";
        }
        User user = usersDao.findById(currentUserId);
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }
    @RequestMapping(path = "/delete")
    public String deletePostById(@RequestParam("id") long id,Model model){
        postsDao.deleteById(id);
        model.addAttribute("posts",postsDao.findAll());
        return "redirect:/posts";
    }
}
