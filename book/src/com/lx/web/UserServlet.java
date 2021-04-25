package com.lx.web;

import com.lx.pojo.User;
import com.lx.service.UserService;
import com.lx.service.impl.UserServiceImpl;
import com.lx.test.UserServletTest;
import com.lx.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser == null){
            //把错误信息和回显的表单项信息保存到req域当中
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            //跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {

            req.getSession().setAttribute("user", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        User loginUser = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //2.检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)){
            //3.检查用户名是否可用
            UserService userService= new UserServiceImpl();
            if (userService.existsUsername(username)){
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                //跳回注册页面
                System.out.println("用户名["+username+"]已存在！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else {
                //跳回成功主页
                userService.registUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        }else {
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码["+code+"]错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

        }

    }
}
