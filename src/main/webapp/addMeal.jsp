<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Add new meal</title>
</head>
<body style="align-content: center">
<h2><a href="index.html">Home</a></h2>
<div>
    <form method="POST" action="meals" name="frmAddMeal">
        Meal ID : <input type="text" readonly="readonly" name="mealId"
                         value="${meal.id}"/>
        DateTime : <input type="datetime-local" name="dateTime"
                          value="${meal.dateTime}"/>
        Description : <input type="text" name="description"
                          value="${meal.description}"/>
        Calories : <input type="text" name="calories"
                          value="${meal.calories}"/>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
