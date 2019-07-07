<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: elnino228
  Date: 27/06/2019
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
<h3>Upload Multi File</h3>
<form:form method="post" modelAttribute="myUploadForm" action="" enctype="multipart/form-data">
    Description
    <br/>
    <form:input path="description" style="width:300px"/>
    <br/><br/>
    File to upload:<form:input path="fileDatas" type="file"/>
    <br/>
    <input type="submit" value="Upload">
</form:form>
</body>
</html>
