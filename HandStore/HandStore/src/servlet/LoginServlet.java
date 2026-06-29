package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; // 1. 导入注解包
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 2. 添加注解绑定路由，彻底解决点击登录后的 404 错误
@WebServlet("/login") 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求编码，防止获取表单参数时出现中文乱码
		request.setCharacterEncoding("UTF-8");
		
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");

		// 验证登录信息
		if("root".equals(uname) && "123456".equals(pwd)){
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			
			// 3. 重定向逻辑（根据项目需求二选一）：
			// 情况 A：如果你的首页需要通过 IndexServlet 去查数据库、加载数据，请用这行：
			response.sendRedirect("IndexServlet");
			
			// 情况 B：如果你只是想单纯展示静态的 Index.jsp 页面，请注释掉上面那行，开启下面这行：
			// response.sendRedirect("Index.jsp");
			
		}else{
			// 登录失败，携带错误提示信息转发回登录页面
			request.setAttribute("loginMsg", "用户名或密码错误");
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// GET 请求直接调用 POST 方法统一处理
		doPost(request, response);
	}
	
}