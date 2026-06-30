package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.User;
import bean.LoginBean;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置请求编码
        request.setCharacterEncoding("UTF-8");
        
        // 2. 获取前台输入的账号密码
        String nameStr = request.getParameter("username");
        String pwdStr = request.getParameter("password");
        
        LoginBean loginBean = new LoginBean();
        User user = new User();
        
        // 获取当前请求的 Session 对象
        HttpSession session = request.getSession();
        
        String url = "Index.jsp"; // 验证成功默认跳转到商城首页
        
        // 3. 调用底层持久层方法进行账号密码比对
        if (!user.isUserExist(nameStr, pwdStr)) {
            // 校验失败：跳转回登录页，设置错误消息并回显用户名
            url = "login.jsp";
            loginBean.setMsg("登录失败：用户名或密码错误");
            loginBean.setNameStr(nameStr); 
        } else {
            // 校验成功：将合法的用户名存入会话 Session 中，生命周期贯穿整个浏览器访问流程
            session.setAttribute("username", nameStr);
        }
        
        // 4. 将带有反馈信息的 Bean 存入 request，执行页面跳转
        request.setAttribute("loginBean", loginBean);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}