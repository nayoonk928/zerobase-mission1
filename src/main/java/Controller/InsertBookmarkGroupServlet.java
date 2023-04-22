package Controller;

import DAO.BookmarkGroupDAO;

import javax.servlet.annotation.WebServlet;

/*
    @author Nayoon
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "InsertBookmarkGroupServlet", urlPatterns = {"/bookmark/InsertBookmarkGroupServlet"})
public class InsertBookmarkGroupServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 클라이언트 측에서 전달한 값 받기
        String bmgName = request.getParameter("bmgName");
        String orderStr = request.getParameter("bmgOrder");

        if (bmgName == null || orderStr == null) {
            return;
        }

        int order = Integer.parseInt(orderStr);

        // 버튼을 누른 시간 구하기
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());

        // BookmarkGroupDAO 객체를 생성하고 BookmarkGroupDAO() 메서드를 호출하여 값을 전달
        BookmarkGroupDAO bmgDAO = new BookmarkGroupDAO();
        try {
            boolean isInsert = bmgDAO.insertBookmarkGroup(bmgName, order, formattedDate);
            if (!isInsert) {
                response.getWriter().println("exists");
                return;
            }
            response.getWriter().println("created");
        } catch (SQLException e) {
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}
