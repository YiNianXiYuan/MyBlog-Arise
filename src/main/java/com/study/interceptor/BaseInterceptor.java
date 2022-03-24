package com.study.interceptor;

import com.study.Service.meta.MetaService;
import com.study.Service.option.OptionService;
import com.study.Service.site.SiteService;
import com.study.Service.user.UserService;
import com.study.constant.Types;
import com.study.constant.WebConst;
import com.study.dto.MetaDto;
import com.study.dto.StatisticsDto;
import com.study.pojo.Option;
import com.study.pojo.User;
import com.study.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:37
 * @Description: 自定义拦截器
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "User-Agent";


    @Autowired
    private UserService userService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private Commons commons;

    @Autowired
    private AdminCommons adminCommons;

    @Autowired
    private MetaService metaService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private HttpSession session;


    private MapCache cache = MapCache.single();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        // 请求URL不包含域名
        String uri = request.getRequestURI();
        // 日志输出
        LOGGER.info("UserAgent：{}", request.getHeader(USER_AGENT));
        LOGGER.info("用户访问地址：{}，来路地址：{}",uri, IPKit.getIpAddressByRequest1(request));

        // 请求拦截处理
        User user = TaleUtils.getLoginUser(request);
        if (null == user) {
            Integer uid = TaleUtils.getCookieUid(request);
            if (null != uid) {
                //这里还是有安全隐患,cookie是可以伪造的
                user = userService.getUserInfoById(uid);
                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            }
        }

        if (uri.startsWith("/admin") && !uri.startsWith("/admin/login") && !uri.startsWith("/admin/register")
                && null == user
                && !uri.startsWith("/admin/css") && !uri.startsWith("/admin/images")
                && !uri.startsWith("/admin/js") && !uri.startsWith("/admin/plugins")
                && !uri.startsWith("/admin/editormd")) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }

        // 设置GET请求的token
        if (request.getMethod().equals("GET")) {
            String csrf_token = UUID.UU64();
            // 默认存储30分钟
            cache.hset(Types.CSRF_TOKEN.getType(), csrf_token, uri,30 * 60);
            request.setAttribute("_csrf_token", csrf_token);
        }
        // 返回true才会执行postHandle
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView view) throws Exception {
        Option ov = optionService.getOptionByName("site_record");
        // 分类总数
        Long categoryCount = metaService.getMetasCountByType(Types.CATEGORY.getType());
        // 标签总数
        Long tagCount = metaService.getMetasCountByType(Types.TAG.getType());
        // 获取文章总数
        StatisticsDto statistics = siteService.getStatistics();
        // 获取友情链接
        List<MetaDto> links = metaService.getMetaList(Types.LINK.getType(),null,WebConst.MAX_POSTS);

        session.setAttribute("categoryCount",categoryCount);
        session.setAttribute("tagCount",tagCount);
        session.setAttribute("articleCount",statistics.getArticles());
        session.setAttribute("links",links);
        request.setAttribute("commons", commons);
        request.setAttribute("option", ov);
        request.setAttribute("adminCommons", adminCommons);
        initSiteConfig(request);
    }

    private void initSiteConfig(HttpServletRequest request) {
        if (WebConst.initConfig.isEmpty()) {
            List<Option> options = optionService.getOptions();
            Map<String, String> querys = new HashMap<>();
            options.forEach(option -> {
                querys.put(option.getName(),option.getValue());
            });
            WebConst.initConfig = querys;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
