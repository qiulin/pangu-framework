package org.pf9.pangu.framework.auth.security.captcha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.octo.captcha.service.CaptchaService;
import org.pf9.pangu.framework.common.config.PanguProperties;
import org.pf9.pangu.framework.data.domain.ResponseResult;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CaptchaFilter extends GenericFilterBean {

    private CaptchaService captchaService;
    private String codeParam;
    private ObjectMapper objectMapper;
    private PanguProperties panguProperties;

    private Boolean ajax;

    public CaptchaFilter(CaptchaService captchaService, String codeParam, ObjectMapper objectMapper, Boolean ajax, PanguProperties panguProperties) {

        Assert.notNull(captchaService);
        Assert.notNull(codeParam);
        Assert.notNull(objectMapper);

        this.captchaService = captchaService;
        this.codeParam = codeParam;
        this.objectMapper = objectMapper;
        this.ajax = ajax;
        this.panguProperties = panguProperties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        doFilterInternal(request, response, filterChain);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestPath = request.getRequestURI();
        List<String> securedUrls = panguProperties.getCaptcha().getSecuredUrls();
        boolean captchaEnabled = panguProperties.getCaptcha().isEnabled();

        if (request.getMethod().equals("POST") && captchaEnabled && securedUrls.stream().anyMatch(p -> p.equals(requestPath))) {
            System.out.println("开始验证");
            String code = request.getParameter(codeParam);
            String toValidateCode = captchaService.getQuestionForID(request.getSession().getId());
            if (!Strings.isNullOrEmpty(code) && captchaService.validateResponseForID(request.getSession(true).getId(), code.toUpperCase())) {
                filterChain.doFilter(request, response);
            } else {
                if (ajax) {
                    objectMapper.writeValue(response.getOutputStream(), ResponseResult.captchaError());
                } else {
                    response.sendRedirect("/login");
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
