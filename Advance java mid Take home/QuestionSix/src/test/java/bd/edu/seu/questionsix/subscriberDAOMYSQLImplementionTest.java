/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionsix;

import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nayem
 */
public class subscriberDAOMYSQLImplementionTest {
    
    public subscriberDAOMYSQLImplementionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class subscriberDAOMYSQLImplemention.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
         user p = new user("2012",new name("nayem", "siddique"),"demo@email.com");
        subscriber t = new subscriber("2017",p, "password", LocalDate.now());
        subscriberDAOMYSQLImplemention instance = new subscriberDAOMYSQLImplemention();
        subscriber expResult = t;
        subscriber result = instance.create(t);
        assertEquals(expResult, result);
        subscriber t1 = new subscriber("2018",new user("2013",new name("nayem", "siddique"),"demo@email.com"), "password", LocalDate.now());
        instance.create(t1);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of retrive method, of class subscriberDAOMYSQLImplemention.
     */
    @Test
    public void testRetriveAll() {
        System.out.println("retrive");
        user p = new user("2012",new name("nayem", "siddique"),"demo@email.com");
        subscriber t = new subscriber("2017",p, "password", LocalDate.now());
        subscriberDAOMYSQLImplemention instance = new subscriberDAOMYSQLImplemention();
        List<subscriber> result = instance.retrive();
        assertEquals(1, result.size());
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of retrive method, of class subscriberDAOMYSQLImplemention.
     */
    @Test
    public void testRetriveById() {
        System.out.println("retrive");
        subscriber t1 = new subscriber("2018",new user("2012",new name("nayem", "siddique"),"demo@email.com"), "password", LocalDate.now());
        subscriberDAOMYSQLImplemention instance = new subscriberDAOMYSQLImplemention();
        subscriber expResult = null;
        subscriber result = instance.retrive(t1.getSubscriberId());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of delete method, of class subscriberDAOMYSQLImplemention.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
       subscriber t1 = new subscriber("2018",new user("2013",new name("nayem", "siddique"),"demo@email.com"), "password", LocalDate.now());
        subscriberDAOMYSQLImplemention instance = new subscriberDAOMYSQLImplemention();
        boolean expResult = true;
        boolean result = instance.delete(t1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
}
