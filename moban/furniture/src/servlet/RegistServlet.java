package servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.RegistBean;
import db.User;
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String nameStr = request.getParameter("username");
        String pwdStr = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String sexStr = request.getParameter("sex");
        String mailAddrStr = request.getParameter("email");
        RegistBean registBean = new RegistBean();
        String msg = "";
        String url = "Register.jsp"; 
        User user = new User();
        if (nameStr == null || nameStr.isEmpty()) {
            msg = "用户名不能为空，请输入用户名！";
        } else if (pwdStr == null || pwdStr.isEmpty() || password2 == null || password2.isEmpty()) {
            msg = "密码不能为空，请重新输入！";
        } else if (!pwdStr.equals(password2)) {
            msg = "两次输入的密码不一致，请检查！";
        } else if (sexStr == null || sexStr.isEmpty()) {
            msg = "请选择性别！";
        } else if (mailAddrStr == null || mailAddrStr.isEmpty()) {
            msg = "邮箱不能为空！";
        } else if (user.isUserExist(nameStr)) {
            msg = "糟糕！该用户名已被占用，请换一个名字。";
        } else {
            user = new User();
            if (user.insertUserInfo(nameStr, pwdStr, sexStr, mailAddrStr)) {
                msg = "注册成功！现在您可以去登录了。";
            } else {
                msg = "系统繁忙，注册失败，请稍后再试。";
            }
        }
        registBean.setMsg(msg);
        request.setAttribute("registBean", registBean);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}