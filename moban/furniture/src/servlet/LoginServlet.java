package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String nameStr = request.getParameter("username");
        String pwdStr = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        String url = "IndexServlet"; 
        
        User user = new User();

        if (!user.isUserExist(nameStr, pwdStr)) {
            url = "Login.jsp";
            loginBean.setMsg("µÇÂ¼Ê§°Ü£ºÓÃ»§Ãû»òÃÜÂë´íÎó£¬Çë¼ì²é£¡");
            loginBean.setNameStr(nameStr);
            request.setAttribute("loginBean", loginBean);
        } else {

            HttpSession session = request.getSession();

            session.setAttribute("username", nameStr); 
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}