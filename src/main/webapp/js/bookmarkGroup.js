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