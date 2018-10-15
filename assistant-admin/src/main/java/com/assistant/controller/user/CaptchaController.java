package com.assistant.controller.user;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assistant.util.RandomGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @author :wkh
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    /**
     * 中文加号
     */
    private static final String CHINESE_PLUS_SIGN = "加";

    @Autowired
    private Producer captchaProducer;

    /**
     * 验证码接口
     * 验证码
     *
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "/captchaImage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 禁止服务器端缓存
        response.setDateHeader("Expires", 0);
        // 设置标准的 HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // 设置IE扩展 HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // 设置标准 HTTP/1.0 不缓存图片
        response.setHeader("Pragma", "no-cache");
        // 返回一个 jpeg 图片，默认是text/html(输出文档的MIMI类型)
        response.setContentType("image/jpeg");
        // 创建验证码并储存在session
        String capText = chooseCaptchaAndSetupSession(request);
        // 创建带有文本的图片
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // 图片数据输出
        ImageIO.write(bi, "jpg", out);
        String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        System.err.println("登录的验证码是：" + kaptchaExpected);

        try {
            out.flush();
        } finally {
            out.close();
        }

    }

    /**
     * 选择验证码 （数字验证码或者中文算数验证码）
     *
     * @return 验证码
     */
    private String chooseCaptchaAndSetupSession(HttpServletRequest request) {

        int[] arr = {1, 2};
        if (arr[(int) (Math.random() * arr.length)] == 1) {
            String capText = RandomGeneratorUtil.getRandomCharAndNumber(4);
            // 将文本保存在session中
            request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
            return capText;
        }

        return getChineseMathText(request);
    }

    /**
     * 获取验证码中文文本
     *
     * @return 字符串
     */
    private String getChineseMathText(HttpServletRequest request) {

        int num1 = RandomGeneratorUtil.getRandom(10);
        int num2 = RandomGeneratorUtil.getRandom(10);

        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, String.valueOf(num1 + num2));

        return RandomGeneratorUtil.toChineseUpper(num1) + CHINESE_PLUS_SIGN + RandomGeneratorUtil.toChineseUpper(num2) + "= ？";
    }


}
