package demo.dao;

import demo.bean.User;

import java.util.List;
import java.util.Map;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * Created by dengjie on 2015/12/26.
 */
public interface IUserDao {
    List<User> query(User user);
}
