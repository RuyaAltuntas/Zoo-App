package com.zoo.zoo_app.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.zoo_app.dao.AnimalDao;


@Service
public class AnimalService {
  @Autowired
    private AnimalDao animalDao;

    public void fetchMaleanimal() {
        animalDao.readMaleAnimalfromDB();
    }
    public void fetchFemaleanimal() {
        animalDao.readFemaleAnimalfromDB();
    }
    public List<Map<String, Object>> getAnimalStats() {
    return animalDao.getAnimalStats();
}
public List<Map<String, Object>> fetchTigerAndLionData() {
    return animalDao.getTigerandLionData();
}


}