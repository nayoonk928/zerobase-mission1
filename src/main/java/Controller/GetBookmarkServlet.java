package Controller;
/*
    @author Nayoon
 */
import DAO.BookmarkDAO;
import DTO.BookmarkDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetBookmarkServlet", urlPatterns = {"/GetBookmarkServlet"})
public class GetBookmarkServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookmarkDAO bmDAO = new BookmarkDAO();
        try {
            List<BookmarkDTO> bookmarks = bmDAO.getBookmark();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new Gson().toJson(bookmarks));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
