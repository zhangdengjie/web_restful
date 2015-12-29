package demo.service;

import java.util.List;
import java.util.Map;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * Created by dengjie on 2015/12/26.
 */
public interface BaseService<T> {
    /**
     * 获取集合对象
     * @param t
     * @return
     */
    List<T> get(T t);
}
