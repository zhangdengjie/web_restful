package demo.service;

import demo.bean.User;
import demo.dao.IUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * 用户Service层
 * Created by dengjie on 2015/12/26.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao iUserDao;

    @Override
    public List<User> get(User user) {
        return iUserDao.query(user);
    }
}
