package demo.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * 用户集合
 * Created by dengjie on 2015/12/26.
 */
@XmlRootElement(name="users")
// 使用该注解不能使用继承，待研究
public class UserList extends BaseBean<User> implements Serializable {
    public UserList() {
    }

    public UserList(String errorCode, String msg, List<User> beans) {
        super(errorCode, msg, beans);
    }
}
