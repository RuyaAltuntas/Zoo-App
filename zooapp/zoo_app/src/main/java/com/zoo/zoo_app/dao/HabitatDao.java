package com.zoo.zoo_app.dao;
import org.springframework.stereotype.Repository;

import com.zoo.zoo_app.model.Habitat;
import com.zoo.zoo_app.model.Visitor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Repository
public class HabitatDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int insertHabitat(Habitat habitat) {
        return this.jdbcTemplate.update(
            "INSERT INTO habitat (HabitatID, Capacity, Habitat_Type) VALUES (?, ?, ?)",
            habitat.getHabitatID(), habitat.getCapacity(), habitat.getHabitat_Type()
        );
    }
    public int deleteHabitat(int HabitatID) {
        return this.jdbcTemplate.update(
            "DELETE FROM habitat WHERE HabitatID = ?",
            HabitatID
        );
    }
    public List<Map<String, Object>> getHabitats() {
       return this.jdbcTemplate.queryForList("select * from habitat");
      
    }
    public List<Map<String, Object>> getVisitorHabitatCapacityView() {
        return this.jdbcTemplate.queryForList("SELECT * FROM VisitorHabitatCapacityView");
    }

    public List<Visitor> getSpecialCapacityView() {
        String sql = "SELECT Visitor_FirstName, Visitor_LastName, VisitDate " +
                     "FROM VisitorHabitatCapacityView " +
                     "WHERE Capacity > 5 " +
                     "UNION " +
                     "SELECT Visitor_FirstName, Visitor_LastName, VisitDate " +
                     "FROM VisitorHabitatCapacityView " +
                     "WHERE Capacity < 20";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Visitor visitor = new Visitor();
            visitor.setVisitorFirstName(rs.getString("Visitor_FirstName"));
            visitor.setVisitorLastName(rs.getString("Visitor_LastName"));
            visitor.setVisitDate(rs.getDate("VisitDate"));
            return visitor;
        });
    }
    public void addHumidityLevelColumn() {
        String sql = "ALTER TABLE habitat ADD COLUMN humidity_level INT;";
                
        try {
            jdbcTemplate.execute(sql);
            System.out.println("Column humidity_level added successfully.");
        } catch (Exception e) {
            System.out.println("Failed to add column: " + e.getMessage());
        }
    }
    public void dropHumidityLevelColumn() {
        String sql = "ALTER TABLE habitat DROP COLUMN humidity_level;";
                
        try {
            jdbcTemplate.execute(sql);
            System.out.println("Column humidity_level dropped successfully.");
        } catch (Exception e) {
            System.out.println("Failed to drop column: " + e.getMessage());
        }
    }
    public void updateHumidityLevel() {
        String sql = "UPDATE habitat SET humidity_level = 90 WHERE Habitat_Type = 'Desert' AND humidity_level IS NULL;";

        try {
            jdbcTemplate.update(sql);
            System.out.println("Humidity level updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update humidity level: " + e.getMessage());
        }
    }
    
    
    
}
