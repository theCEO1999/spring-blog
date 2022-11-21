package com.codeup.springblog.models;


import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    public Post(Integer id, String title, String body) {
        Id = id;
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 500)
    private String body;

    public Post(){}

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
