package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    protected MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL1_USER_ID, USER_ID);
        MATCHER.assertEquals(meal, MEAL1_USER);
    }

    @Test(expected = NotFoundException.class)
    public void testGetForeignMeal() throws Exception {
       service.get(MEAL1_USER_ID, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL1_USER_ID, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteForeignMeal() throws Exception {
        service.delete(MEAL1_USER_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1,2);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        Collection<Meal> all = service.getBetweenDates(LocalDate.parse("2016-09-23"), LocalDate.parse("2016-09-23"), USER_ID);
        MATCHER.assertCollectionEquals(all, Arrays.asList(MEAL2_USER, MEAL1_USER));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<Meal> all = service.getBetweenDateTimes(LocalDateTime.parse("2016-09-23T09:00:00"), LocalDateTime.parse("2016-09-23T15:00:00"), USER_ID);
        MATCHER.assertCollectionEquals(all, Arrays.asList(MEAL2_USER, MEAL1_USER));
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(all, Arrays.asList(MEAL3_USER, MEAL2_USER, MEAL1_USER));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updatedMeal = new Meal(MEAL1_USER_ID, LocalDateTime.now(), "Полдник", 1000);
        service.update(updatedMeal, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(updatedMeal, MEAL3_USER, MEAL2_USER), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateForeignMeal() throws Exception {
        service.update(new Meal(MEAL1_USER_ID, LocalDateTime.now(), "Полдник", 1000), ADMIN_ID);
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "Полдник", 1000);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(newMeal, MEAL3_USER, MEAL2_USER, MEAL1_USER), service.getAll(USER_ID));
    }
}