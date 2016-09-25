package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int MEAL1_USER_ID = START_SEQ + 2;
    public static final int MEAL2_USER_ID = START_SEQ + 3;
    public static final int MEAL3_USER_ID = START_SEQ + 4;
    public static final int MEAL1_ADMIN_ID = START_SEQ + 5;
    public static final int MEAL2_ADMIN_ID = START_SEQ + 6;
    public static final int MEAL3_ADMIN_ID = START_SEQ + 7;

    public static final Meal MEAL1_USER = new Meal(MEAL1_USER_ID, LocalDateTime.parse("2016-09-23T10:00:00"), "Завтрак пользователя", 500);
    public static final Meal MEAL2_USER = new Meal(MEAL2_USER_ID, LocalDateTime.parse("2016-09-23T14:00:00"), "Обед пользователя", 1600);
    public static final Meal MEAL3_USER = new Meal(MEAL3_USER_ID, LocalDateTime.parse("2016-09-24T14:00:00"), "Обед пользователя", 1000);
    public static final Meal MEAL1_ADMIN = new Meal(MEAL1_ADMIN_ID, LocalDateTime.parse("2016-09-24T10:00:00"), "Завтрак администратора", 400);
    public static final Meal MEAL2_ADMIN = new Meal(MEAL2_ADMIN_ID, LocalDateTime.parse("2016-09-24T15:00:00"), "Обед администратора", 2000);
    public static final Meal MEAL3_ADMIN = new Meal(MEAL3_ADMIN_ID, LocalDateTime.parse("2016-09-25T15:00:00"), "Обед администратора", 1000);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            ((expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId()))
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
            )
    );

}
