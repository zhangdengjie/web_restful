package demo.utils;

import demo.common.Config;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 字符串工具类
 * Created by dengjie on 2015/11/04.
 */
public class StringUitl {


	/**
	 * 判断字符串是否为空
	 * @author dengjie 
	 * created at 2015年12月1日  下午3:23:18
	 * @param str 需要判断的字符串
	 * @return <b>true</b> 为空 </br> <b>false</b> 不为空
	 */
	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str.replace("\\s", "")));
	}

    /**
     * 将一个对象拼成一个map
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static <T extends Object> Map<String,Object> properties2Map(T t) throws IllegalAccessException {
        if(t == null){
            throw new NullPointerException("object can not be null");
        }
        Map<String,Object> result = new HashMap<>();
        Class clz = t.getClass();
        /**
         * 获取所有属性，组装为map的元素
         * created at 2015/12/26 14:42
         */
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            String name = field.getName();
            Object object = field.get(t);
            if("id".equals(name) && object.equals(0)){
                continue;
            }
            result.put(field.getName(),object);
        }
        return result;
    }
}
