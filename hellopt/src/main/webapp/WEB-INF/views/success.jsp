<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Uploaded Successfully</title>
</head>
<body>
<h3>File "${filename}" was uploaded successfully!!! </h3>

Click to view or save the file. <a href='<c:url value="resources/files/${filename}" />'>${filename}</a>
<br>
<br>
Return to <a href="<c:url value='/uploadForm' />">Upload File</a>

</body>
</html>