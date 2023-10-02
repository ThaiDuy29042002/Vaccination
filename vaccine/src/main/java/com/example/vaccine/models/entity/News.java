package com.example.vaccine.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "NEWS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEW_ID")
    private Integer newsId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PREVIEW")
    private String preview;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "News_Type_ID")
    private NewsType newsType_n;

}
