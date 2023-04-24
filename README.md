# zerobase-mission1

# 🖥️ Project
공공 와이파이 Open API를 기반으로 와이파이 정보를 저장하고 조회하는 프로젝트

# 👩‍💻 Tech Stack
Language: Java, JavaScript

Tools: IntelliJ, Tomcat

Framework/Library: gson, lombok, jQuery

DataBase: SQLite, DBeaver

# 🗒️ Requirements
* 서울시 공공 와이파이 API
  * 와이파이 정보 가져오기 및 db에 저장
  
* 와이파이 데이터 조회
  * 내 위치 가져오기 기능
  * 근처 와이파이 정보 보기 기능
  * 와이파이 상세 정보 보기 기능
  
* 위치 히스토리 기능
* 북마크 그룹 기능
* 북마크에 와이파이 정보 추가 및 삭제 기능

# 🤨 진행 중 어려웠던 점
* 공공 API를 사용해 본 적이 없어 처음 시작이 어려웠고, 1000 개씩 나눠서 가져와야 했던 것
* 처음에는 VSCode로 진행하려 했지만 Tomcat 실행시 계속 에러가 발생해서 IntelliJ로 IDE를 한 번 변경한 것

# ⁉️ 진행하면서 배운 점
* ajax를 사용하면 전체 페이지를 로드하지 않고 필요한 데이터만을 서버로부터 비동기적으로 받아와서 화면을 갱신할 수 있다.
