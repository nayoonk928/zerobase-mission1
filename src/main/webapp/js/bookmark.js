/*
    @author Nayoon
 */
function insertBookmark() {
    let selectedbmgId = $("#bookmarkGroupSelect option:selected").val();
    let selectedbmgName = $("#bookmarkGroupSelect option:selected").text();
    let mgrNo = $("#mgrNo").val();

    let bookmarkGroupDTO = {
        id: selectedbmgId,
        name: selectedbmgName
    };

    $.ajax({
        url: "InsertBookmarkServlet",
        type: "GET",
        data: {
            bmgId: bookmarkGroupDTO.id,
            bmgName: bookmarkGroupDTO.name,
            mgrNo: mgrNo
        },
        success: function(data) {
            alert("북마크가 추가되었습니다.");
            window.location.href = "bookmark-view.jsp";
        },
        error: function() {
            alert("북마크 추가에 실패했습니다.");
            window.location.href = "wifi-detailed-info.jsp";
        }
    });
}

function getBookmark() {
    $.ajax({
        type: "GET",
        url: "GetBookmarkServlet",
        dataType: "json",
        success: function (bookmarks) {

            if (bookmarks.length > 0) {
                let tbody = document.getElementById("bookmarkTableBody");
                tbody.innerHTML = "";

                for (let bookmark of bookmarks) {
                    let row = document.createElement("tr");
                    row.innerHTML = "<td>" + bookmark.BMG_ID.toString() + "</td>" +
                        "<td>" + bookmark.BMG_NM + "</td>" +
                        "<td>" + bookmark.X_SWIFI_MAIN_NM + "</td>" +
                        "<td>" + bookmark.BM_REGI_DTTM + "</td>" +
                        "<td><a href='GetBookmarkByIdServlet?bmId=" + bookmark.BM_ID.toString() + "'>삭제</a></td>";

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

function deleteBookmark(bmId) {
    $.ajax({
        type: "POST",
        url: "DeleteBookmarkServlet",
        data: {bmId: bmId},
        success: function () {
            // 삭제 완료 메시지 출력 후 북마크 목록을 업데이트합니다.
            alert("북마크가 삭제되었습니다.");
            window.location.href = "bookmark-view.jsp";
        },
        error: function (xhr, status, error) {
            console.log(xhr); // 오류 출력
            console.log(status);
            console.log(error);
            alert("Error: " + error);
        }
    });
}
