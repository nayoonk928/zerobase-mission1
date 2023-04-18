function getLocation() {
    if (navigator.geolocation) {
        navigator.permissions.query({name:'geolocation'}).then(function(result) {
            if (result.state === 'granted') {
                navigator.geolocation.getCurrentPosition(showPosition, showError);
            } else if (result.state === 'prompt') {
                // 위치 정보 공유 동의 창 띄우기
                alert('위치 정보를 이용하기 위해 위치 정보 공유에 동의해주세요.');
                navigator.geolocation.getCurrentPosition(showPosition, showError);
            } else {
                alert('위치 정보를 이용하기 위해서는 위치 정보 공유에 동의해야합니다.');
            }
        });
    } else {
        alert('이 브라우저에서는 Geolocation이 지원되지 않습니다.');
    }
}

function showPosition(position) {
    setTimeout(function(){
        let lat = position.coords.latitude;
        let lnt = position.coords.longitude;
        // 현재 위치 정보 받아오기 성공 시, 위도와 경도 값을 각각의 input 요소에 할당
        document.querySelector('.lat').value = lat;
        document.querySelector('.lnt').value = lnt;
    }, 1000); // 1초 대기
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            alert("위치 정보 공유에 동의가 필요합니다.");
            break;
        case error.POSITION_UNAVAILABLE:
            alert("위치 정보가 올바르지 않습니다.");
            break;
        case error.TIMEOUT:
            alert("요청 시간이 초과되어 위치 정보를 가져오는데 실패했습니다.");
            break;
        case error.UNKNOWN_ERROR:
            alert("알 수 없는 에러가 발생했습니다.");
            break;
    }
}

function findNearWifi() {
    let myLat = $("#myLat").val();
    let myLnt = $("#myLnt").val();

    $.ajax({
        type: "GET",
        url: "FindNearWifiServlet",
        data: {myLat: myLat, myLnt: myLnt},
        dataType: "json",
        success: function(data) {
            console.log("succeess");
            // 받아온 데이터를 이용하여 테이블을 수정하는 코드
            let wifiTableBody = document.getElementById("wifiTableBody");
            wifiTableBody.innerHTML = ""; // 기존에 있던 내용을 모두 지우기

            let wifiList = data; // JSON 데이터를 자바스크립트 객체로 변환

            for (let i = 0; i < wifiList.length; i++) {
                let wifi = wifiList[i];
                console.log(i);
                console.log(wifi);
                let row = document.createElement("tr");
                row.innerHTML = "<td>" + wifi.distance + "</td>" +
                                "<td>" + wifi.X_SWIFI_MGR_NO + "</td>" +
                                "<td>" + wifi.X_SWIFI_WRDOFC + "</td>" +
                                "<td>" + wifi.X_SWIFI_MAIN_NM + "</td>" +
                                "<td>" + wifi.X_SWIFI_ADRES1 + "</td>" +
                                "<td>" + wifi.X_SWIFI_ADRES2 + "</td>" +
                                "<td>" + wifi.X_SWIFI_INSTL_FLOOR + "</td>" +
                                "<td>" + wifi.X_SWIFI_INSTL_TY + "</td>" +
                                "<td>" + wifi.X_SWIFI_INSTL_MBY + "</td>" +
                                "<td>" + wifi.X_SWIFI_SVC_SE + "</td>" +
                                "<td>" + wifi.X_SWIFI_CMCWR + "</td>" +
                                "<td>" + wifi.X_SWIFI_CNSTC_YEAR + "</td>" +
                                "<td>" + wifi.X_SWIFI_INOUT_DOOR + "</td>" +
                                "<td>" + wifi.X_SWIFI_REMARS3 + "</td>" +
                                "<td>" + wifi.LAT + "</td>" +
                                "<td>" + wifi.LNT + "</td>" +
                                "<td>" + wifi.WORK_DTTM + "</td>";

                wifiTableBody.appendChild(row);
            }
        },
        error: function(xhr, status, error) {
            alert("Error: " + error);
        }
    });
}