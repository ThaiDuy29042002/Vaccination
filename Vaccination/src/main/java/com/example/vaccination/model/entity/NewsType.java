package com.example.vaccination.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news_type")
public class NewsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_type_id", length = 36)
    private String newsTypeId;
    @Column(name = "description", length = 10)
    private String description;
    @Column(name = "news_type_name", length = 50)
    private String newsTypeName;
}
