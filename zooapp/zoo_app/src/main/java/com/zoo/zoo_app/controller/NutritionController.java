package com.zoo.zoo_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zoo.zoo_app.dao.NutritionDao;

import java.util.List;
import java.util.Map;

@RestController
public class NutritionController {

    @Autowired
    private NutritionDao nutritionDao;

    @GetMapping("/api/nutrition")
    public List<Map<String, Object>> getNutrition() {
        return nutritionDao.getNutritionData();
    }
}