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
    <script>
        $(document).ready(function() {
            getBookmarkGroup();
        });
    </script>
</head>
<body>

<header>
    <h1>북마크 그룹</h1>
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
    <section>
        <button type="button" onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
    </section>

    <table class="tables">
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>
        <tbody id="bookmarkGroupTableBody">
        <tr>
            <td colspan="17" height="50">등록된 북마크 그룹이 없습니다.</td>
        </tr>
        </tbody>
    </table>
</main>
</body>
</html>
