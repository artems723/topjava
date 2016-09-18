package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
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

    public Meal save(Meal meal) {
        LOG.info("save meal: " + meal + ", userId: " + AuthorizedUser.id());
        return service.save(meal, AuthorizedUser.id());
    };

    public void delete(int id) throws NotFoundException {
        LOG.info("delete meal id: " + id + ", userId: " + AuthorizedUser.id());
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) throws NotFoundException {
        LOG.info("get meal id: " + id + ", userId: " + AuthorizedUser.id());
        return service.get(id, AuthorizedUser.id());
    }

    public Collection<Meal> getAll() {
        LOG.info("getAll meal, userId: " + AuthorizedUser.id());
        return service.getAll(AuthorizedUser.id());
    }

    public Meal update(Meal meal) throws NotFoundException {
        LOG.info("update meal: " + meal + ", userId: " + AuthorizedUser.id());
        return service.update(meal, AuthorizedUser.id());
    }

}
