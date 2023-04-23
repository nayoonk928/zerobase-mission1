<%--
  @author Nayoon
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="style.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/bookmark.js" charset="UTF-8"></script>
    <script>
        $(document).ready(function () {
            getBookmark();
        });
    </script>
</head>
<body>

<header>
    <h1>북마크 목록</h1>
    <nav>
        <ul>
            <li><a href="index.jsp">홈</a></li>
            <li>|</li>
            <li><a href="history.jsp">위치 히스토리 목록</a></li>
            <li>|</li>
            <li><a href="LoadWifiServlet">Open API 와이파이 정보 가져오기</a></li>
            <li>|</li>
            <li><a href="bookmark-view.jsp">북마크 보기</a></li>
            <li>|</li>
            <li><a href="bookmark-group-view.jsp">북마크 그룹 관리</a></li>
        </ul>
    </nav>
</header>

<table class="tables">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    <tbody id="bookmarkTableBody">
    <tr>
        <td colspan="17" height="50">등록된 북마크가 없습니다.</td>
    </tr>
    </tbody>
</table>
</body>
</html>
