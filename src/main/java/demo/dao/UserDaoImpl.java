package demo.dao;

import demo.bean.User;
import demo.utils.StringUitl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * Created by dengjie on 2015/12/26.
 */
@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<User> query(User user) {
        List<User> list = null;
        try {
            list = queryByParams(User.class,StringUitl.properties2Map(user));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logger.error(list.toString());
        return list;
    }
}
