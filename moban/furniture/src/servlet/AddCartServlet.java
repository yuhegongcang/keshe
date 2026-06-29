package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.CartDb;

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username == null || username.isEmpty()) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int furnitureId = Integer.parseInt(request.getParameter("fid"));
        
        CartDb cartDb = new CartDb();
        cartDb.addToCart(username, furnitureId);

        response.sendRedirect("CartServlet");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}