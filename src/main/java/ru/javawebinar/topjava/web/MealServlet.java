package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private MealRestController mealController;
    private AdminRestController adminUserController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        adminUserController = appCtx.getBean(AdminRestController.class);
        mealController = appCtx.getBean(MealRestController.class);
        adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN));
        mealController.save(new Meal(LocalDateTime.now(), "Ужин чемпиона", 1000));
        mealController.save(new Meal(LocalDateTime.now().minusHours(1), "Обед чемпиона", 1000));
        mealController.save(new Meal(LocalDateTime.now().minusHours(2), "Завтрак чемпиона", 500));
        mealController.save(new Meal(LocalDateTime.now().minusDays(1), "Ужин претендента", 800));
        mealController.save(new Meal(LocalDateTime.now().minusDays(1).minusHours(1), "Обед претендента", 700));
        mealController.save(new Meal(LocalDateTime.now().minusDays(1).minusHours(2), "Завтрак претендента", 500));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        mealController.save(meal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList", mealController.getAll());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            mealController.delete(id);
            response.sendRedirect("meals");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000) :
                    mealController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        } else if ("filter".equals(action)) {
            String startDate = request.getParameter("startDate");
            String startTime = request.getParameter("startTime");
            String endDate = request.getParameter("endDate");
            String endTime = request.getParameter("endTime");
            LocalDate sDate = (!startDate.isEmpty()) ? LocalDate.parse(startDate) : LocalDate.MIN;
            LocalTime sTime = (!startTime.isEmpty()) ? LocalTime.parse(startTime) : LocalTime.MIN;
            LocalDate eDate = (!endDate.isEmpty()) ? LocalDate.parse(endDate) : LocalDate.MAX;
            LocalTime eTime = (!endTime.isEmpty()) ? LocalTime.parse(endTime) : LocalTime.MAX;
            LOG.info("getBetween" + " ,startDate=" + startDate + " ,startTime=" + startTime + " ,endDate=" + endDate + " ,endTime=" + endTime);
            request.setAttribute("mealList", mealController.getBetween(sDate, sTime, eDate, eTime));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
