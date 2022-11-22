package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Coffee> coffees;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Supplier() {
    }

    public Supplier(String name) {
        this.name = name;
    }
}
