package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
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
@Service
public class MealServiceImpl implements MealService {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public Meal update(Meal meal, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFound(repository.save(meal, userId), "id =" + meal.getId());
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<MealWithExceed> getAll(int userId) {
        List<Meal> list = repository.getAll(userId);
        return MealsUtil.getWithExceeded(list, AuthorizedUser.getCaloriesPerDay());
    }

    @Override
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int userId) {
        LocalDateTime start = LocalDateTime.of(startDate, startTime);
        LocalDateTime end = LocalDateTime.of(endDate, endTime);
        List<MealWithExceed> list1 = getAll(userId);
        LOG.info("getBetween, startDateTime: " + start + " ,endDateTime: " + end);
        return list1.stream().filter(mealWithExceed -> TimeUtil.isBetween(mealWithExceed.getDateTime(), start, end)).collect(Collectors.toList());
    }
}
