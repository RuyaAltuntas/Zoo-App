package com.zoo.zoo_app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


@Repository
public class AnimalDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Map<String, Object>> readMaleAnimalfromDB() {
       return this.jdbcTemplate.queryForList("SELECT DISTINCT AnimalID, " +
       "CONCAT('Caretaker: ', CaretakerID, ', ', 'Supplier: ', SupplierID) AS Caretaker_Supplier_Info, " +
       "Animal_name, " +
       "Animal_Birthdate, " +
       "Animal_Gender, " +
       "Animal_Specie " +
       "FROM animal " +
       "WHERE is_sick = 1 " +
       "AND (NutritionID IN (1, 2, 3) OR NutritionID IS NULL) " +
       "AND Animal_Specie LIKE 'G%' " +
       "AND Animal_name IS NOT NULL " +
       "AND LENGTH(Animal_name) > 3;"
);
      

    }
    @Autowired
    public List<Map<String, Object>> readFemaleAnimalfromDB() {
       return this.jdbcTemplate.queryForList("SELECT AnimalID, " +
       "CONCAT('Caretaker: ', CaretakerID, ', ', 'Supplier: ', SupplierID) AS Caretaker_Supplier_Info, " +
       "Animal_name " +
       "FROM animal " +
       "WHERE is_sick = 1 " +
       "AND NutritionID IN (SELECT DISTINCT NutritionID FROM animal WHERE Animal_Gender = 'Male') " +
       "AND Animal_Specie LIKE 'G%' " +
       "AND Animal_name IS NOT NULL " +
       "AND LENGTH(Animal_name) > 3 " +
       "AND AnimalID = SOME (SELECT AnimalID FROM animal WHERE is_sick = 1) " +
       "AND AnimalID = ALL (SELECT AnimalID FROM animal WHERE Animal_Specie LIKE 'A%');");
      

    }
        
    public List<Map<String, Object>> getAnimalStats() {
      String sql = "SELECT animal.HabitatID, COUNT(*) as AnimalCount, AVG(animal.is_sick) as AverageSicknessRate " +
                   "FROM animal " +
                   "JOIN nutrition ON animal.NutritionID = nutrition.NutritionID " +
                   "JOIN nutrition_supplier ON animal.SupplierID = nutrition_supplier.SupplierID " +
                   "GROUP BY animal.HabitatID";
      return jdbcTemplate.queryForList(sql);
  }

  public List<Map<String, Object>> getTigerandLionData() {
   String sql = "SELECT DISTINCT a.AnimalID, a.Animal_name, a.Animal_Birthdate, a.Animal_Gender, a.Animal_Specie," +
                "a.is_sick, a.CaretakerID, c.Caretaker_firstname, c.Caretaker_lastname," +
                "CASE " +
                "WHEN a.Animal_Gender = 'Male' THEN 'M'" +
                "WHEN a.Animal_Gender = 'Female' THEN 'F'" +
                "ELSE 'Unknown'" +
                "END AS GenderShort " +
                "FROM animal a " +
                "LEFT OUTER JOIN caretaker c ON a.CaretakerID = c.CaretakerID " +
                "WHERE a.Animal_Birthdate BETWEEN '2022-01-01' AND '2023-12-31' " +
                "AND a.is_sick = 0 " +
                "AND (a.CaretakerID = 'CTK001' OR a.CaretakerID = 'CTK004') " +
                "AND a.Animal_Specie IN ('Lion', 'Tiger') " +
                "AND EXISTS ( " +
                "SELECT 1 " +
                "FROM nutrition n " +
                "WHERE n.NutritionID = a.NutritionID " +
                "AND (n.Food_Type = 'Fish' OR n.Food_Type = 'Fruits') " +
                ") " +
                "ORDER BY a.Animal_Birthdate DESC";
   return jdbcTemplate.queryForList(sql);
   
}
public List<Map<String, Object>> getCaretakerAnimalStats() {
   String sql = "SELECT a.CaretakerID, COUNT(*) AS TotalAnimals, MAX(a.Animal_Birthdate) AS MaxBirthdate, SUM(a.is_sick) AS TotalSickAnimals " +
                "FROM animal a " +
                "WHERE a.Animal_Birthdate < '2023-01-01' AND a.is_sick = 0 " +
                "GROUP BY a.CaretakerID " +
                "HAVING COUNT(*) > 0";
   return jdbcTemplate.queryForList(sql);
}







}
    