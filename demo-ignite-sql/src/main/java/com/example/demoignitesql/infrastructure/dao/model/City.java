package com.example.demoignitesql.infrastructure.dao.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class City implements Serializable {
    private Long id;
    private String name;
}
