package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import db.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 2. 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 3. 准备相关类
        LoginBean loginBean = new LoginBean();
        User userDb = new User();
        String url = ""; // 决定一会儿跳转到哪个页面

        // 4. 去数据库校验账号密码
        if (userDb.isUserExist(username, password)) {
            // 登录成功！
        	// 登录成功后，加上这一行
        	request.getSession().setAttribute("currentUser", username);
            // 把用户名存进 Session (相当于给用户发了一张通行证)
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            // 登录成功后，跳转到商城主页 (Index.jsp，我们马上就写)
            url = "Index.jsp";
        } else {
            // 登录失败！
            loginBean.setMsg("登录失败：用户名或密码错误，请重试！");
            loginBean.setNameStr(username); // 把用户名带回去，免得用户重新打字
            request.setAttribute("loginBean", loginBean);
            
            // 失败则留在登录页
            url = "Login.jsp";
        }

        // 5. 执行页面跳转
        request.getRequestDispatcher(url).forward(request, response);
    }
}