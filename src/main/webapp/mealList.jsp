<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>

    <section>
        <h3>Filter</h3>
        <form method="get" action="meals">
            <input type="hidden" value="filter" name="action">
        <dl>
            <dt>From Date</dt>
            <dd><input type="date" id="startDate" name="startDate" value=${startDate}></dd>
        </dl>
        <dl>
            <dt>To Date</dt>
            <dd><input type="date" id="endDate" name="endDate" value=${endDate}></dd>
        </dl>
        <dl>
            <dt>From Time</dt>
            <dd><input type="time" id="startTime" name="startTime" value=${startTime}></dd>
        </dl>
        <dl>
            <dt>To Time</dt>
            <dd><input type="time" id="endTime" name="endTime" value=${endTime}></dd>
        </dl>
        <button type="submit">Filter</button>
        </form>
        <button onclick="location.href='meals'">Reset</button>
    </section>

    <h3>Meal list</h3>
    <a href="meals?action=create">Add meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>