package com.example.demoignitesql.infrastructure.dao.mapper;

import com.example.demoignitesql.infrastructure.dao.model.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {
    int initTable();

    int insert(City city);

    City selectByKey(Long key);

    List<City> selectLimit(int size);

    List<City> selectByName(String name);

    void deleteByKey(long key);
}
