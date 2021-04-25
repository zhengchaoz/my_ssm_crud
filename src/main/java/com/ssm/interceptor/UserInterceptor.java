package com.ssm.interceptor;

import com.ssm.bean.User;
import com.ssm.service.UserService;
import com.ssm.utils.CookieUtil;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @user 郑超
 * @date 2021/4/25
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        User sessionUser = (User) request.getSession().getAttribute("user");
        // 已经登陆了，放行
        if (sessionUser != null) {
            return true;
        } else {
            //得到带过来cookie是否存在
            String loginToken = CookieUtil.findCookieByName(request, "loginToken");
            if (StringUtils.isNotBlank(loginToken)) {
                //到数据库查询有没有该Cookie
                User user = userService.findUserByLoginToken(loginToken);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    return true;
                } else {
                    //没有该Cookie与之对应的用户(Cookie不匹配)
                    CookieUtil.clearCookie(request, response, "loginToken");
                    return false;
                }
            } else {
                //没有cookie、也没有登陆。是index请求获取用户信息，可以放行
                if (request.getRequestURI().contains("session")) {
                    return true;
                }

                //没有cookie凭证
                response.sendRedirect("/login.html");
                return false;
            }
        }
    }
}
