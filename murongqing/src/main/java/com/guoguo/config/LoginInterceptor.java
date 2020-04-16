package com.guoguo.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoguo.common.Const;
import com.guoguo.murongqing.entity.user.User;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import com.guoguo.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MurongqingProperty murongqingProperty;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = WebUtils.getCookie(request, Const.User.UUID);
        String userStr = stringRedisTemplate.opsForValue().get(uuid);
        if (StringUtils.isBlank(userStr)) {
            request.getRequestDispatcher("/turn").forward(request, response);
            return false;
        }else{
            HttpSession session = request.getSession();
            Object obj = session.getAttribute(Const.User.USERSTR);
            if (ObjectUtils.isNull(obj)) {
                User user = gson.fromJson(userStr, User.class);
                session.setAttribute(Const.User.USERSTR, user);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
