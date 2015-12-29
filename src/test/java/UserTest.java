import demo.bean.User;
import demo.controller.UserController;
import demo.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * Created by dengjie on 2015/12/26.
 */
public class UserTest {
    @Test
    public void testGet(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
        UserController iUserController = (UserController) ac.getBean("userController");
        User user = new User();
        user.setUsername("zdj");
        user.setPassword("123456");
        System.out.println(iUserController.postUser(user));
    }
}
