<%@ page import="meTube.domain.models.views.TubeDetailsVM" %><%--
  Created by IntelliJ IDEA.
  User: HP Pavilion
  Date: 2/3/2019
  Time: 10:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jspl" %>
<html>
<head>
    <jspl:import url="fragments/head.jsp"/>
</head>
<body>
<% TubeDetailsVM tube = (TubeDetailsVM) request.getAttribute("tube");%>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1><%=tube.getTitle()%>
                    </h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3><%=tube.getDescription()%>
                    </h3>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="<%=tube.getYouTubeLink()%>" class="btn btn-primary">Link to video</a>
                </div>
                <div class="col col-md-6 d-flex justify-content-center">
                    <p><%=tube.getUploader()%>
                    </p>
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
