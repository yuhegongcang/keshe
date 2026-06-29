package servlet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.OrderBean;
import db.Cart;
import db.Order;
import util.Common;
import java.io.IOException;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public OrderServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "Login.jsp";
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		Common common = new Common();
		Order orderDb = new Order();
		Cart cartDb = new Cart();
		OrderBean orderBean = new OrderBean();
		String type = request.getParameter("type");
		
		if(username != null){
			url = "Order.jsp";
			if("init".equals(type)){
				ArrayList<OrderBean> orderList = orderDb.getOrderListByUser(username);
				request.setAttribute("arr", orderList);
			}else if("add".equals(type)){
				//购物车全部生成订单
				ArrayList<bean.CartBean> cartList = cartDb.getCartListByUsername(username);
				String orderNo = common.GenerateOrderNo();
				for(bean.CartBean c : cartList){
					OrderBean ob = new OrderBean();
					ob.setId(c.getId());
					ob.setHandName(c.getHandName());
					ob.setPrice(c.getPrice());
					ob.setAmount(c.getAmount());
					ob.setSumPrice(c.getSumPrice());
					ob.setUsername(username);
					ob.setOrderNo(orderNo);
					ob.setStatus("未支付");
					orderDb.addOrder(ob);
				}
				cartDb.clearUserCart(username);
				ArrayList<OrderBean> orderList = orderDb.getOrderListByUser(username);
				request.setAttribute("arr", orderList);
			}else if("delete".equals(type)){
				String no = request.getParameter("orderNo");
				orderDb.deleteOrder(no);
				ArrayList<OrderBean> orderList = orderDb.getOrderListByUser(username);
				request.setAttribute("arr", orderList);
			}else if("submit".equals(type)){
				String no = request.getParameter("orderNo");
				orderDb.payOrder(no);
				ArrayList<OrderBean> orderList = orderDb.getOrderListByUser(username);
				request.setAttribute("arr", orderList);
			}else if("directBuy".equals(type)){
				int gid = Integer.parseInt(request.getParameter("id"));
				String hname = common.convertChinese(request.getParameter("handName"));
				int price = Integer.parseInt(request.getParameter("price"));
				String orderNo = common.GenerateOrderNo();
				OrderBean ob = new OrderBean();
				ob.setId(gid);
				ob.setHandName(hname);
				ob.setPrice(price);
				ob.setAmount(1);
				ob.setSumPrice(price);
				ob.setUsername(username);
				ob.setOrderNo(orderNo);
				ob.setStatus("未支付");
				orderDb.addOrder(ob);
				url = "OrderInfo.jsp";
				ArrayList<OrderBean> single = new ArrayList<>();
				single.add(ob);
				request.setAttribute("arr", single);
				request.setAttribute("orderNo", orderNo);
				request.setAttribute("status", "未支付");
			}else if("orderInfo".equals(type)){
			    String no = request.getParameter("orderNo");
			    String stat = request.getParameter("status");
			    // 修改此处方法名为 getOrderByOrderNo
			    ArrayList<OrderBean> infoList = orderDb.getOrderByOrderNo(no);
			    request.setAttribute("arr", infoList);
			    request.setAttribute("orderNo", no);
			    request.setAttribute("status", stat);
			    url = "OrderInfo.jsp";
			}
		}
		request.setAttribute("bean", orderBean);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}