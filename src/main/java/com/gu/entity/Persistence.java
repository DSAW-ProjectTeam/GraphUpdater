package com.gu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persistence {

    @Id
    private int id;

    private String word;

    private String translation;

    private String likes;

    private int wordId;

    private String source;

    private String fuzzyWord;

    private String url;
}
