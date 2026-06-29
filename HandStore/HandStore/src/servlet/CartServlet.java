package servlet;

import bean.CartBean;
import db.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    // 新增序列化版本号，消除警告
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("loginUser");
        String type = request.getParameter("type");
        String url = "Index.jsp";
        Cart cartDb = new Cart();

        if(username != null && !username.isEmpty()){
            if("init".equals(type)){
                // 加载购物车列表
                ArrayList<CartBean> list = cartDb.getCartListByUsername(username);
                request.setAttribute("arr", list);
                url = "Cart.jsp";
            }else if("addFromIndex".equals(type)){
                // 首页加入购物车
                int gid = Integer.parseInt(request.getParameter("gid"));
                String handName = request.getParameter("handName");
                int price = Integer.parseInt(request.getParameter("price"));
                CartBean cartBean = new CartBean();
                cartBean.setId(gid);
                cartBean.setHandName(handName);
                cartBean.setPrice(price);
                cartBean.setAmount(1);
                cartBean.setSumPrice(price);
                cartBean.setUsername(username);
                cartDb.addCart(cartBean);
                // 添加后刷新购物车
                ArrayList<CartBean> list = cartDb.getCartListByUsername(username);
                request.setAttribute("arr", list);
                url = "Cart.jsp";
            }else if("del".equals(type)){
                // 删除购物车商品
                int cartId = Integer.parseInt(request.getParameter("id"));
                cartDb.deleteCartItem(cartId, username);
                ArrayList<CartBean> list = cartDb.getCartListByUsername(username);
                request.setAttribute("arr", list);
                url = "Cart.jsp";
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}