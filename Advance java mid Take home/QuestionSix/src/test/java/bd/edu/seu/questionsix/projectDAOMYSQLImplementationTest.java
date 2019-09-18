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
import org.junit.Ignore;

/**
 *
 * @author nayem
 */
public class projectDAOMYSQLImplementationTest {
    
    public projectDAOMYSQLImplementationTest() {
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
     * Test of create method, of class projectDAOMYSQLImplementation.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        project t = new project("11111111",categories.A,status.D,"");
        projectDAOMYSQLImplementation instance = new projectDAOMYSQLImplementation();
        //project expResult = null;
        project result = instance.create(t);
        assertEquals(t, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of retrive method, of class projectDAOMYSQLImplementation.
     */
    @Test
    public void testRetriveAll() {
        System.out.println("retrive");
         project t = new project("11111011",categories.B,status.P,"");
        
        projectDAOMYSQLImplementation instance = new projectDAOMYSQLImplementation();
        instance.create(t);
        //List<project> expResult = null;
        List<project> result = instance.retrive();
        assertEquals(1, result.size());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of retrive method, of class projectDAOMYSQLImplementation.
     */
    @Test
    @Ignore
    public void testRetriveByID() {
        System.out.println("retrive");
       project t = new project("11111111",categories.A,status.D,"");
        projectDAOMYSQLImplementation instance = new projectDAOMYSQLImplementation();
       
        project result = instance.retrive(t.getProjectId());
        assertEquals(t, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class projectDAOMYSQLImplementation.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
         project t = new project("11111111",categories.A,status.D,"");
        projectDAOMYSQLImplementation instance = new projectDAOMYSQLImplementation();
        boolean expResult = true;
        boolean result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
