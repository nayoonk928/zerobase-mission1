<%@ page import="DTO.WifiDTO" %><%--
  @author Nayoon
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="style.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/bookmarkGroup.js" charset="UTF-8"></script>
    <script type="text/javascript" src="js/bookmark.js" charset="UTF-8"></script>
    <script>
        $(document).ready(function() {
            getBookmarkGroupName();

            $("#insertBookmark").click(function() {
                insertBookmark();
            });
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
            <li><a href="LoadWifiServlet">Open API 와이파이 정보 가져오기</a></li>
            <li>|</li>
            <li><a href="bookmark-view.jsp">북마크 보기</a></li>
            <li>|</li>
            <li><a href="bookmark-group-view.jsp">북마크 그룹 관리</a></li>
        </ul>
    </nav>
</header>
<%
    WifiDTO wifiDTO = (WifiDTO) request.getAttribute("wifiDTO");
%>
<form id="insertBookmarkForm" action="InsertBookmarkServlet" method="GET">
    <input type="hidden" id="bmgId" name="bmgId" value="">
    <input type="hidden" id="mgrNo" name="mgrNo" value="<%=wifiDTO.getX_SWIFI_MGR_NO()%>">
    <div>
        <select id="bookmarkGroupSelect">
            <option>북마크 그룹 이름 선택</option>
        </select>
        <button type="button" id="insertBookmark" onclick="insertBookmark()">북마크 추가하기</button>
    </div>
</form>

<table class="tables">
    <tr><th>거리(Km)</th><td>${distance}</td></tr>
    <tr><th>관리번호</th><td>${wifiDTO.getX_SWIFI_MGR_NO()}</td></tr>
    <tr><th>자치구</th><td>${wifiDTO.getX_SWIFI_WRDOFC()}</td></tr>
    <tr><th>와이파이명</th><td><a href="GetDetailedInfoServlet?mgrNo=${wifiDTO.getX_SWIFI_MGR_NO()}&distance=${distance}">${wifiDTO.getX_SWIFI_MAIN_NM()}</a></td></tr>
    <tr><th>도로명주소</th><td>${wifiDTO.getX_SWIFI_ADRES1()}</td></tr>
    <tr><th>상세주소</th><td>${wifiDTO.getX_SWIFI_ADRES2()}</td></tr>
    <tr><th>설치위치(층)</th><td>${wifiDTO.getX_SWIFI_INSTL_FLOOR()}</td></tr>
    <tr><th>설치유형</th><td>${wifiDTO.getX_SWIFI_INSTL_TY()}</td></tr>
    <tr><th>설치기관</th><td>${wifiDTO.getX_SWIFI_INSTL_MBY()}</td></tr>
    <tr><th>서비스구분</th><td>${wifiDTO.getX_SWIFI_SVC_SE()}</td></tr>
    <tr><th>망종류</th><td>${wifiDTO.getX_SWIFI_CMCWR()}</td></tr>
    <tr><th>설치년도</th><td>${wifiDTO.getX_SWIFI_CNSTC_YEAR()}</td></tr>
    <tr><th>실내외구분</th><td>${wifiDTO.getX_SWIFI_INOUT_DOOR()}</td></tr>
    <tr><th>WIFI접속환경</th><td>${wifiDTO.getX_SWIFI_REMARS3()}</td></tr>
    <tr><th>X좌표</th><td>${wifiDTO.getLAT()}</td></tr>
    <tr><th>Y좌표</th><td>${wifiDTO.getLNT()}</td></tr>
    <tr><th>작업일자</th><td>${wifiDTO.getWORK_DTTM()}</td></tr>
</table>

</body>
</html>
