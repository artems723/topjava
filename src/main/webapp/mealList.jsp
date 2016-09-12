<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table>
    <tr>
        <td> DateTime</td>
        <td> Description</td>
        <td> Calories</td>
    </tr>
    <c:forEach var="meal" items="${mealList}">
        <c:if test="${meal.exceed}">
            <tr style="color:red">
                <td> ${meal.dateTime} </td>
                <td> ${meal.description} </td>
                <td> ${meal.calories} </td>
            </tr>
        </c:if>
        <c:if test="${!meal.exceed}">
            <tr style="color:limegreen">
                <td> ${meal.dateTime} </td>
                <td> ${meal.description} </td>
                <td> ${meal.calories} </td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
