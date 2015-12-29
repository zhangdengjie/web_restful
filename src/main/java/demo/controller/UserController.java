package demo.controller;

import demo.bean.User;
import demo.bean.UserList;
import demo.common.Config;
import demo.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * 用户控制器
 * Created by dengjie on 2015/12/26.
 */
@RestController
public class UserController {
    // Logger instance
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private IUserService iUserService;

    /**
     * restful 接口的get请求demo 如果返回值是void的话，则不遵循
     * 请求头的限制：Accept不匹配，返回值为void的方法不是返回406状态码
     * text/xml:返回类型必须是由@XmlRootElement注解的对象
     * application/xml:url以.xml结尾才会返回xml格式，所以
     * headers 设置为headers = { "Accept=application/xml,application/json" }即可json xml灵活切换
     * @author dengjie created at 2015年12月22日 上午10:49:03
     * @param request
     * @param version
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET, headers = { "Accept=application/xml,application/json" })
    public User getUsers(@RequestParam(value = "request", required = false) String request,
                                   @RequestParam(value = "version", required = false, defaultValue = "1") int version) {
        if (logger.isDebugEnabled()) {
            logger.debug("Start getSomething");
            logger.debug("data: '" + request + "'");
        }

        String response = null;
        try {
            switch (version) {
                case 1:
                    if (logger.isDebugEnabled())
                        logger.debug("in version 1");
                    // TODO: add your business logic here
                    response = "Response from Spring RESTful Webservice : " + request;
                    break;
                default:
                    throw new Exception("Unsupported version: " + version);
            }
        } catch (Exception e) {
            response = e.getMessage().toString();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("result: '" + response + "'");
            logger.debug("End getSomething");
        }
        // return new ModelAndView("person", "persons", personList);
        return null;
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView postUser(User user) {
        List<User> users = iUserService.get(user);
        UserList userList = null;
        if(users.size() == 0){
            userList = new UserList(Config.ERROR,"用户名或者密码错误",users);
        }else{
            userList = new UserList(Config.OK,"登录成功",users);
        }
        return new ModelAndView("user","userList",userList);
    }
}
