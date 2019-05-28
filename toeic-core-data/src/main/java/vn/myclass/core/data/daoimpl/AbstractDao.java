package vn.myclass.core.data.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.myclass.core.common.utils.HibernateUtil;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable,T> implements GenericDao<ID,T> {
    private Class<T> persistenceClass;

    public AbstractDao(){
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName(){
        return persistenceClass.getSimpleName();
    }

    protected Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }
    public List<T> findAll() {

        List<T> list = new ArrayList<T>();
        Transaction transaction=null;
        try {
            transaction = getSession().beginTransaction();
            //HQL
            StringBuilder sql =new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query= this.getSession().createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }

        return list;
    }
}
