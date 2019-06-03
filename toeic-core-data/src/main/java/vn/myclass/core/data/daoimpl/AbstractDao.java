package vn.myclass.core.data.daoimpl;

import org.hibernate.*;
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


    public List<T> findAll() {

        List<T> list = new ArrayList<T>();
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction = session.beginTransaction();
            //HQL
            StringBuilder sql =new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query= session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }

    //update(merge)
    public T update(T entity) {
        T result = null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try{
           Object object= session.merge(entity);
           result= (T) object;
           transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    //save (persist)
    public void save(T entity) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try{
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    //findbyId
    public T findById(ID id) {
        T result=null;
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        try{
            result = (T)session.get(persistenceClass, id);
            if(result==null){
                throw new ObjectNotFoundException("NOT FOUND "+id, null);
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }
}
