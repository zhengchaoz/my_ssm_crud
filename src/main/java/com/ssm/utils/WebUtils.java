package com.ssm.utils;

import javax.servlet.http.HttpSession;

/**
 * @user 郑超
 * @date 2021/4/25
 */
public class WebUtils {

    public static boolean validateCaptcha(String inputCaptcha, String type, HttpSession session) {
        // TODO: 判断验证码是否正确
        return false;
    }

    public static String md5(String str) {
        // TODO: 将当前时间加上sessionId进行md5加密作为loginToken
        return "";
    }

}
