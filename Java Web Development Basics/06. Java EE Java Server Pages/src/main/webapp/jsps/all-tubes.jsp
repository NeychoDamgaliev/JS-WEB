<%@ page import="java.util.List" %>
<%@ page import="meTube.domain.models.views.AllTubesVM" %><%--
  Created by IntelliJ IDEA.
  User: HP Pavilion
  Date: 2/6/2019
  Time: 12:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jspl" %>
<html>
<head>
    <jspl:import url="fragments/head.jsp"/>
</head>
<body>
<% List<AllTubesVM> tubes = (List<AllTubesVM>) request.getAttribute("allTubes");%>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>All Tubes
                    </h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>Check our tubes below
                    </h3>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <%if ( tubes.size() == 0) {%>
                    <p>No tubes – <a href="/tubes/create">Create some</a>!</p>
                    <%} else {%>
                    <ul>
                        <% for (AllTubesVM tube : tubes) {%>
                        <li><a href="/tubes/details?name=<%=tube.getTitle()%>"><%=tube.getTitle()%></a></li>
                        <%}%>
                    </ul>
                    <%}%>
                </div>
            </div>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to home page</a>
                </div>
            </div>
        </div>

    </main>
</div>
<footer>
    <jspl:import url="fragments/footer.jsp"/>
</footer>
</body>
</html>
