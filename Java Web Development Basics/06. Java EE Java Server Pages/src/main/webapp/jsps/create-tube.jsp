<%--
  Created by IntelliJ IDEA.
  User: HP Pavilion
  Date: 2/3/2019
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>
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
            <form action="/tubes/create" method="post">

                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h1>Create Tube</h1>
                    </div>
                </div>
                <hr/>

                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="titleInput">Title</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="titleInput" name="title">
                        </div>
                    </div>
                </div>
                <hr/>

                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="descriptionTextarea">Description</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                        <textarea id="descriptionTextarea" name="description"
                                  cols="22" rows="4"></textarea>
                        </div>
                    </div>
                </div>
                <hr/>

                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="youtubeLinkInput">YouTube Link</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="youtubeLinkInput" name="youTubeLink">
                        </div>
                    </div>
                </div>
                <hr/>

                <div class="row">
                    <div class="col col-md-12">
                        <div class="row d-flex justify-content-center">
                            <label for="uploaderInput">Uploader</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="uploaderInput" name="uploader">
                        </div>
                    </div>
                </div>
                <hr/>

                <div class="row mt-4">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary">Create Tube</button>
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to home page</a>
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
