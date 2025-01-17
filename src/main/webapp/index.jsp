<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  @author: Nayoon
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="style.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(window).on('beforeunload', function () {
            // "근처 Wifi 정보 보기" 버튼을 누른 후에만 사용자의 입력값 유지
            if (myLat && myLnt) {
                $('.myLat').val(myLat);
                $('.myLnt').val(myLnt);
            } else {
                $('.myLat').val('0.0');
                $('.myLnt').val('0.0');
                $('#wifiTableBody').html('<tr><th colspan="17" height="50">위치 정보를 입력한 후에 조회해 주세요.</th></tr>');
            }
        });
    </script>
    <script type="text/javascript" src="js/index.js" charset="UTF-8"></script>
    <script type="text/javascript" src="js/history.js" charset="UTF-8"></script>
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

<main>
    <section>
        <%
            String latStr = request.getParameter("myLat");
            String lntStr = request.getParameter("myLnt");

            Double myLat = latStr == null ? 0.0 : Double.parseDouble(latStr);
            Double myLnt = lntStr == null ? 0.0 : Double.parseDouble(lntStr);
        %>
        LAT: <input class="myLat" name="myLat" value="<%= myLat %>" placeholder="0.0">
        , LNT: <input class="myLnt" name="myLnt" value="<%= myLnt %>" placeholder="0.0">
        <button type="button" id="myLocation" onclick="getLocation()">내 위치 가져오기</button>
        <button type="button" id="getWifiInfo" onclick="getNearWifi(); insertHistory()">근처 WIFI 정보 보기</button>
    </section>

    <table class="tables">
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        <tbody id="wifiTableBody">
        <tr>
            <td colspan="17" height="50">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        </tbody>
    </table>
</main>
</body>
</html>
