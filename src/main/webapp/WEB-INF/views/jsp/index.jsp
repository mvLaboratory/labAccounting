<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <spring:url value="/resources/css/main.css" var="mainCss" />
    <link href="${mainCss}" rel="stylesheet" />

    <title>Laboratory accountant</title>
</head>

<body>
       <h1>Welcome to labAccounting!</h1>

       	<c:if test="${not empty balance}">
       	    <h2>Reagent balance:</h2>

       		<table border = "2" width = "100%">
                <col width="10%">
                <col width="30%">
                <col width="40%">
                <col width="10%">
                <col width="10%">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Precursor</th>
                    <th>Balance</th>
                </tr>
       			<c:forEach var="reagent" items="${balance}">
       			    <tr>
       				    <td>${reagent.getReagent().getId()}</td>
       				    <td>${reagent.getReagent().getName()}</td>
       				    <td>${reagent.getReagent().getDescription()}</td>
       				    <td>${reagent.getReagent().isPrecursor()}</td>
       				    <td align="right">${reagent.getBalance()}</td>
       				</tr>
       			</c:forEach>
       		</table>
       	</c:if>

       <c:if test="${empty balance}">
           <h2>Sorry, you have no reagents!</h2>
       </c:if>
</body>
</html>