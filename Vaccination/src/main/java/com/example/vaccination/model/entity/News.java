package com.example.vaccination.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id", length = 36)
    private Integer newsId;

    @Column(name = "content", length = 4000)
    @NotNull
    private String content;

    @Column(name = "preview", length = 1000)
    @NotNull
    private String preview;

    @Column(name = "title", length = 300)
    @NotNull
    private String title;

    @Column(name = "postdate")
    @NotNull
    private Date postdate;
}