<%--
  @author Nayoon
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/history.js"></script>
    <script>
        $(document).ready(function() {
            getAllHistory();
        });
    </script>
</head>
<body>
<header>
    <h1>와이파이 정보 구하기</h1>
    <nav>
        <ul>
            <li><a href="index.jsp">홈</a></li>
            <li>|</li>
            <li><a href="history.jsp">위치 히스토리 목록</a></li>
            <li>|</li>
            <li><a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a></li>
            <li>|</li>
            <li><a href="HomeServlet">북마크 보기</a></li>
            <li>|</li>
            <li><a href="HomeServlet">북마크 그룹 관리</a></li>
        </ul>
    </nav>
</header>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody id="historyTableBody">
    <tr>
        <td colspan="17" height="50">위치 히스토리가 없습니다.</td>
    </tr>
    </tbody>
</table>
</body>
</html>