package com.ssm.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @user 郑超
 * @date 2021/4/25
 */
public class CookieUtil {

    public static void addCookie(HttpServletResponse response, String type, String loginToken, Integer i) {
        // TODO: 向cookie添加loginToken
    }

    public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String loginToken) {
        // TODO: 删除cookie里的loginToken
    }

    public static String findCookieByName(HttpServletRequest request, String loginToken) {
        // TODO: 查看cookie中是否有loginToken
        return "";
    }
}
