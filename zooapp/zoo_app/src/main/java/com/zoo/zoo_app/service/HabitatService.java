package com.zoo.zoo_app.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.zoo.zoo_app.dao.HabitatDao;
import com.zoo.zoo_app.model.Habitat;

@Service
public class HabitatService {
    @Autowired
    private HabitatDao habitatDao;

    public void fetchHabitat() {
        habitatDao.getHabitats();
    }
    public int insertHabitat(Habitat habitat) {
        return habitatDao.insertHabitat(habitat);
    }
    public void addHumidityLevelColumn() {
        habitatDao.addHumidityLevelColumn();
    }
    public void updateHumidityLevel() {
        habitatDao.updateHumidityLevel();
    }
    public void dropHumidityLevelColumn() {
        habitatDao.dropHumidityLevelColumn();
    }

}
