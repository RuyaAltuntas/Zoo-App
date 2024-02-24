package com.zoo.zoo_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zoo.zoo_app.dao.AnimalDao;

import java.util.List;
import java.util.Map;

@RestController
public class AnimalController {

   
    @Autowired
    private AnimalDao animalDao;

    @GetMapping("/api/animals/male")
    public List<Map<String, Object>> getMaleAnimals() {
        return animalDao.readMaleAnimalfromDB();
    }
     @GetMapping("/api/animals/female")
    public List<Map<String, Object>> getFemaleAnimals() {
        return animalDao.readFemaleAnimalfromDB();
    }

    @GetMapping("/api/animals/stats")
    public List<Map<String, Object>> getAnimalStats() {
        return animalDao.getAnimalStats();
    }
    @GetMapping("/api/tigerandlion")
public List<Map<String, Object>> getTigerAndLionData() {
    return animalDao.getTigerandLionData();
}

@GetMapping("/api/caretakerAnimalStats")
public List<Map<String, Object>> getCaretakerAnimalStats() {
    return animalDao.getCaretakerAnimalStats();
}

}