<%@ page import="DTO.BookmarkDTO" %>
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
<%
    BookmarkDTO bookmarkDTO = (BookmarkDTO) request.getAttribute("bookmarkDTO");
%>
<a>북마크를 삭제하시겠습니까?</a>
<table class="tables">
    <tr><th>북마크 이름</th><td>${bookmarkDTO.getBMG_NM()}</td></tr>
    <tr><th>와이파이명</th><td>${bookmarkDTO.getX_SWIFI_MAIN_NM()}</td></tr>
    <tr><th>등록일자</th><td>${bookmarkDTO.getBM_REGI_DTTM()}</td></tr>
    <tr>
        <td colspan="2">
            <a href="bookmark-view.jsp">돌아가기</a> | <button type="button" onclick="deleteBookmark(${bookmarkDTO.getBM_ID()})">삭제</button>
        </td>
    </tr>
</table>
</body>
</html>
