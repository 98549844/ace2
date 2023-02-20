//package com.dao.common;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.orm.hibernate5.HibernateCallback;
//import org.springframework.orm.hibernate5.HibernateTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
//
//
///**
// * @author 604765
// */
//@Repository
//public class HibernateBaseDaoTemplateImpl<T> implements HibernateBaseDao<T> {
//
//    private HibernateTemplate hibernateTemplate;
//
//    protected Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Override
//    @Transactional
//    public void save(T t) {
//        hibernateTemplate.save(t);
//    }
//
//    @Override
//    @Transactional
//    public void update(T t) {
//        hibernateTemplate.update(t);
//    }
//
//    @Override
//    @Transactional
//    public void delete(T t) {
//        hibernateTemplate.delete(t);
//    }
//
//    @Override
//    public T get(Class<T> class1, Serializable key) {
//        T t = hibernateTemplate.get(class1, key);
//        return t;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<T> findByPage(String hql, Map<String, Object> parameters, int currentPage, int pageSize) {
//        debug(hql, parameters);
//        return (List<T>) findObjectByPage(hql, parameters, currentPage, pageSize);
//    }
//
//    @SuppressWarnings("rawtypes")
//    @Override
//    public List findListByPage(String hql, Map<String, Object> parameters, int currentPage, int pageSize) {
//        debug(hql, parameters);
//        return (List) findObjectByPage(hql, parameters, currentPage, pageSize);
//    }
//
//    @SuppressWarnings("rawtypes")
//    @Override
//    public int getTotal(String hql, Map<String, Object> parameters) {
//        debug(hql, parameters);
//        List rs = (List) findObjectByPage(hql, parameters, 0, 0);
//        if (rs != null && !rs.isEmpty()) {
//            return Integer.parseInt(String.valueOf(rs.get(0)));
//        }
//        return 0;
//    }
//
//    @SuppressWarnings("rawtypes")
//    @Override
//    public int getId(String hql, Map<String, Object> parameters) {
//        debug(hql, parameters);
//        List rs = (List) findObjectByPage(hql, parameters, 0, 0);
//        if (rs != null && !rs.isEmpty()) {
//            return Integer.parseInt(String.valueOf(rs.get(0)));
//        }
//        return 0;
//    }
//
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public Double[] getAmounts(String hql, Map<String, Object> parameters) {
//        debug(hql, parameters);
//        List<Object[]> rs = findListByPage(hql, parameters, 0, 0);
//        Double[] result;
//        if (rs != null && !rs.isEmpty()) {
//            if (!"null".equals(String.valueOf(rs.get(0)))) {
//                Object[] objects = rs.get(0);
//                result = new Double[objects.length];
//                for (int i = 0; i < objects.length; i++) {
//                    if (objects[i] != null && !"".equals(String.valueOf(objects[i]))) {
//                        result[i] = Double.parseDouble(String.valueOf(objects[i]));
//                    }
//                }
//                return result;
//            }
//        }
//        return null;
//    }
//
//    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
//    @Override
//    @Transactional
//    public int executeByHql(final String hql, final Map<String, Object> parameters) {
//        debug(hql, parameters);
//        return (int) hibernateTemplate.execute(new HibernateCallback<Integer>() {
//            @Override
//            public Integer doInHibernate(Session session) throws HibernateException {
//                Query query = session.createQuery(hql);
//                for (Map.Entry<String, Object> param : parameters.entrySet()) {
//                    query.setParameter(param.getKey(), param.getValue());
//                }
//                return query.executeUpdate();
//            }
//        });
//    }
//
//    private Object findObjectByPage(final String hql, final Map<String, Object> parameters, final int currentPage, final int pageSize) {
//        return (Object) hibernateTemplate.execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                Query query = session.createQuery(hql);
//                if (parameters != null && parameters.size() > 0) {
//                    for (Map.Entry<String, Object> param : parameters.entrySet()) {
//                        query.setParameter(param.getKey(), param.getValue());
//                    }
//                }
//                if (currentPage > 0 && pageSize > 0) {
//                    query.setFirstResult((currentPage - 1) * pageSize);
//                    query.setMaxResults(pageSize);
//                }
//                return query.list();
//            }
//        });
//    }
//
//    /**
//     * 如果开启了Debug模式的话，那么打印Hql语句
//     *
//     * @param hql
//     * @param parameters
//     */
//    private void debug(final String hql, final Map<String, Object> parameters) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("Hql: " + hql);
//            if (parameters == null) {
//                return;
//            }
//            for (Map.Entry<String, Object> param : parameters.entrySet()) {
//                logger.debug("Class: " + param.getValue().getClass().getName() + ", Value: " + param.getValue());
//            }
//        }
//    }
////
////	@Resource(name = "sessionFactory")
////	public void setSFactory(SessionFactory sessionFactory) {
////		if (hibernateTemplate == null) {
////			hibernateTemplate = new HibernateTemplate();
////		}
////		hibernateTemplate.setSessionFactory(sessionFactory);
////	}
//
//}
