/*
    @author Nayoon
 */
function insertBookmarkGroup() {
    let bmgName = $(".bmgName").val();
    let bmgOrder = $(".bmgOrder").val();

    if (!/^\d+$/.test(bmgOrder)) {
        alert("순서에는 숫자만 입력해주세요.");
        return;
    }

    $.ajax({
        type: "GET",
        url: "InsertBookmarkGroupServlet",
        data: {bmgName: bmgName, bmgOrder: bmgOrder},
        success: function (response) {
            if (response.includes("created")) {
                alert("북마크 추가를 완료하였습니다.");
                window.location.href = "bookmark-group.jsp";
            } else if (response.includes("exists")) {
                alert("같은 순서의 북마크 그룹이 이미 존재합니다.")
            } else {
                console.error(response);
            }
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}

function getBookmarkGroup() {
    $.ajax({
        type: "GET",
        url: "GetBookmarkGroupServlet",
        dataType: "json",
        success: function (bookmarkGroups) {
            console.log(bookmarkGroups);
            if (bookmarkGroups.length > 0) {
                let tbody = document.getElementById("bookmarkGroupTableBody");
                tbody.innerHTML = "";

                for (let bookmarkGroup of bookmarkGroups) {
                    let row = document.createElement("tr");
                    row.innerHTML = "<td>" + bookmarkGroup.BMG_ID.toString() + "</td>" +
                        "<td>" + bookmarkGroup.BMG_NM + "</td>" +
                        "<td>" + bookmarkGroup.BMG_ORDER.toString() + "</td>" +
                        "<td>" + bookmarkGroup.BMG_CR_DTTM + "</td>" +
                        "<td>" + bookmarkGroup.BMG_UP_DTTM + "</td>" +
                        "<td><a href='bookmark-group-update.jsp?bmgId=" + bookmarkGroup.BMG_ID.toString() + "'>수정</a>" +
                        "<a>  </a>" +
                        "<a href='#' onclick='deleteBookmarkGroup(" + bookmarkGroup.BMG_ID.toString() + ")'>삭제</a></td>";

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

function updateBookmarkGroup() {
    let bmgId = $(".bmgId").val();
    let bmgName = $(".bmgName").val();
    let bmgOrder = $(".bmgOrder").val();

    if (!/^\d+$/.test(bmgOrder)) {
        alert("순서에는 숫자만 입력해주세요.");
        return;
    }

    $.ajax({
        type: "GET",
        url: "UpdateBookmarkGroupServlet",
        data: {bmgId: bmgId, bmgName: bmgName, bmgOrder: bmgOrder},
        success: function (response) {
            if (response.includes("updated")) {
                alert("북마크 그룹 수정을 완료하였습니다.");
                window.location.href = "bookmark-group.jsp";
            } else if (response.includes("exists")) {
                alert("같은 순서의 북마크 그룹이 이미 존재합니다.")
            } else {
                console.error(response);
            }
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
        }
    });
}

function deleteBookmarkGroup(bmgId) {
    $.ajax({
        type: "POST",
        url: "DeleteBookmarkGroupServlet",
        data: {bmgId: bmgId},
        success: function () {
            // 삭제 완료 메시지 출력 후 북마크 그룹 목록을 업데이트합니다.
            alert("북마크 그룹이 삭제되었습니다.");
            getBookmarkGroup();
        },
        error: function (xhr, status, error) {
            console.log(xhr); // 오류 출력
            console.log(status);
            console.log(error);
            alert("Error: " + error);
        }
    });
}
