package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal update(Meal meal) throws NotFoundException {
        LOG.info("update meal: " + meal + ", userId: " + AuthorizedUser.id());
        return service.update(meal, AuthorizedUser.id());
    }

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

    public List<MealWithExceed> getAll() {
        LOG.info("getAll mealWithExceed, userId: " + AuthorizedUser.id());
        return service.getAll(AuthorizedUser.id());
    }

    public List<MealWithExceed> getAll(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LOG.info("getAll mealWithExceed,  userId: " + AuthorizedUser.id() + " ,startDate=" + startDate + " ,startTime=" + startTime + " ,endDate=" + endDate + " ,endTime=" + endTime);
        return service.getBetween(startDate, startTime, endDate, endTime, AuthorizedUser.id());
    }
}
