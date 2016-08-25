package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceededByCycle(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        for (UserMealWithExceed um : list) {
            System.out.println(um);
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> list = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                int calories = 0;
                for (UserMeal um : mealList) {
                    if (userMeal.getDateTime().toLocalDate().equals(um.getDateTime().toLocalDate())) {
                        calories += um.getCalories();
                    }
                }
                boolean exceeded = calories > caloriesPerDay;
                UserMealWithExceed userMealWithExceed = new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceeded);
                list.add(userMealWithExceed);
            }
        }
        return list;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> map = new HashMap<>();
        for (UserMeal um : mealList) {
            LocalDate date = um.getDateTime().toLocalDate();
            int calories = um.getCalories();
            int value = 0;
            if (map.containsKey(date)) value += map.get(date) + calories;
            map.put(date, value);
        }

        List<UserMealWithExceed> list = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            LocalDate date = userMeal.getDateTime().toLocalDate();
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                boolean exceeded = map.get(date) > caloriesPerDay;
                UserMealWithExceed userMealWithExceed = new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceeded);
                list.add(userMealWithExceed);
            }
        }
        return list;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        mealList.stream();
        return null;
    }
}