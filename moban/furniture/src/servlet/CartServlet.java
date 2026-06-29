package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.CartBean;
import db.CartDb;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String type = request.getParameter("type");
        CartDb cartDb = new CartDb();

        if ("addQty".equals(type)) {
            int cartId = Integer.parseInt(request.getParameter("cid"));
            cartDb.addQty(cartId);
            response.sendRedirect("CartServlet"); 
            return;
        } else if ("sub".equals(type)) {
            int cartId = Integer.parseInt(request.getParameter("cid"));
            cartDb.subQty(cartId);
            response.sendRedirect("CartServlet");
            return;
        } else if ("del".equals(type)) {
            int cartId = Integer.parseInt(request.getParameter("cid"));
            cartDb.deleteItem(cartId);
            response.sendRedirect("CartServlet");
            return;
        }

        ArrayList<CartBean> cartList = cartDb.getMyCart(username);
        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}