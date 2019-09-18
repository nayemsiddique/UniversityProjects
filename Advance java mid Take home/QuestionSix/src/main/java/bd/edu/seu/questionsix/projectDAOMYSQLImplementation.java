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
public class projectDAOMYSQLImplementation implements CrudDAO<project, String>{
       @Override
    public project create(project t) {
        Session session=null;
        Transaction transaction = null;
        try{
            
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        
        return retrive(t.getProjectId());
            
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
    public List<project> retrive() {
        Session session=null;
       
        
        try{
             session = HibernateUtil.getSessionFactory().openSession();
            Query<project> query = session.createQuery("from project", project.class);
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
    public project retrive(String projectId) {
       Session session=null;
       try{
           session=HibernateUtil.getSessionFactory().openSession();
           Query<project> query = session.createQuery("from project where projectId= :projectId", project.class);
           query.setParameter("projectId", projectId);
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
    public boolean delete(project t) {
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
