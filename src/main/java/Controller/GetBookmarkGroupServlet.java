package Controller;
/*
    @author Nayoon
 */
import DAO.BookmarkGroupDAO;
import DTO.BookmarkGroupDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetBookmarkGroupServlet", urlPatterns = {"/bookmark/GetBookmarkGroupServlet"})
public class GetBookmarkGroupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookmarkGroupDAO bmgDAO = new BookmarkGroupDAO();

        try {
            List<BookmarkGroupDTO> bookmarkGroups = bmgDAO.getBookmarkGroup();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new Gson().toJson(bookmarkGroups));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
