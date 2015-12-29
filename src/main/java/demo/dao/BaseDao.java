package demo.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 * dao基类
 * Created by dengjie on 2015/10/30.
 */
@Repository
public class BaseDao {

    @Resource
    protected SessionFactory sessionFactory;

    /**
     * gerCurrentSession 会自动关闭session，使用的是当前的session事务
     *
     * @return
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * openSession 需要手动关闭session 意思是打开一个新的session
     *
     * @return
     */
    public Session getNewSession() {
        return sessionFactory.openSession();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    /**
     * 根据 id 查询信息
     *
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Deprecated
    public Object load(Class c, String id) {
        Session session = getSession();
        return session.get(c, id);
    }

    /**
     * 根据 id 查询信息
     *
     * @param
     * @return
     */
    public <T extends Object> List<T> queryByParams(Class<T> cls,Map<String, Object> params) {
        Session session = getSession();
        String sql = "select id,username,password from z_user where 1=1 ";
        if (params.keySet().size() != 0) {
            for (Map.Entry entry : params.entrySet()) {
                if(entry.getValue() instanceof String){
                    sql += " and " + entry.getKey() + "='" + entry.getValue() + "'";
                }else{
                    sql += " and " + entry.getKey() + "=" + entry.getValue();
                }
            }
        }
        return session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(cls)).list();
    }

    /**
     * 获取所有信息
     *
     * @param c
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public List getAllList(Class c) {
        String hql = "from " + c.getName();
        Session session = getSession();
        return session.createQuery(hql).list();
    }

    /**
     * 获取总数量
     *
     * @param c
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Long getTotalCount(Class c) {
        Session session = getNewSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        session.close();
        return count != null ? count.longValue() : 0;
    }

    /**
     * 保存,persistent
     *
     * @param bean
     */
    @Deprecated
    public void save(Object bean) {
        try {
            Session session = getNewSession();
            session.save(bean);
            session.flush();
            session.clear();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入一条数据
     *
     * @param bean
     * @author dengjie
     * created at 2015年11月23日  下午3:53:39
     */
    public void insert(Object bean) {
        try {
            Session session = getNewSession();
            session.save(bean);
            session.flush();
            session.clear();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量插入
     *
     * @param beans
     * @author dengjie
     * created at 2015年12月3日  下午5:25:33
     */
    public <T extends Object> void inserts(List<T> beans) {
        try {
            Session session = getNewSession();
            for (T t : beans) {
                session.save(t);
            }
            session.flush();
            session.clear();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新
     *
     * @param bean
     */
    public void update(Object bean) {
        Session session = getNewSession();
        session.update(bean);
        session.flush();
        session.clear();
        session.close();
    }

    /**
     * 删除
     *
     * @param bean
     */
    public void delete(Object bean) {
        Session session = getNewSession();
        session.delete(bean);
        session.flush();
        session.clear();
        session.close();
    }

    /**
     * 根据ID删除
     *
     * @param c  类
     * @param id ID
     */
    @SuppressWarnings({"rawtypes"})
    public void delete(Class c, String id) {
        Session session = getNewSession();
        Object obj = session.get(c, id);
        session.delete(obj);
        flush();
        clear();
    }

    /**
     * 批量删除 hibernate
     *
     * @param c   类
     * @param ids ID 集合
     */
    @SuppressWarnings({"rawtypes"})
    public void delete(Class c, String[] ids) {
        for (String id : ids) {
            Object obj = getSession().get(c, id);
            if (obj != null) {
                getSession().delete(obj);
            }
        }
    }

    /**
     * 批量删除 native sql
     *
     * @param ids
     * @author dengjie
     * created at 2015年12月8日  下午2:03:04
     */
    public void delete(String sql, String... ids) {
        Session session = getSession();
        for (int i = 0; i < ids.length; i++) {
            session.createSQLQuery(sql + ids[i]).executeUpdate();
        }
    }


    /**
     * 获取总数量
     *
     * @param hql
     * @param isHql
     * @return
     */
    @SuppressWarnings("unused")
    private String getCountHql(String hql, boolean isHql) {
        String e = hql.toUpperCase().substring(hql.indexOf(" from".toUpperCase()));
        String c = "select count(*) " + e;
        if (isHql)
            c = c.replaceAll("fetch", "");
        System.out.println(c);
        return c;
    }

    /**
     * 执行sql语句获取总数
     *
     * @param sql
     * @return
     * @author dengjie at 2015-11-17 上午11:28:00
     */
    private long getCount(String sql) {
        sql = sql.toUpperCase();
        String e = sql.substring(sql.indexOf(" from".toUpperCase()));
        String c = "select count(*) " + e;
        long total = ((BigInteger) getSession().createSQLQuery(c).uniqueResult()).longValue();
        return total;
    }

    /**
     * 获取数据集合
     *
     * @param sql
     * @param cls
     * @return
     * @author dengjie at 2015-11-17 上午11:35:28
     */
    private List getDatas(String sql, Class cls, int start, int size) {
        Query query = getSession().createSQLQuery(sql).setResultTransformer(
                Transformers.aliasToBean(cls));
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.list();
    }
}
