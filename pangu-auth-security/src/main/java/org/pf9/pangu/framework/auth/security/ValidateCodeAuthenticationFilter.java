package org.pf9.pangu.framework.auth.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class ValidateCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final static String VALIDATECODE = "validateCode";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("开始校验-验证码-"+new Date());

        String requestCode = request.getParameter(VALIDATECODE);
        String sessionCode = (String) request.getSession().getAttribute(VALIDATECODE);
        request.getSession().removeAttribute(VALIDATECODE);
        if (!StringUtils.equalsIgnoreCase(requestCode, sessionCode)) {
            throw new SessionAuthenticationException("validatecode error");
        }else{
            //验证成功
            Authentication auth = super.attemptAuthentication(request, response);
            return auth;
        }
    }

}