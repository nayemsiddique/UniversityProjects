/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionsix;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author nayem
 */
public class UserDAOMYSQLImplementation implements CrudDAO<user, String>{

    @Override
    public user create(user t) {
        Session session=null;
        Transaction transaction = null;
        try{
            
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        
        return retrive(t.getUserId());
            
        }catch(Exception e){
            e.printStackTrace();
            if(transaction!=null)
                transaction.rollback();
        }finally{
            if(session!=null)
                session.close();
        }
        return null;
    }

    @Override
    public List<user> retrive() {
        Session session=null;
       
        
        try{
             session = HibernateUtil.getSessionFactory().openSession();
            Query<user> query = session.createQuery("from user", user.class);
           return query.getResultList();
            //session.
            
        }catch(Exception e){
            e.printStackTrace();
            
                
            
        }
        finally{
         if(session!=null)
             session.close();
        }
        return null;
    }
    

    @Override
    public user retrive(String userId) {
       Session session=null;
       try{
           session=HibernateUtil.getSessionFactory().openSession();
           Query<user> query = session.createQuery("from user where userId= :userId", user.class);
           query.setParameter("userId", userId);
           return query.getSingleResult();
           
       }catch(Exception e){
           e.printStackTrace();
    }finally{
           if(session!=null)
               session.close();
       }
        return null;
    }

    @Override
    public boolean delete(user t) {
         Session session=null;
        Transaction transaction=null;
        try{
             session = HibernateUtil.getSessionFactory().openSession(); 
             transaction=session.beginTransaction();
             session.delete(t);
             transaction.commit();
             return  true;
       }catch(Exception e){
            e.printStackTrace();
            if(transaction!=null)
                transaction.rollback();
        }finally{
            if(session!=null)
                session.close();
        }
        return false;
    }
    
}
