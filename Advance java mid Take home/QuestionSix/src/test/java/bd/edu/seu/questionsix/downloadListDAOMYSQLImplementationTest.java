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
public class downloadListDAOMYSQLImplementationTest {
    user u1 = new user("2012",new name("nayem", "siddique"),"demo@email.com");
      user u2 = new user("2013",new name("nayem", "siddique"),"demo@email.com");
    
     project p2 = new project("11111011",categories.B,status.P,"");
    
    public downloadListDAOMYSQLImplementationTest() {
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
     * Test of create method, of class downloadListDAOMYSQLImplementation.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        downloadList t = new downloadList("1212", p2, u1, LocalDate.now());
        downloadListDAOMYSQLImplementation instance = new downloadListDAOMYSQLImplementation();
        downloadList expResult = null;
        downloadList result = instance.create(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of retrive method, of class downloadListDAOMYSQLImplementation.
     */
    @Test
    public void testRetrive_0args() {
        System.out.println("retrive");
        downloadListDAOMYSQLImplementation instance = new downloadListDAOMYSQLImplementation();
        List<downloadList> expResult = null;
        List<downloadList> result = instance.retrive();
        assertEquals(0, result.size());
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of retrive method, of class downloadListDAOMYSQLImplementation.
     */
    @Test
    public void testRetrive_String() {
        System.out.println("retrive");
        String downloadId = "";
        downloadListDAOMYSQLImplementation instance = new downloadListDAOMYSQLImplementation();
        downloadList expResult = null;
        downloadList result = instance.retrive(downloadId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of delete method, of class downloadListDAOMYSQLImplementation.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        downloadList t = null;
        downloadListDAOMYSQLImplementation instance = new downloadListDAOMYSQLImplementation();
        boolean expResult = false;
        boolean result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
