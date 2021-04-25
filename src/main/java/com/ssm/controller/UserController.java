package com.ssm.controller;

import com.ssm.bean.User;
import com.ssm.service.UserService;
import com.ssm.utils.CookieUtil;
import com.ssm.utils.Msg;
import com.ssm.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @user 郑超
 * @date 2021/4/25
 */
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     *
     * @param mobileNo 账号
     * @param password 密码
     * @param inputCaptcha 验证码
     * @param session
     * @param response
     * @return
     */
    @PostMapping(value = "/user/session", produces = "application/json;charset=UTF-8")
    public Msg login(Long mobileNo, String password, String inputCaptcha,
                     HttpSession session, HttpServletResponse response) {
        //判断验证码是否正确
        if (WebUtils.validateCaptcha(inputCaptcha, "captcha", session)) {
            User user = userService.userLogin(mobileNo, password);
            if (user != null) {
                /*设置自动登陆，一个星期.  将token保存在数据库中*/
                String loginToken = WebUtils.md5(new Date().toString() + session.getId());
                user.setLoginToken(loginToken);
                User userUpload = userService.userUpload(user);

                session.setAttribute("user", userUpload);
                CookieUtil.addCookie(response, "loginToken", loginToken, 604800);

                return Msg.success().put("user", userUpload);
            } else {
                return Msg.bust().put("LOGIN_ERROR", 201);
            }
        } else {
            return Msg.bust().put("CAPTCHA_ERROR", 202);
        }
    }

    /**
     * 登出
     *
     * @param session
     * @param request
     * @param response
     * @return
     */
    @DeleteMapping(value = "/session", produces = {"application/json;charset=UTF-8"})
    public Msg logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //删除session和cookie
        session.removeAttribute("user");
        CookieUtil.clearCookie(request, response, "loginToken");

        return Msg.success();
    }

}
