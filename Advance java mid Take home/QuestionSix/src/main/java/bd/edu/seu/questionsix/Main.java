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
public class Main {
    public static void main(String Args[]){
        new Main();
    }

    public Main() {
        project project= new project("11111111",categories.A,status.D,"");
        Session openSession = HibernateUtil.getSessionFactory().openSession();
    }
    
}
