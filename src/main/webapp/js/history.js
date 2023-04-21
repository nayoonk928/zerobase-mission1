/*
    @author Nayoon
 */
function insertHistory() {
    let myLat = $(".myLat").val();
    let myLnt = $(".myLnt").val();

    $.ajax({
        type: "GET",
        url: "InsertHistoryServlet",
        data: {myLat: myLat, myLnt: myLnt},
        success: function (response) {
            console.log(response);
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}

function getAllHistory() {
    $.ajax({
        type: "GET",
        url: "GetHistoryServlet",
        dataType: "json",
        success: function (histories) {
            console.log(histories);
            if (histories.length > 0) {
                let tbody = document.getElementById("historyTableBody");
                tbody.innerHTML = "";

                for (let history of histories) {
                    let row = document.createElement("tr");
                    row.innerHTML = "<td>" + history.HIS_NO.toString() + "</td>" +
                        "<td>" + history.LAT + "</td>" +
                        "<td>" + history.LNT + "</td>" +
                        "<td>" + history.LKUP_DTTM + "</td>" +
                        "<td><button class='deleteBtn'>삭제</button></td>";

                    // 삭제 버튼 눌렀을 때
                    row.getElementsByTagName("button")[0].addEventListener("click", clickDeleteButton);

                    tbody.appendChild(row);
                }
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr); // 오류 출력
            console.log(status);
            console.log(error);
            alert("Error: " + error);
        }
    });
}

function clickDeleteButton(event) {
    let hisNo = event.target.parentNode.parentNode.cells[0].textContent;
    if (confirm(hisNo + " 번 히스토리를 삭제하시겠습니까?")) {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "DeleteHistoryServlet?hisNo=" + hisNo, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                alert(hisNo + " 번 히스토리의 삭제가 완료되었습니다.");
                location.reload();
            }
        };
        xhr.send();
    } else {
        history.back();
    }
}