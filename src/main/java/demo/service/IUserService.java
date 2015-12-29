package demo.service;

import demo.bean.User;

import java.util.List;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * Created by dengjie on 2015/12/26.
 */
public interface IUserService{
    /**
     * 获取集合对象
     * @param user
     * @return
     */
    List<User> get(User user);
}
