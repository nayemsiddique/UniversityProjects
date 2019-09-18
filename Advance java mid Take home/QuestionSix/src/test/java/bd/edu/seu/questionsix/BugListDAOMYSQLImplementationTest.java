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
public class BugListDAOMYSQLImplementationTest {
     user u1 = new user("2012",new name("nayem", "siddique"),"demo@email.com");
      user u2 = new user("2013",new name("nayem", "siddique"),"demo@email.com");
    
     project p2 = new project("11111011",categories.B,status.P,"");
    
    public BugListDAOMYSQLImplementationTest() {
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
     * Test of create method, of class BugListDAOMYSQLImplementation.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        bugList t = new bugList(123, u1, p2," ", LocalDate.now());
        BugListDAOMYSQLImplementation instance = new BugListDAOMYSQLImplementation();
        bugList expResult = null;
        bugList result = instance.create(t);
        assertEquals(t, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of retrive method, of class BugListDAOMYSQLImplementation.
     */
    @Test
    public void testRetrive_0args() {
        System.out.println("retrive");
         bugList t = new bugList(1, u1, p2,"", LocalDate.now());
        BugListDAOMYSQLImplementation instance = new BugListDAOMYSQLImplementation();
        List<bugList> expResult = null;
        List<bugList> result = instance.retrive();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of retrive method, of class BugListDAOMYSQLImplementation.
     */
    @Test
    public void testRetriveById() {
        System.out.println("retrive");
        
        BugListDAOMYSQLImplementation instance = new BugListDAOMYSQLImplementation();
        bugList expResult = null;
        bugList result = instance.retrive(1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of delete method, of class BugListDAOMYSQLImplementation.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
         bugList t = new bugList(1, u1, p2,"", LocalDate.now());
        BugListDAOMYSQLImplementation instance = new BugListDAOMYSQLImplementation();
        boolean expResult = true;
        boolean result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
