<%--
  Created by IntelliJ IDEA.
  User: KOSA
  Date: 2024-06-04
  Time: PM 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>파일 업로드</title>
</head>
<body>
 <p><a href="<c:url value='/upload/new'/>">업로드</a></p>
 <p><a href="<c:url value='/upload/list'/>">파일 전체 목록</a></p>
 <p><a href="<c:url value='/upload/list/bigdata'/>">빅데이터 디렉토리</a></p>
 <p><a href="<c:url value='/upload/gallery'/>">갤러리</a></p>
</body>
</html>
