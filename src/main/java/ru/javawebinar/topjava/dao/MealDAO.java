package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;

/**
 * Created by AStaver on 13.09.2016.
 */
public interface MealDAO {
    public void addMeal(Meal meal);
    public void deleteMeal(int mealId);
    public void updateMeal(Meal meal);
    public List<Meal> getAllMeal();
    public Meal getMealById(int mealId);
}
