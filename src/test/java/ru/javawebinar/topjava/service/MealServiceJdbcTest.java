package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by AStaver on 07.10.2016.
 */

@ActiveProfiles(Profiles.JDBC)
public class MealServiceJdbcTest extends AbstractMealServiceTest {
}
