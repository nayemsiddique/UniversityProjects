/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionsix;

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
public class UserDAOMYSQLImplementationTest {
    
    public UserDAOMYSQLImplementationTest() {
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
     * Test of create method, of class UserDAOMYSQLImplementation.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        user t = new user("2012",new name("nayem", "siddique"),"demo@email.com");
        UserDAOMYSQLImplementation instance = new UserDAOMYSQLImplementation();
        //user expResult = t;
        user result = instance.create(t);
        assertEquals(t, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of retrive method, of class UserDAOMYSQLImplementation.
     */
    @Test
    public void testRetriveAll() {
        System.out.println("retrive");
        UserDAOMYSQLImplementation instance = new UserDAOMYSQLImplementation();
       user t = new user("2013",new name("nayem", "siddique"),"demo@email.com");
       instance.create(t);
        List<user> result = instance.retrive();
        assertEquals(2, result.size());
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of retrive method, of class UserDAOMYSQLImplementation.
     */
    @Test
    public void testRetriveById() {
        System.out.println("retrive");
         user t = new user("2012",new name("nayem", "siddique"),"demo@email.com");
        String id = t.getUserId();
        UserDAOMYSQLImplementation instance = new UserDAOMYSQLImplementation();
        //user expResult = t;
        user result = instance.retrive(t.getUserId());
        assertEquals(t, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserDAOMYSQLImplementation.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        user t = new user("2013",new name("nayem", "siddique"),"demo@email.com");
        UserDAOMYSQLImplementation instance = new UserDAOMYSQLImplementation();
        boolean expResult = true;
        boolean result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
