<%@ page import="DAO.BookmarkGroupDAO" %>
<%@ page import="DTO.BookmarkGroupDTO" %>
<%--
  @author Nayoon
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="../style.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="../js/bookmarkGroup.js" charset="UTF-8"></script>
</head>
<body>
<%
    int bmgId = Integer.parseInt(request.getParameter("bmgId"));
    BookmarkGroupDAO dao = new BookmarkGroupDAO();
    BookmarkGroupDTO bmgDTO = dao.getBookmarkGroupById(bmgId);
    String bmgName = bmgDTO.getBMG_NM();
    int bmgOrder = bmgDTO.getBMG_ORDER();
%>
<header>
    <h1>북마크 그룹 수정</h1>
    <nav>
        <ul>
            <li><a href="../index.jsp">홈</a></li>
            <li>|</li>
            <li><a href="../history.jsp">위치 히스토리 목록</a></li>
            <li>|</li>
            <li><a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a></li>
            <li>|</li>
            <li><a href="bookmark-view.jsp">북마크 보기</a></li>
            <li>|</li>
            <li><a href="bookmark-group-view.jsp">북마크 그룹 관리</a></li>
        </ul>
    </nav>
</header>

<main>
    <form action="bookmark/UpdateBookmarkGroupServlet" method="GET">
        <input type="hidden" class="bmgId" name="bmgId" value="<%= String.valueOf(bmgId) %>">
        <table class="tables">
            <tr><th>북마크 이름</th><td><input class="bmgName" name="bmgName" value="<%= bmgName %>"></td></tr>
            <tr><th>순서</th><td><input class="bmgOrder" name="bmgOrder" value="<%= bmgOrder %>"></td></tr>
            <tr>
                <td colspan="2">
                    <a href="bookmark-group.jsp">돌아가기</a> | <button type="button" onclick="updateBookmarkGroup()">수정</button>
                </td>
            </tr>
        </table>
    </form>
</main>

</body>
</html>
