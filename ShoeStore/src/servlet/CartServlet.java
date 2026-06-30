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
import db.Cart;
import db.Shoe;
import bean.ShoeBean;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // 如果没登录，直接踢回登录页 [cite: 3468-3470]
        if (username == null || username.isEmpty()) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        Cart cartDb = new Cart();
        String msg = "";

        if ("add".equals(type)) {
            // 处理加入购物车逻辑
            String shoeId = request.getParameter("id");
            Shoe shoeDao = new Shoe();
            ShoeBean shoe = shoeDao.getShoeById(shoeId); 
            	

            if (shoe != null) {
                // ▼▼▼ 修改这里 ▼▼▼
                if (cartDb.isExist(username, shoeId)) {
                    // 如果已经存在，不再报错，而是直接让数量 +1
                    cartDb.updateCartAmount(username, shoeId, 1);
                    msg = "该球鞋已在购物车，数量自动 +1！";
                } else {
                    // 如果不存在，就是新商品，插入1条新记录
                    cartDb.addCart(username, shoeId, shoe.getShoeName(), shoe.getPrice());
                    msg = "成功加入购物车！";
                }
                // ▲▲▲ 修改结束 ▲▲▲
            }
            // 加完后，跳转去查看购物车
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("CartServlet?type=init").forward(request, response);

        } else if ("del".equals(type)) {
            // 处理删除逻辑 [cite: 3539-3546]
            String shoeId = request.getParameter("id");
            cartDb.delByGoods(username, shoeId);
            request.getRequestDispatcher("CartServlet?type=init").forward(request, response);
        }
            else if ("addOne".equals(type)) {
                String shoeId = request.getParameter("id");
                cartDb.updateCartAmount(username, shoeId, 1);
                request.getRequestDispatcher("CartServlet?type=init").forward(request, response);
            } else if ("subOne".equals(type)) {
                String shoeId = request.getParameter("id");
                cartDb.updateCartAmount(username, shoeId, -1);
                request.getRequestDispatcher("CartServlet?type=init").forward(request, response);

        } else if ("init".equals(type)) {
            // 处理查看购物车列表逻辑 [cite: 3474-3480]
            ArrayList<CartBean> arr = cartDb.getListBean(username);
            request.setAttribute("arr", arr);
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        }
    }
}