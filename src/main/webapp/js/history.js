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
        url: 'GetHistoryServlet',
        type: 'GET',
        //dataType: 'json',
        success: function(histories) {
            let tbody = document.getElementById("historyTableBody");
            tbody.innerHTML = "";

            if (histories.length === 0) {
                let row = document.createElement("tr");
                row.innerHTML = "<td colspan='5' height='50'>위치 히스토리가 없습니다.</td>";
                tbody.appendChild(row);

            } else {
                for (let history of histories) {
                    let row = document.createElement("tr");
                    row.innerHTML = "<td>" + history.HIS_NO + "</td>" +
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
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "DeleteHistoryServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            alert(xhr.responseText);
            location.reload();
        }
    };
    xhr.send("hisNo=" + hisNo);
}
