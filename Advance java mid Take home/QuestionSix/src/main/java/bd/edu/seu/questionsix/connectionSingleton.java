/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionsix;

import org.hibernate.Session;

/**
 *
 * @author nayem
 */
public class connectionSingleton {
    private static Session session;
    private connectionSingleton() {
        session= HibernateUtil.getSessionFactory().openSession();
    }
    
    
    public static Session getConnection(){
        
        return session;
    }
}
