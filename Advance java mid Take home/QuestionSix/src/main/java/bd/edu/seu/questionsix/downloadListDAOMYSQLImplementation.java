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
public class downloadListDAOMYSQLImplementation implements CrudDAO<downloadList, String>{
                   @Override
    public downloadList create(downloadList t) {
        
        
        Session session=null;
        Transaction transaction = null;
        try{
            
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        
        return retrive(t.getDownloadId());
            
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
    public List<downloadList> retrive() {
        Session session=null;
       
        
        try{
             session = HibernateUtil.getSessionFactory().openSession();
            Query<downloadList> query = session.createQuery("from downloadList", downloadList.class);
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
    public downloadList retrive(String downloadId) {
       Session session=null;
       try{
           session=HibernateUtil.getSessionFactory().openSession();
           Query<downloadList> query = session.createQuery("from downloadList where downloadId= :downloadId", downloadList.class);
           query.setParameter("downloadId", downloadId);
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
    public boolean delete(downloadList t) {
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
