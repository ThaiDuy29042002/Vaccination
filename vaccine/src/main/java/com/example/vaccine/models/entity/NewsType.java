package com.example.vaccine.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "NEWS_TYPE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEWS_TYPE_ID")
    private Integer newsTypeId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NEWS_TYPE_NAME")
    private String newsTypeName;

    @OneToMany(mappedBy = "newsType_n", cascade = CascadeType.ALL)
    private List<News> newsList;
}
