package com.example.demoignitesql.infrastructure.dao.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private Long id;
    private String name;
    private Long cityId;
}
