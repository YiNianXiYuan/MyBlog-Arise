package com.study.controller.admin;

import com.github.pagehelper.PageInfo;
import com.study.Service.log.LogService;
import com.study.Service.site.SiteService;
import com.study.Service.user.UserService;
import com.study.constant.LogActions;
import com.study.constant.WebConst;
import com.study.controller.BaseController;
import com.study.dto.StatisticsDto;
import com.study.exception.BusinessException;
import com.study.pojo.Comment;
import com.study.pojo.Content;
import com.study.pojo.Log;
import com.study.pojo.User;
import com.study.utils.APIResponse;
import com.study.utils.GsonUtils;
import com.study.utils.TaleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: arise
 * @Date: 2021/7/29 下午3:30
 * @Description: 后台首页
 */
@Api("后台首页")
@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @Autowired
    private SiteService siteService;


    @ApiOperation("进入首页")
    @GetMapping(value = {"","/index"})
    public String index(HttpServletRequest request) {
        LOGGER.info("Enter admin index method");
        // 获取5条评论
        List<Comment> comments = siteService.getComments(5);
        // 获取5篇文章
        List<Content> contents = siteService.getNewArticles(5);
        // 获取后台统计数
        StatisticsDto statistics = siteService.getStatistics();

        // 获取5篇日志
        PageInfo<Log> logs = logService.getLogs(1,5);
        List<Log> list = logs.getList();

        request.setAttribute("comments",comments);
        request.setAttribute("articles",contents);
        request.setAttribute("statistics",statistics);
        request.setAttribute("logs",list);
        LOGGER.info("Exit admin index method");
        return "admin/index";
    }

    /**
     * 个人设置页面
     */
    @GetMapping(value = "/profile")
    public String profile() {
        return "admin/profile";
    }


    /**
     * 保存个人信息
     * @param screenName    用户名称
     * @param email         邮箱
     * @param request       请求对象
     * @param session       请求session
     * @return
     */
    @PostMapping(value = "/profile")
    @ResponseBody
    public APIResponse saveProfile(
            @RequestParam String screenName,
            @RequestParam String email,
            HttpServletRequest request,
            HttpSession session
    ) {
        User users = this.user(request);
        if (StringUtils.isNotBlank(screenName) && StringUtils.isNotBlank(email)) {
            User temp = new User();
            temp.setUid(users.getUid());
            temp.setScreenName(screenName);
            temp.setEmail(email);
            // 更新数据
            userService.updateUserInfo(temp);
            // 写入日志
            logService.addLog(LogActions.UP_INFO.getAction(), GsonUtils.toJsonString(temp),request.getRemoteAddr(),this.getUid(request));

            // 更新session中的数据
            User originAL = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            originAL.setScreenName(screenName);
            originAL.setEmail(email);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY, originAL);
        }
        return APIResponse.success();
    }

    /**
     * 修改密码
     * @param oldPassword   参数旧密码
     * @param newPassword   参数新密码
     * @param request       请求对象
     * @param session       请求session
     * @return
     */
    @PostMapping(value = "/password")
    @ResponseBody
    public APIResponse upPwd(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            HttpServletRequest request,
            HttpSession session
    ) {
        User users = this.user(request);
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            return APIResponse.fail("请确认信息输入完整");
        }

        if (!users.getPassword().equals(TaleUtils.MD5encode(users.getUsername() + oldPassword))) {
            return APIResponse.fail("旧密码错误");
        }

        if (newPassword.length() < 6 || newPassword.length() > 14) {
            return APIResponse.fail("请输入6-14位密码");
        }

        try {
            User temp = new User();
            temp.setUid(users.getUid());
            String pwd = TaleUtils.MD5encode(users.getUsername() + newPassword);
            temp.setPassword(pwd);
            userService.updateUserInfo(temp);
            logService.addLog(LogActions.UP_PWD.getAction(), null,request.getRemoteAddr(),this.getUid(request));

            // 更新session中的数据
            User originAL = (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
            originAL.setPassword(pwd);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY,originAL);
            return APIResponse.success();
        } catch (Exception e) {
            String msg = "密码修改失败";
            if (e instanceof BusinessException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg,e);
            }
            return APIResponse.fail(msg);
        }
    }
}
