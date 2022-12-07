package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository <Post, Long>{
    Post findById(long id);


    Post findByTitle(String post_to_be_deleted);
}
