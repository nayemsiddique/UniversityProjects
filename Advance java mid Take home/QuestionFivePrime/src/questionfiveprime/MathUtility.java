/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionfiveprime;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nayem
 */
public class MathUtility {
    private long number;
    

    public MathUtility() {
    }

    public MathUtility(long number) {
        this.number = number;
    }
    
    public boolean isPrime(long number) throws InputNotInRangeException{
        
            if(number< 0l || number > 400l)
                throw new InputNotInRangeException();
            //return true;
           
       
        return false;
        
    }
    
    
}
