package com.zoo.zoo_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.zoo_app.dao.NutritionDao;
import java.util.List;
import java.util.Map;

@Service
public class NutritionService {

    @Autowired
    private NutritionDao nutritionDao;
    
    public List<Map<String, Object>> fetchNutrition() {
        return nutritionDao.getNutritionData();
    }
}