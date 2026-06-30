package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.Cart;
import bean.CartBean;
import util.Common;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // [cite: 3438]
        Common common = new Common();
        Cart cart = new Cart();
        CartBean cartBean = new CartBean();
        
        String url = "Index.jsp";
        HttpSession session = request.getSession(); // [cite: 3454]
        String username = (String) session.getAttribute("username"); // [cite: 3458]
        
        String type = request.getParameter("type"); // [cite: 3460]
        String goodsId = request.getParameter("id"); // [cite: 3462]

        // 1. 判断是否登录 [cite: 3468]
        if (username == null || username.isEmpty()) {
            url = "login.jsp"; // 未登录则跳转到登录页 [cite: 3470]
        } else if (type != null) {
            
        	// 2. 查看购物车 (init)
            if (type.equals("init")) {
                ArrayList<CartBean> arr = cart.getListBean(username);
                request.setAttribute("arr", arr);
                url = "Cart.jsp";
            } 
            // 3. 从首页添加商品 (addFromIndex)
            else if (type.equals("addFromIndex")) {
                String foodName = common.convertChinese(request.getParameter("foodName"));
                int price = Integer.parseInt(request.getParameter("price"));
                
                if (cart.isExist(username, goodsId)) {
                    // 如果已存在，调用刚刚定义的更新方法
                    cart.updateAmount(username, goodsId, 1);
                } else {
                    // 如果不存在，插入新记录
                    cart.addFromIndex(username, goodsId, foodName, price);
                }
                response.sendRedirect(request.getContextPath() + "/CartServlet?type=init");
                return;
            }
            // 4. 【新增】删除购物车商品
         // 把原来写 delete 的地方改成 del
            else if (type.equals("del")) { 
                cart.deleteItem(username, goodsId);
                response.sendRedirect(request.getContextPath() + "/CartServlet?type=init");
                return;
            }
         // 5. 【新增】提交结算
            else if (type.equals("submit")) {
            	
                // 在 if 外面定义，这样下面才能访问到它
                db.Order order = new db.Order(); 
                String orderNo = System.currentTimeMillis() + ""; 
                
                // 1. 执行数据库搬运
                boolean success = order.addOrder(username, orderNo); 
                
                // 打印调试信息放在这里
                System.out.println("DEBUG: 订单搬运结果: " + success); 

                if (success) {
                    // 2. 搬运成功后，立刻清空购物车
                    cart.clearCart(username); 
                    
                    // 3. 跳转到订单列表
                    response.sendRedirect(request.getContextPath() + "/OrderServlet?type=list");
                    return; 
                } else {
                    request.setAttribute("msg", "结算失败！");
                    url = "Cart.jsp";
                }
                System.out.println("DEBUG: 结算成功了吗？: " + success); 

                if (success) {
                    cart.clearCart(username); 
                    System.out.println("DEBUG: 已执行清空购物车操作。");
                    response.sendRedirect(request.getContextPath() + "/OrderServlet?type=list");
                    return; 
                } else {
                    // 如果 success 是 false，说明 addOrder 里的 SQL 没插入任何数据
                    System.out.println("DEBUG: 结算失败，可能是 SQL 插入数据为 0。");
                    request.setAttribute("msg", "结算失败！");
                    url = "Cart.jsp";
                }  
            }
            else if (type.equals("update")) {
                String id = request.getParameter("id");
                int amountChange = Integer.parseInt(request.getParameter("amount")); // 传入 1 或 -1
                
                // 调用之前定义的更新方法
                cart.updateAmount(username, id, amountChange);
                
                // 更新完后重定向回购物车页面，重新加载最新数据
                response.sendRedirect(request.getContextPath() + "/CartServlet?type=init");
                return;
            }
            
                
                

       
        }
        request.setAttribute("cartBean", cartBean);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url); // [cite: 3568]
        dispatcher.forward(request, response); // [cite: 3570]
    }
}