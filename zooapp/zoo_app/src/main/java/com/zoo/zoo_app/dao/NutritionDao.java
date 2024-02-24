package com.zoo.zoo_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NutritionDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getNutritionData() {
        String sql = "SELECT Nutrition.NutritionID, Nutrition.Food_Type, nutrition_supplier.SupplierID, nutrition_supplier.Supplier_FirstName, nutrition_supplier.Supplier_LastName, COUNT(*) AS TotalRows " +
                     "FROM Nutrition " +
                     "LEFT JOIN nutrition_supplier ON Nutrition.NutritionID = nutrition_supplier.NutritionID " +
                     "WHERE (Nutrition.Food_Type = 'Fruits' OR Nutrition.Food_Type = 'Vegetables' OR Nutrition.Food_Type = 'Meat') " +
                     "AND (nutrition_supplier.Supplier_LastName = 'Johnson' OR nutrition_supplier.Supplier_LastName = 'Miller') " +
                     "GROUP BY Nutrition.NutritionID, Nutrition.Food_Type, nutrition_supplier.SupplierID, nutrition_supplier.Supplier_FirstName, nutrition_supplier.Supplier_LastName " +
                     "ORDER BY Nutrition.NutritionID ASC";
        return jdbcTemplate.queryForList(sql);
    }
}