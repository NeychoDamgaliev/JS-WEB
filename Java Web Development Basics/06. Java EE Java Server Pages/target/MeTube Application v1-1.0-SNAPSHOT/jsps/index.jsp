
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jspl" %>
<html>
<head>
    <jspl:import url="fragments/head.jsp"/>
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>Wellcome to MeTube!</h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h2>Cool app in beta version</h2>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="/tubes/create" class="btn btn-primary">Create Tube</a>
                </div>
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="/tubes/all" class="btn btn-primary">All Tubes</a>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <jspl:import url="fragments/footer.jsp"/>
    </footer>
</div>
</body>

</html>