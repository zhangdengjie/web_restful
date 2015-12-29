package demo.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * All Right Reserved, Copyright (C) 2015, dengjie, Ltd.
 * 实体类集合基类
 * Created by dengjie on 2015/12/26.
 */
public class BaseBean<T> implements Serializable {
    private int count;
    private String errorCode;
    private String msg;
    private List<T> beans;


    public BaseBean() {
    }

    public BaseBean(String errorCode,String msg,List<T> beans) {
        this.errorCode = errorCode;
        this.beans = beans;
        this.count = beans.size();
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getBeans() {
        return beans;
    }

    public void setBeans(List<T> beans) {
        this.beans = beans;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "count=" + count +
                ", errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                ", beans=" + beans +
                '}';
    }
}
