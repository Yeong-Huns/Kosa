<%--
  Created by IntelliJ IDEA.
  User: KOSA
  Date: 2024-06-04
  Time: PM 3:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>파일 업로드</title>
</head>
<body>
    <c:url var="actionURL" value="/upload/new"/>
    <form action="${actionURL}" method="post" enctype="multipart/form-data">
        <label>
            <select name="dir">
                <option value="/">/
                <option value="/images">/이미지
                <option value="/data">/자료실
                <option value="/bigdata">/빅데이터
                <option value="/common">/공통
            </select>
        </label>
        <input type="file" name="file">
        <input type="submit" value="Save">
        <input type="reset" value="Cancel">
    </form>
</body>
</html>
