package Controller;

import DAO.BookmarkDAO;
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

@WebServlet(name = "InsertBookmarkServlet", urlPatterns = {"/InsertBookmarkServlet"})
public class InsertBookmarkServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bmgName = request.getParameter("bmgName");
        String wifiName = request.getParameter("wifiName");


        if (bmgName == null || wifiName == null) {
            return;
        }

        // 버튼을 누른 시간 구하기
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());

        // BookmarkDAO 객체를 생성하고 BookmarkDAO() 메서드를 호출하여 값을 전달
        BookmarkDAO bmDAO = new BookmarkDAO();
        try {
            bmDAO.insertBookmark(bmgName, wifiName, formattedDate);
        } catch (SQLException e) {
            response.getWriter().write("Database error: " + e.getMessage());
        }
    }
}
