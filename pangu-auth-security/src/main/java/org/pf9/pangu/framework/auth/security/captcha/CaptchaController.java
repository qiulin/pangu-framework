package org.pf9.pangu.framework.auth.security.captcha;

import com.octo.captcha.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Collections;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public BufferedImage get(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE,
                Collections.singleton(MediaType.IMAGE_JPEG));
        return (BufferedImage) captchaService.getChallengeForID(request.getSession(true).getId());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Boolean test(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }

}
