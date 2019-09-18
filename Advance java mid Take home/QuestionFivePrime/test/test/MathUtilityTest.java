/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import questionfiveprime.InputNotInRangeException;
import questionfiveprime.MathUtility;
import static org.junit.Assert.*;

/**
 *
 * @author nayem
 */
public class MathUtilityTest {
    
    public MathUtilityTest() {
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
     * Test of isPrime method, of class MathUtility.
     * @throws questionfiveprime.InputNotInRangeException
     */
    @Test(timeout = 2000,expected = InputNotInRangeException.class)
    public void testIsPrime() throws InputNotInRangeException {
        System.out.println("isPrime");
        long number = -1l;
        MathUtility instance = new MathUtility();
        boolean expResult = false;
        boolean result = instance.isPrime(number);
        
               assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
