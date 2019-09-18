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
public class BugListDAOMYSQLImplementation implements CrudDAO<bugList, Integer>{
               @Override
    public bugList create(bugList t) {
        
        
        Session session=null;
        Transaction transaction = null;
        try{
            
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        
        return retrive(t.getBugId());
            
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
    public List<bugList> retrive() {
        Session session=null;
       
        
        try{
             session = HibernateUtil.getSessionFactory().openSession();
            Query<bugList> query = session.createQuery("from bugList", bugList.class);
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
    public bugList retrive(Integer bugId) {
       Session session=null;
       try{
           session=HibernateUtil.getSessionFactory().openSession();
           Query<bugList> query = session.createQuery("from bugList where bugId= :bugId", bugList.class);
           query.setParameter("bugId", bugId);
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
    public boolean delete(bugList t) {
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
