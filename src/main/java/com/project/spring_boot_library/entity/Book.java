package com.project.spring_boot_library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="description")
    private String description;

    @Column(name="copies")
    private int copies;

    @Column(name="copiesAvailable")
    private int copiesAvailable;

    @Column(name="category")
    private String category;

    @Column(name="img")
    private String img;
}
