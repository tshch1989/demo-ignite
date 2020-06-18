package com.example.demoignitesql.application;

import com.example.demoignitesql.infrastructure.dao.mapper.CityMapper;
import com.example.demoignitesql.infrastructure.dao.model.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class CityService {
    private final CityMapper cityMapper;

    public CityService(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ,
            rollbackFor = Exception.class
    )
    public String insertTran() throws InterruptedException {
        cityMapper.deleteByKey(1L);
        City city = new City();
        city.setName("city"+System.currentTimeMillis());
        city.setId(1L);
        cityMapper.insert(city);
        TimeUnit.SECONDS.sleep(30);
        return "success";
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ,
            rollbackFor = Exception.class
    )
    public String selectTran() throws InterruptedException {
        City city = cityMapper.selectByKey(1L);
        System.out.println(city);
        TimeUnit.SECONDS.sleep(30);
        City city2 = cityMapper.selectByKey(1L);
        if(city == null || city.equals(city2)){
            return "success";
        }else {
            return "fail";
        }
    }

}
