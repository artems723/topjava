package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal, int userId) {
        LOG.info("save meal: " + meal + ", userId: " + userId);
        return service.save(meal, userId);
    };

    public void delete(int id, int userId) throws NotFoundException {
        LOG.info("delete meal id: " + id + ", userId: " + userId);
        service.delete(id, userId);
    }

    public Meal get(int id, int userId) throws NotFoundException {
        LOG.info("get meal id: " + id + ", userId: " + userId);
        return service.get(id, userId);
    }

    public Collection<Meal> getAll(int userId) {
        LOG.info("getAll meal, userId: " + userId);
        return service.getAll(userId);
    }

    public Meal update(Meal meal, int userId) throws NotFoundException {
        LOG.info("update meal: " + meal + ", userId: " + userId);
        return service.update(meal, userId);
    }

}
