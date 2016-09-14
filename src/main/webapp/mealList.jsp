<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<div>
<table class="tg">
    <tr>
        <th> ID </th>
        <th> DateTime </th>
        <th> Description </th>
        <th> Calories </th>
        <th colspan="2"> Action </th>
    </tr>
    <c:forEach var="meal" items="${mealList}">
            <tr style="${meal.exceed ? 'color:red' : 'color:limegreen'}">
                <td> ${meal.id} </td>
                <td> ${fn:replace(meal.dateTime, 'T', ' ')} </td>
                <td> ${meal.description} </td>
                <td> ${meal.calories} </td>
                <td> <a href="meals?action=edit&mealId=${meal.id}"> Update</a> </td>
                <td> <a href="meals?action=delete&mealId=${meal.id}"> Delete</a> </td>
            </tr>
    </c:forEach>
</table>
    <p><a href="meals?action=insert">Add meal</a> </p>
</div>
</body>
</html>
