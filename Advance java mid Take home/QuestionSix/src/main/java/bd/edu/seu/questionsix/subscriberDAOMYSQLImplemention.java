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
public class subscriberDAOMYSQLImplemention implements CrudDAO<subscriber, String>{
           @Override
    public subscriber create(subscriber t) {
        
        
        Session session=null;
        Transaction transaction = null;
        try{
            
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        
        return retrive(t.getSubscriberId());
            
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
    public List<subscriber> retrive() {
        Session session=null;
       
        
        try{
             session = HibernateUtil.getSessionFactory().openSession();
            Query<subscriber> query = session.createQuery("from subscriber", subscriber.class);
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
    public subscriber retrive(String subscriberId) {
       Session session=null;
       try{
           session=HibernateUtil.getSessionFactory().openSession();
           Query<subscriber> query = session.createQuery("from subscriber where subscriberId= :subscriberId", subscriber.class);
           query.setParameter("subscriberId", subscriberId);
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
    public boolean delete(subscriber t) {
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
