package servlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.UserInfoBean;
import db.User;
import java.io.IOException;

@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public UserInfoServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "UserInfo.jsp";
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		UserInfoBean uiBean = new UserInfoBean();
		User user = new User();
		
		String type = request.getParameter("type");
		if("edit".equals(type)){
			String pwd = request.getParameter("password");
			String pwd2 = request.getParameter("password2");
			String mail = request.getParameter("email");
			if(pwd==null||pwd.isEmpty()||pwd2==null||pwd2.isEmpty()||mail.isEmpty()){
				uiBean.setMsg("所有内容不能为空");
			}else if(!pwd.equals(pwd2)){
				uiBean.setMsg("两次密码不一致");
			}else{
				user.updateUserInfo(uiBean, username, pwd, mail);
			}
		}
		user.getUserInfo(uiBean, username);
		request.setAttribute("uibean", uiBean);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
