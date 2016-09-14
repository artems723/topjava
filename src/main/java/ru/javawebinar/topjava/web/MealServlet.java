package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImplInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    private MealDAO dao;
    private static String LIST_MEAL = "/mealList.jsp";
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String ADD_MEAL = "/addMeal.jsp";

    public MealServlet() {
        super();
        dao = new MealDAOImplInMemory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to mealList");

        String forward = "";
        if (request.getParameter("action")!=null){
            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("delete")) {
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                dao.deleteMeal(mealId);
                forward = LIST_MEAL;
                request.setAttribute("mealList", MealsUtil.getAllMealWithExceed(2000, dao));
            } else if (action.equalsIgnoreCase("edit")) {
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                forward = INSERT_OR_EDIT;
                Meal meal = dao.getMealById(mealId);
                request.setAttribute("meal", meal);
            } else {
                forward = ADD_MEAL;
            }
        } else {
            forward = LIST_MEAL;
            request.setAttribute("mealList", MealsUtil.getAllMealWithExceed(2000, dao));
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDescription(req.getParameter("description"));
        meal.setDateTime(LocalDateTime.parse(req.getParameter("dateTime")));
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));
        String mealId = req.getParameter("mealId");

        if (mealId==null || mealId.isEmpty()) {
            dao.addMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            dao.updateMeal(meal);
        }
        req.setAttribute("mealList", MealsUtil.getAllMealWithExceed(2000, dao));
        req.getRequestDispatcher(LIST_MEAL).forward(req, resp);

    }
}
