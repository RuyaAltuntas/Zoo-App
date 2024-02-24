package com.zoo.zoo_app;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zoo.zoo_app.dao.HabitatDao;
import com.zoo.zoo_app.dao.NutritionDao;
import com.zoo.zoo_app.model.Habitat;
import com.zoo.zoo_app.dao.AnimalDao;
import com.zoo.zoo_app.service.AnimalService;
import com.zoo.zoo_app.service.HabitatService;

@SpringBootApplication


public class ZooAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(ZooAppApplication.class, args);
    }
    @GetMapping("/zooview.html")
    public String zooview() {
    return "zooview";
}


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

  

        @Autowired
        private HabitatDao habitatDao; // Declare an instance of HabitatDao

        @GetMapping("/api/habitats")
        public List<Map<String, Object>> getHabitats() {
            return habitatDao.getHabitats();
        }
        
        @PostMapping("/api/habitats/insert")
    public ResponseEntity<String> insertHabitat(@RequestBody Habitat habitat) {
    
        return ResponseEntity.ok("Habitat inserted successfully");
    }


        @Bean
        public CommandLineRunner commandLineRunnerMale(ApplicationContext ctx) {
            return args -> {
                AnimalService animalService = ctx.getBean(AnimalService.class);
                animalService.fetchMaleanimal();
            };
        }
    

        @Bean
        public CommandLineRunner commandLineRunnerFemale(ApplicationContext ctx) {
            return args -> {
                AnimalService animalService = ctx.getBean(AnimalService.class);
                animalService.fetchFemaleanimal();
            };
        }
        

        @Bean
        public CommandLineRunner commandLineRunnerFetchHabitat(ApplicationContext ctx) {
            return args -> {
                HabitatService habitatService = ctx.getBean(HabitatService.class);
                habitatService.fetchHabitat();
            };
            
        }
        @Autowired
        private NutritionDao nutritionDao;

        @GetMapping("/api/nutrition")
        public List<Map<String, Object>> getNutritionData() {
            return nutritionDao.getNutritionData();
        }
        @GetMapping("/api/tigerandlion")
        public String getTigerAndLionView() {
            return "tigerandlion";
        }
        


    }

