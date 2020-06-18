package com.example.demoignitesql.interfaces.controller;

import com.example.demoignitesql.application.CityService;
import com.example.demoignitesql.infrastructure.dao.mapper.CityMapper;
import com.example.demoignitesql.infrastructure.dao.model.City;
import com.example.demoignitesql.infrastructure.idgen.IdGen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;

@RestController
public class CityController {

    private final CityMapper cityMapper;
    private final CityService cityService;

    public CityController(CityMapper cityMapper,
                          CityService cityService) {
        this.cityMapper = cityMapper;
        this.cityService = cityService;
    }

    @GetMapping("/city/init")
    @ResponseBody
    public String initTable(){
        cityMapper.initTable();
        return "success";
    }

    @GetMapping("/city/insert")
    @ResponseBody
    public String insert(){
        IdGen idGen = new IdGen();
        for (int i = 0; i < 1000; i++) {
            City city = new City();
            city.setId(idGen.nextId());
            city.setName("city_"+i);
            System.out.println(city);;
            cityMapper.insert(city);
        }
        return "success";
    }

    @GetMapping("/city/select")
    @ResponseBody
    public List<City> select(){
        List<City> cities = cityMapper.selectLimit(1000);
        return cities;
    }

    /**
     * 简单测试一下插入速度,单节点
     * @return
     */
    @GetMapping("/city/insert_per")
    @ResponseBody
    public String insert_per(){
        IdGen idGen = new IdGen();
        Instant stime = Instant.now();
        int size = 100000;
        for (int i = 0; i < size; i++) {
            City city = new City();
            city.setId(idGen.nextId());
            city.setName("city_"+i);
//            System.out.println(city);;
            cityMapper.insert(city);
        }
        Instant etime = Instant.now();
        BigDecimal sub = new BigDecimal(Duration.between(stime, etime).toMillis());
        BigDecimal tt = new BigDecimal(size)
                .divide(sub, 8, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(1000));
        System.out.println(tt);
        return "success";
    }

    /**
     * 无索引随机查询,性能很差
     * 尽量使用索引
     * @return
     */
    @GetMapping("/city/select_ran")
    @ResponseBody
    public String select_ran(){
        IdGen idGen = new IdGen();
        int size = 100000;
        Random random = new Random();
        Instant stime = Instant.now();
        for (int i = 0; i < size; i++) {
            int i1 = random.nextInt(size);
            List<City> cities = cityMapper.selectByName("city_" + i1);
//            System.out.println(cities);
        }
        Instant etime = Instant.now();
        BigDecimal sub = new BigDecimal(Duration.between(stime, etime).toMillis());
        BigDecimal tt = new BigDecimal(size)
                .divide(sub, 8, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(1000));
        System.out.println(tt);
        return "success";
    }

    /**
     * 事务功能检测
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/city/insert_tran")
    @ResponseBody
    public String insert_tran() throws InterruptedException {
        cityService.insertTran();
        return "success";
    }

    /**
     * 事务功能检测
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/city/select_tran")
    @ResponseBody
    public String select_tran() throws InterruptedException {
        return cityService.selectTran();
    }
}
