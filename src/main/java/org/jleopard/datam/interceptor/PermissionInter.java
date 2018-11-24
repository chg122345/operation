/**
 * @author (c) 2018,
 * @date 2018/11/24  11:12
 * @version 1.0
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
package org.jleopard.datam.interceptor;

import org.jleopard.datam.model.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截
 */
public class PermissionInter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || StringUtils.isEmpty(user.getId())){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
