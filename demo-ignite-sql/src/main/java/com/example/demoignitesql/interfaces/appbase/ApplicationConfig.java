package com.example.demoignitesql.interfaces.appbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.demoignitesql.infrastructure.dao.mapper")
public class ApplicationConfig {
}
