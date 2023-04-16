<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<header>
  <h1>와이파이 정보 구하기</h1>
  <nav>
    <ul>
      <li><a href="/home">홈</a></li>
      <li>|</li>
      <li><a href="/history">위치 히스토리 목록</a></li>
      <li>|</li>
      <li><a href="#">Open API 와이파이 정보 가져오기</a></li>
    </ul>
  </nav>
</header>

<main>
  <section>
    <form method="post" action="/submit-form.jsp">
      <label for="LAT">LAT:</label>
      <input type="text" id="LAT" name="LAT">
      <label for="LNT">LNT:</label>
      <input type="text" id="LNT" name="LNT">
      <button>내 위치 가져오기</button>
      <button>근처 WIFI 정보 보기</button>
    </form>
  </section>

  <table>
    <thead>
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
    </thead>
    <tbody>
    <tr>
      <th colspan="17" height="50">위치 정보를 입력한 후에 조회해 주세요.</th>
    </tr>
    </tbody>
  </table>
</main>
</body>
</html>
