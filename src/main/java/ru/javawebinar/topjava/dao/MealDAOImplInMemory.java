package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by AStaver on 13.09.2016.
 */
public class MealDAOImplInMemory implements MealDAO {
    private static Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private static AtomicInteger id = new AtomicInteger(0);

    public MealDAOImplInMemory() {
        addMeal(new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        addMeal(new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        addMeal(new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        addMeal(new Meal(0, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        addMeal(new Meal(0, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        addMeal(new Meal(0, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public void addMeal(Meal meal) {
        meal.setId(id.incrementAndGet());
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(int mealId) {
        mealMap.remove(mealId);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAllMeal() {
        return new ArrayList<>(mealMap.values());
    }

    @Override
    public Meal getMealById(int mealId) {
        return mealMap.get(mealId);
    }
}
