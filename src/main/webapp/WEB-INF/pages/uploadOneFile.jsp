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
<h3>Upload F=Multiple File:</h3>
<form:form modelAttribute="myUploadForm" method="post" action="" enctype="multipart/form-data">
    Description:
    <br>
    <form:input path="description" style="width: 300px"/>

    <br/><br/>
<%--    File to upload (1):<form:input path="fileDatas" type="file"/><br/>--%>
<%--    File to upload (2):<form:input path="fileDatas" type="file"/><br/>--%>
<%--    File to upload (3):<form:input path="fileDatas" type="file"/><br/>--%>
<%--    File to upload (4):<form:input path="fileDatas" type="file"/><br/>--%>
<%--    File to upload (5):<form:input path="fileDatas" type="file"/><br/>--%>
    <input type="submit" value="Upload">
</form:form>
</body>
</html>
